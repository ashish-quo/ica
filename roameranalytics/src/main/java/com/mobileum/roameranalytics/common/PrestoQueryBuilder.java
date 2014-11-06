/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.enums.Relation;
import com.mobileum.roameranalytics.enums.RoamType;
import com.mobileum.roameranalytics.model.Filter;


/**
 * The Class QueryBuilder. Creates dynamic queries.
 *
 * @author sarvesh
 */
public class PrestoQueryBuilder {

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
		final String countryIB = RAPropertyUtil.getProperty("common.table.country");
		final String countryRelationIB = RAPropertyUtil.getProperty("common.table.country.relation");
		final String tadigNetworkIB = RAPropertyUtil.getProperty("common.table.tadignetwork");
		final String gdpThreshold = RAPropertyUtil.getProperty("low.gdp.threshold");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" select distinct countryIB.country countryName, countryIB.countryId countryId, ")
				.append(" countryRelationIB.isBordering bordering, ")
				.append(" countryIB.IsLeisureDestination leisure, ")
				.append(" countryIB.IsPremiumLeisureDestination leisurePremium, ")
				.append(" case when countryIB.gdp < ").append(gdpThreshold).append(" then 1 else 0 end lowGDP  ")
				.append(" from ").append(tadigNetworkIB).append(" tadigNetworkIB ")
				.append(" inner join ").append(countryIB).append(" countryIB ")
				.append(" on countryIB.countryid = tadigNetworkIB.countryid ")
				.append(" inner join ").append(countryRelationIB).append(" countryRelationIB ")
				.append(" on countryRelationIB.visitedcountryid = countryIB.countryid ")
				.append(" where tadigNetworkIB.mcc in (select distinct visitedmcc from ")
				.append(RAPropertyUtil.getProperty("out.table.trip"))
				.append(" ) and countryRelationIB.homecountryid in (select distinct countryid from ")
				.append(tadigNetworkIB).append(" where mcc in (select  homemcc from ")
				.append(RAPropertyUtil.getProperty("out.table.trip")).append(" limit 1) limit 1)  ");
		} else {
			query.append(" select distinct  countryIB.country countryName, countryIB.countryId countryId, ")
				.append(" countryRelationIB.isBordering bordering, ")
				.append(" countryIB.IsLeisureDestination leisure, ")
				.append(" countryIB.IsPremiumLeisureDestination leisurePremium, ")
				.append(" case when countryIB.gdp < 10000 then 1 else 0 end lowGDP  ")
				.append(" from  ").append(tadigNetworkIB).append(" tadigNetworkIB ")
				.append(" inner join ").append(countryIB).append(" countryIB ")
				.append(" on countryIB.countryid = tadigNetworkIB.countryid ")
				.append(" inner join ").append(countryRelationIB).append(" countryRelationIB ")
				.append(" on countryRelationIB.homecountryid = countryIB.countryid ")
				.append(" where tadigNetworkIB.mcc in (select distinct homemcc from ")
				.append(RAPropertyUtil.getProperty("in.table.trip"))
				.append(" ) and countryRelationIB.visitedcountryid in (select countryid from ")
				.append(tadigNetworkIB).append(" where mcc in (select  visitedmcc from ")
				.append(RAPropertyUtil.getProperty("in.table.trip")).append(" limit 1) limit 1) ");
		}
		query.append(" order by countryName ");
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
			.append(" and trip.endtime != 0 ");
		final List<String> exculdeCountryList = new ArrayList<String>();
		final List<String> selectedCountryList = new ArrayList<String>();
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
//		if (!filter.getExcludedCountries().isEmpty()){
//			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
//		}
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
	public static void populateQueryForTrends(final Filter filter, final StringBuilder query, 
			final Map<String, Object> parameterMap, final String roamType)  {
		
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
			query.append(" and trip.visitedmcc = triptime.visitedmcc and trip.visitedmnc = triptime.visitedmnc ");
		} else {
			query.append(" and trip.homemcc = triptime.homemcc and trip.homemnc = triptime.homemnc ");
		}
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(getClauseForCountryJoin(filter.getSelectedCountries(), roamType));
		}	
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and (triptime.usagebintime between ")
			.append(filter.getDateFrom()).append(" and ").append(filter.getDateTo())
			.append(") ");

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
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(getClauseForCountryJoin(filter.getSelectedCountries(), roamType));
		}
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ");

		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);
		
		query.append(" group by trip.").append(column);
		query.append(" order by imsicount desc, mocallminutes desc, mtcallminutes desc, ")
			.append(" datausage desc ");
	}
	
	private static String getClauseForCountryJoin(final String selectedCountries, final String roamType) {
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
	
	/**
	 * Populate query for network chart.
	 *
	 * @param filter the filter
	 * @param query the query
	 * @param column the column
	 * @param columnType the column type
	 * @param parameterMap the parameter map
	 */
	public static void populateQueryForNetworkChart(final Filter filter, final StringBuilder query,  
			final Map<String, Object> parameterMap, final String roamType) {
		query.append(" select count(trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" network.network_name networkName from ");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(RAPropertyUtil.getProperty("out.table.trip")).append(" trip ")
				.append(" inner join ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork")).append(" network ")
				.append(" on trip.visitedmcc = network.mcc and trip.visitedmnc = network.mnc ");
				
		} else {
			query.append(RAPropertyUtil.getProperty("in.table.trip")).append(" trip ")
				.append(" inner join ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork")).append(" network ")
				.append(" on trip.visitedmcc = network.mcc and trip.visitedmnc = network.mnc ");
		}

		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ");

		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and network.countryid in (").append(filter.getSelectedCountries()).append(")");
		} 

		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);

		query.append(" group by network.network_name ");
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
	public static void populateQueryForNetworkGroupChart(final Filter filter, final StringBuilder query, 
			final Map<String, Object> parameterMap, final String roamType) {
		query.append(" select count(trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" networkGroup.NetworkGroupName networkGroup from ");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(RAPropertyUtil.getProperty("out.table.trip")).append(" trip ")
				.append(" inner join ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork")).append(" network ")
				.append(" on trip.visitedmcc = network.mcc and trip.visitedmnc = network.mnc ")
				.append(" inner join ").append(RAPropertyUtil.getProperty("common.table.networkgroup"))
				.append(" networkGroup on networkGroup.network_id = network.network_id ");
		} else {
			query.append(RAPropertyUtil.getProperty("in.table.trip")).append(" trip ")
				.append(" inner join ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork")).append(" network ")
				.append(" on trip.visitedmcc = network.mcc and trip.visitedmnc = network.mnc ")
				.append(" inner join ").append(RAPropertyUtil.getProperty("common.table.networkgroup"))
				.append(" networkGroup on networkGroup.network_id = network.network_id ");
		}
		
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ");

		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and network.countryid in (").append(filter.getSelectedCountries()).append(")");
		} 
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		appendClauseForAttributes(query, parameterMap, filterParameters);
		
		query.append(" group by networkGroup.NetworkGroupName ");
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
			.append(" and trip.endtime != 0 ");

		final List<String> exculdeCountryList = new ArrayList<String>();
		final List<String> selectedCountryList = new ArrayList<String>();
		
		if (!filter.getSelectedCountries().isEmpty()) {
			selectedCountryList.addAll(Arrays.asList(filter.getSelectedCountries().split(RAConstants.COMMA)));
		} 
