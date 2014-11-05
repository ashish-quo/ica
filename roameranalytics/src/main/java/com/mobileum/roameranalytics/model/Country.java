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
	
	private long countryId;
	
	private byte lowGDP;
	
	private byte leisure;
	
	private byte leisurePremium;

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(final String countryName) {
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
	public void setBordering(final byte bordring) {
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

	/**
	 * @return the countryId
	 */
	public long getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(final long countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the lowGDP
	 */
	public byte getLowGDP() {
		return lowGDP;
	}

	/**
	 * @param lowGDP the lowGDP to set
	 */
	public void setLowGDP(final byte lowGDP) {
		this.lowGDP = lowGDP;
	}

	/**
	 * @return the leisure
	 */
	public byte getLeisure() {
		return leisure;
	}

	/**
	 * @param leisure the leisure to set
	 */
	public void setLeisure(final byte leisure) {
		this.leisure = leisure;
	}

	/**
	 * @return the leisurePremium
	 */
	public byte getLeisurePremium() {
		return leisurePremium;
	}

	/**
	 * @param leisurePremium the leisurePremium to set
	 */
	public void setLeisurePremium(final byte leisurePremium) {
		this.leisurePremium = leisurePremium;
	}
	
}
