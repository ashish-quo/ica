/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * @author sarvesh
 *
 */
public enum RoamType {
	IN("IN"),OUT("OUT");
	private String roamType;
	
	private RoamType(String type) {
		roamType = type;
	}
	/**
	 * @return the roamType
	 */
	public String getRoamType() {
		return roamType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return roamType;
	}
}
