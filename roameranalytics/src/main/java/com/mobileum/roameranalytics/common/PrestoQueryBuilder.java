/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.mobileum.roameranalytics.enums.Relation;
import com.mobileum.roameranalytics.enums.RoamType;
import com.mobileum.roameranalytics.model.Filter;


/**
 * The Class QueryBuilder. Creates dynamic queries.
 *
 * @author sarvesh
 */
public class PrestoQueryBuilder {

	private static ResourceBundle resourseBundle = ResourceBundle.getBundle("application");
	
	/**
	 * Creates query for getting all attributes for left panel
	 * @return query
	 */
	public static String queryForAttributes(final String roamType) {
		final StringBuilder query = new StringBuilder();
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append("select attr.id attrId, attr.attribute_name attrName, attr.module_id moduleId, ")
				.append(" attr.db_column db_column,  attr.column_type column_type, attr.chart_type chart_type, ")
				.append(" attrCat.categ_name catName, attrCat.categ_value catValue, attrCat.id catId ")
				.append(" from ").append(Relation.ATTRIBUTE).append(" attr left join ")
				.append(Relation.ATTRIBUTE_CATEGORY)
				.append(" attrCat on attr.id = attrCat.attr_id ")
				.append(" order by attr.display_order, attrCat.display_order");
		} else {
			query.append("select attr.id attrId, attr.attribute_name attrName, attr.module_id moduleId, ")
				.append(" attr.db_column_in db_column,  attr.column_type column_type, attr.chart_type chart_type, ")
				.append(" attrCat.categ_name catName, attrCat.categ_value catValue, attrCat.id catId ")
				.append(" from ").append(Relation.ATTRIBUTE).append(" attr left join ")
				.append(Relation.ATTRIBUTE_CATEGORY)
				.append(" attrCat on attr.id = attrCat.attr_id ")
				.append(" order by attr.display_order, attrCat.display_order");
		}
		
		
		return query.toString();
	}

	/**
	 * Query for all countries.
	 *
	 * @return the string
	 */
	public static String queryForAllCountries(final String roamType) {
		final StringBuilder query = new StringBuilder();
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" select visitedcountry countryName, ")
				.append(" case when bordering = 'Distant' then 0 else 1 end bordering from ")
				.append(RAPropertyUtil.getProperty("common.table.country")).append(" order by visitedcountry");
		} else {
			query.append(" select visitedcountry countryName, ")
				.append(" case when bordering = 'Distant' then 0 else 1 end bordering from ")
				.append(RAPropertyUtil.getProperty("common.table.country")).append(" order by visitedcountry");
		}
		
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
			final Map<String, Object> parameterMap, final String roamType) {
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" select distinct trip.visitedcountryname othercountry from ")
				.append(RAPropertyUtil.getProperty("out.table.trip")).append(" trip ");
		} else {
			query.append(" select distinct trip.homecountryname othercountry from ")
			.append(RAPropertyUtil.getProperty("in.table.trip")).append(" trip ");
		}
		
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and trip.roamtype = '").append(roamType).append("' ");
		final List<String> exculdeCountryList = new ArrayList<String>();
		final List<String> selectedCountryList = new ArrayList<String>();
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		final Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
		appendClauseForOtherCountriesTraveled(query, parameterMap, roamType,
				exculdeCountryList, selectedCountryList);
	}

	/**
	 * @param query
	 * @param parameterMap
	 * @param roamType
	 * @param exculdeCountryList
	 * @param selectedCountryList
	 */
	private static void appendClauseForOtherCountriesTraveled(
			final StringBuilder query, final Map<String, Object> parameterMap,
			final String roamType, final List<String> exculdeCountryList,
			final List<String> selectedCountryList) {
		final List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
		}
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			if (!exculdeCountryList.isEmpty()) {
				query.append(" and trip.visitedcountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
					.append(")");
			}
			
			if (!selectedCountryList.isEmpty()) {
				query.append(" and trip.visitedcountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
					.append(")");
			}
			query.append(" order by trip.visitedcountryname ");
		} else {
			if (!exculdeCountryList.isEmpty()) {
				query.append(" and trip.homecountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
					.append(")");
			}
			
			if (!selectedCountryList.isEmpty()) {
				query.append(" and trip.homecountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
					.append(")");
			}
			query.append(" order by trip.homecountryname ");
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
	public static void populateQueryForTrends(final Filter filter, final StringBuilder query, final Map<String, Object> parameterMap, 
			final String roamType)  {
		
		query.append(" select count(trip.imsi) imsicount, sum(triptime.mocallminutes) mocallminutes, ")
			.append(" sum(triptime.mtcallminutes) mtcallminutes, sum(triptime.mosmscount) mosmscount,")
			.append(" sum(triptime.uplink + triptime.downlink)/1048576.0  datausage, ")
			.append(" triptime.usagebintime usagebintime,trip.overalltripcategory overalltripcategory from ");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(RAPropertyUtil.getProperty("out.table.triptime")).append(" triptime inner join ")
				.append(RAPropertyUtil.getProperty("out.table.trip"));
		} else {
			query.append(RAPropertyUtil.getProperty("in.table.triptime")).append(" triptime inner join ")
				.append(RAPropertyUtil.getProperty("in.table.trip"));
		}
		query.append(" trip on triptime.imsi = trip.imsi and triptime.tripstarttime = trip.starttime ");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" and trip.visitedcountryname = triptime.visitedcountryname ");
		} else {
			query.append(" and trip.homecountryname = triptime.homecountryname ");
		}
			
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and (triptime.usagebintime between ")
			.append(filter.getDateFrom()).append(" and ").append(filter.getDateTo())
			.append(") and trip.roamtype = '").append(roamType).append("' ");

		
		final List<String> exculdeCountryList = new ArrayList<String>();
		final List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		final Map<String, String> attributeMap = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, attributeMap);
		
		final List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
		}
		appendCountryClause(query, roamType, exculdeCountryList,
				selectedCountryList);
		
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
			final String column,  final Map<String, Object> parameterMap, final String roamType) {
		query.append(" select count(imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes,")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" trip.").append(column).append(" categoryValue from ");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(RAPropertyUtil.getProperty("out.table.trip")).append(" trip ");
		} else {
			query.append(RAPropertyUtil.getProperty("in.table.trip")).append(" trip ");
		}
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and trip.roamtype = '").append(roamType).append("' ");

		final List<String> exculdeCountryList = new ArrayList<String>();
		final List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);

		final List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
		}
		
		appendCountryClause(query, roamType, exculdeCountryList,
				selectedCountryList);
		
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
			final Map<String, Object> parameterMap, final String roamType) {
		query.append(" select count(trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" network.network_group networkGroup from ");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(RAPropertyUtil.getProperty("out.table.trip")).append(" trip ");
			query.append(" inner join ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork")).append(" network ");
			query.append(" on trip.visitedmcc = network.mcc and trip.visitedmnc = network.mnc ");
		} else {
			query.append(RAPropertyUtil.getProperty("in.table.trip")).append(" trip ");
			query.append(" inner join ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork")).append(" network ");
			query.append(" on trip.homemcc = network.mcc and trip.homemnc = network.mnc ");
		}
		
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and trip.roamtype = '").append(roamType).append("' ");

		final List<String> exculdeCountryList = new ArrayList<String>();
		final List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);
		
		final List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
		}
		
		appendCountryClause(query, roamType, exculdeCountryList,
				selectedCountryList);
		
		query.append(" group by network.network_group ");
		query.append(" order by imsicount desc, mocallminutes desc, mtcallminutes desc, ")
			.append("  datausage desc ");
	}

	/**
	 * @param query
	 * @param roamType
	 * @param exculdeCountryList
	 * @param selectedCountryList
	 */
	private static void appendCountryClause(final StringBuilder query,
			final String roamType, final List<String> exculdeCountryList,
			final List<String> selectedCountryList) {
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			if (!exculdeCountryList.isEmpty()) {
				query.append(" and trip.visitedcountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
					.append(")");
			}
			
			if (!selectedCountryList.isEmpty()) {
				query.append(" and trip.visitedcountryname in (")
					.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
					.append(")");
			}
		} else {
			if (!exculdeCountryList.isEmpty()) {
				query.append(" and trip.homecountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
					.append(")");
			}
			
			if (!selectedCountryList.isEmpty()) {
				query.append(" and trip.homecountryname in (")
					.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
					.append(")");
			}
		}
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
			final Map<String, Object> parameterMap, final String roamType) {
		query.append(" select count(trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ");
			if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
				query.append(" trip.visitedcountryname country from ");
				query.append(RAPropertyUtil.getProperty("out.table.trip")).append(" trip ");
			} else {
				query.append(" trip.homecountryname country from ");
				query.append(RAPropertyUtil.getProperty("in.table.trip")).append(" trip ");
			}
			
			query.append(" where trip.starttime >= ").append(filter.getDateFrom())
				.append(" and trip.endtime <= ").append(filter.getDateTo())
				.append(" and trip.endtime != 0 ")
				.append(" and trip.roamtype = '").append(roamType).append("' ");

		final List<String> exculdeCountryList = new ArrayList<String>();
		final List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);
		
		final List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
		}
		selectedCountryList.addAll(exculdeCountryList);
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			if (!selectedCountryList.isEmpty()) {
				query.append(" and trip.visitedcountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
					.append(")");
			}
			query.append(" group by trip.visitedcountryname ");
		} else {
			if (!selectedCountryList.isEmpty()) {
				query.append(" and trip.homecountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
					.append(")");
			}
			query.append(" group by trip.homecountryname ");
		}
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
//		query.append("select distinct visitednetworkname from ").append(Relation.TRIP).append(" trip where ")
//			.append(" trip.homecountryname = '").append(resourseBundle.getString("home.country"))
//			.append("' and trip.roamtype = '").append(resourseBundle.getString("roam.type")) .append("' order by visitednetworkname ");
		return query.toString();
	}
	
	/**
	 * Query for distinct networks.
	 *
	 * @return the string
	 */
	public static String queryForDistinctNetworkGroups(final String roamType) {
		final StringBuilder query = new StringBuilder();
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append("select distinct network_name , network_group from ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork"))
				.append(" tadignetwork inner join (")
				.append(" select distinct trip.visitednetworkname as network from ")
				.append(RAPropertyUtil.getProperty("out.table.trip"))
				.append(" trip where trip.roamtype = '").append(roamType).append("' ") 
				.append(" ) T on T.network = tadignetwork.network_name ")
				.append(" order by network_name ");
		} else {
			query.append("select distinct network_name , network_group from ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork"))
				.append(" tadignetwork inner join (")
				.append(" select distinct trip.homenetworkname as network from ")
				.append(RAPropertyUtil.getProperty("in.table.trip"))
				.append(" trip where trip.roamtype = '").append(roamType).append("' ") 
				.append(" ) T on T.network = tadignetwork.network_name ")
			.append(" order by network_name ");
		}
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
	public static void populateQueryForRoamingStatistics(final Filter filter, final StringBuilder query, 
			final Map<String, Object> parameterMap, final String roamType)  {
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" select visitedcountryname as visitedcountryname,count(imsi) roamercount, sum(mocallminutes) mocallminutes, ");
		} else {
			query.append(" select homecountryname as visitedcountryname,count(imsi) roamercount, sum(mocallminutes) mocallminutes, ");
		}
		query.append(" sum(mtcallminutes) mtcallminutes, sum(mosmscount) mosmscount,")
			.append(" sum(uplink + downlink)  datausage, ")
			.append(" sum(mocallminuteslocal) mocallminuteslocal, sum(mocallminuteshome) mocallminuteshome,sum(mocallminutesothers) mocallminutesother from ");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(RAPropertyUtil.getProperty("out.table.trip")).append(" trip ");
		} else {
			query.append(RAPropertyUtil.getProperty("in.table.trip")).append(" trip ");
		}
		
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and trip.roamtype = '").append(roamType).append("' ");
		
		final List<String> exculdeCountryList = new ArrayList<String>();
		final List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);

		final List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
		}
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			if (!exculdeCountryList.isEmpty()) {
				query.append(" and trip.visitedcountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
					.append(")");
			}
			
			if (!selectedCountryList.isEmpty()) {
				query.append(" and trip.visitedcountryname in (")
					.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
					.append(")");
			}
			query.append(" group by  visitedcountryname ");
		} else {
			if (!exculdeCountryList.isEmpty()) {
				query.append(" and trip.homecountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
					.append(")");
			}
			
			if (!selectedCountryList.isEmpty()) {
				query.append(" and trip.homecountryname in (")
					.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
					.append(")");
			}
			query.append(" group by  homecountryname ");
		}
		
		
	}
	public static void populateQueryForRoamingCategoryCount(final Filter filter, final StringBuilder query,
			final Map<String, Object> parameterMap, final String roamType)  {
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" select visitedcountryname as visitedcountryname, overalltripcategory as roamingcategory,count(imsi) roamercount  from ");
		} else {
			query.append(" select homecountryname as visitedcountryname, overalltripcategory as roamingcategory,count(imsi) roamercount  from ");
		}
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(RAPropertyUtil.getProperty("out.table.trip")).append(" trip ");
		} else {
			query.append(RAPropertyUtil.getProperty("in.table.trip")).append(" trip ");
		}
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and trip.roamtype = '").append(roamType).append("' ");
		
		final List<String> exculdeCountryList = new ArrayList<String>();
		final List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
		if (!filter.getExcludedCountries().isEmpty()){
			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
		}
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);

		final List<String> countriesIn = (List<String>) parameterMap.get("countries");
		if (countriesIn != null && !countriesIn.isEmpty()) {
			selectedCountryList.addAll(countriesIn);
		}
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			if (!exculdeCountryList.isEmpty()) {
				query.append(" and trip.visitedcountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
					.append(")");
			}
			
			if (!selectedCountryList.isEmpty()) {
				query.append(" and trip.visitedcountryname in (")
					.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
					.append(")");
			}
			query.append(" group by  overalltripcategory,visitedcountryname");
			query.append(" order by visitedcountryname,roamingcategory");
		} else {
			if (!exculdeCountryList.isEmpty()) {
				query.append(" and trip.homecountryname not in (")
					.append(CommonUtil.covnertToCommaSeparatedString(exculdeCountryList))
					.append(")");
			}
			
			if (!selectedCountryList.isEmpty()) {
				query.append(" and trip.homecountryname in (")
					.append(CommonUtil.covnertToCommaSeparatedString(selectedCountryList))
					.append(")");
			}
			query.append(" group by  overalltripcategory,homecountryname");
			query.append(" order by homecountryname,roamingcategory");
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
	private static void appendClauseForAttributes(final StringBuilder query,
			final Map<String, Object> parameterMap, final Map<String, String> attributeMap) {
		for (final String columnName : attributeMap.keySet()) {
			final String value = attributeMap.get(columnName);
			final String[] valueArr = value.split(RAConstants.COLON);
			final String type = valueArr[0];
			final String values = valueArr[1];
			final List<Object> parameterList = CommonUtil.convertToList(values, type);
			if ("networkgroup".equals(columnName)) {
				query.append(" and network.network_name in (").append(CommonUtil.covnertToCommaSeparatedString(parameterList))
					.append(") ");
				parameterMap.put("networknames",parameterList);
			} else if ("othercountriestraveled".equalsIgnoreCase(columnName)) {
				parameterMap.put("countries",parameterList);
			} else {
				query.append(" and trip.").append(columnName).append(" in (")
					.append(CommonUtil.covnertToCommaSeparatedString(parameterList))
					.append(") ");
			}
			
		}
	}
	
}
