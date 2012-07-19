package com.nanhua.trading.web.ui.customer;

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

import com.nanhua.trading.domain.correlator.ServiceAccount;
import com.nanhua.trading.domain.correlator.ServiceInstance;
import com.nanhua.trading.web.common.CommonController;

@Controller
public class TradeAccountController extends CommonController{
	
	//curl -i -X GET -H "Accept: application/json" http://127.0.0.1:8080/tradingmanager/customer/serviceinstance/account?serviceIdendify=1
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "customer/serviceinstance/account",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getServiceAccountList(@RequestParam Long serviceIdendify) {
		ModelAndView mav = new ModelAndView("getServiceInstanceListJsonView");
		mav.addObject("serviceinstances", ServiceInstance.findServiceInstance(serviceIdendify).getAccounts());
        return mav;
    }
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "customer/serviceinstance/account",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView createAccountFromJson(@RequestBody ServiceAccount account,@RequestParam Long serviceIdendify) {
		ModelAndView mav = new ModelAndView("createAccountFromJsonView");
		ServiceInstance si = ServiceInstance.findServiceInstance(serviceIdendify);
		account.setServiceInstance(si);
		account.persist();
		mav.addObject("id", account.getId());
        return mav;
    }
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "customer/serviceinstance/account",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView updateAccountFromJson(@RequestBody @Valid ServiceAccount account,@RequestParam Long serviceIdendify) {
		ModelAndView mav = new ModelAndView("updateAccountFromJsonView");
		account.merge();
		return mav;
    }
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "customer/serviceinstance/account/{id}",method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView deleteAccountFromJson(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("deleteAccountFromJsonView");
		
		ServiceAccount ta = ServiceAccount.findServiceAccount(id);
		ServiceInstance si = ta.getServiceInstance();
		si.getAccounts().remove(ta);
		si.merge();
		return mav;
    }
	
	@RequestMapping(value = "customer/serviceinstance/account",params="manager",method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String gotoAccountPage(Model uiModel) {
		return "customer/serviceinstance/account";
    }
	
	
}
