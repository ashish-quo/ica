/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	public Map<String, List<Attribute>> getAttributes() {
		LOGGER.info("Getting all attributes");
		Map<String, List<Attribute>> attributeMap = new LinkedHashMap<String, List<Attribute>>();
		List<Attribute> attributeList = this.commonDao.getAttributeList();
		for (Attribute attribute : attributeList) {
			String viewType = attribute.getViewType();
			List<Attribute> list = attributeMap.get(viewType);
			if (list == null) {
				list = new ArrayList<Attribute>();
				attributeMap.put(viewType, list);
			}
			list.add(attribute);
		}
		return attributeMap;
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
			DateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
			date = (Date) formatter.parse(str_date);
			return date.getTime();

		} catch (ParseException e)
		{
			LOGGER.info("Parsing exception in dateToTimestamp for date:"+str_date);

		}
		return 0;
	}
}
