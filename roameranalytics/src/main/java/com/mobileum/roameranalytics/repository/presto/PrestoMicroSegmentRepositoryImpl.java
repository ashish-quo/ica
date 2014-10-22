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
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.PrestoQueryBuilder;
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
		public int compare(Object[] o1, Object[] o2) {
			return ((Double)o2[1]).compareTo((Double)o1[1]);
		}
		
	};
	
	@Override
	public Map<String,List<Object[]>> getMSChartData(Filter filter,String attributeName, String column,  
			final Map<String,String> catNameValue, String roamType) throws RADataAccessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		PrestoQueryBuilder.populateQueryForMicrosegmentChart(filter, query, column, parameterMap, roamType);
		
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
				public Object mapRow(ResultSet rs, int rowNum)
						throws SQLException {

					categoryValue = catNameValue.get(rs.getString("categoryValue"));
					
					Object[] roamersObject = new Object[2];
					roamersObject[0] = categoryValue;
					roamersObject[1] = rs.getDouble("imsicount");
					
					Object[] moObject = new Object[2];
					moObject[0] = categoryValue;
					moObject[1] = rs.getDouble("mocallminutes");
				
					
					Object[] mtObject = new Object[2];
					mtObject[0] = categoryValue;
					mtObject[1] = rs.getDouble("mtcallminutes");
					
					
					Object[] dataObject = new Object[2];
					dataObject[0] = categoryValue;
					dataObject[1] = rs.getDouble("datausage");
					
					
					dataMap.get("mt").add(mtObject);
					dataMap.get("mo").add(moObject);
					dataMap.get("roamers").add(roamersObject);
					dataMap.get("data").add(dataObject);
					
					return null;
				}
			});
		} catch (DataAccessException dae) {
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
	public Map<String,List<Object[]>> getNetworkGroupData(Filter filter,
			String column, String columnType, Map<String, String> catNameValue, String roamType)
			throws RADataAccessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		StringBuilder query = new StringBuilder();
		PrestoQueryBuilder.populateQueryForNetworkGroupChart(filter, query,parameterMap,roamType);
		
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
				public Object mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					
					networkGroup = rs.getString("networkGroup");
					
					Object[] roamersObject = new Object[2];
					roamersObject[0] = networkGroup;
					roamersObject[1] = rs.getDouble("imsicount");
					
					Object[] moObject = new Object[2];
					moObject[0] = networkGroup;
					moObject[1] = rs.getDouble("mocallminutes");
				
					
					Object[] mtObject = new Object[2];
					mtObject[0] = networkGroup;
					mtObject[1] = rs.getDouble("mtcallminutes");
					
					
					Object[] dataObject = new Object[2];
					dataObject[0] = networkGroup;
					dataObject[1] = rs.getDouble("datausage");
					
					
					dataMap.get("mt").add(mtObject);
					dataMap.get("mo").add(moObject);
					dataMap.get("roamers").add(roamersObject);
					dataMap.get("data").add(dataObject);
					
					return null;
				}
			});
		} catch (DataAccessException dae) {
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


	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.MicroSegmentDaoI#getAttributeLabelAndValue()
	 */
	@Override
	public Map<String, Map<String, String>> getAttributeLabelAndValue() {
		String query = PrestoQueryBuilder.queryForLabelVsValue();
		return this.jdbcTemplate.query(query, new ResultSetExtractor<Map<String, Map<String, String>>>() {
			@Override
			public Map<String, Map<String, String>> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				Map<String, Map<String, String>> result = new HashMap<String, Map<String,String>>();
				while(rs.next()) {
					String attrName = rs.getString("attrName");
					String catName = rs.getString("catName");
					String catValue = rs.getString("catValue");
					Map<String,String> catValueMap = result.get(attrName);
					if (catValueMap == null) {
						catValueMap = new HashMap<String, String>(3);
						result.put(attrName, catValueMap);
						catValueMap.put("-1", "Unknown");
					} 
					catValueMap.put(catValue, catName);
				}
				return result;
			}
		});
	}

	@Override
	public Map<String, List<Object[]>> getOtherCountriesTraveledData(
			Filter filter, String column, String columnType, 
			Map<String, String> catNameValue, String roamType) throws RADataAccessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		StringBuilder query = new StringBuilder();
		PrestoQueryBuilder.populateQueryForOtherCountriesTraveledChart(filter, query,parameterMap, roamType);
		
		final Map<String,List<Object[]>> dataMap = new HashMap<String, List<Object[]>>();
		dataMap.put("roamers",new ArrayList<Object[]>());
		dataMap.put("mt",new ArrayList<Object[]>());
		dataMap.put("mo",new ArrayList<Object[]>());
		dataMap.put("data",new ArrayList<Object[]>());
		try {
			this.prestoJdbcTempate.query(query.toString(), new RowMapper<Object>() {
				String country = null;
				@Override
				public Object mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					
					country = rs.getString("country");
					
					Object[] roamersObject = new Object[2];
					roamersObject[0] = country;
					roamersObject[1] = rs.getDouble("imsicount");
					
					Object[] moObject = new Object[2];
					moObject[0] = country;
					moObject[1] = rs.getDouble("mocallminutes");
				
					
					Object[] mtObject = new Object[2];
					mtObject[0] = country;
					mtObject[1] = rs.getDouble("mtcallminutes");
					
					
					Object[] dataObject = new Object[2];
					dataObject[0] = country;
					dataObject[1] = rs.getDouble("datausage");
					
					
					dataMap.get("mt").add(mtObject);
					dataMap.get("mo").add(moObject);
					dataMap.get("roamers").add(roamersObject);
					dataMap.get("data").add(dataObject);
					
					return null;
				}
			});
		} catch (DataAccessException dae) {
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
