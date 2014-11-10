/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.Map;

import com.mobileum.roameranalytics.enums.RoamType;
import com.mobileum.roameranalytics.model.Filter;

/**
 * @author sarvesh
 *
 */
public class TrendQueryBuilder {

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
			query.append(StatsQueryBuilder.getClauseForCountryJoin(filter.getSelectedCountries(), roamType));
		}	
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ")
			.append(" and (triptime.usagebintime between ")
			.append(filter.getDateFrom()).append(" and ").append(filter.getDateTo())
			.append(") ");
	
		final Map<String, String> attributeMap = filter.getSelectedAttributes();
		StatsQueryBuilder.appendClauseForAttributes(query, parameterMap, attributeMap);
		
		query.append(" group by  triptime.usagebintime, trip.overalltripcategory ");
		query.append("  order by triptime.usagebintime ");
	}

}
