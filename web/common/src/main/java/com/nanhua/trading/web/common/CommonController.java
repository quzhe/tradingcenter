package com.nanhua.trading.web.common;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.nanhua.trading.web.errorhandler.JsonErrorResult;

public class CommonController {
	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	//@Autowired
	//AuthenticationManager authenticationManager;
	
	
	
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ModelAndView handleJsonValidate(MethodArgumentNotValidException ex){
		JsonErrorResult json = new JsonErrorResult();
		ModelAndView mav = new ModelAndView("jsonerror");
		
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		for (FieldError error : errors) {
			json.getErrors().put(error.getField(),error.getDefaultMessage());
		}
		mav.addObject(json);
		return mav;
	}
	
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AuthenticationException.class)
	public ModelAndView handleAuthenticationFailed(HttpServletRequest req,AuthenticationException ex) throws AuthenticationException{
		if(req.getHeader("Accept").equals(MediaType.APPLICATION_JSON_VALUE)){
			ModelAndView mav = new ModelAndView("authenticationfailed");
			mav.addObject("failure","{message:"+ex.getMessage()+"}");
			return mav;
		}else
			throw ex;
		
	}
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	public ModelAndView handleEntityNotFoundException(EntityNotFoundException ex){
		ModelAndView mav = new ModelAndView("notfoundexception");
		mav.addObject("failure","{message:"+ex.getMessage()+"}");
		return mav;
	}
	
	
	 
	@ResponseStatus(value = HttpStatus.CONFLICT)
	@ExceptionHandler(EntityExistsException.class)
	public ModelAndView handleEntityExists(EntityExistsException ex){
		ModelAndView mav = new ModelAndView("conflict");
		mav.addObject("failure","{message:entity conflict}");
		return mav;
	}
	
	@ResponseStatus(value = HttpStatus.METHOD_FAILURE)
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleUnexpectionException(HttpServletRequest req, RuntimeException ex){
		ModelAndView mav = new ModelAndView("systemexception");
		mav.addObject("failure","{message:"+ex.getMessage()+"}");
		logger.error(ex.getMessage());
		return mav;
	}
	
	
	
}
