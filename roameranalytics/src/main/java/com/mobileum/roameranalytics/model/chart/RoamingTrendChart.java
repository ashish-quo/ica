/**
 * 
 */
package com.mobileum.roameranalytics.model.chart;

import java.util.List;
import java.util.Set;

import com.mobileum.roameranalytics.enums.DayOfWeek;

/**
 * @author sarvesh
 *
 */
public class RoamingTrendChart {

	/** The serieses. */
	private List<ChartSeries> perDaySeriesList;
	
	private List<ChartSeries> dowSeriesList;
	
	/** The categories. */
	private Set<Long> perDayCategoryList;
	
	private Set<DayOfWeek> dowCategoryList;

	/**
	 * @return the perDaySeriesList
	 */
	public List<ChartSeries> getPerDaySeriesList() {
		return perDaySeriesList;
	}

	/**
	 * @param perDaySeriesList the perDaySeriesList to set
	 */
	public void setPerDaySeriesList(List<ChartSeries> perDaySeriesList) {
		this.perDaySeriesList = perDaySeriesList;
	}

	/**
	 * @return the dowSeriesList
	 */
	public List<ChartSeries> getDowSeriesList() {
		return dowSeriesList;
	}

	/**
	 * @param dowSeriesList the dowSeriesList to set
	 */
	public void setDowSeriesList(List<ChartSeries> dowSeriesList) {
		this.dowSeriesList = dowSeriesList;
	}

	/**
	 * @return the perDayCategoryList
	 */
	public Set<Long> getPerDayCategoryList() {
		return perDayCategoryList;
	}

	/**
	 * @param perDayCategoryList the perDayCategoryList to set
	 */
	public void setPerDayCategoryList(Set<Long> perDayCategoryList) {
		this.perDayCategoryList = perDayCategoryList;
	}

	/**
	 * @return the dowCategoryList
	 */
	public Set<DayOfWeek> getDowCategoryList() {
		return dowCategoryList;
	}

	/**
	 * @param dowCategoryList the dowCategoryList to set
	 */
	public void setDowCategoryList(Set<DayOfWeek> dowCategoryList) {
		this.dowCategoryList = dowCategoryList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RoamingTrendChart [perDaySeriesList=" + perDaySeriesList
				+ ", dowSeriesList=" + dowSeriesList + ", perDayCategoryList="
				+ perDayCategoryList + ", dowCategoryList=" + dowCategoryList
				+ "]";
	}

}
