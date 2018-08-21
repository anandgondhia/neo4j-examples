package com.app.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.app.demo.config.AppConfig;
import com.app.demo.util.DBUtils; 
import com.app.demo.DataThread;

public class AssetDataGenerator {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		if(args.length == 3) {
			DBUtils dbUtils = context.getBean(DBUtils.class);
			dbUtils.numberOfAsset = Integer.parseInt(args[0]);
			dbUtils.numberOfCustomer = Integer.parseInt(args[1]);
			dbUtils.numberOfPortfolio = Integer.parseInt(args[2]);
			DataThread dataThread = context.getBean(DataThread.class);
			dataThread.run();
		} else {
			System.out.println("Provide arguments for Asset , Customer and Portoflio for ex:  '100 101 102'");
		}
	}
}
