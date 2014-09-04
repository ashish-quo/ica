/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * @author sarvesh
 *
 */
public enum ARPU {
	
	LOW(4), MED(5), HIGH(6);
	
	private int indicator;
	
	/**
	 * 
	 */
	private ARPU(int indicator) {
		this.indicator = indicator; 
	}
	
	/**
	 * @return the indicator
	 */
	public int getIndicator() {
		return indicator;
	}

}
