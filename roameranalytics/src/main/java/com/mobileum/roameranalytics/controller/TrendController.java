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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobileum.roameranalytics.common.CommonUtil;
import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.model.AggregatedCountryStatistics;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;
import com.mobileum.roameranalytics.model.Country;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.RoamingStatistics;
import com.mobileum.roameranalytics.model.chart.RoamingTrend;
import com.mobileum.roameranalytics.service.MetaDataService;
import com.mobileum.roameranalytics.service.TrendService;

/**
 * The Class TrendController.
 *
 * @author sarvesh
 */
@Controller
@RequestMapping("/")
public class TrendController {
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger(TrendController.class.getName());
	
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
	@RequestMapping(method = RequestMethod.GET, value={"/login","/"})
	public ModelAndView login() {
		final ModelAndView mv = new ModelAndView("login");
		return mv;
	}
	
	/**
	 * Show home.
	 *
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET, value="/{roamType}")
	public ModelAndView showHome(@PathVariable("roamType") final String roamType) {
		ModelAndView mv;
		if(roamType.equals("in") || roamType.equals("out"))
				mv= new ModelAndView("home");
		else 
			mv = new ModelAndView("login");
		
		mv.addObject("roamType", roamType);
		
		return mv;
	}
	
	/**
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{roamType}/heatMap")
	public ModelAndView showHeatMap(@PathVariable("roamType") final String roamType) {
		final ModelAndView mv = new ModelAndView("heatMap");
		mv.addObject("roamType", roamType);
		return mv;
	}
	
	/**
	 * Renders roaming trends.
	 *
	 * @return the model and view
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{roamType}/trends")
	public ModelAndView showRoamingTrends(@PathVariable("roamType") final String roamType) {
		final ModelAndView mv = new ModelAndView("trends");
		mv.addObject("roamType", roamType);
		return mv;
	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{roamType}/getAttributes")
	public @ResponseBody List<Attribute> getAttributes(@PathVariable("roamType") final String roamType) {
		return metaDataService.getAttributes(roamType);
	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{roamType}/getCountries")
	public @ResponseBody List<Country> getCountries(@PathVariable("roamType") final String roamType) {
		return metaDataService.getAllCountries(roamType);
	}
	
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 * @throws ParseException 
	 */
	@RequestMapping(method=RequestMethod.GET, value="/{roamType}/getOtherCountriesTraveled")
	public @ResponseBody List<AttributeCategory> getOtherCountriesTraveled(@PathVariable("roamType") final String roamType,
			final HttpServletRequest request)  throws ParseException {
		final DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		final String startdate = request.getParameter("dateRangeFrom");
		final String endDate = request.getParameter("dateRangeTo");
		final String attributes = request.getParameter("attributes");
		final String countries = request.getParameter("countries");
		
		
		final Filter filter = new Filter();
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		
		final String excludedCountries = request.getParameter("excludedCountries");
		filter.setExcludedCountries(excludedCountries);
		
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		return metaDataService.getOtherCountriesTraveled(filter,roamType);
	}
	
	
	/**
	 * Gets the roaming trends data.
	 *
	 * @param request the req
	 * @return the roaming trends data
	 * @throws ParseException the parse exception
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/{roamType}/getRoamingTrendsData")
	public @ResponseBody RoamingTrend getRoamingTrendsData(@PathVariable("roamType") final String roamType,
			final HttpServletRequest request) throws ParseException {
		
		LOGGER.info("Getting Roaming Trends");
		
		final DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		final String startdate = request.getParameter("dateRangeFrom");
		final String endDate = request.getParameter("dateRangeTo");
		final String attributes = request.getParameter("attributes");
		final String countries = request.getParameter("countries");
		
		final Filter filter = new Filter();
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());

		filter.setSelectedCountries(countries);
		final String excludedCountries = request.getParameter("excludedCountries");
		filter.setExcludedCountries(excludedCountries);
		
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}

		LOGGER.debug("Filter for roaming trends :  " + filter);
		return this.trendService.getTrendsCharts(filter,roamType);
	}
	

	/**
	 * Gets the To 10 Bubble charts data.
	 *
	 * @param request the req
	 * @return the roaming trends data
	 * @throws ParseException the parse exception
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/{roamType}/getHeatMap")
	public @ResponseBody List<RoamingStatistics> getHeatMapData(@PathVariable("roamType") final String roamType,
			final HttpServletRequest request) throws ParseException {
		final String startdate = request.getParameter("dateRangeFrom");
		final String endDate = request.getParameter("dateRangeTo");
		final DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		final String attributes = request.getParameter("attributes");
		final String countries = request.getParameter("countries");
		
		final Filter filter = new Filter();
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		
		final String excludedCountries = request.getParameter("excludedCountries");
		filter.setExcludedCountries(excludedCountries);
		
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}

		return this.trendService.getHeatMap(filter, roamType);
	}
	
	/**
	 * Gets the roaming trends data.
	 *
	 * @param request the req
	 * @return the roaming trends data
	 * @throws ParseException the parse exception
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/{roamType}/getRoamingStatistics")
	public @ResponseBody HashMap<String,Long> getRoamingstatisticsData(@PathVariable("roamType") final String roamType,
			final HttpServletRequest request) throws ParseException {
		final String startdate = request.getParameter("dateRangeFrom");
		final String endDate = request.getParameter("dateRangeTo");
		
		final String attributes = request.getParameter("attributes");
		final String countries = request.getParameter("countries");
		final Filter filter = new Filter();
		final DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		final String excludedCountries = request.getParameter("excludedCountries");
		filter.setExcludedCountries(excludedCountries);
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}

		return this.trendService.getRoamingStatistics(filter,roamType);
	}
	
	/**
	 * Gets the To 10 Bubble charts data.
	 *
	 * @param request the req
	 * @return the roaming trends data
	 * @throws ParseException the parse exception
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/{roamType}/getBubbleChart")
	public @ResponseBody AggregatedCountryStatistics getBubbleChartData(@PathVariable("roamType") final String roamType,
			final HttpServletRequest request) throws ParseException {
		final String startdate = request.getParameter("dateRangeFrom");
		final String endDate = request.getParameter("dateRangeTo");
		
		final String attributes = request.getParameter("attributes");
		final String countries = request.getParameter("countries");
		
		final Filter filter = new Filter();
		final DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		final String excludedCountries = request.getParameter("excludedCountries");
		filter.setExcludedCountries(excludedCountries);
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}

		return this.trendService.getTopCountry(filter, roamType);
	}
	
	@RequestMapping(value = "/getTop10CSV", method = RequestMethod.POST)
	public void getNameAsXML(final HttpServletRequest httpRequest,final HttpServletResponse response, @RequestParam("top10csvtext") final String csvText)
	{
		
		response.setHeader("Content-Disposition", "attachment; filename=top10roamer.csv");
		response.setContentType("text/csv");
		try{
			final PrintWriter out = response.getWriter();  
			out.print(csvText);
		}catch(final IOException ioException)
		{
			
		}
		
	}
	
	/**
	 * Gets the To 10 Bubble charts data.
	 *
	 * @param request the req
	 * @return the roaming trends data
	 * @throws ParseException the parse exception
	 */
	@RequestMapping(method=RequestMethod.GET, value = "/{roamType}/getBubbleChartJson")
	public @ResponseBody String getBubbleChartJson(@PathVariable("roamType") final String roamType,
			final HttpServletRequest request) throws ParseException {
		final String jsonData = request.getParameter("data");
		return jsonData;
	}
	
}
