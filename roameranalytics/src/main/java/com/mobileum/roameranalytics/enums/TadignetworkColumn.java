/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * Enum for tadignetwork table columns
 * @author sarvesh
 *
 */
public enum TadignetworkColumn {
	NETWORK_ID("networkId"),
	TADIG("tadig"),
	MCC("mcc"),
	MNC("mnc"),
	NETWORK_NAME("networkname");

	private String name;
	private TadignetworkColumn(final String name) {
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
