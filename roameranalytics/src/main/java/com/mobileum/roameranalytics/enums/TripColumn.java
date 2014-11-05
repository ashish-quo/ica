/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * Enum for trip table column names
 * @author sarvesh
 *
 */
public enum TripColumn {
	OPCOID("opcoid"),
	ROAMTYPE("roamtype"),
	IMSI("imsi"),
	HOMEMCC("homemcc"),
	HOMEMNC("homemnc"),
	VISITEDMCC("visitedmcc"),
	VISITEDMNC("visitedmnc"),
	MSISDN("msisdn"),
	STARTTIME("starttime"),
	ENDTIME("endtime"),
	IMEI("imei"),
	TAC("tac"),
	CHARGINGPLAN("chargingplan"),
	PRICEPLAN("priceplan"),
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
	
	MOCALLCOUNTQUADROAM("mocallcountquadroam"),
	MOCALLMINUTESQUADROAM("mocallminutesquadroam"),
	MTCALLCOUNTQUADROAM("mtcallcountquadroam"),
	MTCALLMINUTESQUADROAM("mtcallminutesquadroam"),
	MOSMSCOUNTQUADROAM("mosmscountquadroam"),
	MTSMSCOUNTQUADROAM("mtsmscountquadroam"),
	UPLINKQUADROAM("uplinkquadroam"),
	DOWNLINKQUADROAM("downlinkquadroam"),
	TONNAGEQUADROAM("tonnagequadroam"),
	OVERALLTRIPCATEGORY("overalltripcategory"),
	MOCALLCOUNTQUADDOMESTIC("mocallcountquaddomestic"),
	MOCALLMINUTESQUADDOMESTIC("mocallminutesquaddomestic"),
	MTCALLCOUNTQUADDOMESTIC("mtcallcountquaddomestic"),
	MTCALLMINUTESQUADDOMESTIC("mtcallminutesquaddomestic"),
	MOSMSCOUNTQUADDOMESTIC("mosmscountquaddomestic"),
	
	MTSMSCOUNTQUADDOMESTIC("mtsmscountquaddomestic"),
	UPLINKQUADDOMESTIC("uplinkquaddomestic"),
	DOWNLINKQUADDOMESTIC("downlinkquaddomestic"),
	TONNAGEQUADDOMESTIC("tonnagequaddomestic"),
	OVERALLDOMESTICCATEGORY("overalldomesticcategory"),
	MSISDNTIMESTAMP("msisdntimestamp"),
	IMEITIMESTAMP("imeitimestamp"),
	CHARGINGPLANAPPROXIMITY("chargingplanapproximity"),
	STARTTIMESTRING("starttimestring"),
	ENDTIMESTRING("endtimestring"),
	VISITEDCOUNTRYNAME("visitedcountryname"),
	VISITEDNETWORKNAME("visitednetworkname"),
	HOMECOUNTRYNAME("homecountryname"),
	HOMENETWORKNAME("homenetworkname"),
	DEVICENAME("devicename"),
	DEVICEMANUFACTURER("devicemanufacturer");
	
	private String name;
	private TripColumn(final String name) {
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
