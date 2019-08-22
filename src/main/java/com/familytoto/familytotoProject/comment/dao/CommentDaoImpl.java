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
	public List<CommentVO> getListComment(BoardVO vo) {
		return sqlSession.selectList("boardComment.getListComment", vo);
	}

	

}
