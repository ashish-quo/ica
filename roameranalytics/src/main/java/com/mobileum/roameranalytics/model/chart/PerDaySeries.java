/**
 * 
 */
package com.mobileum.roameranalytics.model.chart;

/**
 * @author sarvesh
 *
 */
public class PerDaySeries extends ChartSeries {
	private long pointStart;
	private long pointInterval = 24 * 3600 * 1000L;
	/**
	 * @return the pointStart
	 */
	public long getPointStart() {
		return pointStart;
	}
	/**
	 * @param pointStart the pointStart to set
	 */
	public void setPointStart(long pointStart) {
		this.pointStart = pointStart;
	}
	/**
	 * @return the pointinterval
	 */
	public  long getPointInterval() {
		return 24 * 3600 * 1000;
	}
	
	/**
	 * @param pointInterval the pointInterval to set
	 */
	public void setPointInterval(Long pointInterval) {
		this.pointInterval = pointInterval;
	}
}