//		if (!filter.getExcludedCountries().isEmpty()){
//			exculdeCountryList.addAll(Arrays.asList(filter.getExcludedCountries().split(RAConstants.COMMA)));
//		}
		
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
	public static String queryForDistinctNetworkGroups(final String roamType) {
		final StringBuilder query = new StringBuilder();
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append("select distinct tadignetwork.network_id networkId, network_name networkName ,T.mcc mcc, T.mnc mnc, networkgroup.NetworkGroupName groupName from ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork"))
				.append(" tadignetwork inner join (")
				.append(" select distinct trip.visitedmcc as mcc, trip.visitedmnc as mnc from ")
				.append(RAPropertyUtil.getProperty("out.table.trip"))
				.append(" trip ") 
				.append(" ) T on T.mcc = tadignetwork.mcc and T.mnc = tadignetwork.mnc ")
				.append(" inner join ").append(RAPropertyUtil.getProperty("common.table.networkgroup"))
				.append(" networkgroup on tadignetwork.network_id =  networkgroup.network_id ")
				.append(" order by network_name ");
		} else {
			query.append("select distinct tadignetwork.network_id networkId, network_name networkName ,T.mcc mcc, T.mnc mnc, networkgroup.NetworkGroupName groupName from ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork"))
				.append(" tadignetwork inner join (")
				.append(" select distinct trip.homemcc as mcc, trip.homemnc as mnc from ")
				.append(RAPropertyUtil.getProperty("in.table.trip"))
				.append(" trip ") 
				.append(" ) T on T.mcc = tadignetwork.mcc and T.mnc = tadignetwork.mnc ")
				.append(" inner join ").append(RAPropertyUtil.getProperty("common.table.networkgroup"))
				.append(" networkgroup on tadignetwork.network_id =  networkgroup.network_id ")
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
		
		
		query.append("select country visitedcountryname, count(imsi) roamercount, sum(mocallminutes) mocallminutes, sum(mtcallminutes) mtcallminutes,"+ 
				 "sum(mosmscount) mosmscount, sum(uplink + downlink) datausage, sum(mocallminuteslocal) mocallminuteslocal, "+ 
				 " sum(mocallminuteshome) mocallminuteshome,sum(mocallminutesothers) mocallminutesother from ");
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
			
		query.append(" group by  country ");
		
		
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
	private static void appendClauseForAttributes(final StringBuilder query,
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
