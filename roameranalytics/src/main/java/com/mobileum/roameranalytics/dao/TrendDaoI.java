/**
 * 
 */
package com.mobileum.roameranalytics.dao;


import java.util.List;

import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.HeatMap;
import com.mobileum.roameranalytics.model.RoamingStats;

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
	public List<HeatMap> getHeatMapList(String query,long startDate, long endDate, String country);
		
	


}
