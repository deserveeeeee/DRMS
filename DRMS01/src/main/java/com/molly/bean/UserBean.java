package com.molly.bean;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

public class UserBean {
	private int uid;
	@NotBlank(message="{null}")
	private String uname;
	@NotBlank(message="{null}")
	@Pattern(regexp="^[a-zA-Z0-9]{3,20}$",message="{user}")
	private String upwd;
	private String roles;
	private String utime;
	private String uphone;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getUtime() {
		return utime;
	}
	public void setUtime(String utime) {
		this.utime = utime;
	}
	public String getUphone() {
		return uphone;
	}
	public void setUphone(String uphone) {
		this.uphone = uphone;
	}
	@Override
	public String toString() {
		return "UserBean [uid=" + uid + ", uname=" + uname + ", upwd=" + upwd + ", roles=" + roles + ", utime=" + utime
				+ ", uphone=" + uphone + "]";
	}
	
	
}
