/**
 * 
 */
package com.mobileum.roameranalytics.enums;

import com.mobileum.roameranalytics.exception.ApplicationException;

/**
 * @author sarvesh
 *
 */
public enum PaymentType {

	POSTPAID, PREPAID;
	
	private static final PaymentType[] ENUMS = PaymentType.values();
	
	/**
     * Of.
     *
     * @param type the type
     * @return the roamer type
     */
    public static PaymentType of(int type) {
        if (type < 0 || type > 1) {
            throw new ApplicationException("Invalid value for payment type: " + type);
        }
        return ENUMS[type];
    }

}
