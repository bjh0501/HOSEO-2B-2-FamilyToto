package com.familytoto.familytotoProject.toto.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class LadderVO {
	// 10000001
    private int ladderNo;

    // 2~5
    @Min(2)
    @Max(5)
    private int ladderRegs;

    private double ladderBet;

    // 1~5
    @Min(1)
    @Max(5)
    private int ladderAnswer;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

	public int getLadderNo() {
		return ladderNo;
	}

	public void setLadderNo(int ladderNo) {
		this.ladderNo = ladderNo;
	}

	public int getLadderRegs() {
		return ladderRegs;
	}

	public void setLadderRegs(int ladderRegs) {
		this.ladderRegs = ladderRegs;
	}

	public double getLadderBet() {
		return ladderBet;
	}

	public void setLadderBet(double ladderBet) {
		this.ladderBet = ladderBet;
	}

	public int getLadderAnswer() {
		return ladderAnswer;
	}

	public void setLadderAnswer(int ladderAnswer) {
		this.ladderAnswer = ladderAnswer;
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
    
    
}
