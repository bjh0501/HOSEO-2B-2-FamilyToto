package com.familytoto.familytotoProject.qna.domain;

import java.sql.Timestamp;

public class QnaVO {
	 // 10000001
    private int qnaNo;

    // 사용자
    private int qnaQuestionRegCustNo;

    private String qnaQuestionAnnoId;

    private String qnaQuestionAnnoPw;

    private String qnaQuestionContents;

    private int qnaAdminNo;

    private String qnaAdminContents;

    private Timestamp qnaAdminDt;

    private String qnaAdminIp;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B
    private String useYn;

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public int getQnaQuestionRegCustNo() {
		return qnaQuestionRegCustNo;
	}

	public void setQnaQuestionRegCustNo(int qnaQuestionRegCustNo) {
		this.qnaQuestionRegCustNo = qnaQuestionRegCustNo;
	}

	public String getQnaQuestionAnnoId() {
		return qnaQuestionAnnoId;
	}

	public void setQnaQuestionAnnoId(String qnaQuestionAnnoId) {
		this.qnaQuestionAnnoId = qnaQuestionAnnoId;
	}

	public String getQnaQuestionAnnoPw() {
		return qnaQuestionAnnoPw;
	}

	public void setQnaQuestionAnnoPw(String qnaQuestionAnnoPw) {
		this.qnaQuestionAnnoPw = qnaQuestionAnnoPw;
	}

	public String getQnaQuestionContents() {
		return qnaQuestionContents;
	}

	public void setQnaQuestionContents(String qnaQuestionContents) {
		this.qnaQuestionContents = qnaQuestionContents;
	}

	public int getQnaAdminNo() {
		return qnaAdminNo;
	}

	public void setQnaAdminNo(int qnaAdminNo) {
		this.qnaAdminNo = qnaAdminNo;
	}

	public String getQnaAdminContents() {
		return qnaAdminContents;
	}

	public void setQnaAdminContents(String qnaAdminContents) {
		this.qnaAdminContents = qnaAdminContents;
	}

	public Timestamp getQnaAdminDt() {
		return qnaAdminDt;
	}

	public void setQnaAdminDt(Timestamp qnaAdminDt) {
		this.qnaAdminDt = qnaAdminDt;
	}

	public String getQnaAdminIp() {
		return qnaAdminIp;
	}

	public void setQnaAdminIp(String qnaAdminIp) {
		this.qnaAdminIp = qnaAdminIp;
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

	@Override
	public String toString() {
		return "QnaVO [qnaNo=" + qnaNo + ", qnaQuestionRegCustNo=" + qnaQuestionRegCustNo + ", qnaQuestionAnnoId="
				+ qnaQuestionAnnoId + ", qnaQuestionAnnoPw=" + qnaQuestionAnnoPw + ", qnaQuestionContents="
				+ qnaQuestionContents + ", qnaAdminNo=" + qnaAdminNo + ", qnaAdminContents=" + qnaAdminContents
				+ ", qnaAdminDt=" + qnaAdminDt + ", qnaAdminIp=" + qnaAdminIp + ", regCustNo=" + regCustNo
				+ ", chgCustNo=" + chgCustNo + ", regDt=" + regDt + ", chgDt=" + chgDt + ", regIp=" + regIp + ", chgIp="
				+ chgIp + ", useYn=" + useYn + "]";
	}    
}