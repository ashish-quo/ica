/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Defines application constants
 * @author sarvesh
 *
 */
public class RAConstants {

	/** The Constant DEFAULT_DATE_FORMAT. */
	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yy";
	
	/** The Constant APPLICATION_EXCEPTION_STRING. */
	public static final String APPLICATION_EXCEPTION_STRING = "Application Exception : ";
	
	/** The Constant COMMA. */
	public static final String COMMA = ",";
	
	/** The Constant COLON. */
	public static final String COLON = ":";

	/** The Constant HASH. */
	public static final String HASH = "#";
	
	/** The Constant attributeNameValueCache. */
	public static final Map<String,Map<String,String>> attributeNameValueCache 
		= new HashMap<String, Map<String,String>>(100);
	
	/** The Constant ATTR_NETWORK. */
	public static final String ATTR_NETWORK = "Network";
	
	/** The Constant ATTR_NETWORK_GROUP. */
	public static final String ATTR_NETWORK_GROUP = "Network Group";
	
}
