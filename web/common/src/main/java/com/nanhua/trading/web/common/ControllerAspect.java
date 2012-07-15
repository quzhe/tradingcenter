package com.nanhua.trading.web.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.nanhua.trading.web.errorhandler.SystemErrorMessage;

@Aspect
@Configurable
//TODO move to audit project
public class ControllerAspect {
	private Logger logger=Logger.getLogger(this.getClass().getName());
	//@Autowired
	private RabbitTemplate amqpTemplate;
	
	@AfterReturning(value = "execution(* com.nanhua.trading.web.common.CommonController.handleUnexpectionException(..))&&"+"args(req,ex)")
	public void sendExceptionMessage(HttpServletRequest req, RuntimeException ex){
		SystemErrorMessage message = new SystemErrorMessage();
		ObjectMapper mapper = new ObjectMapper();
		message.setUrl(req.getRequestURL()+"/"+req.getQueryString());
		message.setMessage(ex.getMessage());
		try {
			message.setContent(IOUtils.toString(req.getInputStream()));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		UserDetails user = (UserDetails)(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		message.setStackTrace(getStackTrace(ex));
		message.setUsername(user.getUsername());
		
		try {
			amqpTemplate.convertAndSend("app.error.binding",mapper.writeValueAsString(message));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
	}
	
	public static String getStackTrace(Throwable aThrowable) {
	    final Writer result = new StringWriter();
	    final PrintWriter printWriter = new PrintWriter(result);
	    aThrowable.printStackTrace(printWriter);
	    return result.toString();
	}

	
	
}
