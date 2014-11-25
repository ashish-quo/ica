/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.enums.RoamType;
import com.mobileum.roameranalytics.model.Filter;


/**
 * The Class QueryBuilder. Creates dynamic queries.
 *
 * @author sarvesh
 */
public class StatsQueryBuilder {

	
	static String getClauseForCountryJoin(final String selectedCountries, final String roamType) {
		final StringBuilder clause = new StringBuilder(" inner join (select distinct mcc,mnc from ")
			.append(RAPropertyUtil.getProperty("common.table.tadignetwork")).append(" network where ")
			.append(" network.countryid in (").append(selectedCountries).append(")) country ");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			clause.append(" on country.mcc = trip.visitedmcc and country.mnc = trip.visitedmnc ");
		} else {
			clause.append(" on country.mcc = trip.homemcc and country.mnc = trip.homemnc ");
		}
		return clause.toString();
	}
	
	static String getClauseForCountry(final String selectedCountries, final String roamType) {
		final StringBuilder clause = new StringBuilder();
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			clause.append(" and  trip.visitedmcc in (").append(selectedCountries).append(") ");
		} else {
			clause.append(" and  trip.homemcc in (").append(selectedCountries).append(") ");
		}
		return clause.toString();
	}
	
	static String getClauseForCountryNotIn(final String selectedCountries, final String roamType) {
		final StringBuilder clause = new StringBuilder();
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			clause.append(" and  trip.visitedmcc not in (").append(selectedCountries).append(") ");
		} else {
			clause.append(" and  trip.homemcc not in (").append(selectedCountries).append(") ");
		}
		return clause.toString();
	}
	
	
	
	/**
	 * Populates query for trends chart.
	 *
	 * @param filter - filters selected
	 * @param query the query to be populated 
	 * @param parameterMap the parameter map - will have parameter and its value used in query
	 * @throws ClassNotFoundException 
	 */
	public static void populateQueryForRoamingStatistics(final Filter filter, final StringBuilder query, 
			final Map<String, Object> parameterMap, final String roamType)  {
		
		
		query.append("select ");
		query.append(RoamType.OUT.getRoamType().equalsIgnoreCase(roamType) ? "visitedcountry" : "homecountry" );
		query .append(" visitedcountryname,overalltripcategory roamingcategory, count(distinct imsi) roamercount, sum(mocallminutes) mocallminutes, sum(mtcallminutes) mtcallminutes,"+ 
				 "sum(mosmscount) mosmscount, sum(uplink + downlink) datausage, sum(mocallminuteslocal) mocallminuteslocal, "+ 
				 " sum(mocallminuteshome) mocallminuteshome,sum(mocallminutesothers) mocallminutesother from ");
		query.append(RoamType.OUT.getRoamType().equalsIgnoreCase(roamType) ? RAPropertyUtil.getProperty("out.table.business") : RAPropertyUtil.getProperty("in.table.business") );
		query.append(" trip where trip.usagebintime >= ").append(filter.getDateFrom())
			.append(" and trip.usagebintime <= ").append(filter.getDateTo())
			.append(" ");
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(getClauseForCountry(filter.getSelectedCountries(), roamType));
		}
		   
		//((endtime >= t1 || endtime == 0) && starttime <= t2)
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);
			
		query.append(" group by  overalltripcategory,");
		query.append(RoamType.OUT.getRoamType().equalsIgnoreCase(roamType) ? "visitedcountry" : "homecountry" );
		
	}
	public static void populateQueryForRoamingCategoryCount(final Filter filter, final StringBuilder query,
			final Map<String, Object> parameterMap, final String roamType)  {
		
		query.append("select country visitedcountryname,overalltripcategory as roamingcategory, count(imsi) roamercount  from ");
		query.append(RoamType.OUT.getRoamType().equalsIgnoreCase(roamType) ? RAPropertyUtil.getProperty("out.table.trip") : RAPropertyUtil.getProperty("in.table.trip") );
		query.append(" trip inner join networkib network on trip.").append(RoamType.OUT.getRoamType().equalsIgnoreCase(roamType) ? "visitedmcc" : "homemcc");
		query.append("=network.mcc inner join countryib country on country.countryid=network.countryid");
		
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ");
		
		query.append(" and network.countryid in ")
		.append(!filter.getSelectedCountries().isEmpty() ? "("+filter.getSelectedCountries()+")" :  "(select distinct countryid from countryib) ");
		
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);
			
		query.append(" group by  overalltripcategory,country ");
		
	
	}
	/**
	 * Append clause for attributes.
	 *
	 * @param query the query
	 * @param parameterMap the parameter map
	 * @param attributeMap the attribute map
	 * @throws ClassNotFoundException 
	 */
	static void appendClauseForAttributes(final StringBuilder query,
			final Map<String, Object> parameterMap, final Map<String, String> attributeMap) {
		for (final String columnName : attributeMap.keySet()) {
			final String value = attributeMap.get(columnName);
			final String[] valueArr = value.split(RAConstants.COLON);
			final String type = valueArr[0];
			final String values = valueArr[1];
			final List<Object> parameterList = CommonUtil.convertToList(values, type);
			
			if ("othercountriestraveled".equalsIgnoreCase(columnName)) {
				parameterMap.put("countries",parameterList);
			}  else if ("visitednetwork".equalsIgnoreCase(columnName)) {
				query.append(" and concat(cast(trip.visitedmcc as varchar),")
					.append(" concat('-',cast(trip.visitedmnc as varchar))) ")
					.append(" in (")
					.append(CommonUtil.covnertToCommaSeparatedString(values,type))
					.append(") ");
			}  else if ("homenetwork".equalsIgnoreCase(columnName)) {
				query.append(" and concat(cast(trip.homemcc as varchar),")
					.append(" concat('-',cast(trip.homemnc as varchar))) ")
					.append(" in (")
					.append(CommonUtil.covnertToCommaSeparatedString(values,type))
					.append(") ");
			} else {
				query.append(" and trip.").append(columnName).append(" in (")
					.append(CommonUtil.covnertToCommaSeparatedString(parameterList))
					.append(") ");
			}
			
		}
	}
	
}
