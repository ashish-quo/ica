/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.exception.RADataAccessException;
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
	
	/** The named parameter jdbc template. */
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/** The application configuration. */
	@Autowired
	private Properties applicationConfiguration;
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger("MetaDataRepositoryImpl");
	
	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.CommonDaoI#getAttributeList()
	 */
	public List<Attribute> getAttributeList() throws RADataAccessException {
		String query = QueryBuilder.queryForAttributes();
		LOGGER.debug("Getting all attributes");
		LOGGER.debug(query);
		
		List<Attribute> attributeList = new ArrayList<Attribute>(10);
		try {
			attributeList = jdbcTemplate.query(query, new ResultSetExtractor<List<Attribute>>() {
				public List<Attribute> extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					Map<Integer,Attribute> attrMap = new LinkedHashMap<Integer, Attribute>();
					Map<String,String> nameValueMap = null;
					while(rs.next()) {
						Integer attrId = rs.getInt("attrId");
						String attrName = rs.getString("attrName");
						if (!attrMap.containsKey(attrId)) {
							Attribute attribute = new Attribute();
							attribute.setId(attrId);
							attribute.setAttributeName(attrName);
							attribute.setModuleId(0);
							attribute.setDbColumn(rs.getString("db_column"));
							attribute.setColumnType(rs.getString("column_type"));
							attribute.setChartType(rs.getByte("chart_type"));
							attrMap.put(attrId, attribute);
							attribute.setAttributeCategoryList(new ArrayList<AttributeCategory>());
							nameValueMap = new HashMap<String,String>();
							nameValueMap.put("-1", "Unknown");
							RAConstants.attributeNameValueCache.put(attribute.getAttributeName(),nameValueMap);
						} 
						
						AttributeCategory attrCat = new AttributeCategory();
						attrCat.setCategName(rs.getString("catName"));
						attrCat.setAttrId(attrId);
						attrCat.setId(rs.getLong("catId"));
						attrCat.setCategValue(rs.getString("catValue"));
						attrMap.get(attrId).getAttributeCategoryList().add(attrCat);
						RAConstants.attributeNameValueCache.get(attrName).put(attrCat.getCategValue(),
								attrCat.getCategName());
					}
					return new ArrayList<Attribute>(attrMap.values());
				}
	
			});
		} catch (DataAccessException dae) {
			LOGGER.error("Exception While getting all attribute : ", dae);
			throw new RADataAccessException(dae);
		}
		
		LOGGER.debug("Attributes found : " + attributeList.size());
		LOGGER.trace("Attributes details : " + attributeList);
		return attributeList;
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.CommonDaoI#getAllCountries()
	 */
	
	public List<Country> getAllCountries() throws RADataAccessException {
		String query = QueryBuilder.queryForAllCountries();
		LOGGER.debug("Getting all countries ");
		LOGGER.debug(query);
		List<Country> countries = new ArrayList<Country>(170);
		try {
			countries = jdbcTemplate.query(query, new RowMapper<Country>(){
				public Country mapRow(ResultSet rs, int arg1) throws SQLException {
					Country country = new Country();
					country.setCountryName(rs.getString("countryName"));
					country.setBordering(rs.getByte("bordering"));
					return country;
				}
			});
		} catch(DataAccessException dae) {
			LOGGER.error("Error occurred while getting all countries: ", dae);
			throw new RADataAccessException(dae);
		}
		LOGGER.debug("Countries found : " + countries.size());
		LOGGER.trace("Country details : " + countries);
		return countries;
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.repository.MetaDataRepository#getAllNetworks()
	 */
	@Override
	public List<AttributeCategory> getAllNetworks(long networkAttrId) throws RADataAccessException {
		String query = QueryBuilder.queryForDistinctNetworks();
		LOGGER.debug("Getting all network names ");
		LOGGER.debug(query);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("homeCountry", applicationConfiguration.get("home.country"));
		parameters.addValue("roamType", applicationConfiguration.get("roam.type"));
		
		List<AttributeCategory> networkCategories = new ArrayList<AttributeCategory>(100);
		try{
			networkCategories = namedParameterJdbcTemplate.query(query,parameters, new RowMapper<AttributeCategory>() {
				@Override
				public AttributeCategory mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					AttributeCategory attrCat = new AttributeCategory();
					attrCat.setCategName(rs.getString("visitednetworkname"));
					attrCat.setAttrId(networkAttrId);
					attrCat.setId(rowNum);
					attrCat.setCategValue(attrCat.getCategName());
					RAConstants.attributeNameValueCache.get(RAConstants.ATTR_NETWORK).put(attrCat.getCategValue(),
							attrCat.getCategName());
					return attrCat;
				}
			});
		} catch (DataAccessException dae) {
			LOGGER.error("Error occurred while getting all network names: ", dae);
			throw new RADataAccessException(dae);
		}
		LOGGER.debug("Networks Found : " +
				RAConstants.attributeNameValueCache.get(RAConstants.ATTR_NETWORK).keySet().size());
		LOGGER.trace("Networks Names : " + RAConstants.attributeNameValueCache.get(RAConstants.ATTR_NETWORK).keySet());
		return networkCategories;
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.repository.MetaDataRepository#getNetworkGroups(long)
	 */
	@Override
	public List<AttributeCategory> getNetworkGroups(long attributeId) throws RADataAccessException {
		String query = QueryBuilder.queryForDistinctNetworkGroups();
		LOGGER.debug("Getting all networks groups ");
		LOGGER.debug(query);
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("networks", RAConstants.attributeNameValueCache.get(RAConstants.ATTR_NETWORK).keySet());
		List<AttributeCategory> attributeCategories = new ArrayList<AttributeCategory>(50);
		Map<String,StringBuilder> attrCategoryMap = new TreeMap<String, StringBuilder>();
		try {
			namedParameterJdbcTemplate.query(query,parameters, new RowMapper<AttributeCategory>() {
				@Override
				public AttributeCategory mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					String groupString = rs.getString("network_group");
					String networkName = rs.getString("network_name");
					if (groupString.contains(RAConstants.COMMA)) {
						String[] groups = groupString.split(RAConstants.COMMA);
						for (String group : groups) {
							StringBuilder networkNames = attrCategoryMap.get(group);
							if (networkNames == null) {
								networkNames = new StringBuilder(); 
								attrCategoryMap.put(group, networkNames);
								networkNames.append(networkName);
							} else {
								networkNames.append(RAConstants.COMMA).append(networkName);
							}
						}
					} else {
						StringBuilder networkNames = attrCategoryMap.get(groupString);
						if (networkNames == null) {
							networkNames = new StringBuilder(); 
							attrCategoryMap.put(groupString, networkNames);
							networkNames.append(networkName);
						} else {
							networkNames.append(RAConstants.COMMA).append(networkName);
						}
					}
					return null;
				}
			});
		} catch(DataAccessException dae) {
			LOGGER.error("Error occurred while getting all network groups: ", dae);
			throw new RADataAccessException(dae);
		}
		int catId = 1;
		for (String group : attrCategoryMap.keySet()) {
			AttributeCategory attrCat = new AttributeCategory();
			attrCat.setCategName(group);
			attrCat.setAttrId(attributeId);
			attrCat.setId(catId++);
			attrCat.setCategValue(attrCategoryMap.get(group).toString());
			attributeCategories.add(attrCat);
		}
		LOGGER.debug("networks groups found : " + attributeCategories.size());
		LOGGER.debug("networks groups : " + attributeCategories);
		return attributeCategories;
	}

}
