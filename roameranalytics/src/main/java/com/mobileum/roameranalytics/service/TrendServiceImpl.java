/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.HeatMap;
import com.mobileum.roameranalytics.model.RoamingStats;
import com.mobileum.roameranalytics.model.TopCountry;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;

/**
 * @author Quovantis_Dev
 *
 */
@Service
public class TrendServiceImpl implements TrendServiceI{

	/** The common service. */
	@Autowired
	private CommonServiceI commonService;

	/** The trend dao. */
	@Autowired
	private TrendDaoI trendDao;
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger("TrendServiceImpl");

	public void printQuery()
	{
		System.out.println("hii"+QueryBuilder.queryForHeatMap());
	}
	
	public  Map<String,HeatMap> getHeatMap(String startDate, String endDate, List<String> country){
		Map<String,HeatMap> mapHeatMap =new LinkedHashMap<String,HeatMap>();
		Table table1=new Table("trip","tp");
		table1.addGroupFunctions("sum(mocallcount) mocallcount");
		table1.addGroupFunctions("sum(mtcallcount) mtcallcount");
		table1.addGroupFunctions("sum(mosmscount) mosmscount");
		table1.addGroupFunctions("sum(uplink+downlink) modatacount");
		table1.addGroupFunctions("visitedcountryname");

		SelectQuery sql=new SelectQuery();
		sql.addTable(table1);
		List<Object> listCriteria=new ArrayList<Object>();
		if(!country.isEmpty())
		{
			for (String row : country) {
				sql.addCriteria(table1, "visitedcountryname",Criteria.EQUALS, "?");
				listCriteria.add(row);
				
				}
		}
		sql.addCriteria(table1, "starttime",Criteria.GREATEREQUAL, "?");
		sql.addCriteria(table1, "endtime", Criteria.LESSEQUAL, "?");
		sql.addGroupByColumn(table1, "visitedcountryname");
		//sql.addOrderByColumn(table1, "visitedcountryname");
		try
		{
		LOGGER.info(sql.toString()+" "+commonService.dateToTimestamp(startDate)+" "+commonService.dateToTimestamp(endDate));
		listCriteria.add(commonService.dateToTimestamp(startDate));
		listCriteria.add(commonService.dateToTimestamp(endDate));
		
		Object[] whereCriteria = commonService.listToObjectArray(listCriteria);
		
		
		List<HeatMap> listHeatMap=new ArrayList<HeatMap>();
		
		listHeatMap=trendDao.getHeatMapList(sql.toString(),whereCriteria);

		
		if(!listHeatMap.isEmpty()) {
			
			for (HeatMap heatMap : listHeatMap)
			{
				mapHeatMap.put(heatMap.getCountryCode(), heatMap);

			}
		}else
			LOGGER.info(" No Result found");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}


		return mapHeatMap;
	}
	
	
	public List<RoamingStats> getTopRoamer(String startDate, String endDate, String orderBy){
		
		
		Table table1=new Table("trip","tp");
		table1.addGroupFunctions("sum(distinct msisdn) roamercount");
		table1.addGroupFunctions("sum(mocallcount) mocallcount");
		table1.addGroupFunctions("sum(mtcallcount) mtcallcount");
		table1.addGroupFunctions("sum(mosmscount) mosmscount");
		table1.addGroupFunctions("sum(uplink+downlink) modatacount");
		table1.addGroupFunctions("visitedcountryname");
		SelectQuery sql=new SelectQuery();
		sql.addTable(table1);
		
		List<Object> listCriteria=new ArrayList<Object>();
		sql.addCriteria(table1, "starttime",Criteria.GREATEREQUAL, "?");
		sql.addCriteria(table1, "endtime", Criteria.LESSEQUAL, "?");
		sql.addGroupByColumn(table1, "visitedcountryname");
		sql.addOrderByColumn(table1, orderBy);
		
		LOGGER.info(sql.toString()+" "+commonService.dateToTimestamp(startDate)+" "+commonService.dateToTimestamp(endDate));
		listCriteria.add(commonService.dateToTimestamp(startDate));
		listCriteria.add(commonService.dateToTimestamp(endDate));
		
		Object[] whereCriteria = commonService.listToObjectArray(listCriteria);
		
		return trendDao.getTopRoamerDao(sql.toString(),whereCriteria);
				
	}
	
	
	public  TopCountry getTopCountry(String startDate, String endDate){
		TopCountry topCountry =new TopCountry();

		/**	Get Top 10 Country List **/
		
		try
		{
		topCountry.setTopRoamer(getTopRoamer(startDate,endDate,"roamercount desc limit 10"));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		try
		{
			
		topCountry.setTopMo(getTopRoamer(startDate,endDate,"mocallcount desc limit 10"));

		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		try
		{
		
		topCountry.setTopMt(getTopRoamer(startDate,endDate,"mtcallcount desc limit 10"));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		try
		{
		
		topCountry.setTopData(getTopRoamer(startDate,endDate,"modatacount desc limit 10"));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		try
		{
		
		topCountry.setTopSms(getTopRoamer(startDate,endDate,"mosmscount desc limit 10"));
			
		
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		return topCountry;
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.service.TrendServiceI#getTrendsCharts(com.mobileum.roameranalytics.model.Fitler)
	 */
	@Override
	public RoamingTrend getTrendsCharts(Filter filter) {
		return this.trendDao.getTrendsCharts(filter);
	}


}
