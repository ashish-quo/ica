/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class MicroSegmentRepositorympl implements MicroSegmentRepository{

	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger("MicroSegmentDaoImpl");
	
	/** The named parameter jdbc template. */
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/** The application configuration. */
	@Autowired
	private Properties applicationConfiguration;
	
	@Override
	public Map<String, Object> getNetworkData(Filter filter) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		QueryBuilder.populateNetworkQuery(filter, query, parameterMap);
		LOGGER.info(query.toString());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("endDate", filter.getDateTo());
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", "Network");
		List<DonutData> dataList = this.namedParameterJdbcTemplate.query(query.toString(), parameters,
				new RowMapper<DonutData>() {
			@Override
			public DonutData mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DonutData donutData = new DonutData();
				donutData.setLabel(Network.of(rs.getInt("visitedmnc")).name());
				donutData.setValue(rs.getDouble("imsicount"));
				return donutData;
			}
		});
		
		result.put("data", dataList);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.MicroSegmentDaoI#getRoamerTypeData(com.mobileum.roameranalytics.model.Filter)
	 */
	@Override
	public Map<String, Object> getRoamerTypeData(Filter filter) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		QueryBuilder.populateRoamerTypeQuery(filter, query, parameterMap);
		LOGGER.info(query.toString());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("endDate", filter.getDateTo());
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", "Roaming Category");
		List<DonutData> dataList = this.namedParameterJdbcTemplate.query(query.toString(), parameters, 
				new RowMapper<DonutData>() {
			@Override
			public DonutData mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DonutData donutData = new DonutData();
				donutData.setLabel(RoamerType.of(rs.getInt("tripcategory")).getDisplayName());
				donutData.setValue(rs.getDouble("imsicount"));
				return donutData;
			}
		});
		
		result.put("data", dataList);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.MicroSegmentDaoI#getPaymentTypeData(com.mobileum.roameranalytics.model.Filter)
	 */
	@Override
	public Map<String, Object> getPaymentTypeData(Filter filter) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		QueryBuilder.populatePaymentTypeQuery(filter, query, parameterMap);
		LOGGER.info(query.toString());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("endDate", filter.getDateTo());
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", "Payment Type");
		List<DonutData> dataList = this.namedParameterJdbcTemplate.query(query.toString(), parameters,
				new RowMapper<DonutData>() {
			@Override
			public DonutData mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DonutData donutData = new DonutData();
				donutData.setLabel(PaymentType.of(rs.getInt("chargingplan")).name());
				donutData.setValue(rs.getDouble("imsicount"));
				return donutData;
			}
		});
		
		result.put("data", dataList);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.MicroSegmentDaoI#getDeviceTypeData(com.mobileum.roameranalytics.model.Filter)
	 */
	@Override
	public Map<String, Object> getDeviceTypeData(Filter filter) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		QueryBuilder.populateDeviceTypeQuery(filter, query, parameterMap);
		LOGGER.info(query.toString());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("endDate", filter.getDateTo());
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", "Device Type");
		List<DonutData> dataList = this.namedParameterJdbcTemplate.query(query.toString(), parameters,
				new RowMapper<DonutData>() {
			@Override
			public DonutData mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DonutData donutData = new DonutData();
				donutData.setLabel(rs.getString("devicetype"));
				donutData.setValue(rs.getDouble("imsicount"));
				return donutData;
			}
		});
		
		result.put("data", dataList);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.dao.MicroSegmentDaoI#getARPUData(com.mobileum.roameranalytics.model.Filter)
	 */
	@Override
	public Map<String, Object> getARPUData(Filter filter) {
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		QueryBuilder.populateARPUQuery(filter, query, parameterMap);
		LOGGER.info(query.toString());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("endDate", filter.getDateTo());
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", "Domestic ARPU");
		List<DonutData> dataList = this.namedParameterJdbcTemplate.query(query.toString(), parameters,
				new RowMapper<DonutData>() {
			@Override
			public DonutData mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				DonutData donutData = new DonutData();
				donutData.setLabel(ARPU.of(rs.getInt("domcategory")).name());
				donutData.setValue(rs.getDouble("imsicount"));
				return donutData;
			}
		});
		
		result.put("data", dataList);
		return result;
	}

	@Override
	public Map<String, Object> getMSChartData(Filter filter,String attributeName, String column,  
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
		
		LOGGER.debug(attributeName + " query Parameters : " + parameters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<DonutData> dataList = new ArrayList<DonutData>(10);
		try {
			dataList = this.namedParameterJdbcTemplate.query(query.toString(), parameters,
					new RowMapper<DonutData>() {
				@Override
				public DonutData mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					DonutData donutData = new DonutData();
					
					donutData.setLabel(catNameValue.get(rs.getString("categoryValue")));
					donutData.setValue(rs.getDouble("imsicount"));
					return donutData;
				}
			});
		} catch (DataAccessException dae) {
			LOGGER.error("Exception While getting "+ attributeName + " chart's data : ", dae);
			throw new RADataAccessException(dae);
		}
		LOGGER.trace("Microsegment chart data list: " + dataList);
		result.put("data", dataList);
		return result;
	}
	

	@Override
	public Map<String, Object> getNetworkGroupData(Filter filter,
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
		
		LOGGER.debug("Network Group query Parameters : " + parameters);
		
		Map<String, Object> result = new HashMap<String, Object>();
		List<DonutData> dataList = new ArrayList<DonutData>(10);
		try {
			dataList = this.namedParameterJdbcTemplate.query(query.toString(), parameters,
					new RowMapper<DonutData>() {
				@Override
				public DonutData mapRow(ResultSet rs, int rowNum)
						throws SQLException {
					DonutData donutData = new DonutData();
					
					donutData.setLabel(rs.getString("networkGroup"));
					donutData.setValue(rs.getDouble("imsicount"));
					return donutData;
				}
			});
		} catch (DataAccessException dae) {
			LOGGER.error("Exception While getting network group data in microsegment : ", dae);
			throw new RADataAccessException(dae);
		}
		
		result.put("data", dataList);
		return result;
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
