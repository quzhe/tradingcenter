package com.nanhua.trading.web.ui.customer;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {
	
	
	@RequestMapping(value = "admin/customer/main",method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getCustomerMain() {
		return "admin/customer/main";
    }

}
