/**
 * 
 */
package com.mobileum.roameranalytics.enums;

import com.mobileum.roameranalytics.exception.ApplicationException;

/**
 * @author sarvesh
 *
 */
public enum DeviceType {

	VALUE_PHONE("Value Phone"), FEATURE_PHONE("Feature Phone"), SMART_PHONE(
			"Smart Phone"), PREMIUM_PHONE("Premium Phone"), TABLET("Tablet"), DONGLE(
			"Dongle"), M2M("M2M");

	private static final DeviceType[] ENUMS = DeviceType.values();

	public static DeviceType of(int type) {
		if (type < 1 || type > 7) {
			throw new ApplicationException("Invalid value for device type: "
					+ type);
		}
		return ENUMS[type - 1];
	}

	private String name;

	/**
	 * 
	 */
	private DeviceType(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
