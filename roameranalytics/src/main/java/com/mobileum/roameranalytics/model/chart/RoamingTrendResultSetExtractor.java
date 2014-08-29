/**
 * 
 */
package com.mobileum.roameranalytics.model.chart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.mobileum.roameranalytics.enums.RoamerType;
import com.mobileum.roameranalytics.enums.VoiceType;

/**
 * @author sarvesh
 *
 */
public class RoamingTrendResultSetExtractor implements ResultSetExtractor<RoamingTrend> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.ResultSetExtractor#extractData(java.sql.ResultSet)
	 */
	@Override
	public RoamingTrend extractData(ResultSet rs) throws SQLException,
			DataAccessException {

		Map<RoamerType, Map<DayOfWeek, Double>> dowCountMap = new HashMap<RoamerType, Map<DayOfWeek, Double>>();
		Map<RoamerType, Map<Long, Double>> perDayCountMap = new HashMap<RoamerType, Map<Long, Double>>();
		
		Map<VoiceType, Map<DayOfWeek, Double>> dowCallMap = new HashMap<VoiceType, Map<DayOfWeek,Double>>();
		Map<VoiceType, Map<Long, Double>> perDayCallMap = new HashMap<VoiceType, Map<Long, Double>>();
		
		Map<DayOfWeek, Double> dowDataMap = new TreeMap<DayOfWeek, Double>();
		Map<Long, Double> perDayDataMap = new TreeMap<Long, Double>();
		
		Map<DayOfWeek, Double> dowSMSMap = new TreeMap<DayOfWeek, Double>();
		Map<Long, Double> perDaySMSMap = new TreeMap<Long, Double>();
		
		Set<DayOfWeek> dowCategory = new TreeSet<DayOfWeek>();
		Set<Long> dateCategory = new TreeSet<Long>();
		long startDate = System.currentTimeMillis();
		boolean first = true;
		while (rs.next()) {
			Long date = rs.getLong("usagebintime");
			Double count = rs.getDouble("imsicount");
			Integer tripCategory = rs.getInt("overalltripcategory");
			Double mt = rs.getDouble("mtcallcount");
			Double mo = rs.getDouble("mocallcount");
			Double mosms = rs.getDouble("mosmscount");
			Double data = rs.getDouble("datausage");
			
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(date);
			dateCategory.add(date);
			if (first) {
				startDate = date;
				first = false;
			}
			DayOfWeek dow = DayOfWeek.of(cal.get(Calendar.DAY_OF_WEEK));
			dowCategory.add(dow);
			
			// Roamers Count Chart
			populateRoamersCountDoW(dowCountMap, count, tripCategory, dow);
			populateRoamersCountPerDay(perDayCountMap, date, count, tripCategory);
			
			// Voice Call Chart
			populateCallCountDoW(dowCallMap, dow, mt, mo);
			populateCallCountPerDay(perDayCallMap, date, mt, mo);
			
			// Data chart
			populateDataPerDoW(dowDataMap, dow, data);
			populateDataPerDay(perDayDataMap, date, data);
			
			// SMS chart
			populateDataPerDoW(dowSMSMap, dow, mosms);
			populateDataPerDay(perDaySMSMap, date, mosms);
			
		}

		RoamingTrend roamingTrend = new RoamingTrend();
		
		populateRoamingTrend(dowCountMap, perDayCountMap, dowCallMap, perDayCallMap, 
				dowDataMap, perDayDataMap,dowSMSMap,perDaySMSMap, dowCategory, dateCategory, roamingTrend, startDate);
		
		return roamingTrend;
	}

	/**
	 * @param dowCountMap
	 * @param dowCallMap
	 * @param dowCategory
	 * @param dateCategory
	 * @param roamingTrend
	 */
	private void populateRoamingTrend(
			Map<RoamerType, Map<DayOfWeek, Double>> dowCountMap,
			Map<RoamerType, Map<Long, Double>> perDayCountMap,
			Map<VoiceType, Map<DayOfWeek, Double>> dowCallMap, 
			Map<VoiceType, Map<Long, Double>> perDayCallMap,
			Map<DayOfWeek, Double> dowDataMap,
			Map<Long, Double> perDayDataMap,
			Map<DayOfWeek, Double> dowSMSMap,
			Map<Long, Double> perDaySMSMap,
			Set<DayOfWeek> dowCategory, Set<Long> dateCategory,
			RoamingTrend roamingTrend, long startDate) {
		
		// Roamer Count Chart
		populateRoamerCountChart(dowCountMap, perDayCountMap, dowCategory, dateCategory, roamingTrend, startDate);
		
		// Voice Chart
		populateVoiceCallChart(dowCallMap, perDayCallMap, dowCategory, dateCategory, roamingTrend, startDate);
		
		// Data Chart
		populateDataChart(dowDataMap, perDayDataMap, dowCategory, dateCategory, roamingTrend, startDate);
		
		//SMS  Chart
		populateSMSChart(dowSMSMap, perDaySMSMap, dowCategory, dateCategory, roamingTrend, startDate);
	}

	
	
	/**
	 * @param dowCallMap
	 * @param perDayCallMap
	 * @param dowCategory
	 * @param dateCategory
	 * @param roamingTrend
	 */
	private void populateSMSChart(
			Map<DayOfWeek, Double> dowDataMap,
			Map<Long, Double> perDayDataMap,
			Set<DayOfWeek> dowCategory, Set<Long> dateCategory,
			RoamingTrend roamingTrend, long startDate) {
		
		List<ChartSeries> dowDataSeriesList = new ArrayList<ChartSeries>();
		
		ChartSeries dowSeries = new ChartSeries();
		dowSeries.setData(dowDataMap.values());
		dowDataSeriesList.add(dowSeries);
		
		List<ChartSeries> perDayDataSeriesList = new ArrayList<ChartSeries>();
		
		PerDaySeries dateSeries = new PerDaySeries();
		dateSeries.setData(perDayDataMap.values());
		dateSeries.setPointStart(startDate);
		perDayDataSeriesList.add(dateSeries);
	
		
		RoamingTrendChart dataChart = new RoamingTrendChart();
		dataChart.setDowCategoryList(dowCategory);
		//dataChart.setPerDayCategoryList(dateCategory);
		dataChart.setDowSeriesList(dowDataSeriesList);
		dataChart.setPerDaySeriesList(perDayDataSeriesList);
		roamingTrend.setRoamersSMSChart(dataChart);
	}
	
	/**
	 * @param dowCallMap
	 * @param perDayCallMap
	 * @param dowCategory
	 * @param dateCategory
	 * @param roamingTrend
	 */
	private void populateDataChart(
			Map<DayOfWeek, Double> dowDataMap,
			Map<Long, Double> perDayDataMap,
			Set<DayOfWeek> dowCategory, Set<Long> dateCategory,
			RoamingTrend roamingTrend, long startDate) {
		
		List<ChartSeries> dowDataSeriesList = new ArrayList<ChartSeries>();
		
		ChartSeries dowSeries = new ChartSeries();
		dowSeries.setData(dowDataMap.values());
		dowDataSeriesList.add(dowSeries);
		
		List<ChartSeries> perDayDataSeriesList = new ArrayList<ChartSeries>();
		
		PerDaySeries dateSeries = new PerDaySeries();
		dateSeries.setData(perDayDataMap.values());
		dateSeries.setPointStart(startDate);
		perDayDataSeriesList.add(dateSeries);
	
		
		RoamingTrendChart dataChart = new RoamingTrendChart();
		dataChart.setDowCategoryList(dowCategory);
		//dataChart.setPerDayCategoryList(dateCategory);
		dataChart.setDowSeriesList(dowDataSeriesList);
		dataChart.setPerDaySeriesList(perDayDataSeriesList);
		roamingTrend.setRoamersDataChart(dataChart);
	}
	
	
	/**
	 * @param dowCallMap
	 * @param perDayCallMap
	 * @param dowCategory
	 * @param dateCategory
	 * @param roamingTrend
	 */
	private void populateVoiceCallChart(
			Map<VoiceType, Map<DayOfWeek, Double>> dowCallMap,
			Map<VoiceType, Map<Long, Double>> perDayCallMap,
			Set<DayOfWeek> dowCategory, Set<Long> dateCategory,
			RoamingTrend roamingTrend, long startDate) {
		List<ChartSeries> dowCallSeriesList = new ArrayList<ChartSeries>();
		for (VoiceType voiceType : dowCallMap.keySet()) {
			ChartSeries series = new ChartSeries();
			series.setName(voiceType.name());
			series.setData(dowCallMap.get(voiceType).values());
			dowCallSeriesList.add(series);
		}
		
		List<ChartSeries> perDayCallSeriesList = new ArrayList<ChartSeries>();
		for (VoiceType voiceType : perDayCallMap.keySet()) {
			PerDaySeries series = new PerDaySeries();
			series.setName(voiceType.name());
			series.setPointStart(startDate);
			series.setData(perDayCallMap.get(voiceType).values());
			perDayCallSeriesList.add(series);
		}
		
		RoamingTrendChart voiceChart = new RoamingTrendChart();
		voiceChart.setDowCategoryList(dowCategory);
		//voiceChart.setPerDayCategoryList(dateCategory);
		voiceChart.setDowSeriesList(dowCallSeriesList);
		voiceChart.setPerDaySeriesList(perDayCallSeriesList);
		roamingTrend.setRoamersMTMOChart(voiceChart);
	}

	/**
	 * @param dowCountMap
	 * @param perDayCountMap
	 * @param dowCategory
	 * @param dateCategory
	 * @param roamingTrend
	 */
	private void populateRoamerCountChart(
			Map<RoamerType, Map<DayOfWeek, Double>> dowCountMap,
			Map<RoamerType, Map<Long, Double>> perDayCountMap,
			Set<DayOfWeek> dowCategory, Set<Long> dateCategory,
			RoamingTrend roamingTrend, long startDate) {
		
		List<ChartSeries> dowCountSeriesList = new ArrayList<ChartSeries>();
		for (RoamerType roamerType : dowCountMap.keySet()) {
			ChartSeries series = new ChartSeries();
			series.setName(roamerType.name());
			series.setData(dowCountMap.get(roamerType).values());
			dowCountSeriesList.add(series);
		}
		
		List<ChartSeries> perDayCountSeriesList = new ArrayList<ChartSeries>();
		for (RoamerType roamerType : perDayCountMap.keySet()) {
			PerDaySeries series = new PerDaySeries();
			series.setName(roamerType.name());
			series.setPointStart(startDate);
			series.setData(perDayCountMap.get(roamerType).values());
			perDayCountSeriesList.add(series);
		}
		
		RoamingTrendChart roamerCountChart = new RoamingTrendChart();
		roamerCountChart.setDowCategoryList(dowCategory);
		//roamerCountChart.setPerDayCategoryList(dateCategory);
		roamerCountChart.setDowSeriesList(dowCountSeriesList);
		roamerCountChart.setPerDaySeriesList(perDayCountSeriesList);
		roamingTrend.setRoamersCountChart(roamerCountChart);
	}

	
	/**
	 * Extract call count dow.
	 *
	 * @param dowDataMap the dow data map
	 * @param dow the dow
	 * @param data the data
	 */
	private void populateDataPerDay(Map<Long, Double> perDayDataMap,Long date, 
			Double data) {
		Double perDayData = perDayDataMap.get(date);
		if (perDayData == null) {
			perDayDataMap.put(date, data);
		} else {
			perDayDataMap.put(date, perDayData + data);
		}
	}
	
	
	/**
	 * Extract call count dow.
	 *
	 * @param dowDataMap the dow data map
	 * @param dow the dow
	 * @param data the data
	 */
	private void populateDataPerDoW(Map<DayOfWeek, Double> dowDataMap,DayOfWeek dow, 
			Double data) {
		Double dowData = dowDataMap.get(dow);
		if (dowData == null) {
			dowDataMap.put(dow, data);
		} else {
			dowDataMap.put(dow, dowData + data);
		}
	}
	
	
	/**
	 * Extract call count dow.
	 *
	 * @param dowCallMap the dow call map
	 * @param dow the dow
	 * @param mt the mt
	 * @param mo the mo
	 */
	private void populateCallCountPerDay(Map<VoiceType, Map<Long, Double>> perDayCallMap,Long date, 
			Double mt, Double mo) {
		Map<Long, Double> mtMap = perDayCallMap.get(VoiceType.MT);
		if (mtMap == null) {
			mtMap = new TreeMap<Long, Double>();
			perDayCallMap.put(VoiceType.MT, mtMap);
		}
		
		Double mtcalls = mtMap.get(date);
		if (mtcalls == null) {
			mtMap.put(date, mt);
		} else {
			mtMap.put(date, mt + mtcalls);
		}
		
		Map<Long, Double> moMap = perDayCallMap.get(VoiceType.MO);
		if (moMap == null) {
			moMap = new TreeMap<Long, Double>();
			perDayCallMap.put(VoiceType.MO, moMap);
		}
		
		Double mocalls = moMap.get(date);
		if (mocalls == null) {
			moMap.put(date, mo);
		} else {
			moMap.put(date, mo + mocalls);
		}
		
	}
	
	
	/**
	 * Extract call count dow.
	 *
	 * @param dowCallMap the dow call map
	 * @param dow the dow
	 * @param mt the mt
	 * @param mo the mo
	 */
	private void populateCallCountDoW(Map<VoiceType, Map<DayOfWeek, Double>> dowCallMap,DayOfWeek dow, 
			Double mt, Double mo) {
		Map<DayOfWeek, Double> mtMap = dowCallMap.get(VoiceType.MT);
		if (mtMap == null) {
			mtMap = new TreeMap<DayOfWeek, Double>();
			dowCallMap.put(VoiceType.MT, mtMap);
		}
		
		Double mtcalls = mtMap.get(dow);
		if (mtcalls == null) {
			mtMap.put(dow, mt);
		} else {
			mtMap.put(dow, mt + mtcalls);
		}
		
		Map<DayOfWeek, Double> moMap = dowCallMap.get(VoiceType.MO);
		if (moMap == null) {
			moMap = new TreeMap<DayOfWeek, Double>();
			dowCallMap.put(VoiceType.MO, moMap);
		}
		
		Double mocalls = moMap.get(dow);
		if (mocalls == null) {
			moMap.put(dow, mo);
		} else {
			moMap.put(dow, mo + mocalls);
		}
		
	}
	/**
	 * @param dateDataMap
	 * @param date
	 * @param count
	 * @param tripCategory
	 */
	private void populateRoamersCountPerDay(
			Map<RoamerType, Map<Long, Double>> dateDataMap, Long date,
			Double count, Integer tripCategory) {
		Map<Long, Double> dateRoamerTypeMap = dateDataMap
				.get(RoamerType.of(tripCategory));

		if (dateRoamerTypeMap == null) {
			dateRoamerTypeMap = new TreeMap<Long, Double>();
			dateDataMap.put(RoamerType.of(tripCategory),
					dateRoamerTypeMap);
		}

		Double dateData = dateRoamerTypeMap.get(date);
		if (dateData == null) {
			dateRoamerTypeMap.put(date, count);
		} else {
			dateRoamerTypeMap.put(date, count + dateData);
		}
	}

	/**
	 * @param dayDataMap
	 * @param count
	 * @param tripCategory
	 * @param dow
	 */
	private void populateRoamersCountDoW(
			Map<RoamerType, Map<DayOfWeek, Double>> dayDataMap, Double count,
			Integer tripCategory, DayOfWeek dow) {
		Map<DayOfWeek, Double> dayRoamerTypeMap = dayDataMap
				.get(RoamerType.of(tripCategory));
		if (dayRoamerTypeMap == null) {
			dayRoamerTypeMap = new TreeMap<DayOfWeek, Double>();
			dayDataMap.put(RoamerType.of(tripCategory),
					dayRoamerTypeMap);
		}

		Double dayData = dayRoamerTypeMap.get(dow);
		if (dayData == null) {
			dayRoamerTypeMap.put(dow, count);
		} else {
			dayRoamerTypeMap.put(dow, count + dayData);
		}
	}

}
