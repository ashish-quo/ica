/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.model.AggregatedCountryStatistics;
import com.mobileum.roameranalytics.model.CountryUsageStatistics;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.RoamingStatistics;
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
	public  Map<String,CountryUsageStatistics> getHeatMap(String startDate, String endDate, List<String> country);
	
	/**
	 * @Authr Smruti
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public  AggregatedCountryStatistics getTopCountry(Filter filter);
	/**
	 *  @Authr Smruti
	 * @param startDate
	 * @param endDate
	 * @param orderBy
	 * @param groupBy
	 * @return
	 */
	public List<RoamingStatistics> getHeatMap(Filter filter);	

	/**
	 * Gets the trends charts.
	 *
	 * @param filter the filter
	 * @return the trends charts
	 */
	public RoamingTrend getTrendsCharts(Filter filter);		
	
	/**
	 * Gets the Roaming Statistics.
	 *
	 * @param filter the filter
	 * @return the trends charts
	 */
	public HashMap<String,Long> getRoamingStatistics(Filter filter);

}
