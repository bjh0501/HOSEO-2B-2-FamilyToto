package com.familytoto.familytotoProject.model.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Board {
	private int no;
	private String title;
	private String contents;
	private String gubun;
	private Date regDt;
	private String regCustNo;
	private Date chgDt;
	private String chgCustNo;
	private String ip;
	private String useYn;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getGubun() {
		return gubun;
	}
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getRegCustNo() {
		return regCustNo;
	}
	public void setRegCustNo(String regCustNo) {
		this.regCustNo = regCustNo;
	}
	public Date getChgDt() {
		return chgDt;
	}
	public void setChgDt(Date chgDt) {
		this.chgDt = chgDt;
	}
	public String getChgCustNo() {
		return chgCustNo;
	}
	public void setChgCustNo(String chgCustNo) {
		this.chgCustNo = chgCustNo;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

}

