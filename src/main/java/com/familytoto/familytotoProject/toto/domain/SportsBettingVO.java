package com.familytoto.familytotoProject.toto.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class SportsBettingVO {

    // 100000001
    private int sportsBettingNo;

    private int familyCustNo;

    private int sportsNo;

    private int creditId;

    // 엮은 배팅
    private int sportsBettingGroupNo;

    private int bettingTeamChoice;

    private double bettingBet;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    private String useYn;
    
    private double bettingGroupBet;

    // W,L
    private String bettingGroupResult;

    private String sportsResult;
    
	public String getSportsResult() {
		return sportsResult;
	}

	public void setSportsResult(String sportsResult) {
		this.sportsResult = sportsResult;
	}

	public int getSportsBettingNo() {
		return sportsBettingNo;
	}

	public void setSportsBettingNo(int sportsBettingNo) {
		this.sportsBettingNo = sportsBettingNo;
	}

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public int getSportsNo() {
		return sportsNo;
	}

	public void setSportsNo(int sportsNo) {
		this.sportsNo = sportsNo;
	}

	public int getCreditId() {
		return creditId;
	}

	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}	
	
	public int getSportsBettingGroupNo() {
		return sportsBettingGroupNo;
	}

	public void setSportsBettingGroupNo(int sportsBettingGroupNo) {
		this.sportsBettingGroupNo = sportsBettingGroupNo;
	}

	public int getBettingTeamChoice() {
		return bettingTeamChoice;
	}

	public void setBettingTeamChoice(int bettingTeamChoice) {
		this.bettingTeamChoice = bettingTeamChoice;
	}

	public double getBettingBet() {
		return bettingBet;
	}

	public void setBettingBet(double bettingBet) {
		this.bettingBet = bettingBet;
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

	public double getBettingGroupBet() {
		return bettingGroupBet;
	}

	public void setBettingGroupBet(double bettingGroupBet) {
		this.bettingGroupBet = bettingGroupBet;
	}

	public String getBettingGroupResult() {
		return bettingGroupResult;
	}

	public void setBettingGroupResult(String bettingGroupResult) {
		this.bettingGroupResult = bettingGroupResult;
	}
    
    
}
