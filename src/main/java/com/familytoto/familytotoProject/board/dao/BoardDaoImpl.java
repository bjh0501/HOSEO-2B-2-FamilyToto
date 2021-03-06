package com.familytoto.familytotoProject.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.FileVO;
import com.familytoto.familytotoProject.board.domain.SearchVO;

@Repository
public class BoardDaoImpl implements BoardDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertCustBoard(BoardVO vo) {
		return sqlSession.insert("board.insertCustBoard", vo);
	}
	
	@Override
	public int insertAnnoBoard(BoardVO vo) {
		return sqlSession.insert("board.insertAnnoBoard", vo);
	}
	
	@Override
	public int updateBoardGrpNo(BoardVO vo) {
		return sqlSession.insert("board.updateBoardGrpNo", vo);
	}
	
	@Override
	public int updateBeforeReply(BoardVO vo) {
		return sqlSession.update("board.updateBeforeReply", vo);
	}

	@Override
	public int updateDeleteBoard(BoardVO vo) {
		return sqlSession.update("board.updateDeleteBoard", vo);
	}
	
	public int updateDeleteAnnoBoard(BoardVO vo) {
		return sqlSession.update("board.updateDeleteAnnoBoard", vo);
	}
	
	public String checkAnnoBoardPass(BoardVO vo) {
		return sqlSession.selectOne("board.checkAnnoBoardPass", vo);
	}
	
	public int updateBoard(BoardVO vo) {
		return sqlSession.update("board.updateBoard", vo);
	}
	
	public int updateSocialBoard(BoardVO vo) {
		return sqlSession.update("board.updateSocialBoard", vo);
	}
	
	// 페이징
	public int getBoardListCnt(SearchVO search) throws Exception {
		return sqlSession.selectOne("board.getBoardListCnt", search);
	}
	
	public List<BoardVO> getBoardList(SearchVO search) throws Exception {
		return sqlSession.selectList("board.getBoardList", search);
	}

	@Override
	public BoardVO getShowBoard(BoardVO vo) {
		return sqlSession.selectOne("board.getShowBoard", vo);
	}

	@Override
	public BoardVO getUpdateBoard(BoardVO vo) {
		return sqlSession.selectOne("board.getUpdateBoard", vo);
	}

	@Override
	public int getCommentCnt(BoardVO vo) {
		return sqlSession.selectOne("board.commentCnt", vo);
	}

	@Override
	public int insertFile(FileVO vo) {
		return sqlSession.insert("board.insertFile", vo);
	}

	@Override
	public int updateFile(FileVO vo) {
		return sqlSession.update("board.updateNotUseFile", vo);
	}

	@Override
	public int updateBeforeBoardReply(BoardVO vo) {
		return sqlSession.update("board.updateBeforeBoardReply", vo);
	}

	@Override
	public int updateVisitLog(int boardNo) {
		return sqlSession.update("board.updateBoardLog", boardNo);
	}

	@Override
	public FileVO getUploadedFile(int boardNo) {
		return sqlSession.selectOne("board.getUploadedFile", boardNo);
	}

	@Override
	public List<BoardVO> listNotice() {
		return sqlSession.selectList("board.listNotice");
	}

	@Override
	public List<BoardVO> listReplyBoard(int boardGrpNo) {
		return sqlSession.selectList("board.listReplyBoard", boardGrpNo);
	}

	@Override
	public boolean isDeleteGrpBoard(BoardVO vo) {
		return sqlSession.selectOne("board.isDeleteGrpBoard", vo);
	}

	@Override
	public int updateIfAllWThenN(BoardVO vo) {
		return sqlSession.update("board.updateIfAllWThenN", vo);
	}

	@Override
	public boolean isAssertWAndGrpNo(int boardGrpNo) {
		return sqlSession.selectOne("board.isAssertWAndGrpNo", boardGrpNo);
	}
}
