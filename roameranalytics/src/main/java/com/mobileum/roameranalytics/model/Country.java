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

	/** The id. */
	private long id;
	
	/** The country code. */
	private String countryCode;
	
	/** The country name. */
	private String countryName;
	
	/** The dial code. */
	private int dialCode;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the dialCode
	 */
	public int getDialCode() {
		return dialCode;
	}

	/**
	 * @param dialCode the dialCode to set
	 */
	public void setDialCode(int dialCode) {
		this.dialCode = dialCode;
	}
	
}
