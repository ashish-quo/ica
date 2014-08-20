/**
 * 
 */
package com.mobileum.roameranalytics.dao;

import java.util.List;

import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.Country;

/**
 * @author sarvesh
 *
 */
public interface CommonDaoI {

	/**
	 * Gets the attribute list for left panel.
	 *
	 * @return the attribute list
	 */
	public List<Attribute> getAttributeList();
	
	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	public List<Country> getAllCountries();
}
