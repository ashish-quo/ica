/**
 * 
 */
package com.mobileum.roameranalytics.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.Country;
import com.mobileum.roameranalytics.service.CommonServiceI;
import com.mobileum.roameranalytics.service.TrendServiceI;

/**
 * @author Quovantis_Dev
 *
 */
@Controller
@RequestMapping("/")
public class TrendController {

	@Autowired
	private CommonServiceI commonService;
	
	@Autowired
	private TrendServiceI trendService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showHome() {
		System.out.println("home");
		//tdao.insertData();
		System.out.println(trendService.getHeatMap("2014-01-11", "2014-011-17", new ArrayList<String>() {{ add("NIGERIA"); }} ) );
		System.out.println(trendService.getHeatMap("2014-01-11", "2014-011-17", new ArrayList<String>()));
		return new ModelAndView("trendHome");
	}
	
	/**
	 * Renders Roaming trend header
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/trendHeader")
	public ModelAndView showTrendHeader() {
		System.out.println("Trend header");
		return new ModelAndView("trendHeader");
	}
	/**
	 * Renders roaming trends
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/roamingTrend")
	public ModelAndView showRoamingTrends() {
		System.out.println("Roaming Trends");
		return new ModelAndView("roamingTrend");
	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getAttributes")
	public @ResponseBody Map<String,List<Attribute>> getAttributes() {
		return commonService.getAttributes();
	}
	
	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	@RequestMapping(method=RequestMethod.GET, value="/getCountries")
	public @ResponseBody List<Country> getCountries() {
		return commonService.getAllCountries();
	}
	
	
}
