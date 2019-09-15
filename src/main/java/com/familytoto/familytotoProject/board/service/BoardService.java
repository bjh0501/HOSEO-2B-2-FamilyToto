package com.familytoto.familytotoProject.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.FileVO;
import com.familytoto.familytotoProject.board.domain.SearchVO;

public interface BoardService {
	// 글쓰기
	int insertCustBoard(BoardVO vo, int nGubun);
	
	// 삭제하기
	int updateDeleteBoard(String sNo, HttpSession session, HttpServletRequest request);
	
	// 수정하기
	int updateBoard(BoardVO vo, HttpSession session);
	
	// 게시글보기
	BoardVO getShowBoard(BoardVO vo);
	
	// 게시글 수정 보기
	BoardVO getUpdateBoard(BoardVO vo);
	
	// 익명 게시글삭제
	int updateDeleteAnnoBoard(String sNo, HttpServletRequest request, BoardVO vo);
	
	// 페이징
	public int getBoardListCnt(SearchVO search) throws Exception;
	public List<BoardVO> getBoardList(SearchVO search) throws Exception;
	
	// 익명 글쓰기
	int insertAnnoBoard(BoardVO vo, int nGubun); // 0 글쓰기, 1 답글

	// 댓글 카운트
	public int getCommentCnt(BoardVO vo);
	
	// 파일첨부
	public int insertFile(FileVO vo);
	
	// 파일수정
	public int updateFile(FileVO vo);

	// 로그 추가
	public int updateVisitLog(int boardNo);
	
	public FileVO getUploadedFile(int boardNo);
	
	// 공지사항보기
	public List<BoardVO> listNotice();
	
	// 게시글 답장 리스트보기
	List<BoardVO> listReplyBoard(int boardGrpNo);
}
