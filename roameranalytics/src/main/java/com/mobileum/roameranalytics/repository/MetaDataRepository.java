/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;
import com.mobileum.roameranalytics.model.Country;
import com.mobileum.roameranalytics.model.Filter;

/**
 * @author sarvesh
 *
 */
public interface MetaDataRepository {

	/**
	 * Gets the attribute list for left panel.
	 *
	 * @return the attribute list
	 */
	public List<Attribute> getAttributeList(Filter filter, String roamType) throws RADataAccessException;
	
	
	/**
	 * Gets the all networks.
	 *
	 * @return the all networks
	 */
	public Map<Long, List<AttributeCategory>> getAllNetworkAndNetworkGroups(Filter filter, long networkAttrId, 
			long networkGroupAttrId, String roamType) throws RADataAccessException ;
	
	
	/**
	 * Gets the all networks.
	 *
	 * @return the all networks
	 */
	public Map<Long, List<AttributeCategory>> getDeviceModelsAndManufactures(Filter filter, long deviceModelAttrId, 
			long deviceManufacturerAttrId, String roamType) throws RADataAccessException ;
	
	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	public List<Country> getAllCountries(Filter filter, String roamType) throws RADataAccessException;
	
	/**
	 * Gets the other countries traveled.
	 *
	 * @param filter the filter
	 * @return the other countries traveled
	 */
	public List<AttributeCategory> getOtherCountriesTraveled(Filter filter, String roamType) throws RADataAccessException;
}
