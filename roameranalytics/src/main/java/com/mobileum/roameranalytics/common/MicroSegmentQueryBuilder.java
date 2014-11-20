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
public class MicroSegmentQueryBuilder {

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
		query.append(" select count(distinct trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes,")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" trip.").append(column).append(" categoryValue ");
			
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" from ").append(RAPropertyUtil.getProperty("out.table.business")).append(" trip ");
		} else {
			query.append(" from ").append(RAPropertyUtil.getProperty("in.table.business")).append(" trip ");
		}
		
		query.append(" where (trip.usagebintime between ")
			.append(filter.getDateFrom()).append(" and ").append(filter.getDateTo())
			.append(") ");

		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(StatsQueryBuilder.getClauseForCountry(filter.getSelectedCountries(), roamType));
		}	
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		StatsQueryBuilder.appendClauseForAttributes(query, parameterMap, filterParameters);
		
		query.append(" group by trip.").append(column);
		query.append(" order by imsicount desc, mocallminutes desc, mtcallminutes desc, ")
			.append(" datausage desc ");
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
		query.append(" select count(distinct trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ");
			
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append("trip.visitednetwork networkName ")
				.append(" from ").append(RAPropertyUtil.getProperty("out.table.business")).append(" trip ");
				
		} else {
			query.append("trip.homenetwork networkName ")
				.append(" from ").append(RAPropertyUtil.getProperty("out.table.business")).append(" trip ");
		}
	
		query.append(" where (trip.usagebintime between ")
			.append(filter.getDateFrom()).append(" and ").append(filter.getDateTo())
			.append(") ");
	
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(StatsQueryBuilder.getClauseForCountry(filter.getSelectedCountries(), roamType));
		} 
	
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		StatsQueryBuilder.appendClauseForAttributes(query, parameterMap, filterParameters);
	
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" group by trip.visitednetwork ");
				
		} else {
			query.append(" group by trip.homenetwork ");
		}
		
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
		query.append(" select count(distinct trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ");
		
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append("trip.visitednetworkgroup networkGroup ")
				.append(" from ").append(RAPropertyUtil.getProperty("out.table.business")).append(" trip ");
				
		} else {
			query.append("trip.homenetworkgroup networkGroup ")
				.append(" from ").append(RAPropertyUtil.getProperty("out.table.business")).append(" trip ");
		}
		
		query.append(" where (trip.usagebintime between ")
			.append(filter.getDateFrom()).append(" and ").append(filter.getDateTo())
			.append(") ");

		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(StatsQueryBuilder.getClauseForCountry(filter.getSelectedCountries(), roamType));
		} 
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		StatsQueryBuilder.appendClauseForAttributes(query, parameterMap, filterParameters);
		
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" group by trip.visitednetworkgroup ");
				
		} else {
			query.append(" group by trip.homenetworkgroup ");
		}
		
		
		query.append(" order by imsicount desc, mocallminutes desc, mtcallminutes desc, ")
			.append("  datausage desc ");
	}

}
