package com.nanhua.trading.history.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nanhua.trading.history.service.HistoryDepthService;

@Controller
public class HistoryDepthController {
	@Autowired
	private HistoryDepthService historyDepthService;
	
	@RequestMapping(value = "/history/depth", method = RequestMethod.GET)
	public void findHistoryDepths(HttpServletResponse response){
		try {
			String name=java.net.URLEncoder.encode("", "UTF-8");
            name=java.net.URLEncoder.encode(name,"UTF-8");
            name=java.net.URLDecoder.decode(name, "UTF-8");
            response.setContentType(""+";charset=UTF-8");
			/*
			byte[] a = Document.findDocument(id).getOrignFileName().getBytes("iso-8859-1");
			*/
			response.setHeader("Content-Disposition", "attachment; filename="+ java.net.URLDecoder.decode(name, "UTF-8"));
			//response.setHeader("Content-Disposition", "attachment; filename="+Document.findDocument(id).getOrignFileName());
			InputStream is = new FileInputStream("");
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
			
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}
	}
	
}
