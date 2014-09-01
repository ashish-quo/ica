/**
 * 
 */
package com.mobileum.roameranalytics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mobileum.roameranalytics.common.CommonUtil;
import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.enums.RoamerType;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.HeatMap;
import com.mobileum.roameranalytics.model.RoamingStats;
import com.mobileum.roameranalytics.model.chart.ChartSeries;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;
import com.mobileum.roameranalytics.model.chart.RoamingTrendChart;
import com.mobileum.roameranalytics.model.chart.RoamingTrendResultSetExtractor;

/**
 * @author smruti
 *
 */
@Repository
public class TrendDaoImpl implements TrendDaoI {

	@Autowired
	DataSource dataSource;

	/** The jdbc template. */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/** The named parameter jdbc template. */
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger("TrendDaoImpl");

	public void insertData() {

		String sql = "INSERT INTO country(id,country_code) VALUES(?, ?)";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { 2, "df", });

	}

	public List<Attribute> getAttributeList() {
		String query = QueryBuilder.queryForAttributes();
		return jdbcTemplate.query(query,
				new ResultSetExtractor<List<Attribute>>() {
					public List<Attribute> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						Map<Long, Attribute> attrMap = new LinkedHashMap<Long, Attribute>();
						while (rs.next()) {
							Long attrId = rs.getLong("attrId");
							if (!attrMap.containsKey(attrId)) {
								Attribute attribute = new Attribute();
								attribute.setId(attrId);
								attribute.setAttributeName(rs
										.getString("attrName"));
								attribute.setModuleId(rs.getInt("moduleId"));
								attribute.setIcon(rs.getString("attrIcon"));
								attribute.setType(rs.getInt("attrType"));
								attribute.setViewType(rs.getString("viewType"));
								attrMap.put(attrId, attribute);
								attribute
										.setAttributeCategoryList(new ArrayList<AttributeCategory>());
							}

							AttributeCategory attrCat = new AttributeCategory();
							attrCat.setCategoryName(rs.getString("catName"));
							attrCat.setIcon(rs.getString("catIcon"));
							attrMap.get(attrId).getAttributeCategoryList()
									.add(attrCat);
						}
						return new ArrayList<Attribute>(attrMap.values());
					}

				});

	}
	
	@Override
	public List<HeatMap> getHeatMapList(String query, Object criteria[]) {

		return jdbcTemplate.query(query, criteria, new RowMapper<HeatMap>() {
			public HeatMap mapRow(ResultSet rs, int rowNum) throws SQLException {
				HeatMap hm = new HeatMap();
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
	public List<RoamingStats> getTopRoamerDao(String query, Object criteria[]) {

		return jdbcTemplate.query(query, criteria,
				new RowMapper<RoamingStats>() {
					public RoamingStats mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						RoamingStats roamingStat = new RoamingStats();
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

		String query = QueryBuilder.queryForTrends(filter);
		LOGGER.info(query);
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		parameters.addValue("startDate", filter.getDateFrom());
		parameters.addValue("endDate", filter.getDateTo());
		return namedParameterJdbcTemplate.query(query,parameters, new RoamingTrendResultSetExtractor());
	}

}
