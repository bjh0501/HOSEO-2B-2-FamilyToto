package com.familytoto.familytotoProject.creditShop.domain;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class ProductCommentVO {

    // 40000001
    private int productCommentNo;

    // 30000001
    private int productNo;

    // 한글250
    @Length(min= 0, max=250)
    private String productCommentContents;

    // 평점
    @Range(min=1,max=5)
    private double productCommentRating;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B
    private String useYn;

    private String familyCustNickname;
    
    public String getFamilyCustNickname() {
		return familyCustNickname;
	}

	public void setFamilyCustNickname(String familyCustNickname) {
		this.familyCustNickname = familyCustNickname;
	}

	private int familyCustNo;
    
	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public int getProductCommentNo() {
		return productCommentNo;
	}

	public void setProductCommentNo(int productCommentNo) {
		this.productCommentNo = productCommentNo;
	}

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getProductCommentContents() {
		return productCommentContents;
	}

	public void setProductCommentContents(String productCommentContents) {
		this.productCommentContents = productCommentContents;
	}

	public double getProductCommentRating() {
		return productCommentRating;
	}

	public void setProductCommentRating(double productCommentRating) {
		this.productCommentRating = productCommentRating;
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
