package com.familytoto.familytotoProject.webLog.domain;

import java.sql.Timestamp;

public class WebLogVO {
	private int logNo;
	private String currentUrl;
	private int boardNo;
	private String prevUrl;
	private String ip;
	private int custNo;
	private Timestamp regDt;
	
	public int getLogNo() {
		return logNo;
	}
	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}
	public String getCurrentUrl() {
		return currentUrl;
	}
	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getPrevUrl() {
		return prevUrl;
	}
	public void setPrevUrl(String prevUrl) {
		this.prevUrl = prevUrl;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getCustNo() {
		return custNo;
	}
	public void setCustNo(int custNo) {
		this.custNo = custNo;
	}
	public Timestamp getRegDt() {
		return regDt;
	}
	public void setRegDt(Timestamp regDt) {
		this.regDt = regDt;
	}
	
	
	
}
