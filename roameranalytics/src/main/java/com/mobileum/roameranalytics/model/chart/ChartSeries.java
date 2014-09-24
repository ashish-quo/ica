/**
 * 
 */
package com.mobileum.roameranalytics.model.chart;

import java.util.Collection;

/**
 * @author sarvesh
 *
 */
public class ChartSeries {

	private String name;
	private Collection<Double> data;
	private boolean showInLegend = true;
	
	public boolean getShowInLegend() {
		return showInLegend;
	}
	
	/**
	 * @param showInLegend the showInLegend to set
	 */
	public void setShowInLegend(boolean showInLegend) {
		this.showInLegend = showInLegend;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the data
	 */
	public Collection<Double> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Collection<Double> data) {
		this.data = data;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ChartSeries [name=" + name + ", data=" + data
				+ ", showInLegend=" + showInLegend + "]";
	}
	
}
