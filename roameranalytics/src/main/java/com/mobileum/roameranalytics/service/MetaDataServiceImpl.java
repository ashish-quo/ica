/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	@Override
	public List<Attribute> getAttributes(final Filter filter,final String roamType) {
		List<Attribute> commonAttributes = null;
		try {
			commonAttributes = this.metaDataRepository.getAttributeList(filter,roamType);
			long networkAttrId = 0;
			long networkGroupAttrId = 0;
			long deviceModelAttrId = 0;
			long manufacturerAttrId = 0;
			
			Attribute networkAttr = null ;
			Attribute neworkgGrpupAttr = null;
			Attribute deviceModel = null;
			Attribute manufacturer = null;
			for (final Attribute attribute : commonAttributes) {
				if (RAConstants.ATTR_NETWORK.equalsIgnoreCase(attribute.getAttributeName())) {
					networkAttrId = attribute.getId();
					networkAttr = attribute;
				} else if (RAConstants.ATTR_NETWORK_GROUP.equalsIgnoreCase(attribute.getAttributeName())) {
					neworkgGrpupAttr = attribute;
					networkGroupAttrId = attribute.getId();
				} else if (RAConstants.ATTR_DEVICE_MODEL.equalsIgnoreCase(attribute.getAttributeName())) {
					deviceModel = attribute;
					deviceModelAttrId = attribute.getId();
				} else if (RAConstants.ATTR_MANUFACTURER.equalsIgnoreCase(attribute.getAttributeName())) {
					manufacturer = attribute;
					manufacturerAttrId = attribute.getId();
				}
			}
			
			final Map<Long,List<AttributeCategory>> networkAndGroupMap = 
					this.metaDataRepository.getAllNetworkAndNetworkGroups(filter,networkAttrId, networkGroupAttrId,roamType);
			networkAttr.setAttributeCategoryList(networkAndGroupMap.get(networkAttrId));
			neworkgGrpupAttr.setAttributeCategoryList(networkAndGroupMap.get(networkGroupAttrId));
			
			
			final Map<Long,List<AttributeCategory>> deviceModelManufacturerMap = 
					this.metaDataRepository.getDeviceModelsAndManufactures(filter, deviceModelAttrId, manufacturerAttrId, roamType);	
			
			deviceModel.setAttributeCategoryList(deviceModelManufacturerMap.get(deviceModelAttrId));
			manufacturer.setAttributeCategoryList(deviceModelManufacturerMap.get(manufacturerAttrId));
			
			
		} catch (final RADataAccessException dae) {
			throw new ApplicationException(RAConstants.APPLICATION_EXCEPTION_STRING, dae);
		}
		return commonAttributes;
		
	}

	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.service.CommonServiceI#getAllCountries()
	 */
	@Override
	public List<Country> getAllCountries(final Filter filter,final String roamType) {
		try {
			return this.metaDataRepository.getAllCountries(filter,roamType);
		} catch (final RADataAccessException dae) {
			throw new ApplicationException(RAConstants.APPLICATION_EXCEPTION_STRING, dae);
		}
	}
	
	@Override
	public List<AttributeCategory> getOtherCountriesTraveled(final Filter filter,final String roamType) {
		try {
			return this.metaDataRepository.getOtherCountriesTraveled(filter, roamType);
		} catch (final RADataAccessException dae) {
			throw new ApplicationException(RAConstants.APPLICATION_EXCEPTION_STRING, dae);
		}
	}
	
	/**
	 * Added by smruti on 2014-07-21
	 * @param str_date
	 * @return
	 */
	@Override
	public long dateToTimestamp(final String str_date)
	{
		try{

			Date date;
			final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = formatter.parse(str_date);
			return date.getTime()/1000;

		} catch (final ParseException e)
		{
			LOGGER.error("Parsing exception in dateToTimestamp for date:"+str_date);

		}
		return 0;
	}
	
	@Override
	public Object[] listToObjectArray(final List<Object> list)
	{
		Object[] whereCriteria = new Object[list.size()];
		whereCriteria = list.toArray(whereCriteria);
		return whereCriteria;
	}


}
