package com.app.demo.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.demo.model.Portfolio;

@Service
public class PortfolioDAO {
	@Autowired
	DataSource dataSource;
	
	@Transactional
	public void create(Portfolio portfolio) {
		String sql = "INSERT INTO PORTFOLIO(customer_id, NUMBER_OF_ASSETS, portfolio_name) VALUES(?,?,?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { portfolio.getCustomerId(), portfolio.getAssetId() , portfolio.getName() });
	}
}
