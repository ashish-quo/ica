/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.HeatMap;
import com.mobileum.roameranalytics.model.RoamingStats;
import com.mobileum.roameranalytics.model.TopCountry;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;

/**
 * @author sarvesh
 *
 */
public interface TrendService {

	public void printQuery();
	
	/**
	 * @Authr Smruti
	 * @param startDate
	 * @param endDate
	 * @param country
	 * @return
	 */
	public  Map<String,HeatMap> getHeatMap(String startDate, String endDate, List<String> country);
	
	/**
	 * @Authr Smruti
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public  TopCountry getTopCountry(String startDate, String endDate);
	
	/**
	 *  @Authr Smruti
	 * @param startDate
	 * @param endDate
	 * @param orderBy
	 * @param groupBy
	 * @return
	 */
	public List<RoamingStats> getTopRoamer(String startDate, String endDate,  String orderBy);
		

	/**
	 * Gets the trends charts.
	 *
	 * @param filter the filter
	 * @return the trends charts
	 */
	public RoamingTrend getTrendsCharts(Filter filter);		

}
