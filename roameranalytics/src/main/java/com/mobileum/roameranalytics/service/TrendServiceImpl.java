/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.model.AggregatedCountryStatistics;
import com.mobileum.roameranalytics.model.CountryUsageStatistics;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.RoamingCategory;
import com.mobileum.roameranalytics.model.RoamingStatistics;
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
	private static Logger LOGGER = LogManager.getLogger(TrendServiceImpl.class.getName());

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
	
	
	public List<RoamingStatistics> getHeatMap(Filter filter){
				
		return trendDao.getRoamingStatisticsRepository(filter);
		
				
	}
	
	@Override
	public  AggregatedCountryStatistics getTopCountry(Filter filter){
		List<RoamingStatistics> roamingStatisticslist= trendDao.getRoamingStatisticsRepository(filter);
		AggregatedCountryStatistics topCountry=new AggregatedCountryStatistics();
		
		/**	Get Top 10 Country List **/
		
		try
		{
			Collections.sort(roamingStatisticslist, ROAMER_COUNT_COMPARATOR);			
			topCountry.setTopRoamer(roamingStatisticslist.subList(0, 10));
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		try
		{
			Collections.sort(roamingStatisticslist, MO_COUNT_COMPARATOR);
			topCountry.setTopMo(roamingStatisticslist.subList(0, 10));

		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		try
		{
			Collections.sort(roamingStatisticslist, MT_COUNT_COMPARATOR);
			topCountry.setTopMt(roamingStatisticslist.subList(0, 10));
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		try
		{
			Collections.sort(roamingStatisticslist, DATA_COUNT_COMPARATOR);
			topCountry.setTopData(roamingStatisticslist.subList(0, 10));
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		try
		{
		
		//topCountry.setTopSms(getTopRoamer(startDate,endDate,"mosmscount desc limit 10"));
			
		
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
	
	@Override
	public HashMap<String,Long> getRoamingStatistics(Filter filter)
	{
		HashMap<String,Long> roamingStatisticsMap=new LinkedHashMap<String,Long>();
		roamingStatisticsMap.put("totalRoamer", new Long(0));
		roamingStatisticsMap.put("silentRoamer", new Long(0));
		roamingStatisticsMap.put("valueRoamer", new Long(0));
		roamingStatisticsMap.put("premiumRoamer", new Long(0));
		roamingStatisticsMap.put("totalMo", new Long(0));
		roamingStatisticsMap.put("homeMo", new Long(0));
		roamingStatisticsMap.put("localMo", new Long(0));
		roamingStatisticsMap.put("intlMo", new Long(0));
		roamingStatisticsMap.put("totalMt", new Long(0));
		roamingStatisticsMap.put("totalData", new Long(0));
		roamingStatisticsMap.put("totalSms", new Long(0));
		
		List<RoamingStatistics> roamingStatisticslist= trendDao.getRoamingStatisticsRepository(filter);
		
		for(RoamingStatistics roamingStatistics : roamingStatisticslist ){
			roamingStatisticsMap.put("totalRoamer",roamingStatisticsMap.get("totalRoamer")+roamingStatistics.getRoamerTotal() );
			roamingStatisticsMap.put("silentRoamer", roamingStatisticsMap.get("silentRoamer")+roamingStatistics.getRoamerSilent());
			roamingStatisticsMap.put("valueRoamer", roamingStatisticsMap.get("valueRoamer")+roamingStatistics.getRoamerValue());
			roamingStatisticsMap.put("premiumRoamer", roamingStatisticsMap.get("premiumRoamer")+roamingStatistics.getRoamerPremium());
			roamingStatisticsMap.put("totalMo", roamingStatisticsMap.get("totalMo")+roamingStatistics.getMoTotal());
			roamingStatisticsMap.put("homeMo", roamingStatisticsMap.get("homeMo")+roamingStatistics.getMoHome());
			roamingStatisticsMap.put("localMo",roamingStatisticsMap.get("localMo")+roamingStatistics.getMoLocal());
			roamingStatisticsMap.put("intlMo", roamingStatisticsMap.get("intlMo")+roamingStatistics.getMoIntl());
			roamingStatisticsMap.put("totalMt",roamingStatisticsMap.get("totalMt")+roamingStatistics.getMt());
			roamingStatisticsMap.put("totalData", roamingStatisticsMap.get("totalData")+roamingStatistics.getDataUsage());
			roamingStatisticsMap.put("totalSms", roamingStatisticsMap.get("totalSms")+roamingStatistics.getSmsUsage());
			
		}
		
		
		List<RoamingCategory> roamingCategoryList= trendDao.getRoamingCategoryRepository(filter);
		for(RoamingCategory roamingCategory : roamingCategoryList ){
			if(roamingStatisticsMap.get(roamingCategory.getCategory())!=null){
				roamingStatisticsMap.put(roamingCategory.getCategory(), roamingCategory.getCount());
			}
		}
		
		return roamingStatisticsMap;
	}

	private  final Comparator ROAMER_COUNT_COMPARATOR=new Comparator<RoamingStatistics>(){

		@Override
		public int compare(RoamingStatistics o1, RoamingStatistics o2) {

			return new Long(o2.getRoamerTotal()).compareTo(new Long(o1.getRoamerTotal()));
		}


	};

	private  final Comparator MO_COUNT_COMPARATOR=new Comparator<RoamingStatistics>(){

		@Override
		public int compare(RoamingStatistics o1, RoamingStatistics o2) {

			return new Long(o2.getMoTotal()).compareTo(new Long(o1.getMoTotal()));
		}


	};

	private  final Comparator MT_COUNT_COMPARATOR=new Comparator<RoamingStatistics>(){

		@Override
		public int compare(RoamingStatistics o1, RoamingStatistics o2) {

			return new Long(o2.getMt()).compareTo(new Long(o1.getMt()));
		}


	};

	private  final Comparator DATA_COUNT_COMPARATOR=new Comparator<RoamingStatistics>(){

		@Override
		public int compare(RoamingStatistics o1, RoamingStatistics o2) {

			return new Long(o2.getDataUsage()).compareTo(new Long(o1.getDataUsage()));
		}


	};
	
}
