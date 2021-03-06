/**
 * 
 */
package com.mobileum.roameranalytics.controller;

import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;

import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.service.MicroSegmentService;

/**
 * @author sarvesh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "/spring-jdbc-test.xml"})
public class MicroSegmentControllerTest {

	@Autowired
	private MicroSegmentService msService;
	
	MicroSegmentController controller;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		controller = new MicroSegmentController();
		ReflectionTestUtils.setField(controller, "microsegmentSerice", msService,MicroSegmentService.class);
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.MicroSegmentController#showMicroSegment()}.
	 */
	@Test
	@Ignore
	public void testShowMicroSegment() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.MicroSegmentController#getMicroSegmentCharts(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	public void testGetMicroSegmentCharts() {
		//controller.getMicroSegmentCharts(req)
		final Filter filter = new Filter();
		filter.setSelectedAttributes(new HashMap<String, String>());
		filter.setSelectedCountries("China");
		filter.setDateFrom(1412121600);
		filter.setDateTo(1419984000);
		final Map<String, List<Object[]>>  data = msService.getMSChartData(filter, "Network", "visitednetworkname", "", new HashMap<String, String>(),"IN");
	}

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.MicroSegmentController#getMicroSegmentChartData(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	@Ignore
	public void testGetMicroSegmentChartData() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.MicroSegmentController#getNetworkGroupChartData(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	@Ignore
	public void testGetNetworkGroupChartData() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.MicroSegmentController#getNetworkGraph(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	@Ignore
	public void testGetNetworkGraph() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.MicroSegmentController#getRoamingCategoryGraph(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	@Ignore
	public void testGetRoamingCategoryGraph() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.MicroSegmentController#getARPUGraph(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	@Ignore
	public void testGetARPUGraph() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.MicroSegmentController#getPaymentTypeGraph(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	@Ignore
	public void testGetPaymentTypeGraph() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.MicroSegmentController#getDeviceTypeGraph(javax.servlet.http.HttpServletRequest)}.
	 */
	@Test
	@Ignore
	public void testGetDeviceTypeGraph() {
		fail("Not yet implemented");
	}

}
