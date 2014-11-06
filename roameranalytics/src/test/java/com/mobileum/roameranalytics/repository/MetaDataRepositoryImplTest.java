/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;
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
		final List<Country> countriesOut = metaDataRepository.getAllCountries("out");
		final List<Country> countriesIn = metaDataRepository.getAllCountries("in");

		assertNotNull(countriesOut);
		assertNotNull(countriesIn);
		if (countriesOut.isEmpty()) {
			fail("Out countries are empty");
		}
		if (countriesOut.isEmpty()) {
			fail("In countries are empty");
		}
	}
	
	@Test
	public void testGetAllNetworkAndNetworkGroups() throws RADataAccessException {
		metaDataRepository.getAttributeList("out");
		final Map<Long,List<AttributeCategory>> in = metaDataRepository.getAllNetworkAndNetworkGroups(1,10,"in");
		final Map<Long,List<AttributeCategory>> out = metaDataRepository.getAllNetworkAndNetworkGroups(1,10,"out");
		
		assertNotNull(in);
		assertNotNull(out);
		
		if (out.isEmpty()) {
			fail("Out Networks or groups are empty");
		}
		if (in.isEmpty()) {
			fail("In network or group are empty");
		}
	}
	
	@Test
	public void getAttributes() throws RADataAccessException {
		final List<Attribute> outAttributes = metaDataRepository.getAttributeList("out");
		final List<Attribute> inAttributes = metaDataRepository.getAttributeList("in");
		
		assertNotNull(outAttributes);
		assertNotNull(inAttributes);
		
		if (outAttributes.isEmpty()) {
			fail("Out Attributes are empty");
		}
		if (inAttributes.isEmpty()) {
			fail("In attributes are empty");
		}
	}

}
