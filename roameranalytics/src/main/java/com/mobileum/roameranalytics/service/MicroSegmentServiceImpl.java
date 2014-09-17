/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileum.roameranalytics.dao.MicroSegmentDaoI;
import com.mobileum.roameranalytics.model.Filter;

/**
 * @author sarvesh
 *
 */
@Service
public class MicroSegmentServiceImpl implements MicroSegmentServiceI {

	@Autowired
	private MicroSegmentDaoI microsegmentDao;
	
	@Override
	public Map<String, Object> getNetworkData(Filter filter) {
		return microsegmentDao.getNetworkData(filter);
	}

	@Override
	public Map<String, Object> getRoamerTypeData(Filter filter) {
		return microsegmentDao.getRoamerTypeData(filter);
	}


	@Override
	public Map<String, Object> getPaymentTypeData(Filter filter) {
		return microsegmentDao.getPaymentTypeData(filter);
	}


	@Override
	public Map<String, Object> getDeviceTypeData(Filter filter) {
		return microsegmentDao.getDeviceTypeData(filter);
	}


	@Override
	public Map<String, Object> getARPUData(Filter filter) {
		return microsegmentDao.getARPUData(filter);
	}


	@Override
	public Map<String, Object> getMSChartData(Filter filter, String column, String columnType,
			Map<String,String> catNameValue) {
		return microsegmentDao.getMSChartData(filter, column, columnType, catNameValue);
	}

	@Override
	public Map<String, Map<String, String>> getAttributeLabelAndValue() {
		return microsegmentDao.getAttributeLabelAndValue();
	}
}
