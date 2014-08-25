/**
 * 
 */
package com.mobileum.roameranalytics.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sarvesh
 *
 */
public class RoamingTrendChart {

	class Series {
		
		/** The name. */
		private String name;
		
		/** The data. */
		private double[] data;
		
		/**
		 * Instantiates a new series.
		 *
		 * @param days the days
		 */
		Series(int days) {
			data = new double[days];
		}
	}
	
	/** The serieses. */
	private List<Series> serieses;
	
	/** The categories. */
	private List<String> categories;
	
	/**
	 * Instantiates a new roaming trend chart.
	 */
	public RoamingTrendChart() {
		serieses = new ArrayList<Series>();
		categories = new ArrayList<String>();
	}
}
