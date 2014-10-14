/**
 * 
 */
package com.mobileum.roameranalytics.repository;

import static org.junit.Assert.assertNotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

	/** The jdbc template. */
	@Autowired
	@Qualifier("prestoJdbcTempate")
	private JdbcTemplate prestoJdbcTempate;
	
	/** The named parameter jdbc template. */
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/** The named parameter jdbc template. */
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate2;
	
	@Test
	public void testGetCountries() throws RADataAccessException {
		String query = PrestoQueryBuilder.queryForDistinctNetworks();

		List<Country> countries = new ArrayList<Country>(200);

		try {
			prestoJdbcTempate.query(query, new ResultSetExtractor<List<Country>>(){

				@Override
				public List<Country> extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					while(rs.next()) {
						System.out.println(rs.getString(1));
					}
					return countries;
				}
				
			});
		} catch(DataAccessException dae) {
			throw new RADataAccessException(dae);
		}
		
		System.out.println(countries.size());
		assertNotNull(countries);
	}

}
