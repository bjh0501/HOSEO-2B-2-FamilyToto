package com.familytoto.familytotoProject.registerCust.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCrypt;

//통합 회원
public class RegisterCustVO {

	// FAMILY_CUST_NO 10000001
	private int familyCustNo;

	// FAMILY_CUST_NICKNAME
	@Pattern(regexp = "[a-zA-Zㄱ-힣0-9]{2,8}", message = "2자에서 8자 사이의 영문, 숫자, 한글만 가능")
	@NotBlank
	private String familyCustNickname;

	// FAMILY_CUST_EMAIL
	@Email
	@NotBlank
	private String familyCustEmail;

	// FAMILY_CUST_ADDR1
	@Size(max=200, message="200자이하만 가능")
	private String familyCustAddr;
	
	// @Size(min=2, max=8, message="2자에서 8자 사이의 값만 가능합니다")
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

	@NotEmpty
	private String familyCustAddrSi;
	
	@NotNull
	private String familyCustAddrGugun;
	
	@NotNull
	private String familyCustAddrDong;
	
	private String zipCode1;
	
	private String zipCode2;

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

	public String getFamilyCustAddr() {
		return familyCustAddr;
	}

	public void setFamilyCustAddr(String familyCustAddr) {
		this.familyCustAddr = familyCustAddr;
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
	
	
}
