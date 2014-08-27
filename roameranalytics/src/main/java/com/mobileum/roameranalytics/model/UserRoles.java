/**
 * 
 */
package com.mobileum.roameranalytics.model;

/**
 * @author smruti
 *
 */


/**
 * UserRoles generated by hbm2java
 */

public class UserRoles implements java.io.Serializable {

	private Integer id;
	private User user;
	private String roleName;

	public UserRoles() {
	}

	public UserRoles(User user, String roleName) {
		this.user = user;
		this.roleName = roleName;
	}

	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

		public User getUser() {
		return this.user;
	}

	public void setEmployee(User user) {
		this.user = user;
	}

	
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
