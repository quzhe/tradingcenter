package com.nanhua.trading.history.dao;

import java.util.List;

public interface HistoryDepthDAO {

	@SuppressWarnings("rawtypes")
	public abstract List findDepths();

}