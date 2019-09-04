package com.familytoto.familytotoProject.basket.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Min;

public class BasketVO {
	// 50000001
    private int basketNo;

    private int familyCustNo;

    private int productNo;

    @Min(1)
    private int basketAmount;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N
    private String useYn;
    
    private String productName;
    
    private int productCredit;
    
    private int totalCredit;

	public int getBasketNo() {
		return basketNo;
	}

	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getBasketAmount() {
		return basketAmount;
	}

	public void setBasketAmount(int basketAmount) {
		this.basketAmount = basketAmount;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductCredit() {
		return productCredit;
	}

	public void setProductCredit(int productCredit) {
		this.productCredit = productCredit;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	@Override
	public String toString() {
		return "BasketVO [basketNo=" + basketNo + ", familyCustNo=" + familyCustNo + ", productNo=" + productNo
				+ ", basketAmount=" + basketAmount + ", regCustNo=" + regCustNo + ", chgCustNo=" + chgCustNo
				+ ", regDt=" + regDt + ", chgDt=" + chgDt + ", regIp=" + regIp + ", chgIp=" + chgIp + ", useYn=" + useYn
				+ ", productName=" + productName + ", productCredit=" + productCredit + ", totalCredit=" + totalCredit
				+ "]";
	}
}
