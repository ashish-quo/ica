/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileum.roameranalytics.dao.CommonDaoI;
import com.mobileum.roameranalytics.model.Attribute;
import com.mobileum.roameranalytics.model.Country;

/**
 * @author sarvesh
 *
 */
@Service
public class CommonServiceImpl implements CommonServiceI{

	/** The common dao. */
	@Autowired
	private CommonDaoI commonDao;
	
	/** The logger. */
	private static Logger LOGGER = LoggerFactory.getLogger("CommonServiceImpl");
	
	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.service.TrendServiceI#getAttributes()
	 */
	public List<Attribute> getAttributes() {
		LOGGER.info("Getting all attributes");
		return this.commonDao.getAttributeList();
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.service.CommonServiceI#getAllCountries()
	 */
	public List<Country> getAllCountries() {
		LOGGER.info("Getting all countries");
		return this.commonDao.getAllCountries();
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
			LOGGER.info("Parsing exception in dateToTimestamp for date:"+str_date);

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
