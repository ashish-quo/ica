/**
 * 
 */
package roameranalytics;

import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mobileum.roameranalytics.controller.TrendController;

/**
 * @author Quovantis_Dev
 *
 */
public class TrendControllerTest {

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.TrendController#showHome()}.
	 */
	@Autowired
	private TrendController tc;
	
	@Test
	public void testShowHome() {
	}

	/**
	 * Test method for {@link com.mobileum.roameranalytics.controller.TrendController#showRoamingTrends()}.
	 */
	@Test
	@Ignore
	public void testShowRoamingTrends() {
		fail("Not yet implemented");
	}

}
