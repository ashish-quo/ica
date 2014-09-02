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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;
import com.mobileum.roameranalytics.model.Country;

/**
 * @author sarvesh
 *
 */
@Repository
public class CommonDaoImpl implements CommonDaoI{

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger("CommonDaoImpl");
	
	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.CommonDaoI#getAttributeList()
	 */
	public List<Attribute> getAttributeList() {
		String query = QueryBuilder.queryForAttributes();
		LOGGER.info(query);
		return jdbcTemplate.query(query, new ResultSetExtractor<List<Attribute>>() {
			public List<Attribute> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				Map<Integer,Attribute> attrMap = new LinkedHashMap<Integer, Attribute>();
				while(rs.next()) {
					Integer attrInd = rs.getInt("attrInd");
					if (!attrMap.containsKey(attrInd)) {
						Attribute attribute = new Attribute();
						attribute.setAttrInd(attrInd);
						attribute.setAttributeName(rs.getString("attrName"));
						attribute.setModuleId(rs.getInt("moduleId"));
						attrMap.put(attrInd, attribute);
						attribute.setAttributeCategoryList(new ArrayList<AttributeCategory>());
					} 
					
					AttributeCategory attrCat = new AttributeCategory();
					attrCat.setCategName(rs.getString("catName"));
					attrCat.setCatInd(rs.getInt("catInd"));
					attrCat.setAttrInd(attrInd);
					attrMap.get(attrInd).getAttributeCategoryList().add(attrCat);
				}
				return new ArrayList<Attribute>(attrMap.values());
			}

		});

	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.CommonDaoI#getAllCountries()
	 */
	
	public List<Country> getAllCountries() {
		String query = QueryBuilder.queryForAllCountries();
		return jdbcTemplate.query(query, new RowMapper<Country>(){
			public Country mapRow(ResultSet rs, int arg1) throws SQLException {
				Country country = new Country();
				country.setCountryName(rs.getString("countryName"));
				country.setCountryCode(rs.getString("countryCode"));
				return country;
			}
		});
	}

}
