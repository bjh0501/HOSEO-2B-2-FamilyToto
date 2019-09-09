package com.familytoto.familytotoProject.scheduler.domain;

import java.sql.Timestamp;

public class TotoSportsVO {

    // 40000001
    private int sportsNo;

    // (E,I)(BA,SO)
    private String sportsGubun;

    // K리그1 등등
    private String sportsLeagueName;

    private String sportsTeam1;

    private String sportsTeam2;

    private int sportsScore1;

    private int sportsScore2;

    // 1,2,D
    private String sportsResult;

    private Timestamp sportsSchedule;

    private double sportsWinBet;

    private double sportsDrawBet;

    private double sportsLoseBet;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

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

	public String getSportsTeam1() {
		return sportsTeam1;
	}

	public void setSportsTeam1(String sportsTeam1) {
		this.sportsTeam1 = sportsTeam1;
	}

	public String getSportsTeam2() {
		return sportsTeam2;
	}

	public void setSportsTeam2(String sportsTeam2) {
		this.sportsTeam2 = sportsTeam2;
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

	public Timestamp getSportsSchedule() {
		return sportsSchedule;
	}

	public void setSportsSchedule(Timestamp sportsSchedule) {
		this.sportsSchedule = sportsSchedule;
	}

	
	

	public double getSportsWinBet() {
		return sportsWinBet;
	}

	public void setSportsWinBet(double sportsWinBet) {
		this.sportsWinBet = sportsWinBet;
	}

	public double getSportsDrawBet() {
		return sportsDrawBet;
	}

	public void setSportsDrawBet(double sportsDrawBet) {
		this.sportsDrawBet = sportsDrawBet;
	}

	public double getSportsLoseBet() {
		return sportsLoseBet;
	}

	public void setSportsLoseBet(double sportsLoseBet) {
		this.sportsLoseBet = sportsLoseBet;
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

	@Override
	public String toString() {
		return "TotoSportsVO [sportsNo=" + sportsNo + ", sportsGubun=" + sportsGubun + ", sportsLeagueName="
				+ sportsLeagueName + ", sportsTeam1=" + sportsTeam1 + ", sportsTeam2=" + sportsTeam2 + ", sportsScore1="
				+ sportsScore1 + ", sportsScore2=" + sportsScore2 + ", sportsResult=" + sportsResult
				+ ", sportsSchedule=" + sportsSchedule + ", sportsWinBet=" + sportsWinBet + ", sportsDrawBet="
				+ sportsDrawBet + ", sportsLoseBet=" + sportsLoseBet + ", regCustNo=" + regCustNo + ", chgCustNo="
				+ chgCustNo + ", regDt=" + regDt + ", chgDt=" + chgDt + ", regIp=" + regIp + ", chgIp=" + chgIp + "]";
	}

	
	
    
    
}
