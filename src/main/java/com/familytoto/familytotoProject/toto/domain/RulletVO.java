package com.familytoto.familytotoProject.toto.domain;

import java.sql.Timestamp;

public class RulletVO {
	 // 50000001
    private int rulletNo;

    private int familyCustNo;

    private double rulletBet;

    private String rulletResult;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B,W
    private String useYn;

    private String rulletCustResult;

	public int getRulletNo() {
		return rulletNo;
	}

	public void setRulletNo(int rulletNo) {
		this.rulletNo = rulletNo;
	}

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public double getRulletBet() {
		return rulletBet;
	}

	public void setRulletBet(double rulletBet) {
		this.rulletBet = rulletBet;
	}

	public String getRulletResult() {
		return rulletResult;
	}

	public void setRulletResult(String rulletResult) {
		this.rulletResult = rulletResult;
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

	public String getRulletCustResult() {
		return rulletCustResult;
	}

	public void setRulletCustResult(String rulletCustResult) {
		this.rulletCustResult = rulletCustResult;
	}
    
    
}
