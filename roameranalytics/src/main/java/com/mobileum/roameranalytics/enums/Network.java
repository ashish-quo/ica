/**
 * 
 */
package com.mobileum.roameranalytics.enums;

import com.mobileum.roameranalytics.exception.ApplicationException;

/**
 * @author sarvesh
 *
 */
public enum Network {
	
	VODAFONE, IDEA, AIRTEL;
	
	private static final Network[] ENUMS = Network.values();
	
	/**
     * Of.
     *
     * @param type the type
     * @return the roamer type
     */
    public static Network of(int type) {
        if (type < 1 || type > 3) {
            throw new ApplicationException("Invalid value for network type: " + type);
        }
        return ENUMS[type - 1];
    }

}
