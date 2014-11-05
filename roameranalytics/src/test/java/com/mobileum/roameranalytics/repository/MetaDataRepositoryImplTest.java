/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mobileum.roameranalytics.common.PrestoQueryBuilder;
import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Country;

/**
 * @author sarvesh
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "/spring-jdbc-test.xml"})
public class MetaDataRepositoryImplTest {

	/** The common dao. */
	@Autowired
	@Qualifier("prestoMetadataRepository")
	private MetaDataRepository metaDataRepository;
	
	@Test
	public void testGetCountries() throws RADataAccessException {
		final String queryIn = PrestoQueryBuilder.queryForAllCountries("in");
		final String queryOut = PrestoQueryBuilder.queryForAllCountries("out");
		System.out.println(queryOut);
		System.out.println(queryIn);
		final List<Country> countriesOut = metaDataRepository.getAllCountries("out");
		final List<Country> countriesIn = metaDataRepository.getAllCountries("out");

		assertNotNull(countriesOut);
		assertNotNull(countriesIn);
		if (countriesOut.isEmpty()) {
			fail("Out countries are empty");
		}
		if (countriesOut.isEmpty()) {
			fail("In countries are empty");
		}
	}
	
	public void testGetAllNetworkAndNetworkGroups() throws RADataAccessException {
		
	}

}
