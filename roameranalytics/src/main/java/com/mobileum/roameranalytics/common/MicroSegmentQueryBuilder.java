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
			query.append(StatsQueryBuilder.getClauseForCountryJoin(filter.getSelectedCountries(), roamType));
		}
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ");
	
		
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
		query.append(" select count(trip.imsi) imsicount, sum(trip.mocallminutes) mocallminutes, ")
			.append(" sum(trip.mtcallminutes) mtcallminutes, ")
			.append(" sum(trip.uplink + trip.downlink)/1048576.0  datausage, ")
			.append(" network.networkname networkName from ");
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
		StatsQueryBuilder.appendClauseForAttributes(query, parameterMap, filterParameters);
	
		query.append(" group by network.networkname ");
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
			.append(" networkGroup.networkgroup networkGroup from ");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(RAPropertyUtil.getProperty("out.table.trip")).append(" trip ")
				.append(" inner join ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork")).append(" network ")
				.append(" on trip.visitedmcc = network.mcc and trip.visitedmnc = network.mnc ")
				.append(" inner join ").append(RAPropertyUtil.getProperty("common.table.networkgroup"))
				.append(" networkGroup on networkGroup.networkid = network.networkid ");
		} else {
			query.append(RAPropertyUtil.getProperty("in.table.trip")).append(" trip ")
				.append(" inner join ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork")).append(" network ")
				.append(" on trip.visitedmcc = network.mcc and trip.visitedmnc = network.mnc ")
				.append(" inner join ").append(RAPropertyUtil.getProperty("common.table.networkgroup"))
				.append(" networkGroup on networkGroup.networkid = network.networkid ");
		}
		
		query.append(" where trip.starttime >= ").append(filter.getDateFrom())
			.append(" and trip.endtime <= ").append(filter.getDateTo())
			.append(" and trip.endtime != 0 ");
	
		if (!filter.getSelectedCountries().isEmpty()) {
			query.append(" and network.countryid in (").append(filter.getSelectedCountries()).append(")");
		} 
		
		final Map<String,String> filterParameters = filter.getSelectedAttributes();
		StatsQueryBuilder.appendClauseForAttributes(query, parameterMap, filterParameters);
		
		query.append(" group by networkGroup.networkgroup ");
		query.append(" order by imsicount desc, mocallminutes desc, mtcallminutes desc, ")
			.append("  datausage desc ");
	}

}
