/**
 * 
 */
package com.mobileum.roameranalytics.enums;


/**
 * @author sarvesh
 *
 */
public enum FilterColumn {

	NETWORK(1),
	NETWORK_SAME(1),
	NETWORK_DIFF(2),
	
	
	ROAMING_CATEGEGORY(2),
	ROAMING_CATEGEGORY_SILENT(1),
	ROAMING_CATEGEGORY_VALUE(2),
	ROAMING_CATEGEGORY_PREMIUM(3),
	
	PAYMENT_TYPE(4),
	PAYMENT_TYPE_PREPAID(1),
	PAYMENT_TYPE_POSTPAID(0),
	
	DOMESTIC_ARPU(3),
	DOMESTIC_ARPU_HIGH(6),
	DOMESTIC_ARPU_MED(5),
	DOMESTIC_ARPU_LOW(4);
	
	private int ind;
	
	/**
	 * 
	 */
	private FilterColumn(int ind) {
		this.ind = ind;
	}
	
	/**
	 * @return the ind
	 */
	public int getInd() {
		return ind;
	}
	
}
