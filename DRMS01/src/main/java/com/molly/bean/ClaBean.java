package com.molly.bean;

import org.hibernate.validator.constraints.NotBlank;

public class ClaBean {
	private int cid;
	@NotBlank(message="{null}")
	private String cname;
	@NotBlank(message="{null}")
	private String ctime;
	@NotBlank(message="{null}")
	private String cteacher;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getCteacher() {
		return cteacher;
	}
	public void setCteacher(String cteacher) {
		this.cteacher = cteacher;
	}
	@Override
	public String toString() {
		return "ClaBean [cid=" + cid + ", cname=" + cname + ", ctime=" + ctime + ", cteacher=" + cteacher + "]";
	}
	
}
