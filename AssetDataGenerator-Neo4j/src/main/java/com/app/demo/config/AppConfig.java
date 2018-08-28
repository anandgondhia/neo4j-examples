package com.app.demo.config;

import java.io.File;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.app.demo")
@EnableTransactionManagement
@PropertySource("classpath:spring/db.properties")
public class AppConfig {

	@Autowired
	Environment environment;

	//private final String URL = "url";
	//private final String DRIVER = "driverClassName";
	
    private String dbPagecache;
	
    private String dbLocation;
	
	/*@Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;
	}
	@Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource)
    {
        return new DataSourceTransactionManager(dataSource);
    }*/
	
	@Bean
	GraphDatabaseService graphDatabaseService(){	
		dbPagecache = environment.getProperty("dbPagecache");
		dbLocation = environment.getProperty("dbLocation");
        System.out.println("dbLocation="+dbLocation+" dbPagecache="+dbPagecache);
		GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(new File(dbLocation)).setConfig(GraphDatabaseSettings.pagecache_memory, dbPagecache).newGraphDatabase();
		return db;
	}
	
	
}
