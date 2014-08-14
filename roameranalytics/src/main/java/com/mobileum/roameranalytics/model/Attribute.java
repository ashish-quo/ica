/**
 * 
 */
package com.mobileum.roameranalytics.model;

import java.util.List;

/**
 * The Class Attribute.
 *
 * @author sarvesh
 */
public class Attribute {

	/** The id. */
	private long id;
	
	/** The attribute name. */
	private String attributeName;
	
	/** The module id. */
	private int moduleId;
	
	/** The type. */
	private int type;
	
	/** The icon. */
	private String icon;
	
	/** The view type. */
	private String viewType;
	
	/** The display order. */
	private int displayOrder;

	/** The attribute category list. */
	private List<AttributeCategory> attributeCategoryList;
	
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
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	/**
	 * @return the moduleId
	 */
	public int getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId the moduleId to set
	 */
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
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
	 * @return the viewType
	 */
	public String getViewType() {
		return viewType;
	}

	/**
	 * @param viewType the viewType to set
	 */
	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	/**
	 * @return the displayOrder
	 */
	public int getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * Sets the display order.
	 *
	 * @param displayOrder the displayOrder to set
	 */
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}

	/**
	 * @return the attributeCategoryList
	 */
	public List<AttributeCategory> getAttributeCategoryList() {
		return attributeCategoryList;
	}

	/**
	 * @param attributeCategoryList the attributeCategoryList to set
	 */
	public void setAttributeCategoryList(
			List<AttributeCategory> attributeCategoryList) {
		this.attributeCategoryList = attributeCategoryList;
	}
}
