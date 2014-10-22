/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.HashMap;
import java.util.List;

import com.mobileum.roameranalytics.model.AggregatedCountryStatistics;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.RoamingStatistics;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;

/**
 * @author sarvesh
 *
 */
public interface TrendService {

	/**
	 * @Authr Smruti
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public  AggregatedCountryStatistics getTopCountry(Filter filter, String roamType);
	/**
	 *  @Authr Smruti
	 * @param startDate
	 * @param endDate
	 * @param orderBy
	 * @param groupBy
	 * @return
	 */
	public List<RoamingStatistics> getHeatMap(Filter filter , String roamType);	

	/**
	 * Gets the trends charts.
	 *
	 * @param filter the filter
	 * @return the trends charts
	 */
	public RoamingTrend getTrendsCharts(Filter filter, String roamType);		
	
	/**
	 * Gets the Roaming Statistics.
	 *
	 * @param filter the filter
	 * @return the trends charts
	 */
	public HashMap<String,Long> getRoamingStatistics(Filter filter, String roamType);

}
