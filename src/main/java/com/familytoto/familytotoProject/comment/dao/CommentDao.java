package com.familytoto.familytotoProject.comment.dao;

import java.util.List;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.comment.domain.CommentVO;

public interface CommentDao {
	// 댓글추가
	int insertComment(CommentVO vo);
	
	// 댓글추가한거 그룹번호 수정
	int updateInsertComment(CommentVO vo);
	
	// 댓글 리스트 보기
	List<CommentVO> getListComment(BoardVO vo);
	
	// 회원댓글삭제
	int updateDeleteComment(CommentVO vo);
	
	// 비회원 댓글 확인
	CommentVO checkAnnoCommentPass(CommentVO vo);
	
	// 비회원댓글삭제
	int updateDeleteAnnoComment(CommentVO vo);
	
	// 비회원, 회원 댓글 업데이트
	int updateComment(CommentVO vo);
}
