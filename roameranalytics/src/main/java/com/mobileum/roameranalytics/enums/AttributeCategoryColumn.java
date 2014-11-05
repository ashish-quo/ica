/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * Enum for attribute category columns
 * @author sarvesh
 *
 */
public enum AttributeCategoryColumn {
	categ_name("categ_name"),
	display_order("display_order"),
	attr_id("attr_id"),
	categ_value("categ_value");


	private String name;
	private AttributeCategoryColumn(final String name) {
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

}
