/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.HeatMap;

/**
 * @author sarvesh
 *
 */
public interface TrendServiceI {

	/**
	 * Gets the attribute list for left panel.
	 *
	 * @return the attribute list
	 */
	public Map<String, List<Attribute>> getAttributes();
	public void printQuery();
	public Map<String,HeatMap> getHeatMap(String startDate, String endDate, String country);
		

}
