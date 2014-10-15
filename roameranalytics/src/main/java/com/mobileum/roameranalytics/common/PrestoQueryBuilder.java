/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.mobileum.roameranalytics.enums.Relation;
import com.mobileum.roameranalytics.model.Filter;


/**
 * The Class QueryBuilder. Creates dynamic queries.
 *
 * @author sarvesh
 */
@Component
public class PrestoQueryBuilder {

	private static ResourceBundle resourseBundle = ResourceBundle.getBundle("application");
	
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
	 * Populate query for other countries traveled.
	 *
	 * @param filter the filter
	 * @param query the query
	 * @param parameterMap the parameter map
	 */
	public static void populateQueryForOtherCountriesTraveled(Filter filter, StringBuilder query,
			Map<String, Object> parameterMap) {
		query.append(" select distinct trip.visitedcountryname othercountry from ")
			.append(Relation.TRIP).append(" trip ")
			.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and trip.homecountryname = '").append(resourseBundle.getString("home.country"))
			.append("' and trip.roamtype = '").append(resourseBundle.getString("roam.type")).append("' ");
		List<String> exculdeCountryList = new ArrayList<String>();
		List<String> selectedCountryList = new ArrayList<String>();
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
		if (!exculdeCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname not in (")
				.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
				.append(")");
		}
		
		List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
			
		}
		
		if (!selectedCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname not in (")
				.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
				.append(")");
		}
		query.append(" order by trip.visitedcountryname ");
		
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
		
		query.append(" select count(trip.imsi) imsicount, sum(triptime.mocallminutes) mocallminutes, ")
			.append(" sum(triptime.mtcallminutes) mtcallminutes, sum(triptime.mosmscount) mosmscount,")
			.append(" sum(triptime.uplink + triptime.downlink)/1048576.0  datausage, ")
			.append(" triptime.usagebintime usagebintime,trip.overalltripcategory overalltripcategory from ")
			.append(Relation.TRIP_TIME).append(" triptime inner join ").append(Relation.TRIP)
			.append(" trip on triptime.imsi = trip.imsi and triptime.tripstarttime = trip.starttime ")
			.append(" and trip.visitedcountryname = triptime.visitedcountryname ")
			.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and (triptime.usagebintime between ").append(filter.getDateFrom()).append(" and ").append(filter.getDateTo())
			.append(" ) and trip.homecountryname = '").append(resourseBundle.getString("home.country"))
			.append("' and trip.roamtype = '").append(resourseBundle.getString("roam.type")).append("' ");

		
		List<String> exculdeCountryList = new ArrayList<String>();
		List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
		if (!exculdeCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname not in (")
				.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
				.append(")");
		}
		
		List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
			
		}
		
		if (!selectedCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname in (")
				.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
				.append(")");
		}
		
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
	public static void populateQueryForMicrosegmentChart(Filter filter, StringBuilder query, 
			String column,  Map<String, Object> parameterMap) {
		query.append(" select count(imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes,")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" trip.").append(column).append(" categoryValue from ")
			.append(Relation.TRIP).append(" trip ")
			.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and trip.homecountryname = '").append(resourseBundle.getString("home.country"))
			.append("' and trip.roamtype = '").append(resourseBundle.getString("roam.type")).append("' ");

		List<String> exculdeCountryList = new ArrayList<String>();
		List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);

		if (!exculdeCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname not in (")
				.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
				.append(")");
		}
		
		List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
			
		}
		
		if (!selectedCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname in (")
				.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
				.append(")");
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
		query.append(" select count(trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" network.network_group networkGroup from ")
			.append(Relation.TRIP).append(" trip ").append(" inner join ")
			.append(Relation.TADIGNETWORK).append(" network ")
			.append(" on trip.visitedmcc = network.mcc and trip.visitedmnc = network.mnc ")
			.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and trip.homecountryname = '").append(resourseBundle.getString("home.country"))
			.append("' and trip.roamtype = '").append(resourseBundle.getString("roam.type")).append("' ");

		List<String> exculdeCountryList = new ArrayList<String>();
		List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);
		
		if (!exculdeCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname not in (")
				.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
				.append(")");
		}
		
		List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
			
		}
		
		if (!selectedCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname in (")
				.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
				.append(")");
		}
		
		query.append(" group by network.network_group ");
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
	public static void populateQueryForOtherCountriesTraveledChart(Filter filter, StringBuilder query, 
			Map<String, Object> parameterMap) {
		query.append(" select count(trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" trip.visitedcountryname country from ")
			.append(Relation.TRIP).append(" trip ")
			.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and trip.homecountryname = '").append(resourseBundle.getString("home.country"))
			.append("' and trip.roamtype = '").append(resourseBundle.getString("roam.type")).append("' ");

		List<String> exculdeCountryList = new ArrayList<String>();
		List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);
		
		List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
		}
		selectedCountryList.addAll(exculdeCountryList);
		if (!selectedCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname not in (")
				.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
				.append(")");
		}
		query.append(" group by trip.visitedcountryname ");
		query.append(" order by imsicount desc, mocallminutes , mtcallminutes , datausage");
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
	 * Query for distinct networks.
	 *
	 * @return the string
	 */
	public static String queryForDistinctNetworks() {
		StringBuilder query = new StringBuilder();
		query.append("select distinct visitednetworkname from ").append(Relation.TRIP).append(" trip where ")
			.append(" trip.homecountryname = '").append(resourseBundle.getString("home.country"))
			.append("' and trip.roamtype = '").append(resourseBundle.getString("roam.type")) .append("' order by visitednetworkname ");
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
			.append(" tadignetwork inner join (")
			.append(" select distinct trip.visitednetworkname as network from ")
			.append(Relation.TRIP)
			.append(" trip where trip.homecountryname = '").append(resourseBundle.getString("home.country"))
			.append("' and trip.roamtype = '").append(resourseBundle.getString("roam.type" )).append("' ") 
			.append(" ) T on T.network = tadignetwork.network_name ")
			.append(" order by network_name ");
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
	public static void populateQueryForRoamingStatistics(Filter filter, StringBuilder query, Map<String, Object> parameterMap)  {
		
		query.append(" select visitedcountryname,count(imsi) roamercount, sum(mocallminutes) mocallminutes, ")
			.append(" sum(mtcallminutes) mtcallminutes, sum(mosmscount) mosmscount,")
			.append(" sum(uplink + downlink)  datausage, ")
			.append(" sum(mocallminuteslocal) mocallminuteslocal, sum(mocallminuteshome) mocallminuteshome,sum(mocallminutesothers) mocallminutesother from ")
			.append(Relation.TRIP).append(" trip ")
			.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and trip.homecountryname = '").append(resourseBundle.getString("home.country"))
			.append("' and trip.roamtype = '").append(resourseBundle.getString("roam.type")).append("' ");
		
		List<String> exculdeCountryList = new ArrayList<String>();
		List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);

		if (!exculdeCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname not in (")
				.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
				.append(")");
		}
		
		List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
			
		}
		
		if (!selectedCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname in (")
				.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
				.append(")");
		}
		query.append(" group by  visitedcountryname ");
		
	}
	public static void populateQueryForRoamingCategoryCount(Filter filter, StringBuilder query, Map<String, Object> parameterMap)  {
		query.append(" select visitedcountryname, overalltripcategory as roamingcategory,count(imsi) roamercount  from ")
		.append(Relation.TRIP).append(" trip ")
		.append(" where trip.starttime >= ").append(filter.getDateFrom())
		.append(" and trip.endtime <= ").append(filter.getDateTo())
		.append(" and trip.endtime != 0 ")
		.append(" and trip.homecountryname = '").append(resourseBundle.getString("home.country"))
		.append("' and trip.roamtype = '").append(resourseBundle.getString("roam.type")).append("' ");
		
		List<String> exculdeCountryList = new ArrayList<String>();
		List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);

		if (!exculdeCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname not in (")
				.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
				.append(")");
		}
		
		List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
			
		}
		
		if (!selectedCountryList.isEmpty()) {
			query.append(" and trip.visitedcountryname in (")
				.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
				.append(")");
		}
		
		query.append(" group by  overalltripcategory,visitedcountryname");
		query.append(" order by visitedcountryname,roamingcategory");
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
		for (String columnName : attributeMap.keySet()) {
			String value = attributeMap.get(columnName);
			String[] valueArr = value.split(RAConstants.COLON);
			String type = valueArr[0];
			String values = valueArr[1];
			List<Object> parameterList = CommonUtil.convertToList(values, type);
			if ("networkgroup".equals(columnName)) {
				query.append(" and network.network_name in (").append(CommonUtil.covnertToCommaSeparatedString(parameterList))
					.append(") ");
				parameterMap.put("networknames",parameterList);
			} else if ("visitedcountryname".equalsIgnoreCase(columnName)) {
				List<Object> countries = (List<Object>)parameterMap.get("countries"); 
				if (countries == null) {
					parameterMap.put("countries",parameterList);
				} else {
					List<Object> newList = new ArrayList<Object>(countries);
					newList.addAll(parameterList);
					parameterMap.put("countries",newList);
				}
				
			} else {
				query.append(" and trip.").append(columnName).append(" in (")
					.append(CommonUtil.covnertToCommaSeparatedString(parameterList))
					.append(") ");
			}
			
		}
	}
	
}
