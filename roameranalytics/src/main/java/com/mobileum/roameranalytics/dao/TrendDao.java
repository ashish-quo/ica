/**
 * 
 */
package com.mobileum.roameranalytics.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author smruti
 *
 */
public class TrendDao implements TrendDaoI {
	
	@Autowired  
	 DataSource dataSource;  
	  
	 public void insertData() {  
	  
	  String sql = "INSERT INTO country(id,country_code) VALUES(?, ?)";  
	  
	  JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);  
	  
	  jdbcTemplate.update(  
	    sql,new Object[] { 2, "df", });  
	  
	 }  

}
