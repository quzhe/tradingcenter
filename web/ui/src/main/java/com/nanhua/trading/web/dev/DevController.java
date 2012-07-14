package com.nanhua.trading.web.dev;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nanhua.trading.domain.account.Authority;
import com.nanhua.trading.domain.account.User;
import com.nanhua.trading.web.common.CommonController;

@RequestMapping("/dev")
@Controller
public class DevController extends CommonController{
	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="dev/initdata",method = RequestMethod.GET,produces=MediaType.TEXT_HTML_VALUE)
	public String generateInitData(){
		//ModelAndView mav = new ModelAndView("initdata");
		generateAdmin();
		return "devsuccess";
	}
	
	private void generateAdmin(){
		Authority au = new Authority();
		au.setAuthority("ROLE_ADMIN");
		
		User user = new User();
		user.setUsername("admin");
		user.setEnabled(true);
		user.setPassword("password");
		user.getAuthoritiesBySet().add(au);
		user.persist();
		logger.info("init admin data");
	}

}
