/**
 * 
 */
package com.mobileum.roameranalytics.exception;

/**
 * @author sarvesh
 *
 */
public class RADataAccessException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6622453185593552678L;
	
	/**
	 * Instantiates a new RA data access exception.
	 *
	 * @param msg the msg
	 * @param t the t
	 */
	public RADataAccessException(Throwable throwable) {
		super(throwable);
	}

}
