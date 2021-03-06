/** 
* @author Feri 
*/
package com.hualiang.domain;
public class User {
	private int id;
	private String username;
	private String password;
	private String createtime;
	private int flag;
	private String email;
	private String gender;
	private String activatecode;
	private int role;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getActivatecode() {
		return activatecode;
	}
	public void setActivatecode(String activatecode) {
		this.activatecode = activatecode;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	/**
	 * 
	 */
	public User() {
		super();
	}
	/**
	 * @param username
	 * @param password
	 * @param email
	 * @param gender
	 * @param activatecode
	 * @param role
	 */
	public User(String username, String password, String email, String gender, String activatecode, int role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.activatecode = activatecode;
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", createtime=" + createtime
				+ ", flag=" + flag + ", email=" + email + ", gender=" + gender + ", activatecode=" + activatecode
				+ ", role=" + role + "]";
	}
	
}