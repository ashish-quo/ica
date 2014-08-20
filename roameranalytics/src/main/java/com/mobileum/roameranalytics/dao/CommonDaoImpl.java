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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;

/**
 * @author sarvesh
 *
 */
@Repository
public class CommonDaoImpl implements CommonDaoI{

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.CommonDaoI#getAttributeList()
	 */
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
						attribute.setDisplayText();
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
