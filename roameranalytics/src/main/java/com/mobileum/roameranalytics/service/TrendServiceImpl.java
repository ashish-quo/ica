/**
 * 
 */
package com.mobileum.roameranalytics.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobileum.roameranalytics.common.QueryBuilder;
import com.mobileum.roameranalytics.dao.TrendDaoI;
import com.mobileum.roameranalytics.model.Attribute;

/**
 * @author Quovantis_Dev
 *
 */
@Service
public class TrendServiceImpl implements TrendServiceI{

<<<<<<< HEAD
	/** The trend dao. */
	@Autowired
	private TrendDaoI trendDao;
	
	/* (non-Javadoc)
	 * @see com.mobileum.roameranalytics.service.TrendServiceI#getAttributes()
	 */
	public Map<String, List<Attribute>> getAttributes() {
		Map<String, List<Attribute>> attributeMap = new LinkedHashMap<String, List<Attribute>>();
		List<Attribute> attributeList = trendDao.getAttributeList();
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

	public void printQuery()
	{
		System.out.println("hii"+QueryBuilder.queryForHeatMap());
	}
=======
>>>>>>> branch 'master' of git@github.com:tarunkohli/mobileum.git
}
