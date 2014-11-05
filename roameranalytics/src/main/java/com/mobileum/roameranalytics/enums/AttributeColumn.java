/**
 * 
 */
package com.mobileum.roameranalytics.enums;

/**
 * Enum for attribute table columns
 * @author sarvesh
 *
 */
public enum AttributeColumn {
	ATTRIBUTE_NAME("attribute_name"),
	MODULE_ID("module_id"),
	DISPLAY_ORDER("display_order"),
	COLUMN_TYPE("column_type"),
	CHART_TYPE("chart_type"),
	DB_COLUMN_IN("db_column_in");

	private String name;
	private AttributeColumn(final String name) {
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
