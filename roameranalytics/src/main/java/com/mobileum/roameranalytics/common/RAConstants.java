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
	public static final String APPLICATION_EXCEPTION_STRING = "Internal server error.";
	
	/** The Constant COMMA. */
	public static final String COMMA = ",";
	
	/** The Constant PIPE. */
	public static final String PIPE = "|";
	
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
	
	/** The Constant ATTR_NETWORK. */
	public static final String ATTR_DEVICE_MODEL = "Device Type";
	
	/** The Constant ATTR_NETWORK_GROUP. */
	public static final String ATTR_MANUFACTURER = "Device Manufacturer";
	
	/** The Constant ATTR_OTHER_COUNTRIES_TRAVLED. */
	public static final String ATTR_OTHER_COUNTRIES_TRAVLED = "Other Countries Traveled";
	
	public static final String NEIGHBOURS = "Neighbours";
	public static final String LEISURE = "Leisure";
	public static final String LEISURE_PREMIUM = "Leisure Premium";
	public static final String LOW_GDP = "Low GDP";
	
	public static final String DEFAULT_MICROSEGMENT_CHART_LOADING_COUNT = "4";
	
	
}
