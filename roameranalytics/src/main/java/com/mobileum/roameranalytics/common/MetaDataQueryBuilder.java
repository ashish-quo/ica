/**
 * 
 */
package com.mobileum.roameranalytics.common;

import com.mobileum.roameranalytics.enums.BusinessTableColumn;
import com.mobileum.roameranalytics.enums.Relation;
import com.mobileum.roameranalytics.enums.RoamType;
import com.mobileum.roameranalytics.model.Filter;

/**
 * @author sarvesh
 *
 */
public class MetaDataQueryBuilder {

	/**
	 * Creates query for getting all attributes for left panel
	 * @return query
	 */
	public static String queryForAttributes(final String roamType) {
		final StringBuilder query = new StringBuilder();
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append("select attr.id attrId, attr.attribute_name attrName, attr.module_id moduleId, ")
				.append(" attr.db_column db_column,  attr.column_type column_type, attr.chart_type chart_type, ")
				.append(" attrCat.categ_name catName, attrCat.categ_value catValue, attrCat.id catId ")
				.append(" from ").append(Relation.ATTRIBUTE).append(" attr left join ")
				.append(Relation.ATTRIBUTE_CATEGORY)
				.append(" attrCat on attr.id = attrCat.attr_id ")
				.append(" order by attr.display_order, attrCat.display_order");
		} else {
			query.append("select attr.id attrId, attr.attribute_name attrName, attr.module_id moduleId, ")
				.append(" attr.db_column_in db_column,  attr.column_type column_type, attr.chart_type chart_type, ")
				.append(" attrCat.categ_name catName, attrCat.categ_value catValue, attrCat.id catId ")
				.append(" from ").append(Relation.ATTRIBUTE).append(" attr left join ")
				.append(Relation.ATTRIBUTE_CATEGORY)
				.append(" attrCat on attr.id = attrCat.attr_id ")
				.append(" order by attr.display_order, attrCat.display_order");
		}
		
		
		return query.toString();
	}

	/**
	 * Query for all countries.
	 *
	 * @return the string
	 */
	public static String queryForCountries(final Filter filter,final String roamType) {
		final StringBuilder query = new StringBuilder();

		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" select distinct trip.").append(BusinessTableColumn.VISITEDCOUNTRY).append(" countryName,")
				.append(" trip.").append(BusinessTableColumn.VISITEDMCC).append(" mcc, ")
				.append(" trip.").append(BusinessTableColumn.ISNEIGHBOURING).append(" bordering, ")
				.append(" trip.").append(BusinessTableColumn.ISVISITEDCOUNTRYLEISURE).append(" leisure, ")
				.append(" trip.").append(BusinessTableColumn.ISVISITEDCOUNTRYLEISUREPREMIUM).append(" leisurePremium, ")
				.append(" trip.").append(BusinessTableColumn.VISITEDCOUNTRYGDP).append(" lowGDP ")
				.append(" from ").append(RAPropertyUtil.getProperty("out.table.business")).append(" trip ")
				.append(" where trip.usagebintime >= ").append(filter.getDateFrom())
				.append(" and trip.usagebintime <= ").append(filter.getDateTo()).append(" ");
			
			query.append(" order by trip.").append(BusinessTableColumn.VISITEDCOUNTRY);
		} else {
			query.append(" select distinct trip.").append(BusinessTableColumn.HOMECOUNTRY).append(" countryName,")
				.append(" trip.").append(BusinessTableColumn.HOMEMCC).append(" mcc, ")
				.append(" trip.").append(BusinessTableColumn.ISNEIGHBOURING).append(" bordering, ")
				.append(" trip.").append(BusinessTableColumn.ISHOMECOUNTRYLEISURE).append(" leisure, ")
				.append(" trip.").append(BusinessTableColumn.ISHOMECOUNTRYLEISUREPREMIUM).append(" leisurePremium, ")
				.append(" trip.").append(BusinessTableColumn.HOMECOUNTRYGDP).append(" lowGDP ")
				.append(" from ").append(RAPropertyUtil.getProperty("in.table.business")).append(" trip ")
				.append(" where trip.usagebintime >= ").append(filter.getDateFrom())
				.append(" and trip.usagebintime <= ").append(filter.getDateTo()).append(" ");
			query.append(" order by trip.").append(BusinessTableColumn.HOMECOUNTRY);
				
		}
		
		return query.toString();
	}
	
	/**
	 * Query for distinct networks.
	 *
	 * @return the string
	 */
	public static String queryForNetworkGroups(final Filter filter,final String roamType) {
		final StringBuilder query = new StringBuilder();
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append("select distinct trip.").append(BusinessTableColumn.VISITEDMCC).append(" mcc, ")
				.append(" trip.").append(BusinessTableColumn.VISITEDMNC).append(" mnc, ")
				.append(" trip.").append(BusinessTableColumn.VISITEDNETWORK).append(" networkName, ")
				.append(" trip.").append(BusinessTableColumn.VISITEDNETWORKGROUP).append(" networkGroup ")
				.append(" from ").append(RAPropertyUtil.getProperty("out.table.business")).append(" trip ")
				.append(" where trip.usagebintime >= ").append(filter.getDateFrom())
				.append(" and trip.usagebintime <= ").append(filter.getDateTo()).append(" ")
				.append(" order by trip.").append(BusinessTableColumn.VISITEDNETWORK);
		} else {
			query.append("select distinct trip.").append(BusinessTableColumn.HOMEMCC).append(" mcc, ")
				.append(" trip.").append(BusinessTableColumn.HOMEMNC).append(" mnc, ")
				.append(" trip.").append(BusinessTableColumn.HOMENETWORK).append(" networkName, ")
				.append(" trip.").append(BusinessTableColumn.HOMENETWORKGROUP).append(" networkGroup ")
				.append(" from ").append(RAPropertyUtil.getProperty("in.table.business")).append(" trip ")
				.append(" where trip.usagebintime >= ").append(filter.getDateFrom())
				.append(" and trip.usagebintime <= ").append(filter.getDateTo()).append(" ")
				.append(" order by trip.").append(BusinessTableColumn.HOMENETWORK);
		}
		return query.toString();
	}
}
