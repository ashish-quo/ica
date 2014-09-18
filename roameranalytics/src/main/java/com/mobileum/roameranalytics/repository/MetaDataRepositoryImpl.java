/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
public class MetaDataRepositoryImpl implements MetaDataRepository {

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger("FilterDaoImpl");
	
	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.CommonDaoI#getAttributeList()
	 */
	public List<Attribute> getAttributeList() {
		String query = QueryBuilder.queryForAttributes();
		LOGGER.trace("All attributes query : " + query);
		
		List<Attribute> attributeList = new ArrayList<Attribute>(10);
		try {
			attributeList = jdbcTemplate.query(query, new ResultSetExtractor<List<Attribute>>() {
				public List<Attribute> extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					Map<Integer,Attribute> attrMap = new LinkedHashMap<Integer, Attribute>();
					while(rs.next()) {
						Integer attrId = rs.getInt("attrId");
						if (!attrMap.containsKey(attrId)) {
							Attribute attribute = new Attribute();
							attribute.setId(attrId);
							attribute.setAttributeName(rs.getString("attrName"));
							attribute.setModuleId(0);
							attribute.setDbColumn(rs.getString("db_column"));
							attribute.setColumnType(rs.getString("column_type"));
							attrMap.put(attrId, attribute);
							attribute.setAttributeCategoryList(new ArrayList<AttributeCategory>());
						} 
						
						AttributeCategory attrCat = new AttributeCategory();
						attrCat.setCategName(rs.getString("catName"));
						attrCat.setAttrId(attrId);
						attrCat.setId(rs.getLong("catId"));
						attrCat.setCategValue(rs.getString("catValue"));
						attrMap.get(attrId).getAttributeCategoryList().add(attrCat);
					}
					return new ArrayList<Attribute>(attrMap.values());
				}
	
			});
		} catch (DataAccessException dae) {
			LOGGER.error("Exception While getting all attribute : ", dae);
		}
		return attributeList;
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.CommonDaoI#getAllCountries()
	 */
	
	public List<Country> getAllCountries() {
		String query = QueryBuilder.queryForAllCountries();
		LOGGER.trace("All country query : " + query);
		return jdbcTemplate.query(query, new RowMapper<Country>(){
			public Country mapRow(ResultSet rs, int arg1) throws SQLException {
				Country country = new Country();
				country.setCountryName(rs.getString("countryName"));
				country.setBordering(rs.getByte("bordering"));
				return country;
			}
		});
	}

}
