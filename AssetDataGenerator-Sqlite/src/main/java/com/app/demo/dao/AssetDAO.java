package com.app.demo.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.demo.model.Asset;

@Service
public class AssetDAO {
	
	@Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public AssetDAO(DataSource dataSource) {
		//System.out.println("creating new jdbcTemplate");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Transactional
	public void create(Asset asset) {
		String sql = "INSERT INTO ASSET(asset_name, asset_type) VALUES(?,?)";		
		//JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//System.out.println("hashCode="+ jdbcTemplate.hashCode());
		jdbcTemplate.update(sql, new Object[] { asset.getName() , asset.getType() });
	}
}
