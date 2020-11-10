package com.dto;

public class AdminDTO {

	private String userid;
	private String passwd;
	private String email;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public AdminDTO(String userid, String passwd, String email) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.email = email;
	}
	public AdminDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
