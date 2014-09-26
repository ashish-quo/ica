/**
 * 
 */
package com.mobileum.roameranalytics.configuration;

/**
 * @author smruti
 *
 */
import org.springframework.security.crypto.bcrypt.*;  

public class PasswordEncryption {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 System.out.println(getHashPassword("pass"));

	}
	
	 public static String getHashPassword(String password) {  
		  BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
		  String hashedPassword = passwordEncoder.encode(password);  
		  
		  return hashedPassword;  
		 }  

}
