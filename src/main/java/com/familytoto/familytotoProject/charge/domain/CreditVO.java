package com.familytoto.familytotoProject.charge.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Size;

public class CreditVO {

    // 90000001
    private int creditId;

    // 10000001
    private int familyCustNo;

    // 타컬럼연결X
    private int creditValue;

    // 충전,구매
    private String creditState;

    // state의 id
    private int creditStateId;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B
    private String useYn;

	public int getCreditId() {
		return creditId;
	}

	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public int getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(int creditValue) {
		this.creditValue = creditValue;
	}

	public String getCreditState() {
		return creditState;
	}

	public void setCreditState(String creditState) {
		this.creditState = creditState;
	}

	public int getCreditStateId() {
		return creditStateId;
	}

	public void setCreditStateId(int creditStateId) {
		this.creditStateId = creditStateId;
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

	@Override
	public String toString() {
		return "CreditVO [creditId=" + creditId + ", familyCustNo=" + familyCustNo + ", creditValue=" + creditValue
				+ ", creditState=" + creditState + ", creditStateId=" + creditStateId + ", regCustNo=" + regCustNo
				+ ", chgCustNo=" + chgCustNo + ", regDt=" + regDt + ", chgDt=" + chgDt + ", regIp=" + regIp + ", chgIp="
				+ chgIp + ", useYn=" + useYn + "]";
	}
    
    
}
