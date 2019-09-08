package com.familytoto.familytotoProject.productbuy.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductBuyVO {
	 // 60000001
    private int productBuyNo;

    private int familyCustNo;

    private int productNo;

    private int deliveryNo;

    private int productBuyCredit;

    private int productBuyAmount;

    private int productBuyDelCredit;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B
    private String useYn;
    
    private int productBuyGrpNo;

    private String familyCustAddr;

    @NotBlank
    private String familyCustAddrSi;

    @NotBlank
    private String familyCustAddrGugun;

    @NotBlank
    private String familyCustAddrDong;

    @NotBlank
    private String zipCode1;

    private String zipCode2;
    
    private int totalCredit;
    
    public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	public int getProductBuyNo() {
		return productBuyNo;
	}

	public void setProductBuyNo(int productBuyNo) {
		this.productBuyNo = productBuyNo;
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

	public int getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(int deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public int getProductBuyCredit() {
		return productBuyCredit;
	}

	public void setProductBuyCredit(int productBuyCredit) {
		this.productBuyCredit = productBuyCredit;
	}

	public int getProductBuyAmount() {
		return productBuyAmount;
	}

	public void setProductBuyAmount(int productBuyAmount) {
		this.productBuyAmount = productBuyAmount;
	}

	public int getProductBuyDelCredit() {
		return productBuyDelCredit;
	}

	public void setProductBuyDelCredit(int productBuyDelCredit) {
		this.productBuyDelCredit = productBuyDelCredit;
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

	public int getProductBuyGrpNo() {
		return productBuyGrpNo;
	}

	public void setProductBuyGrpNo(int productBuyGrpNo) {
		this.productBuyGrpNo = productBuyGrpNo;
	}

	public String getFamilyCustAddr() {
		return familyCustAddr;
	}

	public void setFamilyCustAddr(String familyCustAddr) {
		this.familyCustAddr = familyCustAddr;
	}

	public String getFamilyCustAddrSi() {
		return familyCustAddrSi;
	}

	public void setFamilyCustAddrSi(String familyCustAddrSi) {
		this.familyCustAddrSi = familyCustAddrSi;
	}

	public String getFamilyCustAddrGugun() {
		return familyCustAddrGugun;
	}

	public void setFamilyCustAddrGugun(String familyCustAddrGugun) {
		this.familyCustAddrGugun = familyCustAddrGugun;
	}

	public String getFamilyCustAddrDong() {
		return familyCustAddrDong;
	}

	public void setFamilyCustAddrDong(String familyCustAddrDong) {
		this.familyCustAddrDong = familyCustAddrDong;
	}

	public String getZipCode1() {
		return zipCode1;
	}

	public void setZipCode1(String zipCode1) {
		this.zipCode1 = zipCode1;
	}

	public String getZipCode2() {
		return zipCode2;
	}

	public void setZipCode2(String zipCode2) {
		this.zipCode2 = zipCode2;
	}

	@Override
	public String toString() {
		return "ProductBuyVO [productBuyNo=" + productBuyNo + ", familyCustNo=" + familyCustNo + ", productNo="
				+ productNo + ", deliveryNo=" + deliveryNo + ", productBuyCredit=" + productBuyCredit
				+ ", productBuyAmount=" + productBuyAmount + ", productBuyDelCredit=" + productBuyDelCredit
				+ ", regCustNo=" + regCustNo + ", chgCustNo=" + chgCustNo + ", regDt=" + regDt + ", chgDt=" + chgDt
				+ ", regIp=" + regIp + ", chgIp=" + chgIp + ", useYn=" + useYn + ", productBuyGrpNo=" + productBuyGrpNo
				+ ", familyCustAddr=" + familyCustAddr + ", familyCustAddrSi=" + familyCustAddrSi
				+ ", familyCustAddrGugun=" + familyCustAddrGugun + ", familyCustAddrDong=" + familyCustAddrDong
				+ ", zipCode1=" + zipCode1 + ", zipCode2=" + zipCode2 + ", totalCredit=" + totalCredit + "]";
	}
	
	
}