/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.dao.Criteria;
import com.mobileum.roameranalytics.dao.SelectQuery;
import com.mobileum.roameranalytics.dao.Table;
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
	 * Returns query for trends chart
	 * @param filter - filters selected
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
		Map<Integer, String> tempAttributeMap = filter.getTempAttributes();
		for (Integer attrInd : attributeMap.keySet()) {
			if (FilterColumn.ROAMING_CATEGEGORY.getInd() == attrInd) {
				query.append(" and OVERALLTRIPCATEGORY in (:tripCategory) ");
				parameterMap.put("tripCategory", CommonUtil.convertToList(attributeMap.get(attrInd)));
			} else if (FilterColumn.PAYMENT_TYPE.getInd() == attrInd) {
				int paymentType = Integer.parseInt(attributeMap.get(attrInd));
				query.append(" and CHARGINGPLAN = :chargePlan ");
				parameterMap.put("chargePlan", paymentType);
			} else if (FilterColumn.DOMESTIC_ARPU.getInd() == attrInd) {
				query.append(" and OVERALLDOMESTICCATEGORY in (:arpu) ");
				parameterMap.put("arpu", CommonUtil.convertToList(attributeMap.get(attrInd)));
			}
		}
		
		// overriding temporary filters
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
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and trip.visitedcountryname in (:countries)");
		}
		
		query.append(" group by  triptime.usagebintime, trip.overalltripcategory ");
		query.append("  order by triptime.usagebintime ");
	}
}
