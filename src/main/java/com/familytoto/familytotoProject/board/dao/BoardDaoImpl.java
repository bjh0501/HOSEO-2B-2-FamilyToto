package com.familytoto.familytotoProject.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.PagingVO;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertCustBoard(BoardVO vo) {
		return sqlSession.insert("board.insertCustBoard", vo);
	}

	@Override
	public int updateDeleteBoard(BoardVO vo) {
		return sqlSession.update("board.updateDeleteBorder", vo);
	}	
	
	@Override
	public int updateBoard(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 페이징
	public int getBoardListCnt() throws Exception {
		return sqlSession.selectOne("board.getBoardListCnt");
	}
	
	public List<BoardVO> getBoardList(PagingVO pagination) throws Exception {
		return sqlSession.selectList("board.getBoardList", pagination);
	}

	@Override
	public BoardVO getShowBoard(BoardVO vo) {
		return sqlSession.selectOne("board.getShowBoard", vo);
	}

	@Override
	public int insertAnnoBoard(BoardVO vo) {
		return sqlSession.insert("board.insertAnnoBoard", vo);
	}

	@Override
	public BoardVO getUpdateBoard(BoardVO vo) {
		return sqlSession.selectOne("board.getUpdateBoard", vo);
	}	
}
