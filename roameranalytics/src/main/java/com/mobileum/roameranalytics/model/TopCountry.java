/**
 * 
 */
package com.mobileum.roameranalytics.model;

import java.util.List;

/**
 * @author smruti
 *
 */
public class TopCountry {

	List<RoamingStats>	topRoamer;

	List<RoamingStats>	topMo;

	List<RoamingStats>	topMt;

	List<RoamingStats>	topData;
	
	List<RoamingStats> topSms;

	/**
	 * @return the topRoamer
	 */
	public List<RoamingStats> getTopRoamer() {
		return topRoamer;
	}

	/**
	 * @param topRoamer the topRoamer to set
	 */
	public void setTopRoamer(List<RoamingStats> topRoamer) {
		this.topRoamer = topRoamer;
	}

	/**
	 * @return the topMo
	 */
	public List<RoamingStats> getTopMo() {
		return topMo;
	}

	/**
	 * @param topMo the topMo to set
	 */
	public void setTopMo(List<RoamingStats> topMo) {
		this.topMo = topMo;
	}

	/**
	 * @return the topMt
	 */
	public List<RoamingStats> getTopMt() {
		return topMt;
	}

	/**
	 * @param topMt the topMt to set
	 */
	public void setTopMt(List<RoamingStats> topMt) {
		this.topMt = topMt;
	}

	/**
	 * @return the topData
	 */
	public List<RoamingStats> getTopData() {
		return topData;
	}

	/**
	 * @param topData the topData to set
	 */
	public void setTopData(List<RoamingStats> topData) {
		this.topData = topData;
	}

	/**
	 * @return the topSms
	 */
	public List<RoamingStats> getTopSms() {
		return topSms;
	}

	/**
	 * @param topSms the topSms to set
	 */
	public void setTopSms(List<RoamingStats> topSms) {
		this.topSms = topSms;
	}
	
	

	
}
