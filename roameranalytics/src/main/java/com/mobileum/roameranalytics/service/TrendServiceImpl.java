/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.CountryUsageStatistics;
import com.mobileum.roameranalytics.model.RoamingStatistics;
import com.mobileum.roameranalytics.model.AggregatedCountryStatistics;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;
import com.mobileum.roameranalytics.repository.Criteria;
import com.mobileum.roameranalytics.repository.SelectQuery;
import com.mobileum.roameranalytics.repository.Table;
import com.mobileum.roameranalytics.repository.TrendRepository;

/**
 * @author Quovantis_Dev
 *
 */
@Service
public class TrendServiceImpl implements TrendService{

	/** The common service. */
	@Autowired
	private MetaDataService commonService;

	/** The trend dao. */
	@Autowired
	private TrendRepository trendDao;
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger("FilterDaoImpl");

	public void printQuery()
	{
		System.out.println("hii"+QueryBuilder.queryForHeatMap());
	}
	
	public  Map<String,CountryUsageStatistics> getHeatMap(String startDate, String endDate, List<String> country){
		Map<String,CountryUsageStatistics> mapHeatMap =new LinkedHashMap<String,CountryUsageStatistics>();
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
		
		
		List<CountryUsageStatistics> listHeatMap=new ArrayList<CountryUsageStatistics>();
		
		listHeatMap=trendDao.getHeatMapList(sql.toString(),whereCriteria);

		
		if(!listHeatMap.isEmpty()) {
			
			for (CountryUsageStatistics heatMap : listHeatMap)
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
	
	
	public List<RoamingStatistics> getTopRoamer(String startDate, String endDate, String orderBy){
		
		
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
	
	
	public  AggregatedCountryStatistics getTopCountry(String startDate, String endDate){
		AggregatedCountryStatistics topCountry =new AggregatedCountryStatistics();

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
		LOGGER.trace("Getting trend chart's data - Roamer Count, MT & MO, SMS and Data");
		return this.trendDao.getTrendsCharts(filter);
	}


}
