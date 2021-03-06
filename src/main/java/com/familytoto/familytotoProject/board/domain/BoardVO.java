package com.familytoto.familytotoProject.board.domain;

import java.sql.Timestamp;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class BoardVO {

    // 10000001
    private int boardNo;
    
    private int boardGrpNo;

    // 한글250글자
    @NotBlank(message = "공백 입력불가능")
    @Length(max=120, message = "최대 120글자까지 입력가능합니다.")
    private String boardTitle;

    @NotBlank
    private String boardContents;

    @Pattern(regexp = "[a-zA-Zㄱ-힣0-9]{2,8}", message = "2자에서 8자 사이의 영문, 숫자, 한글만 가능")
    private String boardAnnoId;

    @Length(min = 8, max = 20)
    private String boardAnnoPw;

    private int regCustNo;

    private int chgCustNo;

    private String regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B
    private String useYn;
    
    private String custNickname;
    
    private String custGubun;
    
    private String visit;    
    
    private String boardFilePath;
    
    private String boardFileName;
    
    private int boardGrpOrd;
    
    @Max(4)
    @Min(0)
    private int boardGrpDepth;
    
    private int commentCnt;
    
    private String boardNotice;
    
    private String regDtStr;

	public String getRegDtStr() {
		return regDtStr;
	}

	public void setRegDtStr(String regDtStr) {
		this.regDtStr = regDtStr;
	}

	public String getBoardNotice() {
		return boardNotice;
	}

	public void setBoardNotice(String boardNotice) {
		this.boardNotice = boardNotice;
	}

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoardGrpNo() {
		return boardGrpNo;
	}

	public void setBoardGrpNo(int boardGrpNo) {
		this.boardGrpNo = boardGrpNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}

	public String getBoardAnnoId() {
		return boardAnnoId;
	}

	public void setBoardAnnoId(String boardAnnoId) {
		this.boardAnnoId = boardAnnoId;
	}

	public String getBoardAnnoPw() {
		return boardAnnoPw;
	}

	public void setBoardAnnoPw(String boardAnnoPw) {
		this.boardAnnoPw = boardAnnoPw;
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

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
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

	public String getCustNickname() {
		return custNickname;
	}

	public void setCustNickname(String custNickname) {
		this.custNickname = custNickname;
	}

	public String getCustGubun() {
		return custGubun;
	}

	public void setCustGubun(String custGubun) {
		this.custGubun = custGubun;
	}

	public String getVisit() {
		return visit;
	}

	public void setVisit(String visit) {
		this.visit = visit;
	}

	public String getBoardFilePath() {
		return boardFilePath;
	}

	public void setBoardFilePath(String boardFilePath) {
		this.boardFilePath = boardFilePath;
	}

	public String getBoardFileName() {
		return boardFileName;
	}

	public void setBoardFileName(String boardFileName) {
		this.boardFileName = boardFileName;
	}

	public int getBoardGrpOrd() {
		return boardGrpOrd;
	}

	public void setBoardGrpOrd(int boardGrpOrd) {
		this.boardGrpOrd = boardGrpOrd;
	}

	public int getBoardGrpDepth() {
		return boardGrpDepth;
	}

	public void setBoardGrpDepth(int boardGrpDepth) {
		this.boardGrpDepth = boardGrpDepth;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardGrpNo=" + boardGrpNo + ", boardTitle=" + boardTitle
				+ ", boardContents=" + boardContents + ", boardAnnoId=" + boardAnnoId + ", boardAnnoPw=" + boardAnnoPw
				+ ", regCustNo=" + regCustNo + ", chgCustNo=" + chgCustNo + ", regDt=" + regDt + ", chgDt=" + chgDt
				+ ", regIp=" + regIp + ", chgIp=" + chgIp + ", useYn=" + useYn + ", custNickname=" + custNickname
				+ ", custGubun=" + custGubun + ", visit=" + visit + ", boardFilePath=" + boardFilePath
				+ ", boardFileName=" + boardFileName + ", boardGrpOrd=" + boardGrpOrd + ", boardGrpDepth="
				+ boardGrpDepth + "]";
	}
    
    
    
}
