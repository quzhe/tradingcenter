package com.nanhua.trading.web.errorhandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class WebAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private AuthenticationFailureHandler htmlAuthenticationFailure;
	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String accept = request.getHeader("accept");
		if(accept!=null&&accept.equals(MediaType.APPLICATION_JSON_VALUE)){
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
		}else{
			htmlAuthenticationFailure.onAuthenticationFailure(request, response, exception);
		}
		
		// TODO Auto-generated method stub

	}


	public AuthenticationFailureHandler getHtmlAuthenticationFailure() {
		return htmlAuthenticationFailure;
	}


	public void setHtmlAuthenticationFailure(AuthenticationFailureHandler authenticationFailure) {
		this.htmlAuthenticationFailure = authenticationFailure;
	}
	
	
	
}
