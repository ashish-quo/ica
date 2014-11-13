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
					if (categoryValue == null) {
						categoryValue = "Unknown";
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
		try {
			this.prestoJdbcTempate.query(query.toString(), new RowMapper<Object>() {
				String networkGroup = null;
				@Override
				public Object mapRow(final ResultSet rs, final int rowNum)
						throws SQLException {
					
					networkGroup = rs.getString("networkGroup");
					
					final Object[] roamersObject = new Object[2];
					roamersObject[0] = networkGroup;
					roamersObject[1] = rs.getDouble("imsicount");
					
					final Object[] moObject = new Object[2];
					moObject[0] = networkGroup;
					moObject[1] = rs.getDouble("mocallminutes");
				
					
					final Object[] mtObject = new Object[2];
					mtObject[0] = networkGroup;
					mtObject[1] = rs.getDouble("mtcallminutes");
					
					
					final Object[] dataObject = new Object[2];
					dataObject[0] = networkGroup;
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
		//PrestoQueryBuilder.populateQueryForOtherCountriesTraveledChart(filter, query,parameterMap, roamType);
		
		final Map<String,List<Object[]>> dataMap = new HashMap<String, List<Object[]>>();
		dataMap.put("roamers",new ArrayList<Object[]>());
		dataMap.put("mt",new ArrayList<Object[]>());
		dataMap.put("mo",new ArrayList<Object[]>());
		dataMap.put("data",new ArrayList<Object[]>());
		try {
			this.prestoJdbcTempate.query(query.toString(), new RowMapper<Object>() {
				String country = null;
				@Override
				public Object mapRow(final ResultSet rs, final int rowNum)
						throws SQLException {
					
					country = rs.getString("country");
					
					final Object[] roamersObject = new Object[2];
					roamersObject[0] = country;
					roamersObject[1] = rs.getDouble("imsicount");
					
					final Object[] moObject = new Object[2];
					moObject[0] = country;
					moObject[1] = rs.getDouble("mocallminutes");
				
					
					final Object[] mtObject = new Object[2];
					mtObject[0] = country;
					mtObject[1] = rs.getDouble("mtcallminutes");
					
					
					final Object[] dataObject = new Object[2];
					dataObject[0] = country;
					dataObject[1] = rs.getDouble("datausage");
					
					
					dataMap.get("mt").add(mtObject);
					dataMap.get("mo").add(moObject);
					dataMap.get("roamers").add(roamersObject);
					dataMap.get("data").add(dataObject);
					
					return null;
				}
			});
		} catch (final DataAccessException dae) {
			LOGGER.error("Exception While getting Other Countries traveled data in microsegment : ", dae);
			throw new RADataAccessException(dae);
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
