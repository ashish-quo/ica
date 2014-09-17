/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.Map;

import com.mobileum.roameranalytics.model.Filter;

/**
 * @author sarvesh
 *
 */
public interface MicroSegmentServiceI {

	/**
	 *  Gets data required for network graph in microsegment section.
	 *
	 * @return the network data
	 */
	Map<String, Object> getNetworkData(Filter filter);
	
	
	/**
	 *  Gets data required for network graph in microsegment section.
	 *
	 * @return the network data
	 */
	Map<String, Object> getMSChartData(Filter filter, String column, String columnType, Map<String,String> catNameValue);
	
	/**
	 * Gets the attribute label and value.
	 *
	 * @return the attribute label and value
	 */
	Map<String,Map<String,String>> getAttributeLabelAndValue();
	
	
	/**
	 *  Gets data required for roamer type graph in microsegment section.
	 *
	 * @return the network data
	 */
	Map<String, Object> getRoamerTypeData(Filter filter);
	
	/**
	 *  Gets data required for  payment type graph in microsegment section.
	 *
	 * @return the network data
	 */
	Map<String, Object> getPaymentTypeData(Filter filter);
	
	/**
	 *  Gets data required for device type graph in microsegment section.
	 *
	 * @return the network data
	 */
	Map<String, Object> getDeviceTypeData(Filter filter);
	
	/**
	 *  Gets data required for APRU graph in microsegment section.
	 *
	 * @return the network data
	 */
	Map<String, Object> getARPUData(Filter filter);
}
