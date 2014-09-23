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
	private long attrId;
	
	private String categValue;
	
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
	 * @return the attrId
	 */
	public long getAttrId() {
		return attrId;
	}

	/**
	 * @param attrId the attrId to set
	 */
	public void setAttrId(long attrId) {
		this.attrId = attrId;
	}

	/**
	 * @return the categValue
	 */
	public String getCategValue() {
		return categValue;
	}

	/**
	 * @param categValue the categValue to set
	 */
	public void setCategValue(String categValue) {
		this.categValue = categValue;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AttributeCategory [id=" + id + ", categName=" + categName
				+ ", attrId=" + attrId + ", categValue=" + categValue
				+ ", displayOrder=" + displayOrder + "]";
	}

	
}
