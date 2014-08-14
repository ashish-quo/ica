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

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertData() {

		String sql = "INSERT INTO country(id,country_code) VALUES(?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { 2, "df", });

	}

	public List<Attribute> getAttributeList() {
		String query = QueryBuilder.queryForAttributes();
		return jdbcTemplate.query(query, new ResultSetExtractor<List<Attribute>>() {
			public List<Attribute> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				Map<Long,Attribute> attrMap = new LinkedHashMap<Long, Attribute>();
				while(rs.next()) {
					Long attrId = rs.getLong("attrId");
					if (!attrMap.containsKey(attrId)) {
						Attribute attribute = new Attribute();
						attribute.setId(attrId);
						attribute.setAttributeName(rs.getString("attrName"));
						attribute.setModuleId(rs.getInt("moduleId"));
						attribute.setIcon(rs.getString("attrIcon"));
						attribute.setType(rs.getInt("attrType"));
						attribute.setViewType(rs.getString("viewType"));
						attrMap.put(attrId, attribute);
						attribute.setAttributeCategoryList(new ArrayList<AttributeCategory>());
					} 
					
					AttributeCategory attrCat = new AttributeCategory();
					attrCat.setCategoryName(rs.getString("catName"));
					attrCat.setIcon(rs.getString("catIcon"));
					attrMap.get(attrId).getAttributeCategoryList().add(attrCat);
				}
				return new ArrayList<Attribute>(attrMap.values());
			}

		});

	}

}
