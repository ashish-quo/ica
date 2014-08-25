/**
 * 
 */
package com.mobileum.roameranalytics.model;

/**
 * @author sarvesh
 *
 */
public class RoamingTrend {

	/** The roamers count chart. */
	private RoamingTrendChart roamersCountChart;
	
	/** The roamers mt mo chart. */
	private RoamingTrendChart roamersMTMOChart;
	
	/** The roamers sms chart. */
	private RoamingTrendChart roamersSMSChart;
	
	/** The roamers data chart. */
	private RoamingTrendChart roamersDataChart;

	public RoamingTrend() {
		roamersMTMOChart = new RoamingTrendChart();
		roamersCountChart = new RoamingTrendChart();
		roamersSMSChart = new RoamingTrendChart();
		roamersDataChart = new RoamingTrendChart();
		
	}
	/**
	 * @return the roamersCountChart
	 */
	public RoamingTrendChart getRoamersCountChart() {
		return roamersCountChart;
	}

	/**
	 * @param roamersCountChart the roamersCountChart to set
	 */
	public void setRoamersCountChart(RoamingTrendChart roamersCountChart) {
		this.roamersCountChart = roamersCountChart;
	}

	/**
	 * @return the roamersMTMOChart
	 */
	public RoamingTrendChart getRoamersMTMOChart() {
		return roamersMTMOChart;
	}

	/**
	 * @param roamersMTMOChart the roamersMTMOChart to set
	 */
	public void setRoamersMTMOChart(RoamingTrendChart roamersMTMOChart) {
		this.roamersMTMOChart = roamersMTMOChart;
	}

	/**
	 * @return the roamersSMSChart
	 */
	public RoamingTrendChart getRoamersSMSChart() {
		return roamersSMSChart;
	}

	/**
	 * @param roamersSMSChart the roamersSMSChart to set
	 */
	public void setRoamersSMSChart(RoamingTrendChart roamersSMSChart) {
		this.roamersSMSChart = roamersSMSChart;
	}

	/**
	 * @return the roamersDataChart
	 */
	public RoamingTrendChart getRoamersDataChart() {
		return roamersDataChart;
	}

	/**
	 * @param roamersDataChart the roamersDataChart to set
	 */
	public void setRoamersDataChart(RoamingTrendChart roamersDataChart) {
		this.roamersDataChart = roamersDataChart;
	}
}
