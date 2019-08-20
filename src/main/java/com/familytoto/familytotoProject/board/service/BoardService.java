package com.familytoto.familytotoProject.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.PagingVO;

public interface BoardService {
	// 글쓰기
	int insertCustBorder(BoardVO vo);
	
	// 삭제하기
	int updateDeleteBoard(String sNo, HttpSession session, HttpServletRequest request);
	
	// 수정하기
	int updateBorder(BoardVO vo);
	
	// 게시글보기
	BoardVO getShowBoard(BoardVO vo);
	
	
	// 페이징
	public int getBoardListCnt() throws Exception;
	public List<BoardVO> getBoardList(PagingVO pagination) throws Exception;
	
	// 익명 글쓰기
	int insertAnnoBoard(BoardVO vo);
}
