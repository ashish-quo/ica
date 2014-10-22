/**
 * 
 */
package com.mobileum.roameranalytics.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Holds application wide properties, initialized at application startup.
 * @author sarvesh
 * 
 *
 */
public class RAPropertyUtil extends PropertyPlaceholderConfigurer {

	 /** The properties map. */
    private static Map<String, String> propertiesMap;

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#processProperties(org.springframework.
     * beans.factory.config.ConfigurableListableBeanFactory, java.util.Properties)
     */
    @Override
    protected void processProperties(final ConfigurableListableBeanFactory beanFactory, final Properties props)
        throws BeansException {
        super.processProperties(beanFactory, props);
        propertiesMap = new HashMap<String, String>();
        for (final Object key : props.keySet()) {
            final String keyStr = key.toString();
            propertiesMap.put(keyStr, resolvePlaceholder(keyStr, props));
        }
    }

    /**
     * Gets the property.
     * 
     * @param name
     *            the name
     * @return the property
     */
    public static String getProperty(final String name) {
        return propertiesMap.get(name);
    }
}
