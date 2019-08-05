package com.familytoto.familytotoProject.mybatis.webLog.domain;

public class WebLogVO {
	private int logNo;
	private String currentUrl;
	private String prevUrl;
	private String ip;
	private int custNo;
	
	public String getCurrentUrl() {
		return currentUrl;
	}
	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
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
	public int getLogNo() {
		return logNo;
	}
	public void setLogNo(int logNo) {
		this.logNo = logNo;
	}
	
}
