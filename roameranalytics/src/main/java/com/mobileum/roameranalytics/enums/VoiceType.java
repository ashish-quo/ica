/**
 * 
 */
package com.mobileum.roameranalytics.enums;

import com.mobileum.roameranalytics.exception.ApplicationException;

/**
 * @author sarvesh
 *
 */
public enum VoiceType {

	MT, MO;

	private static final VoiceType[] ENUMS = VoiceType.values();

	public static VoiceType of(int type) {
		if (type < 1 || type > 2) {
			throw new ApplicationException("Invalid value for roamer type: "
					+ type, new IllegalArgumentException());
		}
		return ENUMS[type - 1];
	}
}
