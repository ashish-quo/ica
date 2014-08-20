/**
 * 
 */
package com.mobileum.roameranalytics.common;

/**
 * The Class QueryBuilder. Creates dynamic queries.
 *
 * @author sarvesh
 */
public class QueryBuilder {

	/**
	 * Creates query for getting all attributes for left panel
	 * @return query
	 */
	public static String queryForAttributes() {
		StringBuilder query = new StringBuilder();
		query.append("select attr.id attrId, attr.attribute_name attrName, attr.module_id moduleId, ")
				.append(" attr.type attrType,  attr.icon attrIcon, attr.view_type viewType, ")
				.append(" attr.display_order attrDO, attrCat.category_name catName,")
				.append(" attrCat.icon catIcon from ")
				.append(Table.ATTRIBUTE).append(" attr inner join ")
				.append(Table.ATTRIBUTE_CATEGORY)
				.append(" attrCat on attr.id = attrCat.attribute_id ")
				.append(" order by attr.display_order, attrCat.attribute_id, attrCat.display_order");
		return query.toString();
	}
	
	/**
	 * Query for all countries.
	 *
	 * @return the string
	 */
	public static String queryForAllCountries() {
		StringBuilder query = new StringBuilder();
		query.append(" select country_name countryName, country_code countryCode from ").append(Table.COUNTRY)
			.append(" order by country_name");
		return query.toString();
	}
}
