package com.familytoto.familytotoProject.creditShop.domain;

import java.sql.Timestamp;

public class MileageVO {

    // 80000001
    private int mileageNo;

    // 10000001
    private int familyCustNo;

    private int mileageValue;

    private String mileageState;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B
    private String useYn;

	public int getMileageNo() {
		return mileageNo;
	}

	public void setMileageNo(int mileageNo) {
		this.mileageNo = mileageNo;
	}

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public int getMileageValue() {
		return mileageValue;
	}

	public void setMileageValue(int mileageValue) {
		this.mileageValue = mileageValue;
	}

	public String getMileageState() {
		return mileageState;
	}

	public void setMileageState(String mileageState) {
		this.mileageState = mileageState;
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
		return "MileageVO [mileageNo=" + mileageNo + ", familyCustNo=" + familyCustNo + ", mileageValue=" + mileageValue
				+ ", mileageState=" + mileageState + ", regCustNo=" + regCustNo + ", chgCustNo=" + chgCustNo
				+ ", regDt=" + regDt + ", chgDt=" + chgDt + ", regIp=" + regIp + ", chgIp=" + chgIp + ", useYn=" + useYn
				+ "]";
	}
    
    
}
