/**
 * 
 */
package com.mobileum.roameranalytics.model;

/**
 * @author smruti
 *
 */
public class RoamingCategory {
	
	String visitedCountryName;
	
	String category;
	
	Long count;

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
	/**
	 * @return the countryName
	 */
	public String getVisitedCountryName() {
		return visitedCountryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setVisitedCountryName(String visitedCountryName) {
		this.visitedCountryName = visitedCountryName;
	}
	
}
