/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.dao.Criteria;
import com.mobileum.roameranalytics.dao.SelectQuery;
import com.mobileum.roameranalytics.dao.Table;
import com.mobileum.roameranalytics.enums.DeviceType;
import com.mobileum.roameranalytics.enums.FilterColumn;
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
		StringBuilder query = new StringBuilder();
		query.append("select attr.id attrId, attr.attribute_name attrName, attr.module_id moduleId, ")
				.append(" attr.attr_ind attrInd, ")
				.append(" attr.display_order attrDO, attrCat.categ_name catName, attrCat.categ_ind catInd")
				.append(" from ").append(Relation.ATTRIBUTE).append(" attr inner join ")
				.append(Relation.ATTRIBUTE_CATEGORY)
				.append(" attrCat on attr.attr_ind = attrCat.attr_ind ")
				.append(" order by attr.display_order, attrCat.attr_ind, attrCat.display_order");
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
		
		
		System.out.println(selectQuery.toString());
		 return selectQuery.toString();
	}	

	/**
	 * Query for all countries.
	 *
	 * @return the string
	 */
	public static String queryForAllCountries() {
		StringBuilder query = new StringBuilder();
		query.append(" select country_name countryName, country_code countryCode from ")
			.append(Relation.COUNTRY).append(" order by country_name");
		return query.toString();
	}
	
	/**
	 * Populates query for trends chart.
	 *
	 * @param filter - filters selected
	 * @param query the query
	 * @param parameterMap the parameter map
	 * @return query
	 */
	public static void populateQueryForTrends(Filter filter, StringBuilder query, Map<String, Object> parameterMap) {
		
		query.append(" select sum(1) imsicount, sum(triptime.mocallminutes) mocallminutes, ")
			.append(" sum(triptime.mtcallminutes) mtcallminutes, sum(triptime.mosmscount) mosmscount,")
			.append(" sum(triptime.uplink + triptime.downlink)  datausage, ")
			.append(" triptime.usagebintime usagebintime,trip.overalltripcategory overalltripcategory from ")
			.append(Relation.TRIP_TIME).append(" triptime inner join ").append(Relation.TRIP)
			.append(" trip on triptime.imsi = trip.imsi and triptime.tripstarttime = trip.starttime ")
			.append(" where trip.starttime >= :startDate ")
			.append(" and trip.endtime <= :endDate ");
		
		Map<Integer, String> attributeMap = filter.getSelectedAttributes();
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
				parameterMap.put("tripCategory", CommonUtil.convertToList(tempAttributeMap.get(attrInd)));
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
				parameterMap.put("arpu", CommonUtil.convertToList(tempAttributeMap.get(attrInd)));
			}
		}
	}


	/**
	 * Append clause for attributes.
	 *
	 * @param query the query
	 * @param parameterMap the parameter map
	 * @param attributeMap the attribute map
	 */
	private static void appendClauseForAttributes(StringBuilder query,
			Map<String, Object> parameterMap, Map<Integer, String> attributeMap) {
		for (Integer attrInd : attributeMap.keySet()) {
			if (FilterColumn.ROAMING_CATEGEGORY.getInd() == attrInd) {
				query.append(" and OVERALLTRIPCATEGORY in (:tripCategory) ");
				parameterMap.put("tripCategory", CommonUtil.convertToList(attributeMap.get(attrInd)));
			} else if (FilterColumn.NETWORK.getInd() == attrInd) {
				appendClauseForNetwork(query, attributeMap, attrInd);
			} else if (FilterColumn.DEVICE_TYPE.getInd() == attrInd) {
				appendClauseForDeviceType(query, parameterMap, attributeMap,attrInd);
			} else if (FilterColumn.PAYMENT_TYPE.getInd() == attrInd) {
				int paymentType = Integer.parseInt(attributeMap.get(attrInd));
				query.append(" and CHARGINGPLAN = :chargePlan ");
				parameterMap.put("chargePlan", paymentType);
			} else if (FilterColumn.DOMESTIC_ARPU.getInd() == attrInd) {
				query.append(" and OVERALLDOMESTICCATEGORY in (:arpu) ");
				parameterMap.put("arpu", CommonUtil.convertToList(attributeMap.get(attrInd)));
			} else if (FilterColumn.TRAVEL_DURATION.getInd() == attrInd) {
				appendClauseForTravelDuration(query, attributeMap, attrInd);
			}
		}
	}


	/**
	 * Append clause for network.
	 *
	 * @param query the query
	 * @param attributeMap the attribute map
	 * @param attrInd the attr ind
	 */
	private static void appendClauseForNetwork(StringBuilder query,
			Map<Integer, String> attributeMap, Integer attrInd) {
		int networkCateg = Integer.parseInt(attributeMap.get(attrInd));
		if (FilterColumn.NETWORK_SAME.getInd() == networkCateg) {
			query.append(" and trip.homemnc = trip.visitedmnc ");
		} else {
			query.append(" and trip.homemnc != trip.visitedmnc ");
		}
	}


	/**
	 * Append clause for device type.
	 *
	 * @param query the query
	 * @param parameterMap the parameter map
	 * @param attributeMap the attribute map
	 * @param attrInd the attr ind
	 */
	private static void appendClauseForDeviceType(StringBuilder query,
			Map<String, Object> parameterMap,
			Map<Integer, String> attributeMap, Integer attrInd) {
		String dtCateg = attributeMap.get(attrInd);
		List<Integer> subCategList = CommonUtil.convertToList(dtCateg);
		List<String> deviceTypes = new ArrayList<String>(6);
		for (Integer subCateg : subCategList) {
			deviceTypes.add(DeviceType.of(subCateg).getName());
		}
		query.append(" and trip.devicename in (:deviceTypes) ");
		parameterMap.put("deviceTypes", deviceTypes);
	}


	/**
	 * Append clause for travel duration.
	 *
	 * @param query the query
	 * @param attributeMap the attribute map
	 * @param attrInd the attr ind
	 */
	private static void appendClauseForTravelDuration(StringBuilder query,
			Map<Integer, String> attributeMap, Integer attrInd) {
		String tdCateg = attributeMap.get(attrInd);
		List<Integer> subCategList = CommonUtil.convertToList(tdCateg);
		query.append(" and ( ");
		boolean first = true;
		for (Integer cat : subCategList) {
			if (!first) {
				query.append(" or ");
			} else {
				first = false;
			}
			if (FilterColumn.TRAVEL_DURATION_WEEKDAY.getInd() == cat) {
				query.append(" ((to_timestamp(trip.endtime/1000)::date - ")
					.append(" to_timestamp(trip.starttime/1000)::date)  < 5 ")
					.append(" and (EXTRACT(DOW FROM to_timestamp(trip.starttime/1000)) between 1 and 5) ")
					.append(" and (EXTRACT(DOW FROM to_timestamp(trip.endtime/1000)) between 1 and 5) )");
			} else if (FilterColumn.TRAVEL_DURATION_WEEKEND.getInd() == cat) {
				query.append(" ((to_timestamp(trip.endtime/1000)::date -  ")
					.append(" to_timestamp(trip.starttime/1000)::date)  < 2 ")
					.append(" and EXTRACT(DOW FROM to_timestamp(trip.starttime/1000)) in (0,6) ")
					.append(" and EXTRACT(DOW FROM to_timestamp(trip.endtime/1000)) in (0,6)) ");
			} else if (FilterColumn.TRAVEL_DURATION_WEEKDAY_WEEKEND.getInd() == cat) {
				query.append(" ((to_timestamp(trip.endtime/1000)::date -  ")
					.append(" to_timestamp(trip.starttime/1000)::date)  between 5 and 12 )");
			} else if (FilterColumn.TRAVEL_DURATION_TWO_WEEKS_PLUS.getInd() == cat) {
				query.append(" ((to_timestamp(trip.endtime/1000)::date -  ")
				.append(" to_timestamp(trip.starttime/1000)::date) > 12 )");
			}
		}
		query.append(" ) ");
	}
}
