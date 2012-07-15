package com.nanhua.trading.web.ui.dev;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;


//TODO wait spring-mvc-test 1.0 release, will remove dependency from pom
public class DevControllerTest {
	
	
	private static MockMvc mockMvc;
	
	/*
	
	@BeforeClass
	public static void setup() {
		mockMvc = MockMvcBuilders.xmlConfigSetup("file:src/main/webapp/WEB-INF/spring/webmvc-config.xml")
	    .configureWebAppRootDir("src/main/webapp", false)
	    .build();
		
		//this.mockMvc = MockMvcBuilders.webApplicationContextSetup(this.xmlWebApplicationContext).build();
	}

	
	@Test
	public void testInitData() throws Exception{
		
		mockMvc.perform(get("/ui/resources/scripts/dojo.string.addon.js"))
		.andExpect(status().isOk())
		.andExpect(content().type("text/javascript"))
		.andExpect(content().string(containsString("Spring={};")))
		;
		
		
		mockMvc.perform(get("/ui/dev/initdata").accept(MediaType.TEXT_HTML).contextPath("/ui"))
		.andExpect(status().isOk())
		//.andExpect(content().string("hello world"))
		//.andExpect(content().type("text/html"))
		
		
		.andExpect(forwardedUrl("/WEB-INF/layouts/standardLayout.jsp"))
		;
	}
	*/

}
