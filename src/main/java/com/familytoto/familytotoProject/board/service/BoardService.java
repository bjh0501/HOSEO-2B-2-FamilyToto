package com.familytoto.familytotoProject.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.PagingVO;

public interface BoardService {
	// 글쓰기
	int insertCustBoard(BoardVO vo);
	
	// 삭제하기
	int updateDeleteBoard(String sNo, HttpSession session, HttpServletRequest request);
	
	// 수정하기
	int updateBoard(BoardVO vo);
	
	// 게시글보기
	BoardVO getShowBoard(BoardVO vo);
	
	// 게시글 수정 보기
	BoardVO getUpdateBoard(BoardVO vo);
	
	// 익명 게시글삭제
	int updateDeleteAnnoBoard(String sNo, HttpServletRequest request, BoardVO vo);
	
	// 페이징
	public int getBoardListCnt() throws Exception;
	public List<BoardVO> getBoardList(PagingVO pagination) throws Exception;
	
	// 익명 글쓰기
	int insertAnnoBoard(BoardVO vo);

	// 댓글 카운트
	public int getCommentCnt(BoardVO vo);
	
	
}
