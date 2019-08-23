package com.familytoto.familytotoProject.comment.dao;

import java.util.List;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.comment.domain.CommentVO;

public interface CommentDao {
	// 댓글추가
	int insertComment(CommentVO vo);
	
	// 댓글 리스트 보기
	List<CommentVO> getListComment(BoardVO vo);
	
	// 댓글삭제
	int updateDeleteComment(CommentVO vo);
}
