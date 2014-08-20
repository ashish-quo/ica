/**
 * 
 */
package com.mobileum.roameranalytics.dao;

import java.util.List;

import com.mobileum.roameranalytics.model.Attribute;

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
}
