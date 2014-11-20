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
		
		
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" select count(distinct trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
				.append(" sum(trip.mtcallminutes) mtcallminutes, sum(trip.mosmscount) mosmscount,")
				.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
				.append(" trip.usagebintime usagebintime,trip.overalltripcategory overalltripcategory ")
				.append(" from ").append(RAPropertyUtil.getProperty("out.table.business")).append(" trip ");
		} else {
			query.append(" select count(distinct trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
				.append(" sum(trip.mtcallminutes) mtcallminutes, sum(trip.mosmscount) mosmscount,")
				.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
				.append(" trip.usagebintime usagebintime,trip.overalltripcategory overalltripcategory ")
				.append(" from ").append(RAPropertyUtil.getProperty("in.table.business")).append(" trip ");
		}
		
		query.append(" where (trip.usagebintime between ")
			.append(filter.getDateFrom()).append(" and ").append(filter.getDateTo())
			.append(") ");
	
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(StatsQueryBuilder.getClauseForCountry(filter.getSelectedCountries(), roamType));
		}	
		
		final Map<String, String> attributeMap = filter.getSelectedAttributes();
		StatsQueryBuilder.appendClauseForAttributes(query, parameterMap, attributeMap);
		
		query.append(" group by  trip.usagebintime, trip.overalltripcategory ");
		query.append("  order by trip.usagebintime ");
	}

}
