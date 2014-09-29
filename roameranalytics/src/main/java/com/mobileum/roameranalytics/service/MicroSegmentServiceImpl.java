/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileum.roameranalytics.common.RAConstants;
import com.mobileum.roameranalytics.exception.ApplicationException;
import com.mobileum.roameranalytics.exception.RADataAccessException;
import com.mobileum.roameranalytics.model.Filter;
import com.mobileum.roameranalytics.repository.MicroSegmentRepository;

/**
 * @author sarvesh
 *
 */
@Service
public class MicroSegmentServiceImpl implements MicroSegmentService {

	@Autowired
	private MicroSegmentRepository microsegmentDao;
	
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
	public Map<String, List<Object[]>> getMSChartData(Filter filter, String attributeName, String column, String columnType,
			Map<String,String> catNameValue) {
		try {
			return microsegmentDao.getMSChartData(filter, attributeName, column, catNameValue);
		} catch (RADataAccessException dae) {
			throw new ApplicationException(RAConstants.APPLICATION_EXCEPTION_STRING, dae);
		}
	}

	@Override
	public Map<String, Map<String, String>> getAttributeLabelAndValue() {
		return microsegmentDao.getAttributeLabelAndValue();
	}

	@Override
	public Map<String, List<Object[]>> getNetworkGroupData(Filter filter,
			String column, String columnType, Map<String, String> catNameValue) {
		try {
			return microsegmentDao.getNetworkGroupData(filter, column, columnType, catNameValue);
		} catch (RADataAccessException dae) {
			throw new ApplicationException(RAConstants.APPLICATION_EXCEPTION_STRING, dae);
		}
	}
}
