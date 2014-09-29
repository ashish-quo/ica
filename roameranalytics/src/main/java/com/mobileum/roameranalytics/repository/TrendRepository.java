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
