/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.Arrays;

/**
 * Contains common utility functions
 * @author sarvesh
 *
 */
public class CommonUtil {

	/**
	 * Converts a comma separated string to object array
	 * @param css - comma separated string
	 * @return object array
	 */
	public static Object[] convertToObjectArray(String css) {
		String[] strArray = css.split(RAConstants.COMMA);
		return Arrays.asList(strArray).toArray();
	}
}
