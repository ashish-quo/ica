/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * Enum for trip time columns.
 * @author sarvesh
 *
 */
public enum TriptimeColumn {
	OPCOID("opcoid"),
	ROAMTYPE("roamtype"),
	HOMEMCC("homemcc"),
	HOMEMNC("homemnc"),
	VISITEDMCC("visitedmcc"),
	VISITEDMNC("visitedmnc"),
	IMSI("imsi"),
	
	TRIPSTARTTIME("tripstarttime"),
	TRIPENDTIME("tripendtime"),
	USAGEBINTIME("tripstarttime"),

	MOCALLCOUNT("mocallcount"),
	MOCALLMINUTES("mocallminutes"),
	MTCALLCOUNT("mtcallcount"),
	MTCALLMINUTES("mtcallminutes"),
	MOSMSCOUNT("mosmscount"),
	MTSMSCOUNT("mtsmscount"),
	UPLINK("uplink"),
	DOWNLINK("downlink"),
	
	
	MOCALLCOUNTLOCAL("mocallcountlocal"),
	MOCALLCOUNTHOME("mocallcounthome"),
	MOCALLCOUNTOTHERS("mocallcountothers"),
	
	MOCALLMINUTESLOCAL("mocallminuteslocal"),
	MOCALLMINUTESHOME("mocallminuteshome"),
	MOCALLMINUTESOTHERS("mocallminutesothers"),
	
	MTCALLCOUNTLOCAL("mtcallcountlocal"),
	MTCALLCOUNTHOME("mtcallcounthome"),
	MTCALLCOUNTOTHERS("mtcallcountothers"),
	
	MTCALLMINUTESLOCAL("mtcallminuteslocal"),
	MTCALLMINUTESHOME("mtcallminuteshome"),
	MTCALLMINUTESOTHERS("mtcallminutesothers"),
	
	MOSMSCOUNTLOCAL("mosmscountlocal"),
	MOSMSCOUNTHOME("mosmscounthome"),
	MOSMSCOUNTOTHERS("mosmscountothers"),
	
	MTSMSCOUNTLOCAL("mtsmscountlocal"),
	MTSMSCOUNTHOME("mtsmscounthome"),
	MTSMSCOUNTOTHERS("mtsmscountothers"),
	
	TRIPSTARTTIMESTRING("tripstarttimestring"),
	TRIPENDTIMESTRING("tripendtimestring"),

	VISITEDCOUNTRYNAME("visitedcountryname"),
	VISITEDNETWORKNAME("visitednetworkname"),
	HOMECOUNTRYNAME("homecountryname"),
	HOMENETWORKNAME("homenetworkname");
	
	private String name;
	private TriptimeColumn(final String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

}
