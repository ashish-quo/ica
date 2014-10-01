/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.enums.ARPU;
import com.mobileum.roameranalytics.enums.Network;
import com.mobileum.roameranalytics.enums.PaymentType;
import com.mobileum.roameranalytics.enums.RoamerType;
import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.chart.DonutData;

/**
 * @author sarvesh
 *
 */
@Repository
public class MicroSegmentRepositoryImpl implements MicroSegmentRepository{

	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger(MicroSegmentRepositoryImpl.class.getName());
	
	/** The named parameter jdbc template. */
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/** The named parameter jdbc template. */
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate2;
	
	/** The count sort desc. */
	private static Comparator<Object[]> COUNT_SORT_DESC = new Comparator<Object[]> () {

		@Override
		public int compare(Object[] o1, Object[] o2) {
			return ((Double)o2[1]).compareTo((Double)o1[1]);
		}
		
	};
	/** The application configuration. */
	@Autowired
	private Properties applicationConfiguration;
	
	@Override
	public Map<String,List<Object[]>> getMSChartData(Filter filter,String attributeName, String column,  
			Map<String,String> catNameValue) throws RADataAccessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		QueryBuilder.populateQueryForMicrosegmentChart(filter, query, column, parameterMap);
		
		LOGGER.debug("Getting microsegment chart data for attribute : " + attributeName);
		LOGGER.debug(attributeName + " query : " + query.toString());
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("endDate", filter.getDateTo());
		parameters.addValue("homeCountry", applicationConfiguration.get("home.country"));
		parameters.addValue("roamType", applicationConfiguration.get("roam.type"));
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		
		LOGGER.debug(attributeName + " query parameters : " + parameters.getValues());
		
		Map<String,List<Object[]>> dataMap = new HashMap<String, List<Object[]>>();
		dataMap.put("roamers",new ArrayList<Object[]>());
		dataMap.put("mt",new ArrayList<Object[]>());
		dataMap.put("mo",new ArrayList<Object[]>());
		dataMap.put("data",new ArrayList<Object[]>());
		
		try {
			this.namedParameterJdbcTemplate2.query(query.toString(), parameters,
					new RowMapper<DonutData>() {
				String categoryValue = null;
				@Override
				public DonutData mapRow(ResultSet rs, int rowNum)
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
			String column, String columnType, Map<String, String> catNameValue)
			throws RADataAccessException {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		StringBuilder query = new StringBuilder();
		QueryBuilder.populateQueryForNetworkGroupChart(filter, query,parameterMap);
		
		LOGGER.debug("Getting microsegment chart data for attribute : Network Group");
		LOGGER.debug(" Network Group query : " + query.toString());
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("endDate", filter.getDateTo());
		parameters.addValue("homeCountry", applicationConfiguration.get("home.country"));
		parameters.addValue("roamType", applicationConfiguration.get("roam.type"));
		
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		
		LOGGER.debug("Network Group query Parameters : " + parameters.getValues());
		
		Map<String,List<Object[]>> dataMap = new HashMap<String, List<Object[]>>();
		dataMap.put("roamers",new ArrayList<Object[]>());
		dataMap.put("mt",new ArrayList<Object[]>());
		dataMap.put("mo",new ArrayList<Object[]>());
		dataMap.put("data",new ArrayList<Object[]>());
		try {
			this.namedParameterJdbcTemplate2.query(query.toString(), parameters,
					new RowMapper<DonutData>() {
				String networkGroup = null;
				@Override
				public DonutData mapRow(ResultSet rs, int rowNum)
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
		String query = QueryBuilder.queryForLabelVsValue();
		return this.namedParameterJdbcTemplate.query(query, new ResultSetExtractor<Map<String, Map<String, String>>>() {
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

}
