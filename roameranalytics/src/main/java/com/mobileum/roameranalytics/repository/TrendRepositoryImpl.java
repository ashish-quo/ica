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

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.CountryUsageStatistics;
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
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger("TrendRepositoryImpl");

	@Override
	public List<CountryUsageStatistics> getHeatMapList(String query, Object criteria[]) {

		return jdbcTemplate.query(query, criteria, new RowMapper<CountryUsageStatistics>() {
			public CountryUsageStatistics mapRow(ResultSet rs, int rowNum) throws SQLException {
				CountryUsageStatistics hm = new CountryUsageStatistics();
				hm.setCountryCode(rs.getString("visitedcountryname"));
				hm.setDataUsage(rs.getLong("modatacount"));
				hm.setMoUsage(rs.getLong("mocallcount"));
				hm.setMtUsage(rs.getLong("mtcallcount"));
				hm.setSmsUsage(rs.getLong("mosmscount"));
				// rstats.setFirstName(rs.getString("first_name"));
				// rstats.setLastName(rs.getString("last_name"));
				return hm;
			}
		});

	}
	@Override
	public List<RoamingStatistics> getTopRoamerDao(String query, Object criteria[]) {

		return jdbcTemplate.query(query, criteria,
				new RowMapper<RoamingStatistics>() {
					public RoamingStatistics mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						RoamingStatistics roamingStat = new RoamingStatistics();
						roamingStat.setCountryCode(rs
								.getString("visitedcountryname"));
						roamingStat.setMoTotal(rs.getLong("mocallcount"));
						roamingStat.setMt(rs.getLong("mtcallcount"));
						roamingStat.setSmsUsage(rs.getLong("mosmscount"));
						roamingStat.setDataUsage(rs.getLong("modatacount"));
						// rstats.setFirstName(rs.getString("first_name"));
						// rstats.setLastName(rs.getString("last_name"));
						return roamingStat;
					}
				});

	}

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
		System.out.println(query.toString());
		LOGGER.info(query.toString());
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("endDate", filter.getDateTo());
		parameters.addValue("homeCountry", applicationConfiguration.get("home.country"));
		parameters.addValue("roamType", applicationConfiguration.get("roam.type"));
		for (String key : parameterMap.keySet()) {
			parameters.addValue(key, parameterMap.get(key));
		}
		return namedParameterJdbcTemplate.query(query.toString(),parameters, new RoamingTrendResultSetExtractor());
	}

}
