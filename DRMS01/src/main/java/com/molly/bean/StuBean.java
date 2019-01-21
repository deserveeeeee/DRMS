package com.molly.bean;

import org.hibernate.validator.constraints.NotBlank;

public class StuBean {
	private int sid;
	@NotBlank(message="{null}")
	private String sname;
	@NotBlank(message="{null}")
	private String sgender;
	@NotBlank(message="{null}")
	private ClaBean cla;
	@NotBlank(message="{null}")
	private String stime;
	@NotBlank(message="{null}")
	private RoomBean room;
	@NotBlank(message="{null}")
	private String sphone;
	private String imgs;
	
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSgender() {
		return sgender;
	}
	public void setSgender(String sgender) {
		this.sgender = sgender;
	}
	public ClaBean getCla() {
		return cla;
	}
	public void setCla(ClaBean cla) {
		this.cla = cla;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public RoomBean getRoom() {
		return room;
	}
	public void setRoom(RoomBean room) {
		this.room = room;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	@Override
	public String toString() {
		return "StuBean [sid=" + sid + ", sname=" + sname + ", sgender=" + sgender + ", cla=" + cla + ", stime=" + stime
				+ ", room=" + room + ", sphone=" + sphone + ", imgs=" + imgs + "]";
	}
	
}
