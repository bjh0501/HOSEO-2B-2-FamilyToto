package com.familytoto.familytotoProject.comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.comment.domain.CommentVO;

@Repository
public class CommentDaoImpl implements CommentDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertComment(CommentVO vo) {
		return sqlSession.insert("boardComment.insertComment", vo);
	}
	
	@Override
	public int updateInsertComment(CommentVO vo) {
		return sqlSession.update("boardComment.updateInsertComment", vo);
	}

	@Override
	public int insertReplyComment(CommentVO vo) {
		return sqlSession.insert("boardComment.insertReplyComment", vo);
	}
	
	@Override
	public int updateBeforeReplyOrder(CommentVO vo) {
		return sqlSession.update("boardComment.updateBeforeReply", vo);
	}
	
	@Override
	public List<CommentVO> getListComment(BoardVO vo) {
		return sqlSession.selectList("boardComment.getListComment", vo);
	}
	
	public int updateDeleteComment(CommentVO vo) {
		return sqlSession.update("boardComment.deleteComment", vo);
	}

	@Override
	public int updateDeleteAnnoComment(CommentVO vo) {
		return sqlSession.update("boardComment.deleteAnnoComment", vo);
	}

	@Override
	public CommentVO checkAnnoCommentPass(CommentVO vo) {
		return sqlSession.selectOne("boardComment.checkAnnoCommentPass", vo);
	}

	@Override
	public int updateComment(CommentVO vo) {
		return sqlSession.update("boardComment.updateComment", vo);
	}

	@Override
	public boolean isDeleteGrpComment(CommentVO vo) {
		return sqlSession.selectOne("boardComment.isDeleteGrpComment", vo);
	}
}
