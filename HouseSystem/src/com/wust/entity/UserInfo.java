package com.wust.entity;

public class UserInfo {
	private int userId;
	private String username;
	private String password;
	private String telephone;
	private String realname;

	public UserInfo() {
	}

	public UserInfo(String username, String password, String telephone, String realname) {
		super();
		this.username = username;
		this.password = password;
		this.telephone = telephone;
		this.realname = realname;
	}

	public String toString() {
		return "userId = " + userId + "\nusername = " + username + "\npassword = " + password + "\ntelephone = "
				+ telephone + "\nrealname = " + realname;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

}
