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
	//private String color;
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
//	/**
//	 * @return the color
//	 */
//	public String getColor() {
//		return color;
//	}
//	/**
//	 * @param color the color to set
//	 */
//	public void setColor(String color) {
//		this.color = color;
//	}
}
