/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Filter;

/**
 * @author sarvesh
 *
 */
public interface MicroSegmentService {
	
	/**
	 *  Gets data required for network graph in microsegment section.
	 *
	 * @return the network data
	 */
	Map<String, List<Object[]>> getMSChartData(Filter filter, String attributeName, String column, String columnType,
			Map<String,String> catNameValue, String roamType);
	
	/**
	 * Gets the network group data.
	 *
	 * @param filter the filter
	 * @param column the column
	 * @param columnType the column type
	 * @param catNameValue the cat name value
	 * @return the network data
	 */
	Map<String, List<Object[]>> getNetworkGroupData(Filter filter, String column, String columnType,
			Map<String,String> catNameValue, String roamType);
	
	/**
	 * Gets the network  data.
	 *
	 * @param filter the filter
	 * @param column the column
	 * @param columnType the column type
	 * @param catNameValue the cat name value
	 * @return the network data
	 */
	Map<String, List<Object[]>> getNetworkData(Filter filter, String column, String columnType,
			Map<String,String> catNameValue, String roamType);
	
	
	/**
	 *  Gets data required for other countries traveled in microsegment section.
	 *
	 * @return the network data
	 * @throws RADataAccessException 
	 */
	Map<String, List<Object[]>> getOtherCountriesTraveledData(Filter filter, String column, String columnType,
			Map<String,String> catNameValue,String roamType);
	
	/**
	 * Gets the attribute label and value.
	 *
	 * @return the attribute label and value
	 */
	Map<String,Map<String,String>> getAttributeLabelAndValue();
}
