/**
 * 
 */
package com.mobileum.roameranalytics.exception;

/**
 * The Class ApplicationException.
 *
 * @author sarvesh
 */
public class ApplicationException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7788481859086366334L;
	
	/**
	 * Instantiates a new application exception.
	 *
	 * @param message the message
	 */
	public ApplicationException(String message) {
		super(message);
	}

}
