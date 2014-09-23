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
	
	SILENT("Silent"), VALUE("Value"), PREMIUM("Premium");
	
    private static final RoamerType[] ENUMS = RoamerType.values();

    /** The display name. */
    private String displayName;
    
    /**
     * Instantiates a new roamer type.
     *
     * @param name the name
     */
	private RoamerType(String name) { 
		displayName = name;
	}
	
    /**
     * Of.
     *
     * @param type the type
     * @return the roamer type
     */
    public static RoamerType of(int type) {
        if (type < 1 || type > 3) {
            throw new ApplicationException("Invalid value for roamer type: " + type, new IllegalArgumentException());
        }
        return ENUMS[type - 1];
    }

    /**
     * Gets the display name.
     *
     * @return the displayName
     */
	public String getDisplayName() {
		return displayName;
	}
    
}
