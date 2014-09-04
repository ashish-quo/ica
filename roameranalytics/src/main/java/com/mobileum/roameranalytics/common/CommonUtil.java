/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.List;

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
	public static List<Integer> convertToList(String css) {
		String[] strArray = css.split(RAConstants.COMMA);
		List<Integer> list = new ArrayList<Integer>(5);
		for(String str : strArray) {
			list.add(Integer.valueOf(str.trim()));
		}
		return list;
	}
}
