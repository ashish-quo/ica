/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.List;

import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;
import com.mobileum.roameranalytics.model.Country;
import com.mobileum.roameranalytics.model.Filter;

/**
 * @author Quovantis_Dev
 *
 */
public interface MetaDataService {

	/**
	 * Gets the attribute list for left panel.
	 *
	 * @return the attribute list
	 */
	public List<Attribute> getAttributes(Filter filter,String roamType);
	
	/**
	 * Gets the all countries.
	 *
	 * @return the all countries
	 */
	public List<Country> getAllCountries(Filter filter,String roamType);
	
	/**
	 * Gets the other countries traveled.
	 *
	 * @param filter the filter
	 * @return the other countries traveled
	 */
	public List<AttributeCategory> getOtherCountriesTraveled(Filter filter,String roamType);
	
	/**
	 * Added by smruti on 2014-07-21
	 * @param str_date
	 * @return
	 */
	public long dateToTimestamp(String str_date);
	
	/**
	 * @Author Smruti 
	 * @param list
	 * @return
	 */
	public Object[] listToObjectArray(List<Object> list);
}
