/**
 * 
 */
package com.mobileum.roameranalytics.common;

/**
 * @author sarvesh
 *
 */
public enum Table {
	
	/** The attribute. */
	ATTRIBUTE("ATTRIBUTE"),
	
	/** The attribute category. */
	ATTRIBUTE_CATEGORY("ATTRIBUTE_CATEGORY"),
	
	/** The country. */
	COUNTRY("COUNTRY");
	
	/** The table name. */
	private String tableName;
	
	/**
	 * Instantiates a new table.
	 *
	 * @param tableName the table name
	 */
	Table(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.tableName;
	}

}
