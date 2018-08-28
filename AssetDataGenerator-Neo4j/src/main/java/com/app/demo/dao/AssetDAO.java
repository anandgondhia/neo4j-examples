package com.app.demo.dao;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.demo.model.Asset;
import com.app.demo.util.DBUtils;

@Service
public class AssetDAO {
	static int assetCounter = 1;
	@Autowired
	GraphDatabaseService graphDatabaseService;
	
	//@Transactional
	public void create(Asset asset) {
		String cql = "CREATE (" + asset.getName() + ":Asset { id:" + asset.getId() + ",name:'" + asset.getName() + "', type:'" + asset.getType() + "'})";
		Transaction tx = graphDatabaseService.beginTx();
		graphDatabaseService.execute(cql);
		assetCounter++;
		//if(assetCounter % 1000 == 0 || assetCounter == DBUtils.numberOfAsset) {
		if(assetCounter == 1 || assetCounter == DBUtils.numberOfAsset) {
			tx.success();
			
	        System.out.println("*****Match N query*****");
	        cql = "MATCH (n:Asset) RETURN n";
	        Result r = graphDatabaseService.execute(cql);
	        //System.out.println(r.resultAsString());
	        if(assetCounter == DBUtils.numberOfAsset) {
	        	tx.close();
	        }
		}
		
	}
	
	public void countAsset() {
		String cql = "MATCH (n:Asset) RETURN count(*)";
		Transaction tx = graphDatabaseService.beginTx();
        System.out.println("*****Count N query*****");
        Result r = graphDatabaseService.execute(cql);
        System.out.println(r.resultAsString());
		
	}
}
