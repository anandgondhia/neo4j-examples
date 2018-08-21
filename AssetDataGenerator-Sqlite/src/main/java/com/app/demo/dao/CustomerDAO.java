package com.app.demo.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.demo.model.Customer;

@Service
public class CustomerDAO {
	@Autowired
	DataSource dataSource;
	
	@Transactional
	public void create(Customer customer) {
		String sql = "INSERT INTO CUSTOMER(customer_name) VALUES(?)";
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] { customer.getName() });
		
	}
}
