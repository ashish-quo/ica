/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mobileum.roameranalytics.model.AggregatedCountryStatistics;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.RoamingCategory;
import com.mobileum.roameranalytics.model.RoamingStatistics;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;
import com.mobileum.roameranalytics.repository.TrendRepository;

/**
 * @author Quovantis_Dev
 *
 */
@Service
public class TrendServiceImpl implements TrendService {

	/** The common service. */
	@Autowired
	private MetaDataService metaDataService;

	/** The trend dao. */
	@Autowired
	@Qualifier("prestoTrendRepository")
	private TrendRepository trendRepository;
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger(TrendServiceImpl.class.getName());

	public List<RoamingStatistics> getHeatMap(Filter filter){
				
		List<RoamingStatistics> roamingStatisticsList=trendRepository.getRoamingStatisticsRepository(filter);
		
		List<RoamingCategory> roamingCategoryList= trendRepository.getRoamingCategoryRepository(filter);
		for(RoamingCategory roamingCategory : roamingCategoryList ){
			LOGGER.info(roamingCategory.getCategory()+roamingCategory.getVisitedCountryName()+roamingCategory.getCount());
			for(RoamingStatistics roamingStatistics : roamingStatisticsList){
				
				if(roamingCategory.getCategory().equals("silentRoamer") && roamingCategory.getVisitedCountryName().equals(roamingStatistics.getCountryCode())){
					
					roamingStatistics.setRoamerSilent(roamingCategory.getCount());
					
				}else if(roamingCategory.getCategory().equals("valueRoamer") && roamingCategory.getVisitedCountryName().equals(roamingStatistics.getCountryCode())){
					
					roamingStatistics.setRoamerValue(roamingCategory.getCount());
					
				}else if(roamingCategory.getCategory().equals("premiumRoamer") && roamingCategory.getVisitedCountryName().equals(roamingStatistics.getCountryCode())){
					
					roamingStatistics.setRoamerPremium(roamingCategory.getCount());
					
				}
			}
		}
		
		return roamingStatisticsList;
	}
	
	@Override
	public  AggregatedCountryStatistics getTopCountry(Filter filter){
		List<RoamingStatistics> roamingStatisticslist= trendRepository.getRoamingStatisticsRepository(filter);
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
		return this.trendRepository.getTrendsCharts(filter);
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
		
		List<RoamingStatistics> roamingStatisticslist= trendRepository.getRoamingStatisticsRepository(filter);
		
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
		
		
		List<RoamingCategory> roamingCategoryList= trendRepository.getRoamingCategoryRepository(filter);
		for(RoamingCategory roamingCategory : roamingCategoryList ){
			if(roamingStatisticsMap.get(roamingCategory.getCategory())!=null){
				roamingStatisticsMap.put(roamingCategory.getCategory(),roamingStatisticsMap.get(roamingCategory.getCategory())+ roamingCategory.getCount());
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
