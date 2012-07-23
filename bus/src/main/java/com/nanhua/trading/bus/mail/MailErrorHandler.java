package com.nanhua.trading.bus.mail;

import org.apache.log4j.Logger;
import org.springframework.util.ErrorHandler;

public class MailErrorHandler implements ErrorHandler {
	
	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	@Override
	public void handleError(Throwable t) {
		logger.info(t.getMessage());

	}

}
