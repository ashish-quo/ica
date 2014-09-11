/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	

	/**
	 * Parses the selected attributes. Splits by # to get selected attributes and categories
	 *
	 * @param attributes the attributes
	 * @return the map <attribute indicator, comma separated string of sub attribute indicators>
	 */
	public static  Map<Integer,String> parseSelectedAttributes(String attributes) {
		String[] attrArray = attributes.split(RAConstants.HASH);
		Map<Integer,String> attributeMap = new HashMap<Integer, String>();
		for (String attrInd : attrArray) {
			String[] currentAttribute = attrInd.split(":");
			attributeMap.put(Integer.valueOf(currentAttribute[0].trim()), currentAttribute[1].trim());
		}
		return attributeMap;
	}
}
