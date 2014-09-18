/**
 * 
 */
package com.mobileum.roameranalytics.model;

/**
 * @author smruti
 *
 */
public class CountryUsageStatistics {
	
	String countryCode;
	long moUsage;
	long mtUsage;
	long dataUsage;
	long smsUsage;
	float usagePojection;
	
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
	 * @return the moUsage
	 */
	public long getMoUsage() {
		return moUsage;
	}
	/**
	 * @param moUsage the moUsage to set
	 */
	public void setMoUsage(long moUsage) {
		this.moUsage = moUsage;
	}
	/**
	 * @return the mtUsage
	 */
	public long getMtUsage() {
		return mtUsage;
	}
	/**
	 * @param mtUsage the mtUsage to set
	 */
	public void setMtUsage(long mtUsage) {
		this.mtUsage = mtUsage;
	}
	/**
	 * @return the dataUsage
	 */
	public long getDataUsage() {
		return dataUsage;
	}
	/**
	 * @param dataUsage the dataUsage to set
	 */
	public void setDataUsage(long dataUsage) {
		this.dataUsage = dataUsage;
	}
	/**
	 * @return the smsUsage
	 */
	public long getSmsUsage() {
		return smsUsage;
	}
	/**
	 * @param smsUsage the smsUsage to set
	 */
	public void setSmsUsage(long smsUsage) {
		this.smsUsage = smsUsage;
	}
	/**
	 * @return the usagePojection
	 */
	public float getUsagePojection() {
		return usagePojection;
	}
	/**
	 * @param usagePojection the usagePojection to set
	 */
	public void setUsagePojection(float usagePojection) {
		this.usagePojection = usagePojection;
	}
	
	

}
