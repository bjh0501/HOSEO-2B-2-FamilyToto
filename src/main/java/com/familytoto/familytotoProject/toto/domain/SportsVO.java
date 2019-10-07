package com.familytoto.familytotoProject.toto.domain;

import java.sql.Timestamp;

public class SportsVO {
	  // 40000001
    private int sportsNo;

    // (E,I)(BA,SO)
    private String sportsGubun;

    // K리그1 등등
    private String sportsLeagueName;

    private String sportsTeam1Name;

    private String sportsTeam2Name;

    private int sportsScore1;

    private int sportsScore2;

    // 1,2,D
    private String sportsResult;

    private String sportsSchedule;

    private double sportsTeam1Bet;

    private double sportsDrawBet;

    private double sportsTeam2Bet;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;
    
    // Y,N,B
    private String useYn;
    
    private String bettingTeamChoice;
    
    private int creditValue;
    
    private int sportsBettingGroupNo;    

    private double bettingGroupBet;
    
    private String bettingGroupResult;
    
    
    
	public String getBettingGroupResult() {
		return bettingGroupResult;
	}

	public void setBettingGroupResult(String bettingGroupResult) {
		this.bettingGroupResult = bettingGroupResult;
	}

	public double getBettingGroupBet() {
		return bettingGroupBet;
	}

	public void setBettingGroupBet(double bettingGroupBet) {
		this.bettingGroupBet = bettingGroupBet;
	}

	public int getSportsBettingGroupNo() {
		return sportsBettingGroupNo;
	}

	public void setSportsBettingGroupNo(int sportsBettingGroupNo) {
		this.sportsBettingGroupNo = sportsBettingGroupNo;
	}

	public int getCreditValue() {
		return creditValue;
	}

	public void setCreditValue(int creditValue) {
		this.creditValue = creditValue;
	}

	public String getBettingTeamChoice() {
		return bettingTeamChoice;
	}

	public void setBettingTeamChoice(String bettingTeamChoice) {
		this.bettingTeamChoice = bettingTeamChoice;
	}

	public int getSportsNo() {
		return sportsNo;
	}

	public void setSportsNo(int sportsNo) {
		this.sportsNo = sportsNo;
	}

	public String getSportsGubun() {
		return sportsGubun;
	}

	public void setSportsGubun(String sportsGubun) {
		this.sportsGubun = sportsGubun;
	}

	public String getSportsLeagueName() {
		return sportsLeagueName;
	}

	public void setSportsLeagueName(String sportsLeagueName) {
		this.sportsLeagueName = sportsLeagueName;
	}

	public String getSportsTeam1Name() {
		return sportsTeam1Name;
	}

	public void setSportsTeam1Name(String sportsTeam1Name) {
		this.sportsTeam1Name = sportsTeam1Name;
	}

	public String getSportsTeam2Name() {
		return sportsTeam2Name;
	}

	public void setSportsTeam2Name(String sportsTeam2Name) {
		this.sportsTeam2Name = sportsTeam2Name;
	}

	public int getSportsScore1() {
		return sportsScore1;
	}

	public void setSportsScore1(int sportsScore1) {
		this.sportsScore1 = sportsScore1;
	}

	public int getSportsScore2() {
		return sportsScore2;
	}

	public void setSportsScore2(int sportsScore2) {
		this.sportsScore2 = sportsScore2;
	}

	public String getSportsResult() {
		return sportsResult;
	}

	public void setSportsResult(String sportsResult) {
		this.sportsResult = sportsResult;
	}
	
	

	public String getSportsSchedule() {
		return sportsSchedule;
	}

	public void setSportsSchedule(String sportsSchedule) {
		this.sportsSchedule = sportsSchedule;
	}

	public double getSportsTeam1Bet() {
		return sportsTeam1Bet;
	}

	public void setSportsTeam1Bet(double sportsTeam1Bet) {
		this.sportsTeam1Bet = sportsTeam1Bet;
	}

	public double getSportsDrawBet() {
		return sportsDrawBet;
	}

	public void setSportsDrawBet(double sportsDrawBet) {
		this.sportsDrawBet = sportsDrawBet;
	}

	public double getSportsTeam2Bet() {
		return sportsTeam2Bet;
	}

	public void setSportsTeam2Bet(double sportsTeam2Bet) {
		this.sportsTeam2Bet = sportsTeam2Bet;
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
