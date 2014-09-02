/**
 * 
 */
package com.mobileum.roameranalytics.model;

/**
 * The Class AttributeCategory.
 *
 * @author sarvesh
 */
public class AttributeCategory {

	/** The id. */
	private long id;
	
	/** The category name. */
	private String categName;
	
	/** The attribute id. */
	private int attrInd;

	/** The cat ind. */
	private int catInd;
	
	/** The display order. */
	private int displayOrder;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the categName
	 */
	public String getCategName() {
		return categName;
	}

	/**
	 * @param categName the categName to set
	 */
	public void setCategName(String categName) {
		this.categName = categName;
	}

	/**
	 * @return the attrInd
	 */
	public int getAttrInd() {
		return attrInd;
	}

	/**
	 * @param attrInd the attrInd to set
	 */
	public void setAttrInd(int attrInd) {
		this.attrInd = attrInd;
	}

	/**
	 * @return the displayOrder
	 */
	public int getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * @return the catInd
	 */
	public int getCatInd() {
		return catInd;
	}

	/**
	 * @param catInd the catInd to set
	 */
	public void setCatInd(int catInd) {
		this.catInd = catInd;
	}

	
}
