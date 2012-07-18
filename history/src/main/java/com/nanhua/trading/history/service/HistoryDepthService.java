package com.nanhua.trading.history.service;

import java.io.InputStream;

public interface HistoryDepthService {
	
	public InputStream generateDepthEventFile();
	
}
