package com.app.demo.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.demo.model.Customer;
import com.app.demo.util.DBUtils;

@Service
public class CustomerDAO {
	static int customerCounter = 1;
	@Autowired
	GraphDatabaseService graphDatabaseService;
	
	//@Transactional
	public void create(Customer customer) {		
		String cql = "CREATE (" + customer.getName() + ":Customer { id:" + customer.getId() + ",name:'" + customer.getName() + "'})";
		Transaction tx = graphDatabaseService.beginTx();
		graphDatabaseService.execute(cql);
		customerCounter++;
		if(customerCounter % 1000 == 0 || customerCounter == DBUtils.numberOfCustomer) {
			tx.success();
	        System.out.println("*****Match N query*****");
	        cql = "MATCH (n:Customer) RETURN n";
	        Result r = graphDatabaseService.execute(cql);
	        System.out.println(r.resultAsString());
	        if(customerCounter == DBUtils.numberOfCustomer) {
	        	tx.close();
	        }
		}
		
	}
}
