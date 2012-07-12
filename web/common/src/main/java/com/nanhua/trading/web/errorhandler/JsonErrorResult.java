package com.nanhua.trading.web.errorhandler;

import java.util.HashMap;
import java.util.Map;

public class JsonErrorResult {
	
	
	private Map<String, String> errors = new HashMap<String, String>();

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
}
