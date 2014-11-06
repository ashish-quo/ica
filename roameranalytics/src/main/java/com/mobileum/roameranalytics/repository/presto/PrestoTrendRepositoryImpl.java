/**
 * 
 */
package com.mobileum.roameranalytics.repository.presto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.PrestoQueryBuilder;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.RoamingCategory;
import com.mobileum.roameranalytics.model.RoamingStatistics;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;
import com.mobileum.roameranalytics.model.chart.RoamingTrendResultSetExtractor;
import com.mobileum.roameranalytics.repository.TrendRepository;

/**
 * @author sarvesh
 *
 */
@Repository
@Qualifier("prestoTrendRepository")
public class PrestoTrendRepositoryImpl implements TrendRepository {

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** The jdbc template. */
	@Autowired
	private JdbcTemplate prestoJdbcTempate;
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger(PrestoTrendRepositoryImpl.class.getName());

	@Override
	public RoamingTrend getTrendsCharts(final Filter filter, final String roamType) {

		final Map<String, Object> parameterMap = new HashMap<String, Object>();
		final StringBuilder query = new StringBuilder();
		
		PrestoQueryBuilder.populateQueryForTrends(filter,query,parameterMap, roamType);
		LOGGER.debug("Roaming Trends query : " + query.toString());
		return prestoJdbcTempate.query(query.toString(), new RoamingTrendResultSetExtractor());
	}
	
	@Override
	public List<RoamingStatistics> getRoamingStatistics(final Filter filter,  final String roamType) {
		
		final Map<String, Object> parameterMap = new HashMap<String, Object>();
		final StringBuilder query = new StringBuilder();
		PrestoQueryBuilder.populateQueryForRoamingStatistics(filter,query,parameterMap, roamType);
		LOGGER.debug(query.toString());

		return prestoJdbcTempate.query(query.toString(), new RowMapper<RoamingStatistics>() {

			@Override
			public RoamingStatistics mapRow(final ResultSet rs, final int rowNum) throws SQLException {
				
				final RoamingStatistics roamingStatistics = new RoamingStatistics();
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
	public List<RoamingCategory> getRoamingCategory(final Filter filter, final String roamType) {
		
		final Map<String, Object> parameterMap = new HashMap<String, Object>();
		final StringBuilder query = new StringBuilder();
		PrestoQueryBuilder.populateQueryForRoamingCategoryCount(filter,query,parameterMap, roamType);
		
		LOGGER.info(query.toString());
		
		return prestoJdbcTempate.query(query.toString(), new RowMapper<RoamingCategory>() {
			@Override
			public RoamingCategory mapRow(final ResultSet resultSet, final int rowNum)
					throws SQLException {
				
				final RoamingCategory roamingCategory = new RoamingCategory();
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
