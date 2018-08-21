package com.app.demo.util;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DBUtils {

	@Autowired
	private DataSource dataSource;

	JdbcTemplate JdbcTemplate;
	
	public static int numberOfAsset = 0;
	public static int numberOfCustomer = 0;
	public static int numberOfPortfolio = 0;

	@PostConstruct
	public void initialize() {
		JdbcTemplate = new JdbcTemplate(dataSource);
		JdbcTemplate.execute("DROP TABLE IF EXISTS CUSTOMER");
		JdbcTemplate.update("CREATE TABLE CUSTOMER(" + "CUSTOMER_ID INTEGER Primary key AUTOINCREMENT, "
				+ "CUSTOMER_NAME varchar(50)) ");
		
		JdbcTemplate.execute("DROP TABLE IF EXISTS ASSET");
		JdbcTemplate.update("CREATE TABLE ASSET(" + "ASSET_ID INTEGER Primary key AUTOINCREMENT, "
				+ "ASSET_NAME varchar(50)," + "ASSET_TYPE varchar(50))" );
		
		JdbcTemplate.execute("DROP TABLE IF EXISTS PORTFOLIO");
		JdbcTemplate.update("CREATE TABLE PORTFOLIO(" + "PORTFOLIO_ID INTEGER Primary key AUTOINCREMENT, "
				+ "CUSTOMER_ID INTEGER," + "NUMBER_OF_ASSETS INTEGER," + "PORTFOLIO_NAME varchar(50),"
				+ "FOREIGN KEY(CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID))");

	}
}
