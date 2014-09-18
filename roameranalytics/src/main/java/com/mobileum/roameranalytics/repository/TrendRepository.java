/**
 * 
 */
package com.mobileum.roameranalytics.repository;


import java.util.List;

import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.CountryUsageStatistics;
import com.mobileum.roameranalytics.model.RoamingStatistics;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;

/**
 * @author smruti
 *
 */
public interface TrendRepository {
	
	public void insertData();
	
	/**
	 * Added by smruti on 2014-08-21
	 * @param query
	 * @return
	 */
	public List<CountryUsageStatistics> getHeatMapList(String query,Object criteria[]);	
	
	/**
	 * Added by smruti on 2014-08-21
	 * @param query
	 * @return
	 */
	public List<RoamingStatistics> getTopRoamerDao(String query,Object criteria[]);
	

	/**
	 * Gets the trends charts.
	 *
	 * @param filter the filter
	 * @return the trends charts
	 */
	public RoamingTrend getTrendsCharts(Filter filter);

}
