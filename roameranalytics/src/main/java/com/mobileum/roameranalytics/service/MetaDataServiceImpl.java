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
import org.springframework.stereotype.Service;

import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.exception.ApplicationException;
import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.Country;
import com.mobileum.roameranalytics.repository.MetaDataRepository;

/**
 * @author sarvesh
 *
 */
@Service
public class MetaDataServiceImpl implements MetaDataService {

	/** The common dao. */
	@Autowired
	private MetaDataRepository commonDao;
	
	/** The logger. */
	private static Logger LOGGER = LogManager.getLogger(MetaDataServiceImpl.class.getName());
	
	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.service.TrendServiceI#getAttributes()
	 */
	public List<Attribute> getAttributes() {
		List<Attribute> commonAttributes = null;
		try {
			commonAttributes = this.commonDao.getAttributeList();
			for (Attribute attribute : commonAttributes) {
				if (RAConstants.ATTR_NETWORK.equalsIgnoreCase(attribute.getAttributeName())) {
					attribute.setAttributeCategoryList(this.commonDao.getAllNetworks(attribute.getId()));
				} else if (RAConstants.ATTR_NETWORK_GROUP.equalsIgnoreCase(attribute.getAttributeName())) {
					attribute.setAttributeCategoryList(this.commonDao.getNetworkGroups(attribute.getId()));
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
		List<Country> countries = null;
		try {
			countries = this.commonDao.getAllCountries();
		} catch (RADataAccessException dae) {
			throw new ApplicationException(RAConstants.APPLICATION_EXCEPTION_STRING, dae);
		}
		return countries;
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
