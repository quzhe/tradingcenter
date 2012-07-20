package com.nanhua.trading.web.ui.audit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.nanhua.trading.domain.audit.ServerError;

@Controller
public class AuditController {
	
	@RequestMapping(value = "admin/audit", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String gotoAuditPage() {
		return "audit/list";
    }
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value="admin/audit/servererror",produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ModelAndView listServerErrors(HttpServletRequest req) {
		ModelAndView mav = new ModelAndView("findservererrorsjsonView");
		String temp = req.getHeader("Range");
		temp = temp.substring(temp.indexOf("=")+1);
		int index = temp.indexOf("-");
		Integer firstResult = Integer.valueOf(temp.substring(0, index));
		Integer maxResults = Integer.valueOf(temp.substring(index+1));
		List<ServerError> se = ServerError.findServerErrorEntries(firstResult, maxResults);
        mav.addObject("ServerErrors", se);
		return mav;
    } 
	
	
}
