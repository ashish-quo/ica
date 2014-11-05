/**
 * 
 */
package com.mobileum.roameranalytics.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobileum.roameranalytics.common.CommonUtil;
import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.chart.MSChartMetadata;
import com.mobileum.roameranalytics.service.MicroSegmentService;

/**
 * @author sarvesh
 *
 */
@Controller
@RequestMapping("/")
public class MicroSegmentController {

	@Autowired
	private MicroSegmentService microsegmentSerice;
	
	@RequestMapping(value="/{roamType}/microsegment", method = RequestMethod.GET)
	public ModelAndView showMicroSegment(@PathVariable("roamType") final String roamType) {
		return new ModelAndView("microsegment");
	}
	
	@RequestMapping(value="/{roamType}/microsegment/graphs", method = RequestMethod.GET)
	public @ResponseBody List<MSChartMetadata> getMicroSegmentCharts(@PathVariable("roamType") final String roamType,
			final HttpServletRequest request) throws ParseException {

		final String microSegmentCharts = request.getParameter("microsegmentcharts");
		final String countries = request.getParameter("countries");
		final List<MSChartMetadata> list = new ArrayList<MSChartMetadata>(5);
		if (!microSegmentCharts.isEmpty()) {
			final String[] chartMetadata = microSegmentCharts.split(RAConstants.COLON);
			for (final String metadata : chartMetadata) {
				
				final String[] chartAttr = metadata.split(RAConstants.COMMA);
				if (countries.isEmpty() 
						&& RAConstants.ATTR_OTHER_COUNTRIES_TRAVLED.equalsIgnoreCase(chartAttr[0]))
					continue;
				final MSChartMetadata msChart = new MSChartMetadata();
				msChart.setColumn(chartAttr[1]);
				msChart.setTitle(chartAttr[0]);
				msChart.setColumnType(chartAttr[2]);
				msChart.setChartType(Byte.parseByte(chartAttr[3]));
				msChart.setAttributeId(Integer.parseInt(chartAttr[4]));
				list.add(msChart);
			}
		}
		
		return list;
	}
	
	
	@RequestMapping(value="/{roamType}/microsegment/graph", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getMicroSegmentChartData(@PathVariable("roamType") final String roamType,
			final HttpServletRequest request) throws ParseException {
		
		final String startdate = request.getParameter("dateRangeFrom");
		final String endDate = request.getParameter("dateRangeTo");
		final String attributes = request.getParameter("attributes");
		final String countries = request.getParameter("countries");
		final String chartMetaData = request.getParameter("chartmetadata");
		final Filter filter = new Filter();
		final DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		final String excludedCountries = request.getParameter("excludedCountries");
		filter.setExcludedCountries(excludedCountries);
		final String chartInfo[] = chartMetaData.split(RAConstants.COMMA);
		
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		final Map<String, List<Object[]>> data = microsegmentSerice.getMSChartData(filter,chartInfo[0], chartInfo[1], chartInfo[2],
				RAConstants.attributeNameValueCache.get(chartInfo[0]) ,roamType);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", chartInfo[0]);
		result.put("data", data);
		return result;
	}
	
	@RequestMapping(value="/{roamType}/microsegment/networkgroup", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getNetworkGroupChartData(@PathVariable("roamType") final String roamType,
			final HttpServletRequest req) throws ParseException {
		
		final String startdate = req.getParameter("dateRangeFrom");
		final String endDate = req.getParameter("dateRangeTo");
		final String attributes = req.getParameter("attributes");
		final String countries = req.getParameter("countries");
		final String chartMetaData = req.getParameter("chartmetadata");
		final Filter filter = new Filter();
		final DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		
		final String chartInfo[] = chartMetaData.split(RAConstants.COMMA);
		
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		final Map<String, List<Object[]>> data = microsegmentSerice.getNetworkGroupData(filter, chartInfo[1], chartInfo[2],
				RAConstants.attributeNameValueCache.get(chartInfo[0]),roamType);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", chartInfo[0]);
		result.put("data", data);
		return result;
	}
	
	@RequestMapping(value="/{roamType}/microsegment/network", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getNetworkChartData(@PathVariable("roamType") final String roamType,
			final HttpServletRequest req) throws ParseException {
		
		final String startdate = req.getParameter("dateRangeFrom");
		final String endDate = req.getParameter("dateRangeTo");
		final String attributes = req.getParameter("attributes");
		final String countries = req.getParameter("countries");
		final String chartMetaData = req.getParameter("chartmetadata");
		final Filter filter = new Filter();
		final DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		final String excludedCountries = req.getParameter("excludedCountries");
		filter.setExcludedCountries(excludedCountries);
		final String chartInfo[] = chartMetaData.split(RAConstants.COMMA);
		
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		final Map<String, List<Object[]>> data = microsegmentSerice.getNetworkData(filter, chartInfo[1], chartInfo[2],
				RAConstants.attributeNameValueCache.get(chartInfo[0]),roamType);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", chartInfo[0]);
		result.put("data", data);
		return result;
	}
	
	
	@RequestMapping(value="/{roamType}/microsegment/otherCountriesTraveled", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getOtherCountriesTraveledChartData(@PathVariable("roamType") final String roamType,
			final HttpServletRequest req)  throws ParseException {
		
		final String startdate = req.getParameter("dateRangeFrom");
		final String endDate = req.getParameter("dateRangeTo");
		final String attributes = req.getParameter("attributes");
		final String countries = req.getParameter("countries");
		final String chartMetaData = req.getParameter("chartmetadata");
		final Filter filter = new Filter();
		final DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		final String excludedCountries = req.getParameter("excludedCountries");
		filter.setExcludedCountries(excludedCountries);
		final String chartInfo[] = chartMetaData.split(RAConstants.COMMA);
		
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		final Map<String, List<Object[]>> data = microsegmentSerice.getOtherCountriesTraveledData(
				filter, chartInfo[1], chartInfo[2], RAConstants.attributeNameValueCache.get(chartInfo[0]), roamType);
		final Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", chartInfo[0]);
		result.put("data", data);
		return result;
	}
}
