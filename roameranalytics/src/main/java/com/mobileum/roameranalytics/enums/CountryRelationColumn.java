/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * @author sarvesh
 *
 */
public enum CountryRelationColumn {
	HOMECOUNTRYID("homecountryid"),
	VISITEDCOUNTRYID("visitedcountryid"),
	ISBORDERING("isbordering");

	private String name;
	private CountryRelationColumn(final String name) {
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
