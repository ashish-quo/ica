/**
 * 
 */
package com.mobileum.roameranalytics.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobileum.roameranalytics.common.CommonUtil;
import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.model.AggregatedCountryStatistics;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.Country;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.RoamingStatistics;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;
import com.mobileum.roameranalytics.service.MetaDataService;
import com.mobileum.roameranalytics.service.TrendService;
import com.mobileum.roameranalytics.service.TrendServiceImpl;

/**
 * The Class TrendController.
 *
 * @author sarvesh
 */
@Controller
@RequestMapping("/")
public class TrendController {
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger(TrendServiceImpl.class.getName());
	
	/** The common service. */
	@Autowired

	private MetaDataService metaDataService;

	
	/** The trend service. */
	@Autowired
	private TrendService trendService;
	
	
	
	/**
	 * Show home.
	 *
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showHome() {
		return new ModelAndView("home");
	}
	
	/**
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/heatMap")
	public ModelAndView showHeatMap() {
		return new ModelAndView("heatMap");
	}
	/**
	 * Renders Roaming trend header.
	 *
	 * @return the model and view
	 */
	@RequestMapping(method=RequestMethod.GET, value="/trendHeader")
	public ModelAndView showTrendHeader() {
		return new ModelAndView("trendHeader");
	}
	
	/**
	 * Renders roaming trends.
	 *
	 * @return the model and view
	 */
	@RequestMapping(method=RequestMethod.GET, value="/trends")
	public ModelAndView showRoamingTrends() {
		return new ModelAndView("trends");
	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getAttributes")
	public @ResponseBody List<Attribute> getAttributes() {

		return metaDataService.getAttributes();

	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getCountries")
	public @ResponseBody List<Country> getCountries() {

		return metaDataService.getAllCountries();

	}
	
	/**
	 * Gets the roaming trends data.
	 *
	 * @param req the req
	 * @return the roaming trends data
	 * @throws ParseException the parse exception
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/getRoamingTrendsData")
	public @ResponseBody RoamingTrend getRoamingTrendsData(HttpServletRequest req) throws ParseException {
		
		LOGGER.info("Getting Roaming Trends");
		
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		String startdate = req.getParameter("dateRangeFrom");
		String endDate = req.getParameter("dateRangeTo");
		String attributes = req.getParameter("attributes");
		String countries = req.getParameter("countries");
		
		Filter filter = new Filter();
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());

		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}

		LOGGER.debug("Filter for roaming trends :  " + filter);
		return this.trendService.getTrendsCharts(filter);
	}
	

	/**
	 * Gets the To 10 Bubble charts data.
	 *
	 * @param request the req
	 * @return the roaming trends data
	 * @throws ParseException the parse exception
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/getHeatMap")
	public @ResponseBody List<RoamingStatistics> getHeatMapData(HttpServletRequest request) throws ParseException {
		String startdate = request.getParameter("dateRangeFrom");
		String endDate = request.getParameter("dateRangeTo");
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		String attributes = request.getParameter("attributes");
		String countries = request.getParameter("countries");
		
		Filter filter = new Filter();
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		
		
		
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}

		return this.trendService.getHeatMap(filter);
	}
	
	/**
	 * Gets the roaming trends data.
	 *
	 * @param request the req
	 * @return the roaming trends data
	 * @throws ParseException the parse exception
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/getRoamingStatistics")
	public @ResponseBody HashMap<String,Long> getRoamingstatisticsData(HttpServletRequest request) throws ParseException {
		String startdate = request.getParameter("dateRangeFrom");
		String endDate = request.getParameter("dateRangeTo");
		
		String attributes = request.getParameter("attributes");
		String countries = request.getParameter("countries");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}

		return this.trendService.getRoamingStatistics(filter);
	}
	
	/**
	 * Gets the To 10 Bubble charts data.
	 *
	 * @param request the req
	 * @return the roaming trends data
	 * @throws ParseException the parse exception
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/getBubbleChart")
	public @ResponseBody AggregatedCountryStatistics getBubbleChartData(HttpServletRequest request) throws ParseException {
		String startdate = request.getParameter("dateRangeFrom");
		String endDate = request.getParameter("dateRangeTo");
		
		String attributes = request.getParameter("attributes");
		String countries = request.getParameter("countries");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}

		return this.trendService.getTopCountry(filter);
	}
	
	@RequestMapping(value = "/getTop10CSV", method = RequestMethod.POST)
	public void getNameAsXML(HttpServletRequest httpRequest,HttpServletResponse response, @RequestParam("top10csvtext") String csvText)
	{
		
		response.setHeader("Content-Disposition", "attachment; filename=top10roamer.csv");
		response.setContentType("text/csv");
		try{
			PrintWriter out = response.getWriter();  
			out.print(csvText);
		}catch(IOException ioException)
		{
			
		}
		
	}
	
}
