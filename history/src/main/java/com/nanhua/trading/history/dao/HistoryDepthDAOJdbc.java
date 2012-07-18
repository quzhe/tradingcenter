package com.nanhua.trading.history.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

public class HistoryDepthDAOJdbc implements HistoryDepthDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	
	
	/* (non-Javadoc)
	 * @see com.nanhua.trading.history.dao.HistoryDepthDAO#findDepths()
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List findDepths(){
		return new ArrayList();
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
	
}
