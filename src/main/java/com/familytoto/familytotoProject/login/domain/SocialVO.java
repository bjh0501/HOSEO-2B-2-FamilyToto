package com.familytoto.familytotoProject.login.domain;

import java.sql.Timestamp;

public class SocialVO {

    // SC_CUST_NO 200000001
    private int scCustNo;

    // FAMILY_CUST_NO 10000001
    private int familyCustNo;

    // FB,NV,KA
    private String scCustGubun;

    // SC_CUST_ID 소셜 고유ID
    private String scCustId;

    // SC_CUST_EMAIL 소셜 이메일
    private String scCustEmail;

    // REG_CUST_NO 
    private int regCustNo;

    // CHG_CUST_NO 
    private int chgCustNo;

    // REG_DT 
    private Timestamp regDt;

    // CHG_DT 
    private Timestamp chgDt;

    // REG_IP 
    private String regIp;

    // CHG_IP 
    private String chgIp;

    // USE_YN Y,N,B
    private String useYn;

    private String scCustNick;

	public int getScCustNo() {
		return scCustNo;
	}

	public void setScCustNo(int scCustNo) {
		this.scCustNo = scCustNo;
	}

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public String getScCustGubun() {
		return scCustGubun;
	}

	public void setScCustGubun(String scCustGubun) {
		this.scCustGubun = scCustGubun;
	}

	public String getScCustId() {
		return scCustId;
	}

	public void setScCustId(String scCustId) {
		this.scCustId = scCustId;
	}

	public String getScCustEmail() {
		return scCustEmail;
	}

	public void setScCustEmail(String scCustEmail) {
		this.scCustEmail = scCustEmail;
	}

	public int getRegCustNo() {
		return regCustNo;
	}

	public void setRegCustNo(int regCustNo) {
		this.regCustNo = regCustNo;
	}

	public int getChgCustNo() {
		return chgCustNo;
	}

	public void setChgCustNo(int chgCustNo) {
		this.chgCustNo = chgCustNo;
	}

	public Timestamp getRegDt() {
		return regDt;
	}

	public void setRegDt(Timestamp regDt) {
		this.regDt = regDt;
	}

	public Timestamp getChgDt() {
		return chgDt;
	}

	public void setChgDt(Timestamp chgDt) {
		this.chgDt = chgDt;
	}

	public String getRegIp() {
		return regIp;
	}

	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}

	public String getChgIp() {
		return chgIp;
	}

	public void setChgIp(String chgIp) {
		this.chgIp = chgIp;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getScCustNick() {
		return scCustNick;
	}

	public void setScCustNick(String scCustNick) {
		this.scCustNick = scCustNick;
	}
    
}
