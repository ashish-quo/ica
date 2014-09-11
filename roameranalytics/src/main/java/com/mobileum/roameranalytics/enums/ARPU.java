/**
 * 
 */
package com.mobileum.roameranalytics.enums;

import com.mobileum.roameranalytics.exception.ApplicationException;

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
	
	private static final ARPU[] ENUMS = ARPU.values();

	public static ARPU of(int type) {
		if (type < 4 || type > 6) {
			throw new ApplicationException("Invalid value for ARPU type: "
					+ type);
		}
		return ENUMS[type - 4];
	}
	

}
