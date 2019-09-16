package com.familytoto.familytotoProject.comment.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class CommentVO {

    // 20000001
    private int commentNo;

    // 10000001
    private int boardNo;

    private int commentGrpNo;

    @Min(0)
    private int commentGrpOrd;

    @Max(6)
    @Min(0)
    private int commentGrpDepth;

    // 한글250글자
    @Size(max = 120, message = "최대120글자 입력가능")
    @NotBlank(message = "공백 입력불가능")
    private String commentContents;

    @Pattern(regexp = "[a-zA-Zㄱ-힣0-9]{2,8}", message = "2자에서 8자 사이의 영문, 숫자, 한글만 가능")
    private String commentAnnoId;

    @Length(min = 4, max = 20)
    private String commentAnnoPw;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // 게시글페이지
    private String scCustGubun;
    
    private String regDtStr;
    
    private String useYn;
    
	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegDtStr() {
		return regDtStr;
	}

	public void setRegDtStr(String regDtStr) {
		this.regDtStr = regDtStr;
	}

	public String getScCustGubun() {
		return scCustGubun;
	}

	public void setScCustGubun(String scCustGubun) {
		this.scCustGubun = scCustGubun;
	}

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
		return "CommentVO [commentNo=" + commentNo + ", boardNo=" + boardNo + ", commentGrpNo=" + commentGrpNo
				+ ", commentGrpOrd=" + commentGrpOrd + ", commentGrpDepth=" + commentGrpDepth + ", commentContents="
				+ commentContents + ", commentAnnoId=" + commentAnnoId + ", commentAnnoPw=" + commentAnnoPw
				+ ", regCustNo=" + regCustNo + ", chgCustNo=" + chgCustNo + ", regDt=" + regDt + ", chgDt=" + chgDt
				+ ", regIp=" + regIp + ", chgIp=" + chgIp + "]";
	}
}
