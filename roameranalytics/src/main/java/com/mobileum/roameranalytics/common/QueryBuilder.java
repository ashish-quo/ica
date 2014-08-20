/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.ArrayList;
import java.util.List;

import com.mobileum.roameranalytics.dao.Criteria;
import com.mobileum.roameranalytics.dao.SelectQuery;
import com.mobileum.roameranalytics.dao.Table;

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
		/*query.append("select attr.id attrId, attr.attribute_name attrName, attr.module_id moduleId, ")
				.append(" attr.type attrType,  attr.icon attrIcon, attr.view_type viewType, ")
				.append(" attr.display_order attrDO, attrCat.category_name catName,")
				.append(" attrCat.icon catIcon from ")
				.append(Table.ATTRIBUTE).append(" attr inner join ")
				.append(Table.ATTRIBUTE_CATEGORY)
				.append(" attrCat on attr.id = attrCat.attribute_id ")
				.append(" order by attr.display_order, attrCat.attribute_id, attrCat.display_order");*/
		return query.toString();
	}
	

	public static String queryForHeatMap() {
				
		SelectQuery selectQuery=new SelectQuery();
		Table table=new Table("roaming_usage","ru");
		table.addColumnsToSelect("mo_local");
		table.addColumnsToSelect("mo_out");
		table.addGroupFunctions("sum(mo_local) mo_local");
		
		Table table1=new Table("trip","tp");
		table1.addColumnsToSelect("stayduration");
	
		
		selectQuery.addTable(table);
		selectQuery.addTable(table1);
		
		selectQuery.addCriteria(table, "visited_country", Criteria.EQUALS, "NG");
		selectQuery.addJoin(table,"visited_country", table1, "visited_country");
		List<String> roamertype=new ArrayList();
		
		roamertype.add("silent");
		roamertype.add("premium");
		
		selectQuery.addCriteria(table, "roamer_type", Criteria.IN, roamertype);
		selectQuery.addGroupByColumn(table, "country");
		selectQuery.addGroupByColumn(table, "roamer_type");
		
		
		System.out.println(selectQuery.toString());
		 return selectQuery.toString();
	}	

	/**
	 * Query for all countries.
	 *
	 * @return the string
	 */
	public static String queryForAllCountries() {
		StringBuilder query = new StringBuilder();
		/*query.append(" select country_name countryName, country_code countryCode from ").append(Table.COUNTRY)
			.append(" order by country_name");*/
		return query.toString();

	}
}
