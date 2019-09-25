package com.familytoto.familytotoProject.exp.domain;

import java.sql.Timestamp;

public class ExpVO {
	 // 100000001
    private int expNo;

    // 10000001
    private int familyCustNo;

    private String expState;

    private int expValue;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    private String useYn;
    
    private int level;
    
    private int familyCustExp;

    private int startExp;
    
    private int endExp;

	public int getStartExp() {
		return startExp;
	}

	public void setStartExp(int startExp) {
		this.startExp = startExp;
	}

	public int getEndExp() {
		return endExp;
	}

	public void setEndExp(int endExp) {
		this.endExp = endExp;
	}

	public int getFamilyCustExp() {
		return familyCustExp;
	}

	public void setFamilyCustExp(int familyCustExp) {
		this.familyCustExp = familyCustExp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExpNo() {
		return expNo;
	}

	public void setExpNo(int expNo) {
		this.expNo = expNo;
	}

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public String getExpState() {
		return expState;
	}

	public void setExpState(String expState) {
		this.expState = expState;
	}

	public int getExpValue() {
		return expValue;
	}

	public void setExpValue(int expValue) {
		this.expValue = expValue;
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
    
    
}
