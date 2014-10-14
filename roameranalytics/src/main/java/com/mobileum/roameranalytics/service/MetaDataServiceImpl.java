/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.exception.ApplicationException;
import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.AttributeCategory;
import com.mobileum.roameranalytics.model.Country;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.repository.MetaDataRepository;

/**
 * @author sarvesh
 *
 */
@Service
public class MetaDataServiceImpl implements MetaDataService {

	/** The common dao. */
	@Autowired
	@Qualifier("prestoMetadataRepository")
	private MetaDataRepository metaDataRepository;
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger(MetaDataServiceImpl.class.getName());
	
	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.service.TrendServiceI#getAttributes()
	 */
	public List<Attribute> getAttributes() {
		List<Attribute> commonAttributes = null;
		try {
			commonAttributes = this.metaDataRepository.getAttributeList();
			
			for (Attribute attribute : commonAttributes) {
				if (RAConstants.ATTR_NETWORK.equalsIgnoreCase(attribute.getAttributeName())) {
					attribute.setAttributeCategoryList(this.metaDataRepository.getAllNetworks(attribute.getId()));
				} else if (RAConstants.ATTR_NETWORK_GROUP.equalsIgnoreCase(attribute.getAttributeName())) {
					attribute.setAttributeCategoryList(this.metaDataRepository.getNetworkGroups(attribute.getId()));
				}
			}
		} catch (RADataAccessException dae) {
			throw new ApplicationException(RAConstants.APPLICATION_EXCEPTION_STRING, dae);
		}
		return commonAttributes;
		
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.service.CommonServiceI#getAllCountries()
	 */
	public List<Country> getAllCountries() {
		try {
			return this.metaDataRepository.getAllCountries();
		} catch (RADataAccessException dae) {
			throw new ApplicationException(RAConstants.APPLICATION_EXCEPTION_STRING, dae);
		}
	}
	
	@Override
	public List<AttributeCategory> getOtherCountriesTraveled(Filter filter) {
		try {
			return this.metaDataRepository.getOtherCountriesTraveled(filter);
		} catch (RADataAccessException dae) {
			throw new ApplicationException(RAConstants.APPLICATION_EXCEPTION_STRING, dae);
		}
	}
	
	/**
	 * Added by smruti on 2014-07-21
	 * @param str_date
	 * @return
	 */
	public long dateToTimestamp(String str_date)
	{
		try{

			Date date;
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = (Date) formatter.parse(str_date);
			return date.getTime()/1000;

		} catch (ParseException e)
		{
			LOGGER.error("Parsing exception in dateToTimestamp for date:"+str_date);

		}
		return 0;
	}
	
	public Object[] listToObjectArray(List<Object> list)
	{
		Object[] whereCriteria = new Object[list.size()];
		whereCriteria = list.toArray(whereCriteria);
		return whereCriteria;
	}


}
