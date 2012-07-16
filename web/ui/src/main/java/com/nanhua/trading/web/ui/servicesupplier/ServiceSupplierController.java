package com.nanhua.trading.web.ui.servicesupplier;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.nanhua.trading.domain.servicesupplier.ServiceSupplier;
import com.nanhua.trading.web.common.CommonController;

@Controller
public class ServiceSupplierController extends CommonController{
	
	//TODO interator all suppliers to get address. because it is lazy
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "servicesupplier",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getServiceSupplierList() {
		ModelAndView mav = new ModelAndView("getServiceSupplierListJsonView");
		mav.addObject("servicesuppliers", ServiceSupplier.findAllServiceSuppliers());
        return mav;
    }
	
}
