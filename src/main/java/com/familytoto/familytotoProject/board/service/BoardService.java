package com.familytoto.familytotoProject.board.service;

import java.util.List;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.PagingVO;

public interface BoardService {
	// 글쓰기
	int insertCustBorder(BoardVO vo);
	
	// 삭제하기
	int updateDeleteBoard(BoardVO vo);
	
	// 수정하기
	int updateBorder(BoardVO vo);
	
	// 게시글보기
	BoardVO getShowBoard(BoardVO vo);
	
	
	// 페이징
	public int getBoardListCnt() throws Exception;
	public List<BoardVO> getBoardList(PagingVO pagination) throws Exception;
}
