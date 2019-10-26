package com.familytoto.familytotoProject.vip.domain;

import java.sql.Timestamp;

public class VipVO {
	// 200000001
    private int vipGamePlayerNo;

    // 100000001
    private int vipGameNo;

    private int creditId;

    private int familyCustNo;

    private int deathTurn;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B,W
    private String useYn;

    private String gameGubun;

    private Integer totalTurn;
    
    private int bettingCredit;

	public int getBettingCredit() {
		return bettingCredit;
	}

	public void setBettingCredit(int bettingCredit) {
		this.bettingCredit = bettingCredit;
	}

	public int getVipGamePlayerNo() {
		return vipGamePlayerNo;
	}

	public void setVipGamePlayerNo(int vipGamePlayerNo) {
		this.vipGamePlayerNo = vipGamePlayerNo;
	}

	public int getVipGameNo() {
		return vipGameNo;
	}

	public void setVipGameNo(int vipGameNo) {
		this.vipGameNo = vipGameNo;
	}

	public int getCreditId() {
		return creditId;
	}

	public void setCreditId(int creditId) {
		this.creditId = creditId;
	}

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public int getDeathTurn() {
		return deathTurn;
	}

	public void setDeathTurn(int deathTurn) {
		this.deathTurn = deathTurn;
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

	public String getGameGubun() {
		return gameGubun;
	}

	public void setGameGubun(String gameGubun) {
		this.gameGubun = gameGubun;
	}

	public Integer getTotalTurn() {
		return totalTurn;
	}

	public void setTotalTurn(Integer totalTurn) {
		this.totalTurn = totalTurn;
	}
    
    
}
