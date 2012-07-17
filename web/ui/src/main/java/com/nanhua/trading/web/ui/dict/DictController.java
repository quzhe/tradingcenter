package com.nanhua.trading.web.ui.dict;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.nanhua.trading.domain.datadict.NetworkType;

@Controller
public class DictController {
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/dict/networktype", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView getNetworkTypes() {
        ModelAndView mav = new ModelAndView("getnetworktypes");
        List<NetworkType> nt = NetworkType.findAllNetworkTypes();
        mav.addObject(nt);
        return mav;
    }
	
}
