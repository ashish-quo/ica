/**
 * 
 */
package com.mobileum.roameranalytics.model.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class DonutData.
 *
 * @author sarvesh
 */
public class DonutData {

	/** The label. */
	private String label;
	
	private int categoryId;
	
	
	/** The value. */
	private double value;
	
	//private double roamers;
	
//	private double mo;
//	
//	private double mt;
//	
//	private double data;
	
	private List<Object> roamers = new ArrayList<Object>();
	
	private List<Object> mts = new ArrayList<Object>();
	
	private List<Object> mos = new ArrayList<Object>();
	
	private List<Object> data = new ArrayList<Object>();
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * @return the categoryId
	 */
	public int getCategoryId() {
		return categoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * @return the roamers
	 */
	public List<Object> getRoamers() {
		return roamers;
	}
	/**
	 * @param roamers the roamers to set
	 */
	public void setRoamers(List<Object> roamers) {
		this.roamers = roamers;
	}
	/**
	 * @return the mts
	 */
	public List<Object> getMts() {
		return mts;
	}
	/**
	 * @param mts the mts to set
	 */
	public void setMts(List<Object> mts) {
		this.mts = mts;
	}
	/**
	 * @return the mos
	 */
	public List<Object> getMos() {
		return mos;
	}
	/**
	 * @param mos the mos to set
	 */
	public void setMos(List<Object> mos) {
		this.mos = mos;
	}
	/**
	 * @return the data
	 */
	public List<Object> getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(List<Object> data) {
		this.data = data;
	}
}
