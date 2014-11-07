/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * @author sarvesh
 *
 */
public enum CountryColumn {
	COUNTRYID("countryid"),
	COUNTRY("country"),
	ISLEISUREDESTINATION("isleisuredestination"),
	ISPREMIUMLEISUREDESTINATION("ispremiumleisuredestination"),
	GDP("gdp");

	private String name;
	private CountryColumn(final String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

}
