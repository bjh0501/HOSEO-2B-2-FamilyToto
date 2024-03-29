package com.familytoto.familytotoProject.creditShop.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import com.google.firebase.database.annotations.NotNull;

public class ProductVO {
    // 30000001
    private int productNo;

    // 100001
    @NotNull
    private int categoryNo;

    @NotBlank
    private String productName;

    @NotBlank
    private String productDesc;

    // 0
    @Min(10)
    @Max(100000000)
    private int productCredit;

    // 재고
    @Min(0)
    @Max(100000)
    private int productAmount;

    private int regCustNo;

    private int chgCustNo;

    private String regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B
    private String useYn;

    private int deliveryNo;
    
    private String categoryName;
    
    private String familyCustNickname;
    
    private int totalCredit;
    
    @URL
    private String productImgUrl;
    
    private int basketNo;    
    
    private String productImgPrimary;
    
    private double ratingAvg;
    
    private String cateogryName;
    
    private int pagingNo;
    
    private int familyCustNo;
    
    private String seller;
    
    private int deliveryCredit;
    
    private String deliveryName;
    
    private int cateogryNo;
    
    private String producctCommentRating;
    
    private int productImageNo;
    
    private String productImgServer;
    
    private int sellCnt;
    
    private int productBuyAmount;

	public int getProductBuyAmount() {
		return productBuyAmount;
	}

	public void setProductBuyAmount(int productBuyAmount) {
		this.productBuyAmount = productBuyAmount;
	}

	public int getSellCnt() {
		return sellCnt;
	}

	public void setSellCnt(int sellCnt) {
		this.sellCnt = sellCnt;
	}

	public String getProductImgServer() {
		return productImgServer;
	}

	public void setProductImgServer(String productImgServer) {
		this.productImgServer = productImgServer;
	}

	public int getProductImageNo() {
		return productImageNo;
	}

	public void setProductImageNo(int productImageNo) {
		this.productImageNo = productImageNo;
	}

	public String getProducctCommentRating() {
		return producctCommentRating;
	}

	public void setProducctCommentRating(String producctCommentRating) {
		this.producctCommentRating = producctCommentRating;
	}

	public int getCateogryNo() {
		return cateogryNo;
	}

	public void setCateogryNo(int cateogryNo) {
		this.cateogryNo = cateogryNo;
	}
    
   	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	public int getDeliveryCredit() {
		return deliveryCredit;
	}

	public void setDeliveryCredit(int deliveryCredit) {
		this.deliveryCredit = deliveryCredit;
	}

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public String getCateogryName() {
		return cateogryName;
	}

	public void setCateogryName(String cateogryName) {
		this.cateogryName = cateogryName;
	}

	public int getPagingNo() {
		return pagingNo;
	}

	public void setPagingNo(int pagingNo) {
		this.pagingNo = pagingNo;
	}

	public double getRatingAvg() {
		return ratingAvg;
	}

	public void setRatingAvg(double ratingAvg) {
		this.ratingAvg = ratingAvg;
	}

	public String getProductImgPrimary() {
		return productImgPrimary;
	}

	public void setProductImgPrimary(String productImgPrimary) {
		this.productImgPrimary = productImgPrimary;
	}

	public int getBasketNo() {
		return basketNo;
	}

	public void setBasketNo(int basketNo) {
		this.basketNo = basketNo;
	}

	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getProductCredit() {
		return productCredit;
	}

	public void setProductCredit(int productCredit) {
		this.productCredit = productCredit;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
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
	
	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
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

	public int getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(int deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getFamilyCustNickname() {
		return familyCustNickname;
	}

	public void setFamilyCustNickname(String familyCustNickname) {
		this.familyCustNickname = familyCustNickname;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	@Override
	public String toString() {
		return "ProductVO [productNo=" + productNo + ", categoryNo=" + categoryNo + ", productName=" + productName
				+ ", productDesc=" + productDesc + ", productCredit=" + productCredit + ", productAmount="
				+ productAmount + ", regCustNo=" + regCustNo + ", chgCustNo=" + chgCustNo + ", regDt=" + regDt
				+ ", chgDt=" + chgDt + ", regIp=" + regIp + ", chgIp=" + chgIp + ", useYn=" + useYn + ", deliveryNo="
				+ deliveryNo + ", categoryName=" + categoryName + ", familyCustNickname=" + familyCustNickname
				+ ", DeliveryName=" + deliveryName + ", totalCredit=" + totalCredit + "]";
	}

	
}
