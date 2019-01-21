package com.molly.bean;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class RoomBean {
	private int rid;
	@NotBlank(message="{null}")
	private String rplan;
	@NotBlank(message="{null}")
	private String raddress;
	@Min(value=1,message="{null}")
	private int rcap;
	private int rused;
	private int rhave;
	@NotBlank(message="{null}")
	private String rlandlord;
	@NotBlank(message="{null}")
	private String rphone;
	@NotBlank(message="{null}")
	private String rtype;
	@NotBlank(message="{null}")
	private String rpayment;
	@NotBlank(message="{null}")
	private String rtime;
	@NotBlank(message="{null}")
	private String rstate;
	@NotBlank(message="{null}")
	private int rmoney;
	private List<StuBean> students;
	private List<FixBean> fixs;
	
	
	@Override
	public String toString() {
		return "RoomBean [rid=" + rid + ", rplan=" + rplan + ", raddress=" + raddress + ", rcap=" + rcap + ", rused="
				+ rused + ", rhave=" + rhave + ", rlandlord=" + rlandlord + ", rphone=" + rphone + ", rtype=" + rtype
				+ ", rpayment=" + rpayment + ", rtime=" + rtime + ", rstate=" + rstate + ", rmoney=" + rmoney
				+ ", students=" + students + ", fixs=" + fixs + "]";
	}
	public int getRhave() {
		return rhave;
	}
	public void setRhave(int rhave) {
		this.rhave = rhave;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getRplan() {
		return rplan;
	}
	public void setRplan(String rplan) {
		this.rplan = rplan;
	}
	public String getRaddress() {
		return raddress;
	}
	public void setRaddress(String raddress) {
		this.raddress = raddress;
	}
	public int getRcap() {
		return rcap;
	}
	public void setRcap(int rcap) {
		this.rcap = rcap;
	}
	public int getRused() {
		return rused;
	}
	public void setRused(int rused) {
		this.rused = rused;
	}
	public String getRlandlord() {
		return rlandlord;
	}
	public void setRlandlord(String rlandlord) {
		this.rlandlord = rlandlord;
	}
	public String getRphone() {
		return rphone;
	}
	public void setRphone(String rphone) {
		this.rphone = rphone;
	}
	public String getRtype() {
		return rtype;
	}
	public void setRtype(String rtype) {
		this.rtype = rtype;
	}
	public String getRpayment() {
		return rpayment;
	}
	public void setRpayment(String rpayment) {
		this.rpayment = rpayment;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	public List<StuBean> getStudents() {
		return students;
	}
	public void setStudents(List<StuBean> students) {
		this.students = students;
	}
	public List<FixBean> getFixs() {
		return fixs;
	}
	public void setFixs(List<FixBean> fixs) {
		this.fixs = fixs;
	}
	public String getRstate() {
		return rstate;
	}
	public void setRstate(String rstate) {
		this.rstate = rstate;
	}
	public int getRmoney() {
		return rmoney;
	}
	public void setRmoney(int rmoney) {
		this.rmoney = rmoney;
	}
	
}	
