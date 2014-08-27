/**
 * 
 */
package com.mobileum.roameranalytics.initializer;

/**
 * @author smruti
 *
 */

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.mobileum.roameranalytics.configuration.MultiHttpSecurityConfig;

public class SecurityWebApplicationInitializer extends
		AbstractSecurityWebApplicationInitializer {
	public SecurityWebApplicationInitializer() {
		super(MultiHttpSecurityConfig.class);
	}
}
