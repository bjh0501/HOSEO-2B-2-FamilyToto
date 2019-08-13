package com.familytoto.familytotoProject.registerCust.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCrypt;

//통합 회원
public class RegisterCustVO {

	// FAMILY_CUST_NO 10000001
	private int familyCustNo;

	// FAMILY_CUST_NICKNAME
	@Size(min=2, max=8, message="2자에서 8자 사이의 값만 가능합니다")
	@Pattern(regexp = "/^[a-zA-Zㄱ-힣0-9+]{2,8}$/", message = "2자에서 8자 사이의 영문, 숫자, 한글만 가능")
	private String familyCustNickname;

	// FAMILY_CUST_EMAIL
	@Email
	private String familyCustEmail;

	// FAMILY_CUST_ADDR1
	private String familyCustAddr1;

	// FAMILY_CUST_ADDR2
	private String familyCustAddr2;
	
	@Size(min=2, max=8, message="2자에서 8자 사이의 값만 가능합니다")
	private String familyCustRecommend;
	
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

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public String getFamilyCustNickname() {
		return familyCustNickname;
	}

	public void setFamilyCustNickname(String familyCustNickname) {
		this.familyCustNickname = familyCustNickname;
	}

	public String getFamilyCustEmail() {
		return familyCustEmail;
	}

	public void setFamilyCustEmail(String familyCustEmail) {
		this.familyCustEmail = familyCustEmail;
	}

	public String getFamilyCustAddr1() {
		return familyCustAddr1;
	}

	public void setFamilyCustAddr1(String familyCustAddr1) {
		this.familyCustAddr1 = familyCustAddr1;
	}

	public String getFamilyCustAddr2() {
		return familyCustAddr2;
	}

	public void setFamilyCustAddr2(String familyCustAddr2) {
		this.familyCustAddr2 = familyCustAddr2;
	}

	public String getFamilyCustRecommend() {
		return familyCustRecommend;
	}

	public void setFamilyCustRecommend(String familyCustRecommend) {
		this.familyCustRecommend = familyCustRecommend;
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
