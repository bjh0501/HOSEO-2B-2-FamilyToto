package com.familytoto.familytotoProject.toto.domain;

import java.sql.Timestamp;

public class GraphVO {
	   // 30000001
    private int graphNo;

    // 배팅
    private double graphMax;

    private Timestamp graphStartDt;

    private Timestamp graphEndDt;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    private String useYn;
    
    private double graphStopBet;

    private int familyCustNo;

	public double getGraphStopBet() {
		return graphStopBet;
	}

	public void setGraphStopBet(double graphStopBet) {
		this.graphStopBet = graphStopBet;
	}

	public int getFamilyCustNo() {
		return familyCustNo;
	}

	public void setFamilyCustNo(int familyCustNo) {
		this.familyCustNo = familyCustNo;
	}

	public int getGraphNo() {
		return graphNo;
	}

	public void setGraphNo(int graphNo) {
		this.graphNo = graphNo;
	}

	public double getGraphMax() {
		return graphMax;
	}

	public void setGraphMax(double graphMax) {
		this.graphMax = graphMax;
	}

	public Timestamp getGraphStartDt() {
		return graphStartDt;
	}

	public void setGraphStartDt(Timestamp graphStartDt) {
		this.graphStartDt = graphStartDt;
	}

	public Timestamp getGraphEndDt() {
		return graphEndDt;
	}

	public void setGraphEndDt(Timestamp graphEndDt) {
		this.graphEndDt = graphEndDt;
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
