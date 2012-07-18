package com.nanhua.trading.web.errorhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

public class SessionTimeOutRedirect {
	
	public String process(HttpServletRequest request){
		//if(request.getHeader("accept").equals(MediaType.APPLICATION_JSON_VALUE)){
		//	throw new SessionAuthenticationException("");
		//}else{
			return "redirect:/";
		//}
	}
	
}
