/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.enums.FilterColumn;
import com.mobileum.roameranalytics.enums.Relation;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.repository.Criteria;
import com.mobileum.roameranalytics.repository.SelectQuery;
import com.mobileum.roameranalytics.repository.Table;

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
		StringBuilder query = new StringBuilder();
		
		query.append("select attr.id attrId, attr.attribute_name attrName, attr.module_id moduleId, ")
				.append(" attr.db_column db_column,  attr.column_type column_type, attr.chart_type chart_type, ")
				.append(" attrCat.categ_name catName, attrCat.categ_value catValue, attrCat.id catId ")
				.append(" from ").append(Relation.ATTRIBUTE).append(" attr left join ")
				.append(Relation.ATTRIBUTE_CATEGORY)
				.append(" attrCat on attr.id = attrCat.attr_id ")
				.append(" order by attr.display_order, attrCat.display_order");
		
		return query.toString();
	}
	

	public static String queryForHeatMap() {
				
		SelectQuery selectQuery=new SelectQuery();
		Table table=new Table("roaming_usage","ru");
		table.addColumnsToSelect("mo_local");
		table.addColumnsToSelect("mo_out");
		table.addGroupFunctions("sum(mo_local) mo_local");
		
		Table table1=new Table("trip","tp");
		table1.addColumnsToSelect("stayduration");
	
		
		selectQuery.addTable(table);
		selectQuery.addTable(table1);
		
		selectQuery.addCriteria(table, "visited_country", Criteria.EQUALS, "NG");
		selectQuery.addJoin(table,"visited_country", table1, "visited_country");
		List<String> roamertype=new ArrayList();
		
		roamertype.add("silent");
		roamertype.add("premium");
		
		selectQuery.addCriteria(table, "roamer_type", Criteria.IN, roamertype);
		selectQuery.addGroupByColumn(table, "country");
		selectQuery.addGroupByColumn(table, "roamer_type");
		
		
		
		 return selectQuery.toString();
	}	

	/**
	 * Query for all countries.
	 *
	 * @return the string
	 */
	public static String queryForAllCountries() {
		StringBuilder query = new StringBuilder();
		query.append(" select visitedcountry countryName, ")
			.append(" case when bordering = 'Distant' then 0 else 1 end bordering from ")
			.append(Relation.COUNTRY).append(" order by visitedcountry");
		return query.toString();
	}
	
	/**
	 * Populates query for trends chart.
	 *
	 * @param filter - filters selected
	 * @param query the query to be populated 
	 * @param parameterMap the parameter map - will have parameter and its value used in query
	 * @throws ClassNotFoundException 
	 */
	public static void populateQueryForTrends(Filter filter, StringBuilder query, Map<String, Object> parameterMap)  {
		
		query.append(" select sum(1) imsicount, sum(triptime.mocallminutes) mocallminutes, ")
			.append(" sum(triptime.mtcallminutes) mtcallminutes, sum(triptime.mosmscount) mosmscount,")
			.append(" sum(triptime.uplink + triptime.downlink)  datausage, ")
			.append(" triptime.usagebintime usagebintime,trip.overalltripcategory overalltripcategory from ")
			.append(Relation.TRIP_TIME).append(" triptime inner join ").append(Relation.TRIP)
			.append(" trip on triptime.imsi = trip.imsi and triptime.tripstarttime = trip.starttime ")
			.append(" where trip.starttime >= :startDate and trip.endtime <= :endDate and trip.endtime != 0 ")
			.append(" and trip.homecountryname = :homeCountry and trip.roamtype = :roamType");
		
		Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
		// overriding temporary filters
		Map<Integer, String> tempAttributeMap = filter.getTempAttributes();
		appendClauseForTempFitlers(query, parameterMap, tempAttributeMap);
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		query.append(" group by  triptime.usagebintime, trip.overalltripcategory ");
		query.append("  order by triptime.usagebintime ");
	}

	/**
	 * Populate network query.
	 *
	 * @param filter the filter selected
	 * @param query the query to be populated
	 * @param parameterMap the parameter map to be used at time of replacing named parameters
	 */
	public static void populateNetworkQuery(Filter filter, StringBuilder query, Map<String, Object> parameterMap) {
		query.append(" select sum(1) imsicount, trip.visitedmnc visitedmnc  from ")
			.append(Relation.TRIP).append(" trip ")
			.append(" where trip.starttime >= :startDate and trip.endtime <= :endDate and trip.endtime != 0 ")
			.append(" and trip.homecountryname = :homeCountry and trip.roamtype = :roamType");
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		query.append(" group by trip.visitedmnc ");
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
	public static void populateQueryForMicrosegmentChart(Filter filter, StringBuilder query, 
			String column,  Map<String, Object> parameterMap) {
		query.append(" select sum(1) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes,")
			.append(" sum(trip.uplink + trip.downlink)  datausage, ");
		
		query.append(" trip.").append(column).append(" categoryValue from ")
			.append(Relation.TRIP).append(" trip ")
			.append(" where trip.starttime >= :startDate and trip.endtime <= :endDate and trip.endtime != 0 ")
			.append(" and trip.homecountryname = :homeCountry and trip.roamtype = :roamType");

		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries) ");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		Map<String,String> filterParameters = filter.getSelectedAttributes();
		for (String columnName : filterParameters.keySet()) {
			String value = filterParameters.get(columnName);
			String[] valueArr = value.split(RAConstants.COLON);
			String type = valueArr[0];
			String values = valueArr[1];
			List<Object> parameterList = CommonUtil.convertToList(values, type);
			query.append(" and trip.").append(columnName).append(" in (:").append(columnName).append(") ");
			parameterMap.put(columnName,parameterList );
		}

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
	public static void populateQueryForNetworkGroupChart(Filter filter, StringBuilder query, 
			Map<String, Object> parameterMap) {
		query.append(" select sum(1) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)  datausage, ")
			.append(" network.network_group networkGroup from ")
			.append(Relation.TRIP).append(" trip ").append(" inner join ")
			.append(Relation.TADIGNETWORK).append(" network ")
			.append(" on trip.visitedmcc = network.mcc and trip.visitedmnc = network.mnc ")
			.append(" where trip.starttime >= :startDate and trip.endtime <= :endDate and trip.endtime != 0 ")
			.append(" and trip.homecountryname = :homeCountry and trip.roamtype = :roamType "); 

		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries) ");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		Map<String,String> filterParameters = filter.getSelectedAttributes();
		for (String columnName : filterParameters.keySet()) {
			String value = filterParameters.get(columnName);
			String[] valueArr = value.split(RAConstants.COLON);
			String type = valueArr[0];
			String values = valueArr[1];
			List<Object> parameterList = CommonUtil.convertToList(values, type);
			if ("networkgroup".equals(columnName)) {
				query.append(" and network.network_name in (:networknames) ");
				parameterMap.put("networknames",parameterList);
			} else {
				query.append(" and trip.").append(columnName).append(" in (:").append(columnName).append(") ");
				parameterMap.put(columnName,parameterList);
			}
			
		}

		query.append(" group by network.network_group ");
		query.append(" order by imsicount desc, mocallminutes desc, mtcallminutes desc, ")
			.append("  datausage desc ");
	}
	
	
	public static String queryForLabelVsValue() {
		StringBuilder query = new StringBuilder();
		query.append("select attr.attribute_name attrName, cat.categ_name catName, cat.categ_value catValue from ")
			.append(Relation.ATTRIBUTE)
			.append(" attr inner join ").append(Relation.ATTRIBUTE_CATEGORY).append(" cat  on ")
			.append(" attr.id = cat.attr_id ");
		return query.toString();
	}
	
	/**
	 * Populate Roaming category query for microsegment.
	 *
	 * @param filter the filter selected
	 * @param query the query to be populated
	 * @param parameterMap the parameter map to be used at time of replacing named parameters
	 */
	public static void populateRoamerTypeQuery(Filter filter, StringBuilder query, Map<String, Object> parameterMap) {
		query.append(" select sum(1) imsicount, trip.overalltripcategory tripcategory  from ")
			.append(Relation.TRIP)
			.append(" where trip.starttime >= :startDate ")
			.append(" and trip.endtime <= :endDate ");
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		query.append(" group by trip.overalltripcategory ");
	}
	
	/**
	 * Populate domestic ARPU query for microsegment.
	 *
	 * @param filter the filter selected
	 * @param query the query to be populated
	 * @param parameterMap the parameter map to be used at time of replacing named parameters
	 */
	public static void populateARPUQuery(Filter filter, StringBuilder query, Map<String, Object> parameterMap) {
		query.append(" select sum(1) imsicount, trip.overalldomesticcategory domcategory  from ")
			.append(Relation.TRIP)
			.append(" where trip.starttime >= :startDate ")
			.append(" and trip.endtime <= :endDate ");
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		query.append(" group by trip.overalldomesticcategory ");
	}

	
	/**
	 * Populate payment type query for microsegment.
	 *
	 * @param filter the filter selected
	 * @param query the query to be populated
	 * @param parameterMap the parameter map to be used at time of replacing named parameters
	 */
	public static void populatePaymentTypeQuery(Filter filter, StringBuilder query, Map<String, Object> parameterMap) {
		query.append(" select sum(1) imsicount, trip.chargingplan chargingplan  from ")
			.append(Relation.TRIP)
			.append(" where trip.starttime >= :startDate ")
			.append(" and trip.endtime <= :endDate ");
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		query.append(" group by trip.chargingplan ");
	}
	
	/**
	 * Populate device type query for microsegment.
	 *
	 * @param filter the filter selected
	 * @param query the query to be populated
	 * @param parameterMap the parameter map to be used at time of replacing named parameters
	 */
	public static void populateDeviceTypeQuery(Filter filter, StringBuilder query, Map<String, Object> parameterMap) {
		query.append(" select sum(1) imsicount, trip.devicename devicetype  from ")
			.append(Relation.TRIP)
			.append(" where trip.starttime >= :startDate ")
			.append(" and trip.endtime <= :endDate ");
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		query.append(" group by trip.devicename ");
	}
	
	
	/**
	 * Query for distinct networks.
	 *
	 * @return the string
	 */
	public static String queryForDistinctNetworks() {
		StringBuilder query = new StringBuilder();
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
		StringBuilder query = new StringBuilder();
		query.append("select distinct network_name , network_group from ").append(Relation.TADIGNETWORK)
			.append(" tadignetwork where network_name in (:networks) order by network_group");
		return query.toString();
	}
	
	
	/**
	 * Append clause for temp fitlers.
	 *
	 * @param query the query
	 * @param parameterMap the parameter map
	 * @param tempAttributeMap the temp attribute map
	 */
	private static void appendClauseForTempFitlers(StringBuilder query,
			Map<String, Object> parameterMap,
			Map<Integer, String> tempAttributeMap) {
		for (Integer attrInd : tempAttributeMap.keySet()) {
			if (FilterColumn.ROAMING_CATEGEGORY.getInd() == attrInd) {
				if (!parameterMap.containsKey("tripCategory")) {
					query.append(" and OVERALLTRIPCATEGORY in (:tripCategory) ");
				}
				//parameterMap.put("tripCategory", CommonUtil.convertToList(tempAttributeMap.get(attrInd)));
			} else if (FilterColumn.PAYMENT_TYPE.getInd() == attrInd) {
				int paymentType = Integer.parseInt(tempAttributeMap.get(attrInd));
				if (!parameterMap.containsKey("chargePlan")) {
					query.append(" and CHARGINGPLAN = :chargePlan ");
				}
				parameterMap.put("chargePlan", paymentType);
			} else if (FilterColumn.DOMESTIC_ARPU.getInd() == attrInd) {
				if (!parameterMap.containsKey("arpu")) {
					query.append(" and OVERALLDOMESTICCATEGORY in (:arpu) ");
				}
				//parameterMap.put("arpu", CommonUtil.convertToList(tempAttributeMap.get(attrInd)));
			}
		}
	}


	/**
	 * Append clause for attributes.
	 *
	 * @param query the query
	 * @param parameterMap the parameter map
	 * @param attributeMap the attribute map
	 * @throws ClassNotFoundException 
	 */
	private static void appendClauseForAttributes(StringBuilder query,
			Map<String, Object> parameterMap, Map<String, String> attributeMap) {
		for (String key : attributeMap.keySet()) {
			String[] valueArr = attributeMap.get(key).split(RAConstants.COLON);
			String valueType = valueArr[0];
			String values = valueArr[1];
			query.append(" and trip.").append(key).append(" in (:").append(key).append( ")");
			parameterMap.put(key, CommonUtil.convertToList(values,valueType));
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
	public static void populateQueryForRoamingStatistics(Filter filter, StringBuilder query, Map<String, Object> parameterMap)  {
		
		query.append(" select visitedcountryname,sum(1) roamercount, sum(mocallminutes) mocallminutes, ")
			.append(" sum(mtcallminutes) mtcallminutes, sum(mosmscount) mosmscount,")
			.append(" sum(uplink + downlink)  datausage, ")
			.append(" sum(mocallminuteslocal) mocallminuteslocal, sum(mocallminuteshome) mocallminuteshome,sum(mocallminutesothers) mocallminutesother from ")
			.append(Relation.TRIP)
			.append(" where trip.starttime >= :startDate ")
			.append(" and trip.homecountryname = :homeCountry")
			.append(" and trip.endtime <= :endDate and trip.endtime != 0 and trip.roamtype = 'OUT' ");
		
		Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
		// overriding temporary filters
		Map<Integer, String> tempAttributeMap = filter.getTempAttributes();
		appendClauseForTempFitlers(query, parameterMap, tempAttributeMap);
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		query.append(" group by  visitedcountryname ");
		
	}
	public static void populateQueryForRoamingCategoryCount(Filter filter, StringBuilder query, Map<String, Object> parameterMap)  {
		query.append(" select visitedcountryname, overalltripcategory as roamingcategory,count(imsi) roamercount  from ")
		.append(Relation.TRIP)
		.append(" where trip.starttime >= :startDate ")
		.append(" and trip.homecountryname = :homeCountry")
		.append(" and trip.endtime <= :endDate and trip.endtime != 0 and trip.roamtype = 'OUT' ");
		
		Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
		// overriding temporary filters
		Map<Integer, String> tempAttributeMap = filter.getTempAttributes();
		appendClauseForTempFitlers(query, parameterMap, tempAttributeMap);
		
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
			parameterMap.put("countries", Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		}
		
		query.append(" group by  overalltripcategory,visitedcountryname");
		
		query.append(" order by visitedcountryname,roamingcategory");
	}


	
}
