/**
 * 
 */
package com.mobileum.roameranalytics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Quovantis_Dev
 *
 */
@Controller
@RequestMapping("/")
public class TrendController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView showHome() {
		System.out.println("home");
		return new ModelAndView("trendHome");
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/trendHeader")
	public ModelAndView getTrendHeader() {
		System.out.println("roaming Trend");
		return new ModelAndView("trendHeader");
	}
}
