/**
 * 
 */
package com.mobileum.roameranalytics.model;

/**
 * Model for Country search filter
 * @author sarvesh
 *
 */
public class Country {
	
	/** The country name. */
	private String countryName;
	
	/** The bordring. */
	private byte bordering;

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @return the bordring
	 */
	public byte getBordering() {
		return bordering;
	}

	/**
	 * @param bordring the bordring to set
	 */
	public void setBordering(byte bordring) {
		this.bordering = bordring;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Country [countryName=" + countryName + ", bordering="
				+ bordering + "]";
	}
	
}
