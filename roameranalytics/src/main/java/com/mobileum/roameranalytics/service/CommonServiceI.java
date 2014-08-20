/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.List;
import java.util.Map;

import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.Country;

/**
 * @author Quovantis_Dev
 *
 */
public interface CommonServiceI {

	/**
	 * Gets the attribute list for left panel.
	 *
	 * @return the attribute list
	 */
	public Map<String, List<Attribute>> getAttributes();
	
	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	public List<Country> getAllCountries();
}
