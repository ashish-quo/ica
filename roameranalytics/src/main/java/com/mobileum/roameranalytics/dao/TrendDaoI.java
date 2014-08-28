/**
 * 
 */
package com.mobileum.roameranalytics.dao;


import java.util.List;

import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.HeatMap;
import com.mobileum.roameranalytics.model.RoamingStats;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;

/**
 * @author smruti
 *
 */
public interface TrendDaoI {
	
	public void insertData();
	

	/**
	 * Gets the attribute list for left panel.
	 *
	 * @return the attribute list
	 */
	public List<Attribute> getAttributeList();
	
	/**
	 * Added by smruti on 2014-08-21
	 * @param query
	 * @return
	 */
	public List<HeatMap> getHeatMapList(String query,Object criteria[]);	
	
	/**
	 * Added by smruti on 2014-08-21
	 * @param query
	 * @return
	 */
	public List<RoamingStats> getTopRoamerDao(String query,Object criteria[]);
	

	/**
	 * Gets the trends charts.
	 *
	 * @param filter the filter
	 * @return the trends charts
	 */
	public RoamingTrend getTrendsCharts(Filter filter);

}
