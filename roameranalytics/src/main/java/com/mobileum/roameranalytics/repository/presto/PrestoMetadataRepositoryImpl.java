/**
 * 
 */
package com.mobileum.roameranalytics.repository.presto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.MetaDataQueryBuilder;
import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.enums.RoamType;
import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;
import com.mobileum.roameranalytics.model.Country;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.repository.MetaDataRepository;

/**
 * @author sarvesh
 *
 */
@Repository
@Qualifier("prestoMetadataRepository")
public class PrestoMetadataRepositoryImpl implements MetaDataRepository {

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate prestoJdbcTempate;
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger("PrestoMetadataRepositoryImpl");
	
	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.CommonDaoI#getAttributeList()
	 */
	@Override
	public List<Attribute> getAttributeList(final String roamType) throws RADataAccessException {
		final String query = MetaDataQueryBuilder.queryForAttributes(roamType);
		LOGGER.debug("Getting all attributes");
		LOGGER.debug(query);
		
		List<Attribute> attributeList = new ArrayList<Attribute>(10);
		try {
			attributeList = jdbcTemplate.query(query, new ResultSetExtractor<List<Attribute>>() {
				
				@Override
				public List<Attribute> extractData(final ResultSet rs) throws SQLException,
						DataAccessException {
					
					final Map<Integer,Attribute> attributeMap = new LinkedHashMap<Integer, Attribute>();
					Map<String,String> nameValueMap = null;
					Integer otherCountriesTravelledAttrId = null;
					while(rs.next()) {
						final Integer attrId = rs.getInt("attrId");
						final String attrName = rs.getString("attrName");
						if (RoamType.IN.getRoamType().equalsIgnoreCase(roamType) && 
								"Domestic ARPU".equalsIgnoreCase(attrName))
							continue;
						if (!attributeMap.containsKey(attrId)) {
							final Attribute attribute = new Attribute();
							
							attribute.setId(attrId);
							attribute.setAttributeName(attrName);
							attribute.setModuleId(0);
							attribute.setDbColumn(rs.getString("db_column"));
							attribute.setColumnType(rs.getString("column_type"));
							attribute.setChartType(rs.getByte("chart_type"));
							
							attributeMap.put(attrId, attribute);
							attribute.setAttributeCategoryList(new ArrayList<AttributeCategory>());
							nameValueMap = new HashMap<String,String>();
							nameValueMap.put("-1", "Unknown");
							nameValueMap.put(null, "Unknown");
							nameValueMap.put("NULL", "Unknown");
							RAConstants.attributeNameValueCache.put(attribute.getAttributeName(),nameValueMap);
							
							if (otherCountriesTravelledAttrId != null && 
									RAConstants.ATTR_OTHER_COUNTRIES_TRAVLED.equalsIgnoreCase(attrName)) {
								otherCountriesTravelledAttrId = attrId;
							}
						} 
						
						final AttributeCategory attributeCategory = new AttributeCategory();
						attributeCategory.setCategName(rs.getString("catName"));
						attributeCategory.setAttrId(attrId);
						attributeCategory.setId(rs.getLong("catId"));
						attributeCategory.setCategValue(rs.getString("catValue"));
						
						attributeMap.get(attrId).getAttributeCategoryList().add(attributeCategory);
						RAConstants.attributeNameValueCache.get(attrName).put(attributeCategory.getCategValue(),
								attributeCategory.getCategName());
					}
					if (otherCountriesTravelledAttrId != null) {
						attributeMap.get(otherCountriesTravelledAttrId).setAttributeCategoryList(null);
						RAConstants.attributeNameValueCache.remove(RAConstants.ATTR_OTHER_COUNTRIES_TRAVLED);
					}
					return new ArrayList<Attribute>(attributeMap.values());
				}
	
			});
		} catch (final DataAccessException dae) {
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
	
	@Override
	public List<Country> getAllCountries(final String roamType) throws RADataAccessException {
		final String query = MetaDataQueryBuilder.queryForCountries(roamType);
		
		LOGGER.debug("Getting all countries ");
		LOGGER.debug("Country query : " + query);

		List<Country> countries = new ArrayList<Country>(200);

		try {
			countries = prestoJdbcTempate.query(query, new RowMapper<Country>(){
				@Override
				public Country mapRow(final ResultSet rs, final int rowNumber) throws SQLException {
					final Country country = new Country();
					country.setCountryName(rs.getString("countryName"));
					country.setBordering(rs.getByte("bordering"));
					country.setLeisure(rs.getByte("leisure"));
					country.setLeisurePremium(rs.getByte("leisurePremium"));
					country.setLowGDP(rs.getByte("lowGDP"));
					country.setMcc(rs.getInt("mcc"));
					
					return country;
				}
			});
		} catch(final DataAccessException dae) {
			LOGGER.error("Error occurred while getting all countries: ", dae);
			throw new RADataAccessException(dae);
		}
		
		LOGGER.debug("Countries found : " + countries.size());
		LOGGER.trace("Country details : " + countries);
		
		return countries;
	}

	

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.repository.MetaDataRepository#getAllNetworkAndNetworkGroups(long, long)
	 */
	@Override
	public Map<Long, List<AttributeCategory>> getAllNetworkAndNetworkGroups(final long networkAttrId, 
			final long networkGroupAttrId,final String roamType) throws RADataAccessException {
		final String query = MetaDataQueryBuilder.queryForNetworkGroups(roamType);
		LOGGER.debug("Getting all networks groups ");
		LOGGER.debug("Network Group Query : " + query);

		final List<AttributeCategory> networkGroupCategories = new ArrayList<AttributeCategory>(50);
		final List<AttributeCategory> networkCategories = new ArrayList<AttributeCategory>(100);
		final Map<String,StringBuilder> networkGroupMap = new TreeMap<String, StringBuilder>();
		final Map<String,StringBuilder> networkMap = new TreeMap<String, StringBuilder>();
		final Map<Long, List<AttributeCategory>> result = new HashMap<Long, List<AttributeCategory>>();
		final Set<String> distinctNetworks = new HashSet<String>(200);
		try {
			this.prestoJdbcTempate.query(query, new RowMapper<AttributeCategory>() {
				@Override
				public AttributeCategory mapRow(final ResultSet rs, final int rowNum)
						throws SQLException {
					final String group = rs.getString("networkGroup");
					final String networkName = rs.getString("networkName");
					final String networkMnc = rs.getString("mnc");
					final String networkMcc = rs.getString("mcc");
					final String network = networkMcc + "-" + networkMnc;
					
					if (networkName != null && !networkName.isEmpty()) {
						if (group != null && !group.isEmpty() && group.contains(RAConstants.PIPE)) {
							final String[] groupArray = group.split("\\"+RAConstants.PIPE);
							
							for (final String groupName : groupArray) {
								StringBuilder networks = networkGroupMap.get(groupName);
								if (networks == null) {
									networks = new StringBuilder(); 
									networkGroupMap.put(groupName, networks);
									networks.append(network);
								} else {
									networks.append(RAConstants.COMMA).append(network);
								}
							}
							
						} else {
							StringBuilder networks = networkGroupMap.get(group);
							if (networks == null) {
								networks = new StringBuilder(); 
								networkGroupMap.put(group, networks);
								networks.append(network);
							} else {
								networks.append(RAConstants.COMMA).append(network);
							}
						}
						
						if (!distinctNetworks.contains(network)) {
							StringBuilder networks = networkMap.get(networkName);
							if (networks == null) {
								networks = new StringBuilder(); 
								networkMap.put(networkName, networks);
								networks.append(network);
							} else {
								networks.append(RAConstants.COMMA).append(network);
							}
							distinctNetworks.add(network);
						}
					}
					return null;
				}
			});
		} catch(final DataAccessException dae) {
			LOGGER.error("Error occurred while getting all network groups: ", dae);
			throw new RADataAccessException(dae);
		}
		int index = 1;
		for (final String group : networkGroupMap.keySet()) {
			final AttributeCategory attrCategory = new AttributeCategory();
			attrCategory.setCategName(group);
			attrCategory.setAttrId(networkGroupAttrId);
			attrCategory.setId(index++);
			attrCategory.setCategValue(networkGroupMap.get(group).toString());
			networkGroupCategories.add(attrCategory);
		}
		
		index = 1;
		for (final String networkName : networkMap.keySet()) {
			final AttributeCategory attrCategory = new AttributeCategory();
			attrCategory.setCategName(networkName);
			attrCategory.setAttrId(networkAttrId);
			attrCategory.setId(index++);
			attrCategory.setCategValue(networkMap.get(networkName).toString());
			networkCategories.add(attrCategory);
		}

		
		result.put(networkGroupAttrId, networkGroupCategories);
		result.put(networkAttrId, networkCategories);
		LOGGER.debug("networks  found : " + networkCategories.size());
		LOGGER.debug("networks  : " + networkCategories);
		
		LOGGER.debug("networks groups found : " + networkGroupCategories.size());
		LOGGER.debug("networks groups : " + networkGroupCategories);
		
		return result;
	}
	

	@Override
	public List<AttributeCategory> getOtherCountriesTraveled(final Filter filter, final String roamType) throws RADataAccessException {
		final StringBuilder query = new StringBuilder();
		final Map<String, Object> parameterMap = new HashMap<String, Object>();
		//PrestoQueryBuilder.populateQueryForOtherCountriesTraveled(filter, query, parameterMap, roamType);
		LOGGER.debug("Getting other countries traveled ");
		LOGGER.debug("Other Countries Traveled query : " + query.toString());
		
		List<AttributeCategory> otherCountries = new ArrayList<AttributeCategory>(10);
		try{
			otherCountries = prestoJdbcTempate.query(query.toString(), 
					new RowMapper<AttributeCategory>() {
				@Override
				public AttributeCategory mapRow(final ResultSet rs, final int rowNum)
						throws SQLException {
					final AttributeCategory attributeCategory = new AttributeCategory();
					attributeCategory.setCategName(rs.getString("othercountry"));
					attributeCategory.setId(rowNum);
					attributeCategory.setCategValue(attributeCategory.getCategName());
					RAConstants.attributeNameValueCache.get(RAConstants.ATTR_OTHER_COUNTRIES_TRAVLED).put(
							attributeCategory.getCategValue(), attributeCategory.getCategName());
					return attributeCategory;
				}
			});
		} catch (final DataAccessException dae) {
			LOGGER.error("Error occurred while getting other countries traveled: ", dae);
			throw new RADataAccessException(dae);
		}
		LOGGER.debug("Other countries traveled found : " +
				RAConstants.attributeNameValueCache.get(RAConstants.ATTR_OTHER_COUNTRIES_TRAVLED).keySet().size());
		LOGGER.trace("Other countries traveled names : " + RAConstants.attributeNameValueCache.get(
				RAConstants.ATTR_OTHER_COUNTRIES_TRAVLED).keySet());
		return otherCountries;
	}

}
