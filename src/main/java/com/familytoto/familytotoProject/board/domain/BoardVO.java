package com.familytoto.familytotoProject.board.domain;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO {

    // 10000001
    private int boardNo;

    private int boardReplyNo;
    
    private int boardGrpNo;

    // 한글250글자
    private String boardTitle;

    private String boardContents;

    private String boardAnnoId;

    private String boardAnnoPw;

    private int regCustNo;

    private int chgCustNo;

    private Timestamp regDt;

    private Timestamp chgDt;

    private String regIp;

    private String chgIp;

    // Y,N,B
    private String useYn;
    
    private String custNickname;
    
    private String custGubun;
    
    private String visit;    

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getBoardReplyNo() {
		return boardReplyNo;
	}

	public void setBoardReplyNo(int boardReplyNo) {
		this.boardReplyNo = boardReplyNo;
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

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", boardReplyNo=" + boardReplyNo + ", boardTitle=" + boardTitle
				+ ", boardContents=" + boardContents + ", boardAnnoId=" + boardAnnoId + ", boardAnnoPw=" + boardAnnoPw
				+ ", regCustNo=" + regCustNo + ", chgCustNo=" + chgCustNo + ", regDt=" + regDt + ", chgDt=" + chgDt
				+ ", regIp=" + regIp + ", chgIp=" + chgIp + ", useYn=" + useYn + "]";
	}
	
	public long diffTime(Timestamp regDt) throws ParseException {
		//요청시간 String
		String reqDateStr = regDt.toString();
		//현재시간 Date
		Date curDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");
		//요청시간을 Date로 parsing 후 time가져오기
		Date reqDate = dateFormat.parse(reqDateStr);
		long reqDateTime = reqDate.getTime();
		//현재시간을 요청시간의 형태로 format 후 time 가져오기
		curDate = dateFormat.parse(dateFormat.format(curDate));
		long curDateTime = curDate.getTime();
		//분으로 표현
		
		long minute = (curDateTime - reqDateTime) / 60000;
		return minute;
	}
}
