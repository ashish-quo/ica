/**
 * 
 */
package com.mobileum.roameranalytics.repository;


import java.util.HashMap;
import java.util.List;

import com.mobileum.roameranalytics.model.CountryUsageStatistics;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.RoamingCategory;
import com.mobileum.roameranalytics.model.RoamingStatistics;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;

/**
 * @author smruti
 *
 */
public interface TrendRepository {
	
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
	
	/**
	 * Gets the Roaming Statistics.
	 *
	 * @param filter the filter
	 * @return the trends charts
	 */
	public List<RoamingStatistics> getRoamingStatisticsRepository(Filter filter);

	/**
	 * Gets the trip category count
	 * @param filter
	 * @return
	 */
	List<RoamingCategory> getRoamingCategoryRepository(Filter filter);

}
