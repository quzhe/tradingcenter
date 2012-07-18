package com.nanhua.trading.history.service;

import java.io.InputStream;

import com.nanhua.trading.history.dao.HistoryDepthDAO;

public class HistoryDepthServiceImpl implements HistoryDepthService {
	
	private HistoryDepthDAO historyDepthDAO;
	
	@Override
	public InputStream generateDepthEventFile() {
		// TODO Auto-generated method stub
		return null;
	}

	public HistoryDepthDAO getHistoryDepthDAO() {
		return historyDepthDAO;
	}

	public void setHistoryDepthDAO(HistoryDepthDAO historyDepthDAO) {
		this.historyDepthDAO = historyDepthDAO;
	}

}
