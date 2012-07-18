package com.nanhua.trading.web.ui.dev;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nanhua.trading.web.common.CommonController;

//@RequestMapping("/dev")
@Controller
public class DevController extends CommonController{
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="/dev/initdata",method = RequestMethod.GET,produces=MediaType.TEXT_HTML_VALUE)
	public String generateInitData(Model uiModel){
		
		DevHelper dh = new DevHelper();
		dh.generateInitData();
		return "devsuccess";
	}
	
	
}
