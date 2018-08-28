package com.app.demo.util;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBUtils {

	@Autowired
	private GraphDatabaseService graphDatabaseService;

	public static int numberOfAsset = 0;
	public static int numberOfCustomer = 0;
	public static int numberOfPortfolio = 0;

	/*@PostConstruct
	public void initialize() {
		JdbcTemplate = new JdbcTemplate(dataSource);
	}*/
	
	public void shutdownGraphDb() {
		graphDatabaseService.shutdown();
	}
}
