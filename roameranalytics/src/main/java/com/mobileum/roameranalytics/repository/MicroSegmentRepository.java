/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Filter;

/**
 * @author sarvesh
 *
 */
public interface MicroSegmentRepository {

	/**
	 *  Gets data required for all charts in microsegment section.
	 *
	 * @return the network data
	 * @throws RADataAccessException 
	 */
	Map<String,List<Object[]>> getMSChartData(Filter filter,String attributeName, String column,
			Map<String,String> catNameValue, String roamType) throws RADataAccessException;
	
	/**
	 *  Gets data required for network group in microsegment section.
	 *
	 * @return the network data
	 * @throws RADataAccessException 
	 */
	Map<String,List<Object[]>> getNetworkGroupData(Filter filter, String column, String columnType,
			Map<String,String> catNameValue, String roamType) throws RADataAccessException;
	
	
	/**
	 *  Gets data required for other countries traveled in microsegment section.
	 *
	 * @return the network data
	 * @throws RADataAccessException 
	 */
	Map<String,List<Object[]>> getOtherCountriesTraveledData(Filter filter, String column, String columnType,
			Map<String,String> catNameValue, String roamType) throws RADataAccessException;
	
	/**
	 * Gets the attribute label and value.
	 *
	 * @return the attribute label and value
	 */
	Map<String,Map<String,String>> getAttributeLabelAndValue();
}
