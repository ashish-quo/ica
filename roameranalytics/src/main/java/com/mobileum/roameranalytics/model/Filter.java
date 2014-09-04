/**
 * 
 */
package com.mobileum.roameranalytics.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents filter selected at a given time. It is the collection of countries selected with the
 * attributes
 * @author sarvesh
 *
 */
public class Filter {

	/** The selected countries. */
	private String selectedCountries;
	
	/** The selected attributes. */
	private Map<Integer,String> selectedAttributes = new HashMap<Integer, String>(1);
	
	/** The temp attributes. */
	private Map<Integer,String> tempAttributes = new HashMap<Integer, String>();
	
	/** The date from. */
	private long dateFrom;
	
	/** The date to. */
	private long dateTo;

	/**
	 * @return the selectedCountries
	 */
	public String getSelectedCountries() {
		return selectedCountries;
	}

	/**
	 * @param selectedCountries the selectedCountries to set
	 */
	public void setSelectedCountries(String selectedCountries) {
		this.selectedCountries = selectedCountries;
	}

	/**
	 * @return the selectedAttributes
	 */
	public Map<Integer, String> getSelectedAttributes() {
		return selectedAttributes;
	}

	/**
	 * @param selectedAttributes the selectedAttributes to set
	 */
	public void setSelectedAttributes(Map<Integer, String> selectedAttributes) {
		this.selectedAttributes = selectedAttributes;
	}

	/**
	 * @return the dateFrom
	 */
	public long getDateFrom() {
		return dateFrom;
	}

	/**
	 * @param dateFrom the dateFrom to set
	 */
	public void setDateFrom(long dateFrom) {
		this.dateFrom = dateFrom;
	}

	/**
	 * @return the dateTo
	 */
	public long getDateTo() {
		return dateTo;
	}

	/**
	 * @param dateTo the dateTo to set
	 */
	public void setDateTo(long dateTo) {
		this.dateTo = dateTo;
	}

	/**
	 * @return the tempAttributes
	 */
	public Map<Integer, String> getTempAttributes() {
		return tempAttributes;
	}

	/**
	 * @param tempAttributes the tempAttributes to set
	 */
	public void setTempAttributes(Map<Integer, String> tempAttributes) {
		this.tempAttributes = tempAttributes;
	}
}
