package com.nanhua.trading.web.ui.customer;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.nanhua.trading.domain.account.Authority;
import com.nanhua.trading.domain.customer.Customer;

@Controller
public class CustomerController {
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "admin/customer",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView createCustomerFromJson(@RequestBody @Valid Customer customer) {
		customer.getAuthoritiesBySet().add(new Authority("ROLE_CUSTOMER"));
		customer.setEnabled(Boolean.TRUE);
		customer.autoGeneratePassword();
		customer.persist();
		
		ModelAndView mav = new ModelAndView("createcustomerjsonView");
        mav.addObject("id", customer.getId());
        return mav;
    }
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "admin/customer",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView updateCustomerFromJson(@RequestBody @Valid Customer customer) {
		Customer old = Customer.findCustomer(customer.getId());
		customer.setPassword(old.getPassword());
		customer.setEnabled(old.isEnabled());
		customer.setAuthorities(old.getAuthoritiesBySet());
        customer.merge();
        ModelAndView mav = new ModelAndView("updatecustomerjsonView");
        return mav;
    }
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "admin/customer",params="findByName", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView findCustomerByName(@RequestParam Map<String,String> params) {
		ModelAndView mav = new ModelAndView("findcustomerjsonView");
		if(params.get("name")!=null)
			mav.addObject("customers",Customer.findCustomerByUniqueIdentify(params.get("name")));
		else
			mav.addObject("customers",Customer.findAllCustomers());
		return mav;
    }
	/*
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "admin/customer",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView findAllCustomer() {
		ModelAndView mav = new ModelAndView("findCustomerjsonView");
		mav.addObject("customers",Customer.findAllCustomers());
		return mav;
    }
	*/
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "admin/customer/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getCustomer(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("getcustomerjsonView");
		Customer c = Customer.findCustomer(id);
		c.setPassword(null);
		mav.addObject("customer", c);
        return mav;
    }
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "admin/customer/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView deleteCustomer(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("deletecustomerjsonView");
		Customer c = Customer.findCustomer(id);
		c.remove();
		return mav;
    }
	
	
	
	@RequestMapping(value = "admin/customer/main",method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getCustomerMain() {
		return "admin/customer/main";
    }
	@RequestMapping(value = "admin/customer", params="baseinfo",method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String gotoBaseinfoPage(Model uiModel) {
		return "customer/baseinfo";
    }
	@RequestMapping(value = "admin/customer", params="correlatorinfo",method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String gotoCorrelatorinfoPage(Model uiModel) {
		return "customer/correlatorinfo";
    }
	@RequestMapping(value = "admin/customer", params="serviceinfo",method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String gotoServiceinfoPage(Model uiModel) {
		return "customer/serviceinfo";
	}
}
