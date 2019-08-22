package com.familytoto.familytotoProject.comment.domain;

import java.sql.Timestamp;

public class CommentVO {

    // 20000001
    private int commentNo;

    // 10000001
    private int boardNo;

    private int commentGrpNo;

    private int commentGrpOrd;

    private int commentGrpDepth;

    // 한글250글자
    private String commentContents;

    private String commentAnnoId;

    private String commentAnnoPw;

    private String regCustNo;

    private String chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getCommentGrpNo() {
		return commentGrpNo;
	}

	public void setCommentGrpNo(int commentGrpNo) {
		this.commentGrpNo = commentGrpNo;
	}

	public int getCommentGrpOrd() {
		return commentGrpOrd;
	}

	public void setCommentGrpOrd(int commentGrpOrd) {
		this.commentGrpOrd = commentGrpOrd;
	}

	public int getCommentGrpDepth() {
		return commentGrpDepth;
	}

	public void setCommentGrpDepth(int commentGrpDepth) {
		this.commentGrpDepth = commentGrpDepth;
	}

	public String getCommentContents() {
		return commentContents;
	}

	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}

	public String getCommentAnnoId() {
		return commentAnnoId;
	}

	public void setCommentAnnoId(String commentAnnoId) {
		this.commentAnnoId = commentAnnoId;
	}

	public String getCommentAnnoPw() {
		return commentAnnoPw;
	}

	public void setCommentAnnoPw(String commentAnnoPw) {
		this.commentAnnoPw = commentAnnoPw;
	}

	public String getRegCustNo() {
		return regCustNo;
	}

	public void setRegCustNo(String regCustNo) {
		this.regCustNo = regCustNo;
	}

	public String getChgCustNo() {
		return chgCustNo;
	}

	public void setChgCustNo(String chgCustNo) {
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
		return "CommentVO [commentNo=" + commentNo + ", boardNo=" + boardNo + ", commentGrpNo=" + commentGrpNo
				+ ", commentGrpOrd=" + commentGrpOrd + ", commentGrpDepth=" + commentGrpDepth + ", commentContents="
				+ commentContents + ", commentAnnoId=" + commentAnnoId + ", commentAnnoPw=" + commentAnnoPw
				+ ", regCustNo=" + regCustNo + ", chgCustNo=" + chgCustNo + ", regDt=" + regDt + ", chgDt=" + chgDt
				+ ", regIp=" + regIp + ", chgIp=" + chgIp + "]";
	}
}
