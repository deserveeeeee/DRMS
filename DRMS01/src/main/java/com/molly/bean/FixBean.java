package com.molly.bean;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class FixBean {
	private int fid;
	@NotBlank(message="{null}")
	private String fcontent;
	@NotBlank(message="{null}")
	private String ftime;
	
	private String fstate;
	@Min(value=1,message="{null}")
	private int rid;
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFcontent() {
		return fcontent;
	}
	public void setFcontent(String fcontent) {
		this.fcontent = fcontent;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	public String getFstate() {
		return fstate;
	}
	public void setFstate(String fstate) {
		this.fstate = fstate;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	@Override
	public String toString() {
		return "FixBean [fid=" + fid + ", fcontent=" + fcontent + ", ftime=" + ftime + ", fstate=" + fstate + ", rid="
				+ rid + "]";
	}
	
}
