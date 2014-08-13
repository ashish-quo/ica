/**
 * 
 */
package com.mobileum.roameranalytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mobileum.roameranalytics.dao.TrendDao;
import com.mobileum.roameranalytics.dao.TrendDaoI;

/**
 * @author Quovantis_Dev
 *
 */
@Controller
@RequestMapping("/")
public class TrendController {

	@Autowired
	TrendDaoI tdao;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showHome() {
		System.out.println("home");
		tdao.insertData();
		
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
}
