package com.app.demo.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.demo.model.Portfolio;
import com.app.demo.util.DBUtils;

@Service
public class PortfolioDAO {
	static int portfolioCounter = 1;
	@Autowired
	GraphDatabaseService graphDatabaseService;
	
	//@Transactional
	public void create(Portfolio portfolio) {
		//String cql = "INSERT INTO ASSET(asset_name, asset_type) VALUES(?,?)";
		String cql = "CREATE (" + portfolio.getName() + ":Portfolio { id:" + portfolio.getId() + ",name:'" + portfolio.getName() + "', assetsNum:'" + portfolio.getAssetId() + "'})";
		String relationCql = "MATCH (a:Customer),(b:Portfolio) where a.id=" + portfolio.getId() + 
				" CREATE (b)-[r:of]->(a)";
		Transaction tx = graphDatabaseService.beginTx();
		graphDatabaseService.execute(cql);
		graphDatabaseService.execute(relationCql);
		portfolioCounter++;
		if(portfolioCounter % 1000 == 0 || portfolioCounter == DBUtils.numberOfPortfolio) {
			tx.success();
			
	        System.out.println("*****Match N query*****");
	        cql = "MATCH (n:Portfolio) RETURN n";
	        Result r = graphDatabaseService.execute(cql);
	        System.out.println(r.resultAsString());
	        
	        System.out.println("*****Match N Relationship*****");
	        //	It will print like Portfolio Of Customer 
	        cql = "start n=node(*) match (n)-[r]->(a) return distinct n,r,a";
	        r = graphDatabaseService.execute(cql);
	        System.out.println(r.resultAsString());
	        
	        if(portfolioCounter == DBUtils.numberOfPortfolio) {
	        	tx.close();
	        }
		}
		
	}
}
