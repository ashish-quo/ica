/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mobileum.roameranalytics.exception.RADataAccessException;

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

	}
	
	@Test
	@Ignore
	public void testGetAllNetworkAndNetworkGroups() throws RADataAccessException {

	}
	
	@Test
	@Ignore
	public void getAttributes() throws RADataAccessException {

	}

}
