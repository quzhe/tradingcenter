package com.nanhua.trading.web.dev;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;



public class DevControllerTest {
	
	
	private static MockMvc mockMvc;

	@BeforeClass
	public static void setup() {
		mockMvc = MockMvcBuilders.xmlConfigSetup("file:src/main/webapp/WEB-INF/spring/webmvc-config.xml","classpath*:META-INF/spring/applicationContext*.xml")
	    .configureWebAppRootDir("src/main/webapp", false)
	    .build();
		
		//this.mockMvc = MockMvcBuilders.webApplicationContextSetup(this.xmlWebApplicationContext).build();
	}

	
	@Test
	public void testInitData() throws Exception{
		
		mockMvc.perform(get("/dev/initdata").accept(MediaType.TEXT_HTML))
		.andExpect(status().isOk())
		.andExpect(content().type("text/html"))
		;
		
		//.andExpect(forwardedUrl("/WEB-INF/layouts/standardLayout.jsp"));
	}


}
