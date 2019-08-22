package com.familytoto.familytotoProject.comment.service;

import java.util.List;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.comment.domain.CommentVO;

public interface CommentService {
	// insert 댓글
	int insertComment(CommentVO vo);
	
	// 댓글 리스트 보기
	List<CommentVO> getListComment(BoardVO vo);
}
