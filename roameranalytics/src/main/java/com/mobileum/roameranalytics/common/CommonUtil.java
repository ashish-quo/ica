/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.Collection;
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
	public static <T> List<Object> convertToList(String css, String type ) {
		String[] strArray =  css.split(RAConstants.COMMA);
		List<Object> list = new ArrayList<Object>(5);
		for(String str : strArray) {
			if (Integer.class.getName().equalsIgnoreCase(type))
				list.add(Integer.valueOf(str));
			else 
				list.add(str);
		}
		return list;
	}
	

	/**
	 * Parses the selected attributes. Splits by # to get selected attributes and categories
	 *
	 * @param attributes the attributes
	 * @return the map <attribute indicator, comma separated string of sub attribute indicators>
	 */
	public static  Map<String,String> parseSelectedAttributes(String attributes) {
		String[] attrArray = attributes.split(RAConstants.HASH);
		Map<String,String> attributeMap = new HashMap<String, String>();
		
		for (String attrInd : attrArray) {
			String[] currentAttribute = attrInd.split(":");
			String attributeKey[] =  currentAttribute[0].trim().split(",");
			String key = attributeKey[1];
			String value = attributeKey[2] + ":" + currentAttribute[1].trim();
			attributeMap.put(key,value );
		}
		return attributeMap;
	}
	
	public static String covnertToCommaSeparatedString(Collection<? extends Object> list) {
		StringBuilder result = new StringBuilder();
		boolean first = true;
		for (Object object : list) {
			if (first) {
				first = false;
			} else {
				result.append(",");
			}
			if (object instanceof String) {
				result.append("'").append(object).append("'");
			} else {
				result.append(object);
			}
			
		}
		return result.toString();
	}
}
