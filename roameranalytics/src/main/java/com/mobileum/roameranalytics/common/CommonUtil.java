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
	public static <T> List<Object> convertToList(final String css, final String type ) {
		final String[] strArray =  css.split(RAConstants.COMMA);
		final List<Object> list = new ArrayList<Object>(5);
		for(final String str : strArray) {
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
	public static  Map<String,String> parseSelectedAttributes(final String attributes) {
		final String[] attrArray = attributes.split(RAConstants.HASH);
		final Map<String,String> attributeMap = new HashMap<String, String>();
		
		for (final String attrInd : attrArray) {
			final String[] currentAttribute = attrInd.split(":");
			final String attributeKey[] =  currentAttribute[0].trim().split(",");
			final String key = attributeKey[1];
			final String value = attributeKey[2] + ":" + currentAttribute[1].trim();
			attributeMap.put(key,value );
		}
		return attributeMap;
	}
	
	public static String covnertToCommaSeparatedString(final Collection<? extends Object> list) {
		final StringBuilder result = new StringBuilder();
		boolean first = true;
		for (final Object object : list) {
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
	
	public static String covnertToCommaSeparatedString(final String css, final String type) {
		final StringBuilder result = new StringBuilder();
		if (String.class.getName().equalsIgnoreCase(type)) {
			final String[] strArray =  css.split(RAConstants.COMMA);
			boolean first = true;
			for(final String str : strArray) {
				if (first) {
					first = false;
				} else {
					result.append(",");
				}
				result.append("'").append(str).append("'");
			}
			return result.toString();
		} else {
			return css;
		}
	}
}
