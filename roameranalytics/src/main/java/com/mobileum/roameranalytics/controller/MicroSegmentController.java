/**
 * 
 */
package com.mobileum.roameranalytics.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.mobileum.roameranalytics.service.MicroSegmentServiceI;

/**
 * @author sarvesh
 *
 */
@Controller
@RequestMapping("/")
public class MicroSegmentController {

	@Autowired
	private MicroSegmentServiceI microsegmentSerice;
	
	@RequestMapping(value="/microsegment", method = RequestMethod.GET)
	public ModelAndView showMicroSegment() {
		return new ModelAndView("microsegment");
	}
	
	@RequestMapping(value="/microsegment/graphs", method = RequestMethod.GET)
	public @ResponseBody List<String> getGraphsToBeShown(HttpServletRequest req) throws ParseException {
		String startdate = req.getParameter("dateRangeFrom");
		String endDate = req.getParameter("dateRangeTo");
		String attributes = req.getParameter("attributes");
		String countries = req.getParameter("countries");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		List<String> list = new ArrayList<String>();
		list.add("network");
		list.add("roamingcategory");
		list.add("arpu");
		list.add("paymenttype");
		list.add("devicetype");
		return list;
	}
	
	@RequestMapping(value="/microsegment/graph/network", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getNetworkGraph(HttpServletRequest req) throws ParseException {
		
		String startdate = req.getParameter("dateRangeFrom");
		String endDate = req.getParameter("dateRangeTo");
		String attributes = req.getParameter("attributes");
		String countries = req.getParameter("countries");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		
		return microsegmentSerice.getNetworkData(filter);
	}
	
	@RequestMapping(value="/microsegment/graph/roamingcategory", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getRoamingCategoryGraph(HttpServletRequest req) throws ParseException {
		String startdate = req.getParameter("dateRangeFrom");
		String endDate = req.getParameter("dateRangeTo");
		String attributes = req.getParameter("attributes");
		String countries = req.getParameter("countries");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		
		return microsegmentSerice.getRoamerTypeData(filter);
	}
	
	@RequestMapping(value="/microsegment/graph/arpu", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getARPUGraph(HttpServletRequest req) throws ParseException {
		String startdate = req.getParameter("dateRangeFrom");
		String endDate = req.getParameter("dateRangeTo");
		String attributes = req.getParameter("attributes");
		String countries = req.getParameter("countries");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		
		return microsegmentSerice.getARPUData(filter);
	}
	
	@RequestMapping(value="/microsegment/graph/paymenttype", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getPaymentTypeGraph(HttpServletRequest req) throws ParseException {
		String startdate = req.getParameter("dateRangeFrom");
		String endDate = req.getParameter("dateRangeTo");
		String attributes = req.getParameter("attributes");
		String countries = req.getParameter("countries");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		
		return microsegmentSerice.getPaymentTypeData(filter);
	}
	
	@RequestMapping(value="/microsegment/graph/devicetype", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getDeviceTypeGraph(HttpServletRequest req) throws ParseException {
		String startdate = req.getParameter("dateRangeFrom");
		String endDate = req.getParameter("dateRangeTo");
		String attributes = req.getParameter("attributes");
		String countries = req.getParameter("countries");
		Filter filter = new Filter();
		DateFormat dateFormat = new SimpleDateFormat(RAConstants.DEFAULT_DATE_FORMAT);
		filter.setDateFrom(dateFormat.parse(startdate).getTime());
		filter.setDateTo(dateFormat.parse(endDate).getTime());
		filter.setSelectedCountries(countries);
		if (!attributes.isEmpty()) {
			filter.setSelectedAttributes(CommonUtil.parseSelectedAttributes(attributes));
		}
		
		return microsegmentSerice.getDeviceTypeData(filter);
	}
	
}
