/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.enums.Relation;
import com.mobileum.roameranalytics.model.Filter;


/**
 * The Class QueryBuilder. Creates dynamic queries.
 *
 * @author sarvesh
 */
public class QueryBuilder {

	/**
	 * Creates query for getting all attributes for left panel
	 * @return query
	 */
	public static String queryForAttributes() {
		final StringBuilder query = new StringBuilder();
		
		query.append("select attr.id attrId, attr.attribute_name attrName, attr.module_id moduleId, ")
				.append(" attr.db_column db_column,  attr.column_type column_type, attr.chart_type chart_type, ")
				.append(" attrCat.categ_name catName, attrCat.categ_value catValue, attrCat.id catId ")
				.append(" from ").append(Relation.ATTRIBUTE).append(" attr left join ")
				.append(Relation.ATTRIBUTE_CATEGORY)
				.append(" attrCat on attr.id = attrCat.attr_id ")
				.append(" order by attr.display_order, attrCat.display_order");
		
		return query.toString();
	}

	/**
	 * Query for all countries.
	 *
	 * @return the string
	 */
	public static String queryForAllCountries() {
		final StringBuilder query = new StringBuilder();
		query.append(" select visitedcountry countryName, ")
			.append(" case when bordering = 'Distant' then 0 else 1 end bordering from ")
			.append(Relation.COUNTRY)
			.append(" where homecountry='Indonesia'")
			.append(" order by visitedcountry");
		return query.toString();
	}
	
	
	/**
	 * Populate query for other countries traveled.
	 *
	 * @param filter the filter
	 * @param query the query
	 * @param parameterMap the parameter map
	 */
	public static void populateQueryForOtherCountriesTraveled(final Filter filter, final StringBuilder query,
			final Map<String, Object> parameterMap) {
		query.append(" select distinct trip.visitedcountryname othercountry from ")
			.append(Relation.TRIP).append(" trip ")
			.append(" where trip.starttime >= :startDate and trip.endtime <= :endDate and trip.endtime != 0 ")
			.append(" and trip.homecountryname = :homeCountry and trip.roamtype = :roamType");
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname not in (:countriesNotIn)");
			parameterMap.put("countriesNotIn", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			query.append(" and trip.visitedcountryname not in (:excludedCountries) ");
			parameterMap.put("excludedCountries", Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		final Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
	}
	/**
	 * Populates query for trends chart.
	 *
	 * @param filter - filters selected
	 * @param query the query to be populated 
	 * @param parameterMap the parameter map - will have parameter and its value used in query
	 * @throws ClassNotFoundException 
	 */
	public static void populateQueryForTrends(final Filter filter, final StringBuilder query, final Map<String, Object> parameterMap)  {
		
		query.append(" select sum(1) imsicount, sum(triptime.mocallminutes) mocallminutes, ")
			.append(" sum(triptime.mtcallminutes) mtcallminutes, sum(triptime.mosmscount) mosmscount,")
			.append(" sum(triptime.uplink + triptime.downlink)/1048576.0  datausage, ")
			.append(" triptime.usagebintime usagebintime,trip.overalltripcategory overalltripcategory from ")
			.append(Relation.TRIP_TIME).append(" triptime inner join ").append(Relation.TRIP)
			.append(" trip on triptime.imsi = trip.imsi and triptime.tripstarttime = trip.starttime ")
			.append(" and trip.visitedcountryname = triptime.visitedcountryname ")
			.append(" where trip.starttime >= :startDate and trip.endtime <= :endDate and trip.endtime != 0 ")
			.append(" and trip.homecountryname = :homeCountry and trip.roamtype = :roamType");
		
		
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries) ");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			final List<String> countries = (List<String>) parameterMap.get("countriesNotIn"); 
			final List<String> parameterList = Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA));
			if (countries == null) {
				query.append(" and trip.visitedcountryname not in (:countriesNotIn) ");
				parameterMap.put("countriesNotIn",parameterList);
			} else {
				parameterList.addAll(countries);
			}

		}
		final Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
		query.append(" group by  triptime.usagebintime, trip.overalltripcategory ");
		query.append("  order by triptime.usagebintime ");
	}

	/**
	 * Populate query for microsegment chart.
	 *
	 * @param filter the filter
	 * @param query the query
	 * @param column the column
	 * @param columnType the column type
	 * @param parameterMap the parameter map
	 */
	public static void populateQueryForMicrosegmentChart(final Filter filter, final StringBuilder query, 
			final String column,  final Map<String, Object> parameterMap) {
		query.append(" select sum(1) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes,")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ");
		
		query.append(" trip.").append(column).append(" categoryValue from ")
			.append(Relation.TRIP).append(" trip ")
			.append(" where trip.starttime >= :startDate and trip.endtime <= :endDate and trip.endtime != 0 ")
			.append(" and trip.homecountryname = :homeCountry and trip.roamtype = :roamType");

		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries) ");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			query.append(" and trip.visitedcountryname not in (:excludedCountries) ");
			parameterMap.put("excludedCountries", Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);

		query.append(" group by trip.").append(column);
		query.append(" order by imsicount desc, mocallminutes desc, mtcallminutes desc, ")
		.append(" datausage desc ");
	}
	
	/**
	 * Populate query for network group chart.
	 *
	 * @param filter the filter
	 * @param query the query
	 * @param column the column
	 * @param columnType the column type
	 * @param parameterMap the parameter map
	 */
	public static void populateQueryForNetworkGroupChart(final Filter filter, final StringBuilder query, 
			final Map<String, Object> parameterMap) {
		query.append(" select sum(1) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" network.networkgrouplist networkGroup from ")
			.append(Relation.TRIP).append(" trip ").append(" inner join ")
			.append(Relation.TADIGNETWORK).append(" network ")
			.append(" on trip.visitedmcc = network.mcc and trip.visitedmnc = network.mnc ")
			.append(" where trip.starttime >= :startDate and trip.endtime <= :endDate and trip.endtime != 0 ")
			.append(" and trip.homecountryname = :homeCountry and trip.roamtype = :roamType "); 

		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries) ");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			query.append(" and trip.visitedcountryname not in (:excludedCountries) ");
			parameterMap.put("excludedCountries", Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);
		
		query.append(" group by network.networkgrouplist ");
		query.append(" order by imsicount desc, mocallminutes desc, mtcallminutes desc, ")
			.append("  datausage desc ");
	}
	
	
	/**
	 * Populate query for network group chart.
	 *
	 * @param filter the filter
	 * @param query the query
	 * @param column the column
	 * @param columnType the column type
	 * @param parameterMap the parameter map
	 */
	public static void populateQueryForOtherCountriesTraveledChart(final Filter filter, final StringBuilder query, 
			final Map<String, Object> parameterMap) {
		query.append(" select sum(1) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" trip.visitedcountryname country from ")
			.append(Relation.TRIP).append(" trip ")
			.append(" where trip.starttime >= :startDate and trip.endtime <= :endDate and trip.endtime != 0 ")
			.append(" and trip.homecountryname = :homeCountry and trip.roamtype = :roamType "); 

		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname not in (:countriesNotIn) ");
			parameterMap.put("countriesNotIn", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			final List<String> countries = (List<String>) parameterMap.get("countriesNotIn"); 
			final List<String> parameterList = Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA));
			if (countries == null) {
				query.append(" and trip.visitedcountryname not in (:countriesNotIn) ");
				parameterMap.put("countriesNotIn",parameterList);
			} else {
				final List<String> newList = new ArrayList<String>(countries);
				newList.addAll(parameterList);
				parameterMap.put("countriesNotIn",newList);
			}

		}
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);
		
		query.append(" group by trip.visitedcountryname ");
		query.append(" order by imsicount desc, mocallminutes , mtcallminutes , datausage");
	}
	
	public static String queryForLabelVsValue() {
		final StringBuilder query = new StringBuilder();
		query.append("select attr.attribute_name attrName, cat.categ_name catName, cat.categ_value catValue from ")
			.append(Relation.ATTRIBUTE)
			.append(" attr inner join ").append(Relation.ATTRIBUTE_CATEGORY).append(" cat  on ")
			.append(" attr.id = cat.attr_id ");
		return query.toString();
	}
	
	/**
	 * Query for distinct networks.
	 *
	 * @return the string
	 */
	public static String queryForDistinctNetworks() {
		final StringBuilder query = new StringBuilder();
		query.append("select distinct visitednetworkname from ").append(Relation.TRIP).append(" trip where ")
			.append(" trip.homecountryname = :homeCountry and trip.roamtype = :roamType order by visitednetworkname ");
		return query.toString();
	}
	
	/**
	 * Query for distinct networks.
	 *
	 * @return the string
	 */
	public static String queryForDistinctNetworkGroups() {
		final StringBuilder query = new StringBuilder();
		query.append("select distinct network_name , networkgrouplist from ").append(Relation.TADIGNETWORK)
			.append(" tadignetwork where network_name in (:networks) order by networkgrouplist");
		return query.toString();
	}
	
	
	/**
	 * Append clause for attributes.
	 *
	 * @param query the query
	 * @param parameterMap the parameter map
	 * @param attributeMap the attribute map
	 * @throws ClassNotFoundException 
	 */
	private static void appendClauseForAttributes(final StringBuilder query,
			final Map<String, Object> parameterMap, final Map<String, String> attributeMap) {
		int index = 0;
		for (final String columnName : attributeMap.keySet()) {
			final String value = attributeMap.get(columnName);
			final String[] valueArr = value.split(RAConstants.COLON);
			final String type = valueArr[0];
			final String values = valueArr[1];
			final List<Object> parameterList = CommonUtil.convertToList(values, type);
			if ("networkgroup".equals(columnName)) {
				query.append(" and network.network_name in (:networknames) ");
				parameterMap.put("networknames",parameterList);
			} else if ("visitedcountryname".equalsIgnoreCase(columnName)) {
				final List<Object> countries = (List<Object>)parameterMap.get("countries"); 
				if (countries == null) {
					query.append(" and trip.visitedcountryname in (:countries)");
					parameterMap.put("countries",parameterList);
				} else {
					final List<Object> newList = new ArrayList<Object>(countries);
					newList.addAll(parameterList);
					parameterMap.put("countries",newList);
				}
				
			} else {
				query.append(" and trip.").append(columnName).append(" in (:")
					.append(columnName + "" + index).append(") ");
				parameterMap.put(columnName + "" + index,parameterList);
				index++;
			}
			
		}
	}
	
	
	/**
	 * Populates query for trends chart.
	 *
	 * @param filter - filters selected
	 * @param query the query to be populated 
	 * @param parameterMap the parameter map - will have parameter and its value used in query
	 * @throws ClassNotFoundException 
	 */
	public static void populateQueryForRoamingStatistics(final Filter filter, final StringBuilder query, final Map<String, Object> parameterMap)  {
		
		query.append(" select visitedcountryname,sum(1) roamercount, sum(mocallminutes) mocallminutes, ")
			.append(" sum(mtcallminutes) mtcallminutes, sum(mosmscount) mosmscount,")
			.append(" sum(uplink + downlink)  datausage, ")
			.append(" sum(mocallminuteslocal) mocallminuteslocal, sum(mocallminuteshome) mocallminuteshome,sum(mocallminutesothers) mocallminutesother from ")
			.append(Relation.TRIP)
			.append(" where trip.starttime >= :startDate ")
			.append(" and trip.homecountryname = :homeCountry")
			.append(" and trip.endtime <= :endDate and trip.endtime != 0 and trip.roamtype = 'OUT' ");
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		if (!filter.getExcludedCountries().isEmpty()){
			query.append(" and trip.visitedcountryname not in (:excludedCountries) ");
			parameterMap.put("excludedCountries", Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		final Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
		query.append(" group by  visitedcountryname ");
		
	}
	public static void populateQueryForRoamingCategoryCount(final Filter filter, final StringBuilder query, final Map<String, Object> parameterMap)  {
		query.append(" select visitedcountryname, overalltripcategory as roamingcategory,count(imsi) roamercount  from ")
		.append(Relation.TRIP)
		.append(" where trip.starttime >= :startDate ")
		.append(" and trip.homecountryname = :homeCountry")
		.append(" and trip.endtime <= :endDate and trip.endtime != 0 and trip.roamtype = 'OUT' ");
		
		final Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		if (!filter.getExcludedCountries().isEmpty()){
			query.append(" and trip.visitedcountryname not in (:excludedCountries) ");
			parameterMap.put("excludedCountries", Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		query.append(" group by  overalltripcategory,visitedcountryname");
		
		query.append(" order by visitedcountryname,roamingcategory");
	}


	
}
