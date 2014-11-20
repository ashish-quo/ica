/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * @author sarvesh
 *
 */
public enum BusinessTableColumn {

	VISITEDMCC("visitedmcc"),
	VISITEDMNC("visitedmnc"),
	VISITEDNETWORK("visitednetwork"),
	VISITEDCOUNTRY("visitedcountry"),
	VISITEDNETWORKGROUP("visitednetworkgroup"),
	HOMEMCC("homemcc"),
	HOMEMNC("homemnc"),
	HOMENETWORK("homenetwork"),
	HOMECOUNTRY("homecountry"),
	HOMENETWORKGROUP("homenetworkgroup"),
	
	ISNEIGHBOURING("isneighbouring"),
	
	ISVISITEDCOUNTRYLEISURE("isvisitedcountryleisure"),
	ISVISITEDCOUNTRYLEISUREPREMIUM("isvisitedcountryleisurepremium"),
	VISITEDCOUNTRYGDP("visitedcountrygdp"),
	
	ISHOMECOUNTRYLEISURE("ishomecountryleisure"),
	ISHOMECOUNTRYLEISUREPREMIUM("ishomecountryleisurepremium"),
	HOMECOUNTRYGDP("homecountrygdp");
	
	private String column;
	
	/**
	 * 
	 */
	private BusinessTableColumn(final String column) {
		this.column = column;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return column;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.column;
	}
}
