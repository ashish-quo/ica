/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * @author sarvesh
 *
 */
public enum CountryColumn {
	HOMEID("homeid"),
	HOMECOUNTRY("homecountry"),
	VISITEDCOUNTRY("visitedcountry"),
	VISITORID("visitorid"),
	BORDERING("bordering"),
	SAMECONTINENT("samecontinent");

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
