/**
 * 
 */
package com.mobileum.roameranalytics.enums;

// TODO: Auto-generated Javadoc
/**
 * The Enum Relation.
 *
 * @author sarvesh
 */
public enum Relation {
	
	/** The attribute. */
	ATTRIBUTE("ATTRIBUTE"),
	
	/** The attribute category. */
	ATTRIBUTE_CATEGORY("ATTRIBUTE_CATEGORY"),
	
	/** The country. */
	COUNTRY("COUNTRY_IB"),
	
	/** The trip time. */
	TRIP_TIME("TRIPTIME"),
	
	/** The tadignetwork. */
	TADIGNETWORK("TADIGNETWORK"),
	
	/** The trip. */
	TRIP("TRIP");
	
	/** The table name. */
	private String tableName;
	
	/**
	 * Instantiates a new table.
	 *
	 * @param tableName the table name
	 */
	Relation(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * Gets the table name.
	 *
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
