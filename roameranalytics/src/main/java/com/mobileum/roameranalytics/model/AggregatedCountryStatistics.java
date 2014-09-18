/**
 * 
 */
package com.mobileum.roameranalytics.model;

import java.util.List;

/**
 * @author smruti
 *
 */
public class AggregatedCountryStatistics {

	List<RoamingStatistics>	topRoamer;

	List<RoamingStatistics>	topMo;

	List<RoamingStatistics>	topMt;

	List<RoamingStatistics>	topData;
	
	List<RoamingStatistics> topSms;

	/**
	 * @return the topRoamer
	 */
	public List<RoamingStatistics> getTopRoamer() {
		return topRoamer;
	}

	/**
	 * @param topRoamer the topRoamer to set
	 */
	public void setTopRoamer(List<RoamingStatistics> topRoamer) {
		this.topRoamer = topRoamer;
	}

	/**
	 * @return the topMo
	 */
	public List<RoamingStatistics> getTopMo() {
		return topMo;
	}

	/**
	 * @param topMo the topMo to set
	 */
	public void setTopMo(List<RoamingStatistics> topMo) {
		this.topMo = topMo;
	}

	/**
	 * @return the topMt
	 */
	public List<RoamingStatistics> getTopMt() {
		return topMt;
	}

	/**
	 * @param topMt the topMt to set
	 */
	public void setTopMt(List<RoamingStatistics> topMt) {
		this.topMt = topMt;
	}

	/**
	 * @return the topData
	 */
	public List<RoamingStatistics> getTopData() {
		return topData;
	}

	/**
	 * @param topData the topData to set
	 */
	public void setTopData(List<RoamingStatistics> topData) {
		this.topData = topData;
	}

	/**
	 * @return the topSms
	 */
	public List<RoamingStatistics> getTopSms() {
		return topSms;
	}

	/**
	 * @param topSms the topSms to set
	 */
	public void setTopSms(List<RoamingStatistics> topSms) {
		this.topSms = topSms;
	}
	
	

	
}
