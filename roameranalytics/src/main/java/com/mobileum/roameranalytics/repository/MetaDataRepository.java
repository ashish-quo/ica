/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import java.util.List;

import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;
import com.mobileum.roameranalytics.model.Country;

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
	public List<Attribute> getAttributeList() throws RADataAccessException;
	
	/**
	 * Gets the all networks.
	 *
	 * @return the all networks
	 */
	public List<AttributeCategory> getAllNetworks(long networkAttrId) throws RADataAccessException ;
	
	
	/**
	 * Gets the network groups.
	 *
	 * @param attributeId the attribute id
	 * @return the network groups
	 */
	public List<AttributeCategory> getNetworkGroups(long attributeId) throws RADataAccessException;
	
	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	public List<Country> getAllCountries() throws RADataAccessException;
}
