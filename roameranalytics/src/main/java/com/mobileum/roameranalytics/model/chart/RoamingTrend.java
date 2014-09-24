/**
 * 
 */
package com.mobileum.roameranalytics.model.chart;

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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoamingTrend [roamersCountChart=" + roamersCountChart
				+ ", roamersMTMOChart=" + roamersMTMOChart
				+ ", roamersSMSChart=" + roamersSMSChart
				+ ", roamersDataChart=" + roamersDataChart + "]";
	}
}
