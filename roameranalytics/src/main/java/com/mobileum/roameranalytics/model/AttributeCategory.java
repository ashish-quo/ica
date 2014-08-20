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
	private String categoryName;
	
	/** The display text. */
	private String displayText;
	
	/** The attribute id. */
	private long attributeId;
	
	/** The icon. */
	private String icon;
	
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
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
		setDisplayText(categoryName);
	}

	/**
	 * @return the attributeId
	 */
	public long getAttributeId() {
		return attributeId;
	}

	/**
	 * @param attributeId the attributeId to set
	 */
	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
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
	 * @return the displayText
	 */
	public String getDisplayText() {
		return displayText;
	}

	/**
	 * @param displayText the displayText to set
	 */
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	
}
