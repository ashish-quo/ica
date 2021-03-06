/**
 * 
 */
package com.mobileum.roameranalytics.repository.presto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.MicroSegmentQueryBuilder;
import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.repository.MicroSegmentRepository;

/**
 * @author sarvesh
 *
 */
@Repository
@Qualifier("prestoMetadataRepository")
public class PrestoMicroSegmentRepositoryImpl implements MicroSegmentRepository {

	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger(PrestoMicroSegmentRepositoryImpl.class.getName());
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate prestoJdbcTempate;
	
	
	/** The count sort desc. */
	private static Comparator<Object[]> COUNT_SORT_DESC = new Comparator<Object[]> () {

		@Override
		public int compare(final Object[] o1, final Object[] o2) {
			return ((Double)o2[1]).compareTo((Double)o1[1]);
		}
		
	};
	
	@Override
	public Map<String,List<Object[]>> getMSChartData(final Filter filter,final String attributeName, final String column,  
			final Map<String,String> catNameValue, final String roamType) throws RADataAccessException {
		final Map<String, Object> parameterMap = new HashMap<String, Object>();
		final StringBuilder query = new StringBuilder();
		MicroSegmentQueryBuilder.populateQueryForMicrosegmentChart(filter, query, column, parameterMap, roamType);
		
		LOGGER.debug("Getting microsegment chart data for attribute : " + attributeName);
		LOGGER.debug(attributeName + " query : " + query.toString());
		
		final Map<String,List<Object[]>> dataMap = new HashMap<String, List<Object[]>>();
		dataMap.put("roamers",new ArrayList<Object[]>());
		dataMap.put("mt",new ArrayList<Object[]>());
		dataMap.put("mo",new ArrayList<Object[]>());
		dataMap.put("data",new ArrayList<Object[]>());
		
		try {
			this.prestoJdbcTempate.query(query.toString(), new RowMapper<Object>() {
				
				String categoryValue = null;
				
				@Override
				public Object mapRow(final ResultSet rs, final int rowNum)
						throws SQLException {

					categoryValue = catNameValue.get(rs.getString("categoryValue"));
					if (categoryValue == null && !(RAConstants.ATTR_DEVICE_MODEL.equalsIgnoreCase(attributeName)
							|| RAConstants.ATTR_MANUFACTURER.equalsIgnoreCase(attributeName))) {
						categoryValue = "Unknown";
					} else if (RAConstants.ATTR_DEVICE_MODEL.equalsIgnoreCase(attributeName)
							|| RAConstants.ATTR_MANUFACTURER.equalsIgnoreCase(attributeName)) {
						categoryValue = rs.getString("categoryValue");
					}
					final Object[] roamersObject = new Object[2];
					roamersObject[0] = categoryValue;
					roamersObject[1] = rs.getDouble("imsicount");
					
					final Object[] moObject = new Object[2];
					moObject[0] = categoryValue;
					moObject[1] = rs.getDouble("mocallminutes");
				
					
					final Object[] mtObject = new Object[2];
					mtObject[0] = categoryValue;
					mtObject[1] = rs.getDouble("mtcallminutes");
					
					
					final Object[] dataObject = new Object[2];
					dataObject[0] = categoryValue;
					dataObject[1] = rs.getDouble("datausage");
					
					
					dataMap.get("mt").add(mtObject);
					dataMap.get("mo").add(moObject);
					dataMap.get("roamers").add(roamersObject);
					dataMap.get("data").add(dataObject);
					
					return null;
				}
			});
		} catch (final DataAccessException dae) {
			LOGGER.error("Exception While getting "+ attributeName + " chart's data : ", dae);
			throw new RADataAccessException(dae);
		}
		
		Collections.sort(dataMap.get("mo"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("mt"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("data"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("roamers"),COUNT_SORT_DESC);
		
		
		LOGGER.debug(attributeName + " chart data found :" + dataMap.size());
		LOGGER.trace( attributeName + " chart data list: " + dataMap);
		return dataMap;
	}
	

	@Override
	public Map<String,List<Object[]>> getNetworkGroupData(final Filter filter,
			final String column, final String columnType, final Map<String, String> catNameValue, final String roamType)
			throws RADataAccessException {
		final Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		final StringBuilder query = new StringBuilder();
		MicroSegmentQueryBuilder.populateQueryForNetworkGroupChart(filter, query,parameterMap,roamType);
		
		LOGGER.debug("Getting microsegment chart data for attribute : Network Group");
		LOGGER.debug(" Network Group query : " + query.toString());
		
		
		final Map<String,List<Object[]>> dataMap = new HashMap<String, List<Object[]>>();
		dataMap.put("roamers",new ArrayList<Object[]>());
		dataMap.put("mt",new ArrayList<Object[]>());
		dataMap.put("mo",new ArrayList<Object[]>());
		dataMap.put("data",new ArrayList<Object[]>());
		
		final Map<String, Map<String, Double>> networkGroupMap = new HashMap<String, Map<String,Double>>(200);
		
		try {
			this.prestoJdbcTempate.query(query.toString(), new RowMapper<Object>() {
				String networkGroup = null;
				@Override
				public Object mapRow(final ResultSet rs, final int rowNum)
						throws SQLException {
					
					networkGroup = rs.getString("networkGroup");
					final double imsicount = rs.getDouble("imsicount");
					final double mocallminutes = rs.getDouble("mocallminutes");
					final double mtcallminutes = rs.getDouble("mtcallminutes");
					final double datausage = rs.getDouble("datausage");
					
					if (networkGroup != null && !networkGroup.isEmpty() && networkGroup.contains(RAConstants.PIPE)) {
						final String[] groupArray = networkGroup.split("\\"+RAConstants.PIPE);
						for (final String groupName : groupArray) {
							Map<String,Double> groupDataMap = networkGroupMap.get(groupName);
							if (groupDataMap == null) {
								groupDataMap = new HashMap<String,Double>(4);
								groupDataMap.put("roamers", 0d);
								groupDataMap.put("mt", 0d);
								groupDataMap.put("mo", 0d);
								groupDataMap.put("data", 0d);
								networkGroupMap.put(groupName, groupDataMap);
							}
							groupDataMap.put("roamers", imsicount + groupDataMap.get("roamers"));
							groupDataMap.put("mt", mtcallminutes + groupDataMap.get("mt"));
							groupDataMap.put("mo", mocallminutes + groupDataMap.get("mo"));
							groupDataMap.put("data", datausage + groupDataMap.get("data"));
						}
						
					} else {
						Map<String,Double> groupDataMap = networkGroupMap.get(networkGroup);
						if (groupDataMap == null) {
							groupDataMap = new HashMap<String,Double>(4);
							groupDataMap.put("roamers", 0d);
							groupDataMap.put("mt", 0d);
							groupDataMap.put("mo", 0d);
							groupDataMap.put("data", 0d);
							networkGroupMap.put(networkGroup, groupDataMap);
						}
						groupDataMap.put("roamers", imsicount + groupDataMap.get("roamers"));
						groupDataMap.put("mt", mtcallminutes + groupDataMap.get("mt"));
						groupDataMap.put("mo", mocallminutes + groupDataMap.get("mo"));
						groupDataMap.put("data", datausage + groupDataMap.get("data"));
					}
					
//					
//					final Object[] roamersObject = new Object[2];
//					roamersObject[0] = networkGroup;
//					roamersObject[1] = rs.getDouble("imsicount");
//					
//					final Object[] moObject = new Object[2];
//					moObject[0] = networkGroup;
//					moObject[1] = rs.getDouble("mocallminutes");
//				
//					
//					final Object[] mtObject = new Object[2];
//					mtObject[0] = networkGroup;
//					mtObject[1] = rs.getDouble("mtcallminutes");
//					
//					
//					final Object[] dataObject = new Object[2];
//					dataObject[0] = networkGroup;
//					dataObject[1] = rs.getDouble("datausage");
//					
//					
//					dataMap.get("mt").add(mtObject);
//					dataMap.get("mo").add(moObject);
//					dataMap.get("roamers").add(roamersObject);
//					dataMap.get("data").add(dataObject);
					
					return null;
				}
			});
		} catch (final DataAccessException dae) {
			LOGGER.error("Exception While getting network group data in microsegment : ", dae);
			throw new RADataAccessException(dae);
		}
		
		for (final String group : networkGroupMap.keySet()) {
			final Object[] roamersObject = new Object[2];
			roamersObject[0] = group;
			roamersObject[1] = networkGroupMap.get(group).get("roamers");
			
			final Object[] moObject = new Object[2];
			moObject[0] = group;
			moObject[1] = networkGroupMap.get(group).get("mo");
		
			
			final Object[] mtObject = new Object[2];
			mtObject[0] = group;
			mtObject[1] = networkGroupMap.get(group).get("mt");
			
			
			final Object[] dataObject = new Object[2];
			dataObject[0] = group;
			dataObject[1] = networkGroupMap.get(group).get("data");
			
			
			dataMap.get("mt").add(mtObject);
			dataMap.get("mo").add(moObject);
			dataMap.get("roamers").add(roamersObject);
			dataMap.get("data").add(dataObject);
		}
		
		
		Collections.sort(dataMap.get("mo"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("mt"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("data"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("roamers"),COUNT_SORT_DESC);
			
		
		LOGGER.debug("Network Group data found :" + dataMap.size());
		LOGGER.trace("Network Group data :" + dataMap);
		return dataMap;
	}
	
	@Override
	public Map<String,List<Object[]>> getNetworkData(final Filter filter,
			final String column, final String columnType, final Map<String, String> catNameValue, final String roamType)
			throws RADataAccessException {
		final Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		final StringBuilder query = new StringBuilder();
		MicroSegmentQueryBuilder.populateQueryForNetworkChart(filter, query,parameterMap,roamType);
		
		LOGGER.debug("Getting microsegment chart data for attribute : Network ");
		LOGGER.debug(" Network  query : " + query.toString());
		
		
		final Map<String,List<Object[]>> dataMap = new HashMap<String, List<Object[]>>();
		dataMap.put("roamers",new ArrayList<Object[]>());
		dataMap.put("mt",new ArrayList<Object[]>());
		dataMap.put("mo",new ArrayList<Object[]>());
		dataMap.put("data",new ArrayList<Object[]>());
		try {
			this.prestoJdbcTempate.query(query.toString(), new RowMapper<Object>() {
				String networkName = null;
				@Override
				public Object mapRow(final ResultSet rs, final int rowNum)
						throws SQLException {
					
					networkName = rs.getString("networkName");
					
					final Object[] roamersObject = new Object[2];
					roamersObject[0] = networkName;
					roamersObject[1] = rs.getDouble("imsicount");
					
					final Object[] moObject = new Object[2];
					moObject[0] = networkName;
					moObject[1] = rs.getDouble("mocallminutes");
				
					
					final Object[] mtObject = new Object[2];
					mtObject[0] = networkName;
					mtObject[1] = rs.getDouble("mtcallminutes");
					
					
					final Object[] dataObject = new Object[2];
					dataObject[0] = networkName;
					dataObject[1] = rs.getDouble("datausage");
					
					
					dataMap.get("mt").add(mtObject);
					dataMap.get("mo").add(moObject);
					dataMap.get("roamers").add(roamersObject);
					dataMap.get("data").add(dataObject);
					
					return null;
				}
			});
		} catch (final DataAccessException dae) {
			LOGGER.error("Exception While getting network group data in microsegment : ", dae);
			throw new RADataAccessException(dae);
		}
		
		Collections.sort(dataMap.get("mo"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("mt"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("data"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("roamers"),COUNT_SORT_DESC);
			
		
		LOGGER.debug("Network  data found :" + dataMap.size());
		LOGGER.trace("Network  data :" + dataMap);
		return dataMap;
	}

	@Override
	public Map<String, List<Object[]>> getOtherCountriesTraveledData(
			final Filter filter, final String column, final String columnType, 
			final Map<String, String> catNameValue, final String roamType) throws RADataAccessException {
		final Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		final StringBuilder query = new StringBuilder();
		MicroSegmentQueryBuilder.populateQueryForOtherCountriesTraveledChart(filter, query,parameterMap, roamType);
		
		final Map<String,List<Object[]>> dataMap = new HashMap<String, List<Object[]>>();
		dataMap.put("roamers",new ArrayList<Object[]>());
		dataMap.put("mt",new ArrayList<Object[]>());
		dataMap.put("mo",new ArrayList<Object[]>());
		dataMap.put("data",new ArrayList<Object[]>());
		final Map<String, Map<String,Double>> countryDataMap = new HashMap<String, Map<String,Double>>();
		try {
			this.prestoJdbcTempate.query(query.toString(), new RowMapper<Object>() {
				@Override
				public Object mapRow(final ResultSet rs, final int rowNum)
						throws SQLException {
					
					final int bordering = rs.getInt("bordering");
					final int leisure = rs.getInt("leisure");
					final int leisurePremium = rs.getInt("leisurePremium");
					final int lowGDP = rs.getInt("lowGDP");
					
					final double imsicount = rs.getDouble("imsicount");
					final double mocallminutes = rs.getDouble("mocallminutes");
					final double mtcallminutes = rs.getDouble("mtcallminutes");
					final double datausage = rs.getDouble("datausage");
					
					if (bordering > 0) {
						Map<String, Double> borderingData = countryDataMap.get(RAConstants.NEIGHBOURS);
						if (borderingData == null) {
							borderingData = new HashMap<String, Double>();
							borderingData.put("imsicount", imsicount);
							borderingData.put("mocallminutes", mocallminutes);
							borderingData.put("mtcallminutes", mtcallminutes);
							borderingData.put("datausage", datausage);
							countryDataMap.put(RAConstants.NEIGHBOURS, borderingData);
						} else {
							borderingData.put("imsicount", imsicount + borderingData.get("imsicount"));
							borderingData.put("mocallminutes", mocallminutes + borderingData.get("mocallminutes"));
							borderingData.put("mtcallminutes", mtcallminutes + borderingData.get("mtcallminutes"));
							borderingData.put("datausage", datausage + borderingData.get("datausage"));
							
						}
					}
					if (leisure > 0) {
						Map<String, Double> borderingData = countryDataMap.get(RAConstants.LEISURE);
						if (borderingData == null) {
							borderingData = new HashMap<String, Double>();
							borderingData.put("imsicount", imsicount);
							borderingData.put("mocallminutes", mocallminutes);
							borderingData.put("mtcallminutes", mtcallminutes);
							borderingData.put("datausage", datausage);
							countryDataMap.put(RAConstants.LEISURE, borderingData);
						} else {
							borderingData.put("imsicount", imsicount + borderingData.get("imsicount"));
							borderingData.put("mocallminutes", mocallminutes + borderingData.get("mocallminutes"));
							borderingData.put("mtcallminutes", mtcallminutes + borderingData.get("mtcallminutes"));
							borderingData.put("datausage", datausage + borderingData.get("datausage"));
							
						}
					}
					if (leisurePremium > 0) {
						Map<String, Double> borderingData = countryDataMap.get(RAConstants.LEISURE_PREMIUM);
						if (borderingData == null) {
							borderingData = new HashMap<String, Double>();
							borderingData.put("imsicount", imsicount);
							borderingData.put("mocallminutes", mocallminutes);
							borderingData.put("mtcallminutes", mtcallminutes);
							borderingData.put("datausage", datausage);
							countryDataMap.put(RAConstants.LEISURE_PREMIUM, borderingData);
						} else {
							borderingData.put("imsicount", imsicount + borderingData.get("imsicount"));
							borderingData.put("mocallminutes", mocallminutes + borderingData.get("mocallminutes"));
							borderingData.put("mtcallminutes", mtcallminutes + borderingData.get("mtcallminutes"));
							borderingData.put("datausage", datausage + borderingData.get("datausage"));
							
						}
					}
					if (lowGDP > 0) {
						Map<String, Double> borderingData = countryDataMap.get(RAConstants.LOW_GDP);
						if (borderingData == null) {
							borderingData = new HashMap<String, Double>();
							borderingData.put("imsicount", imsicount);
							borderingData.put("mocallminutes", mocallminutes);
							borderingData.put("mtcallminutes", mtcallminutes);
							borderingData.put("datausage", datausage);
							countryDataMap.put(RAConstants.LOW_GDP, borderingData);
						} else {
							borderingData.put("imsicount", imsicount + borderingData.get("imsicount"));
							borderingData.put("mocallminutes", mocallminutes + borderingData.get("mocallminutes"));
							borderingData.put("mtcallminutes", mtcallminutes + borderingData.get("mtcallminutes"));
							borderingData.put("datausage", datausage + borderingData.get("datausage"));
							
						}
					}
					return null;
				}
			});
		} catch (final DataAccessException dae) {
			LOGGER.error("Exception While getting Other Countries traveled data in microsegment : ", dae);
			throw new RADataAccessException(dae);
		}
		
		for (final String group : countryDataMap.keySet()) {
			final Object[] roamersObject = new Object[2];
			roamersObject[0] = group;
			roamersObject[1] = countryDataMap.get(group).get("imsicount");
			
			final Object[] moObject = new Object[2];
			moObject[0] = group;
			moObject[1] = countryDataMap.get(group).get("mocallminutes");
		
			
			final Object[] mtObject = new Object[2];
			mtObject[0] = group;
			mtObject[1] = countryDataMap.get(group).get("mtcallminutes");
			
			
			final Object[] dataObject = new Object[2];
			dataObject[0] = group;
			dataObject[1] = countryDataMap.get(group).get("datausage");
			
			
			dataMap.get("mt").add(mtObject);
			dataMap.get("mo").add(moObject);
			dataMap.get("roamers").add(roamersObject);
			dataMap.get("data").add(dataObject);
		}
		
		Collections.sort(dataMap.get("mo"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("mt"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("data"),COUNT_SORT_DESC);
		Collections.sort(dataMap.get("roamers"),COUNT_SORT_DESC);
			
		
		LOGGER.debug("Other Countries traveled data found :" + dataMap.size());
		LOGGER.trace("Other Countries traveled data :" + dataMap);
		return dataMap;
	}

}
