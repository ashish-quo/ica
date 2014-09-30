/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.model.CountryUsageStatistics;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.RoamingCategory;
import com.mobileum.roameranalytics.model.RoamingStatistics;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;
import com.mobileum.roameranalytics.model.chart.RoamingTrendResultSetExtractor;

/**
 * @author smruti
 *
 */
@Repository
public class TrendRepositoryImpl implements TrendRepository {

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** The application configuration. */
	@Autowired
	private Properties applicationConfiguration;
	
	/** The named parameter jdbc template. */
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/** The named parameter jdbc template. */
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate2;
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger(TrendRepositoryImpl.class.getName());

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mobileum.roameranalytics.dao.TrendDaoI#getTrendsCharts(com.mobileum
	 * .roameranalytics.model.Fitler)
	 */
	@Override
	public RoamingTrend getTrendsCharts(Filter filter) {

		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		
		QueryBuilder.populateQueryForTrends(filter,query,parameterMap);

		
		LOGGER.debug("Roaming Trends query : " + query.toString());

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("endDate", filter.getDateTo());
		parameters.addValue("homeCountry", applicationConfiguration.get("home.country"));
		parameters.addValue("roamType", applicationConfiguration.get("roam.type"));
		
		LOGGER.debug("Query parameters : " + parameterMap);
		
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		return namedParameterJdbcTemplate.query(query.toString(),parameters, new RoamingTrendResultSetExtractor());
	}
	
	@Override
	public List<RoamingStatistics> getRoamingStatisticsRepository(Filter filter) {
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		QueryBuilder.populateQueryForRoamingStatistics(filter,query,parameterMap);
		
		LOGGER.info(query.toString());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		//parameters.addValue("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("homeCountry", applicationConfiguration.get("home.country"));
		parameters.addValue("endDate", filter.getDateTo());
		
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		
		
		return namedParameterJdbcTemplate2.query(query.toString(),parameters, new RowMapper<RoamingStatistics>() {
			@Override
			public RoamingStatistics mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				
				RoamingStatistics roamingStatistics = new RoamingStatistics();
				roamingStatistics.setCountryCode(rs.getString("visitedcountryname"));
				roamingStatistics.setRoamerTotal(rs.getLong("roamercount"));
				roamingStatistics.setMoTotal(rs.getLong("mocallminutes"));
				roamingStatistics.setMoLocal(rs.getLong("mocallminuteslocal"));
				roamingStatistics.setMoHome(rs.getLong("mocallminuteshome"));
				roamingStatistics.setMoIntl(rs.getLong("mocallminutesother"));
				roamingStatistics.setDataUsage(rs.getLong("datausage"));
				roamingStatistics.setMt(rs.getLong("mtcallminutes"));
				roamingStatistics.setSmsUsage(rs.getLong("mosmscount"));
				return roamingStatistics;
			}
		});

		
	}

	@Override
	public List<RoamingCategory> getRoamingCategoryRepository(Filter filter) {
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		QueryBuilder.populateQueryForRoamingCategoryCount(filter,query,parameterMap);
		
		LOGGER.info(query.toString());
		
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		//parameters.addValue("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("homeCountry", applicationConfiguration.get("home.country"));
		parameters.addValue("endDate", filter.getDateTo());
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		
		return namedParameterJdbcTemplate2.query(query.toString(),parameters, new RowMapper<RoamingCategory>() {
			@Override
			public RoamingCategory mapRow(ResultSet resultSet, int rowNum)
					throws SQLException {
				
				RoamingCategory roamingCategory = new RoamingCategory();
				roamingCategory.setVisitedCountryName(resultSet.getString("visitedcountryname"));
				
				if(resultSet.getInt("roamingcategory")==1)
				{
					roamingCategory.setCategory("silentRoamer");
					roamingCategory.setCount(resultSet.getLong("roamercount"));
				}else if(resultSet.getInt("roamingcategory")==2)
				{
					roamingCategory.setCategory("valueRoamer");
					roamingCategory.setCount(resultSet.getLong("roamercount"));
				}else if(resultSet.getInt("roamingcategory")==3)
				{
					roamingCategory.setCategory("premiumRoamer");
					roamingCategory.setCount(resultSet.getLong("roamercount"));
				}
				
				return roamingCategory;
			}
		});

		
	}
}
