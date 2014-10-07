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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobileum.roameranalytics.common.CommonUtil;
import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.model.MSChartMetadata;
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
	
	@RequestMapping(value="/microsegment", method = RequestMethod.GET)
	public ModelAndView showMicroSegment() {
		return new ModelAndView("microsegment");
	}
	
	@RequestMapping(value="/microsegment/graphs", method = RequestMethod.GET)
	public @ResponseBody List<MSChartMetadata> getMicroSegmentCharts(HttpServletRequest request) throws ParseException {

		String microSegmentCharts = request.getParameter("microsegmentcharts");
		String countries = request.getParameter("countries");
		List<MSChartMetadata> list = new ArrayList<MSChartMetadata>(5);
		if (!microSegmentCharts.isEmpty()) {
			String[] chartMetadata = microSegmentCharts.split(RAConstants.COLON);
			for (String metadata : chartMetadata) {
				
				String[] chartAttr = metadata.split(RAConstants.COMMA);
				if (countries.isEmpty() 
						&& RAConstants.ATTR_OTHER_COUNTRIES_TRAVLED.equalsIgnoreCase(chartAttr[0]))
					continue;
				MSChartMetadata msChart = new MSChartMetadata();
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
	
	
	@RequestMapping(value="/microsegment/graph", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getMicroSegmentChartData(HttpServletRequest request) throws ParseException {
		
		String startdate = request.getParameter("dateRangeFrom");
		String endDate = request.getParameter("dateRangeTo");
		String attributes = request.getParameter("attributes");
		String countries = request.getParameter("countries");
		String chartMetaData = request.getParameter("chartmetadata");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		
		String chartInfo[] = chartMetaData.split(RAConstants.COMMA);
		
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		Map<String, List<Object[]>> data = microsegmentSerice.getMSChartData(filter,chartInfo[0], chartInfo[1], chartInfo[2],
				RAConstants.attributeNameValueCache.get(chartInfo[0]) );
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", chartInfo[0]);
		result.put("data", data);
		return result;
	}
	
	@RequestMapping(value="/microsegment/networkgroup", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getNetworkGroupChartData(HttpServletRequest req) throws ParseException {
		
		String startdate = req.getParameter("dateRangeFrom");
		String endDate = req.getParameter("dateRangeTo");
		String attributes = req.getParameter("attributes");
		String countries = req.getParameter("countries");
		String chartMetaData = req.getParameter("chartmetadata");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		
		String chartInfo[] = chartMetaData.split(RAConstants.COMMA);
		
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		Map<String, List<Object[]>> data = microsegmentSerice.getNetworkGroupData(filter, chartInfo[1], chartInfo[2],
				RAConstants.attributeNameValueCache.get(chartInfo[0]) );
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", chartInfo[0]);
		result.put("data", data);
		return result;
	}
	
	
	@RequestMapping(value="/microsegment/otherCountriesTraveled", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getOtherCountriesTraveledChartData(HttpServletRequest req) 
			throws ParseException {
		
		String startdate = req.getParameter("dateRangeFrom");
		String endDate = req.getParameter("dateRangeTo");
		String attributes = req.getParameter("attributes");
		String countries = req.getParameter("countries");
		String chartMetaData = req.getParameter("chartmetadata");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		
		String chartInfo[] = chartMetaData.split(RAConstants.COMMA);
		
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		Map<String, List<Object[]>> data = microsegmentSerice.getOtherCountriesTraveledData(
				filter, chartInfo[1], chartInfo[2], RAConstants.attributeNameValueCache.get(chartInfo[0]) );
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("attrName", chartInfo[0]);
		result.put("data", data);
		return result;
	}
}
