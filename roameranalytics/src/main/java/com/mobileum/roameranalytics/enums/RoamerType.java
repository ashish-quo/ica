/**
 * 
 */
package com.mobileum.roameranalytics.enums;

import com.mobileum.roameranalytics.exception.ApplicationException;

/**
 * @author sarvesh
 *
 */
public enum RoamerType {
	
	SILENT, VALUE, PREMIUM;
	
    private static final RoamerType[] ENUMS = RoamerType.values();

    public static RoamerType of(int type) {
        if (type < 1 || type > 3) {
            throw new ApplicationException("Invalid value for roamer type: " + type);
        }
        return ENUMS[type - 1];
    }

}
