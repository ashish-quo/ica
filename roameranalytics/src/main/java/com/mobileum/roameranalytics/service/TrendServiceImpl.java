/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.dao.Criteria;
import com.mobileum.roameranalytics.dao.SelectQuery;
import com.mobileum.roameranalytics.dao.Table;
import com.mobileum.roameranalytics.dao.TrendDaoI;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.HeatMap;

/**
 * @author Quovantis_Dev
 *
 */
@Service
public class TrendServiceImpl implements TrendServiceI{

	private CommonServiceI commonService;

	/** The trend dao. */
	@Autowired
	private TrendDaoI trendDao;
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger("CommonServiceImpl");
	
	
	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.service.TrendServiceI#getAttributes()
	 */
	public Map<String, List<Attribute>> getAttributes() {
		Map<String, List<Attribute>> attributeMap = new LinkedHashMap<String, List<Attribute>>();
		List<Attribute> attributeList = trendDao.getAttributeList();
		for (Attribute attribute : attributeList) {
			String viewType = attribute.getViewType();
			List<Attribute> list = attributeMap.get(viewType);
			if (list == null) {
				list = new ArrayList<Attribute>();
				attributeMap.put(viewType, list);
			}
			list.add(attribute);
		}
		return attributeMap;
	}

	public void printQuery()
	{
		System.out.println("hii"+QueryBuilder.queryForHeatMap());
	}
	
	public  Map<String,HeatMap> getHeatMap(String startDate, String endDate, String country){
		Map<String,HeatMap> mapHeatMap =new LinkedHashMap<String,HeatMap>();

//		Table table1=new Table("trip","tp");
//		table1.addGroupFunctions("sum(mocallcount) mocallcount");
//		table1.addGroupFunctions("sum(mtcallcount) mtcallcount");
//		table1.addGroupFunctions("sum(mosmscount) mosmscount");
//		table1.addGroupFunctions("sum(uplink+downlink) modatacount");
//		table1.addGroupFunctions("visitedcountryname");
//
//
//		SelectQuery sql=new SelectQuery();
//		sql.addTable(table1);
//		sql.addCriteria(table1, "visitedcountryname",Criteria.EQUALS, "?");
//		sql.addCriteria(table1, "starttime",Criteria.GREATEREQUAL, "?");
//		sql.addCriteria(table1, "endtime", Criteria.LESSEQUAL, "?");
//		sql.addGroupByColumn(table1, "visitedcountryname");
//
//		//LOGGER.info(sql.toString()+" "+commonService.dateToTimestamp(startDate)+" ");
//		List<HeatMap> listHeatMap=trendDao.getHeatMapList(sql.toString(),commonService.dateToTimestamp(startDate) ,commonService.dateToTimestamp(endDate),  country);
//
//
//		if(!listHeatMap.isEmpty()) {
//			ListIterator<HeatMap> heatMapIterator = listHeatMap.listIterator();
//			while (heatMapIterator.hasNext())
//			{
//				HeatMap hmap = heatMapIterator.next();
//				mapHeatMap.put(hmap.getCountryCode(), hmap);
//
//			}
//		}else
//			LOGGER.info("No Result found");


		return mapHeatMap;
	}

}
