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
	
	/** The excluded countries. */
	private String excludedCountries;
	
	/** The selected attributes. */
	private Map<String,String> selectedAttributes = new HashMap<String, String>(1);
	
	/** The microsegment attributes. */
	private Map<String, String> microsegmentAttributes = new HashMap<String,String>(1);
	
	
	/** The other countries travelled. */
	private String otherCountriesTravelled;
	
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
	public Map<String, String> getSelectedAttributes() {
		return selectedAttributes;
	}

	/**
	 * @param selectedAttributes the selectedAttributes to set
	 */
	public void setSelectedAttributes(Map<String, String> selectedAttributes) {
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
		this.dateFrom = dateFrom/1000;
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
		this.dateTo = dateTo/1000;
	}

	/**
	 * @return the microsegmentAttributes
	 */
	public Map<String, String> getMicrosegmentAttributes() {
		return microsegmentAttributes;
	}

	/**
	 * @param microsegmentAttributes the microsegmentAttributes to set
	 */
	public void setMicrosegmentAttributes(Map<String, String> microsegmentAttributes) {
		this.microsegmentAttributes = microsegmentAttributes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Filter [selectedCountries=" + selectedCountries
				+ ", selectedAttributes=" + selectedAttributes
				+ ", microsegmentAttributes=" + microsegmentAttributes
				+ ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + "]";
	}

	/**
	 * @return the otherCountriesTravelled
	 */
	public String getOtherCountriesTravelled() {
		return otherCountriesTravelled;
	}

	/**
	 * @param otherCountriesTravelled the otherCountriesTravelled to set
	 */
	public void setOtherCountriesTravelled(String otherCountriesTravelled) {
		this.otherCountriesTravelled = otherCountriesTravelled;
	}

	/**
	 * @return the excludedCountries
	 */
	public String getExcludedCountries() {
		return excludedCountries;
	}

	/**
	 * @param excludedCountries the excludedCountries to set
	 */
	public void setExcludedCountries(String excludedCountries) {
		this.excludedCountries = excludedCountries;
	}
}
