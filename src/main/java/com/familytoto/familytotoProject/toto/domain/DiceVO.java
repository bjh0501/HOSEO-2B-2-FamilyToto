package com.familytoto.familytotoProject.toto.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Pattern;

public class DiceVO {

    // 20000001
    private int diceNo;

    // 1~2
    private int diceAmount;

    // 컴퓨터 랜덤배팅
    private double diceBet;

    // 1~12
    private int diceResult;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B,W
    private String useYn;

    private int familyCustNo;
    
    private String diceOption;
    
    private int creditId;

    public int getCreditId() {
		return creditId;
	}

	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}

	public String getDiceOption() {
		return diceOption;
	}

	public void setDiceOption(String diceOption) {
		this.diceOption = diceOption;
	}

	// 사용자가한 홀(H),짝(J),더블(D)
    private String diceCustBetOption;
    
	public String getDiceCustBetOption() {
		return diceCustBetOption;
	}

	public void setDiceCustBetOption(String diceCustBetOption) {
		this.diceCustBetOption = diceCustBetOption;
	}

	public int getDiceNo() {
		return diceNo;
	}

	public void setDiceNo(int diceNo) {
		this.diceNo = diceNo;
	}

	public int getDiceAmount() {
		return diceAmount;
	}

	public void setDiceAmount(int diceAmount) {
		this.diceAmount = diceAmount;
	}

	public double getDiceBet() {
		return diceBet;
	}

	public void setDiceBet(double diceBet) {
		this.diceBet = diceBet;
	}

	public int getDiceResult() {
		return diceResult;
	}

	public void setDiceResult(int diceResult) {
		this.diceResult = diceResult;
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

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}
    
}
