/**
 * 
 */
package com.mobileum.roameranalytics.common;

import com.mobileum.roameranalytics.enums.Relation;
import com.mobileum.roameranalytics.enums.RoamType;

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
	public static String queryForAllCountries(final String roamType) {
		final StringBuilder query = new StringBuilder();
		final String countryIB = RAPropertyUtil.getProperty("common.table.country");
		final String countryRelationIB = RAPropertyUtil.getProperty("common.table.country.relation");
		final String tadigNetworkIB = RAPropertyUtil.getProperty("common.table.tadignetwork");
		final String gdpThreshold = RAPropertyUtil.getProperty("low.gdp.threshold");
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append(" select distinct countryIB.country countryName, countryIB.countryId countryId, ")
				.append(" countryRelationIB.isBordering bordering, ")
				.append(" countryIB.IsLeisureDestination leisure, ")
				.append(" countryIB.IsPremiumLeisureDestination leisurePremium, ")
				.append(" case when countryIB.gdp < ").append(gdpThreshold).append(" then 1 else 0 end lowGDP  ")
				.append(" from ").append(tadigNetworkIB).append(" tadigNetworkIB ")
				.append(" inner join ").append(countryIB).append(" countryIB ")
				.append(" on countryIB.countryid = tadigNetworkIB.countryid ")
				.append(" inner join ").append(countryRelationIB).append(" countryRelationIB ")
				.append(" on countryRelationIB.visitedcountryid = countryIB.countryid ")
				.append(" where tadigNetworkIB.mcc in (select distinct visitedmcc from ")
				.append(RAPropertyUtil.getProperty("out.table.trip"))
				.append(" ) and countryRelationIB.homecountryid in (select distinct countryid from ")
				.append(tadigNetworkIB).append(" where mcc in (select  homemcc from ")
				.append(RAPropertyUtil.getProperty("out.table.trip")).append(" limit 1) limit 1)  ");
		} else {
			query.append(" select distinct  countryIB.country countryName, countryIB.countryId countryId, ")
				.append(" countryRelationIB.isBordering bordering, ")
				.append(" countryIB.IsLeisureDestination leisure, ")
				.append(" countryIB.IsPremiumLeisureDestination leisurePremium, ")
				.append(" case when countryIB.gdp < 10000 then 1 else 0 end lowGDP  ")
				.append(" from  ").append(tadigNetworkIB).append(" tadigNetworkIB ")
				.append(" inner join ").append(countryIB).append(" countryIB ")
				.append(" on countryIB.countryid = tadigNetworkIB.countryid ")
				.append(" inner join ").append(countryRelationIB).append(" countryRelationIB ")
				.append(" on countryRelationIB.homecountryid = countryIB.countryid ")
				.append(" where tadigNetworkIB.mcc in (select distinct homemcc from ")
				.append(RAPropertyUtil.getProperty("in.table.trip"))
				.append(" ) and countryRelationIB.visitedcountryid in (select countryid from ")
				.append(tadigNetworkIB).append(" where mcc in (select  visitedmcc from ")
				.append(RAPropertyUtil.getProperty("in.table.trip")).append(" limit 1) limit 1) ");
		}
		query.append(" order by countryName ");
		return query.toString();
	}

	/**
	 * Query for distinct networks.
	 *
	 * @return the string
	 */
	public static String queryForDistinctNetworkGroups(final String roamType) {
		final StringBuilder query = new StringBuilder();
		if (RoamType.OUT.getRoamType().equalsIgnoreCase(roamType)) {
			query.append("select distinct tadignetwork.network_id networkId, network_name networkName ,T.mcc mcc, T.mnc mnc, networkgroup.NetworkGroupName groupName from ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork"))
				.append(" tadignetwork inner join (")
				.append(" select distinct trip.visitedmcc as mcc, trip.visitedmnc as mnc from ")
				.append(RAPropertyUtil.getProperty("out.table.trip"))
				.append(" trip ") 
				.append(" ) T on T.mcc = tadignetwork.mcc and T.mnc = tadignetwork.mnc ")
				.append(" inner join ").append(RAPropertyUtil.getProperty("common.table.networkgroup"))
				.append(" networkgroup on tadignetwork.network_id =  networkgroup.network_id ")
				.append(" order by network_name ");
		} else {
			query.append("select distinct tadignetwork.network_id networkId, network_name networkName ,T.mcc mcc, T.mnc mnc, networkgroup.NetworkGroupName groupName from ")
				.append(RAPropertyUtil.getProperty("common.table.tadignetwork"))
				.append(" tadignetwork inner join (")
				.append(" select distinct trip.homemcc as mcc, trip.homemnc as mnc from ")
				.append(RAPropertyUtil.getProperty("in.table.trip"))
				.append(" trip ") 
				.append(" ) T on T.mcc = tadignetwork.mcc and T.mnc = tadignetwork.mnc ")
				.append(" inner join ").append(RAPropertyUtil.getProperty("common.table.networkgroup"))
				.append(" networkgroup on tadignetwork.network_id =  networkgroup.network_id ")
				.append(" order by network_name ");
		}
		return query.toString();
	}

}
