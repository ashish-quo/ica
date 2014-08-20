/**
 * 
 */
package com.mobileum.roameranalytics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;

/**
 * @author smruti
 *
 */
@Repository
public class TrendDaoImpl implements TrendDaoI {

	@Autowired
	DataSource dataSource;


	public void insertData() {

		String sql = "INSERT INTO country(id,country_code) VALUES(?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { 2, "df", });

	}

	

}
