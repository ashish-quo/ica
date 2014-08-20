/**
 * 
 */
package com.mobileum.roameranalytics.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author smruti
 *
 */
public class TopCountry {

	Map <String,ArrayList<RoamingStats>>topTen;

	/**
	 * @return the topTen
	 */
	public Map<String, ArrayList<RoamingStats>> getTopTen() {
		return topTen;
	}

	/**
	 * @param topTen the topTen to set
	 */
	public void setTopTen(Map<String, ArrayList<RoamingStats>> topTen) {
		this.topTen = topTen;
	}
}
