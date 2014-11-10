/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * Enum for Network Group IB table columns
 * @author sarvesh
 *
 */
public enum NetworkGroupColumn {

	NETWORK_ID("network_id"),
	NETWORK_GROUP("networkgroupname");

	private String name;
	private NetworkGroupColumn(final String name) {
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
