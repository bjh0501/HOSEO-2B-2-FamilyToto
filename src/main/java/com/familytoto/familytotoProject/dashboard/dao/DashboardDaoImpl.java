package com.familytoto.familytotoProject.dashboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.comment.domain.CommentVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.exp.domain.ExpVO;
import com.familytoto.familytotoProject.scheduler.domain.TotoSportsVO;
import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;
import com.familytoto.familytotoProject.toto.domain.SportsVO;

@Repository
public class DashboardDaoImpl implements DashboardDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<CreditVO> listRecentCredit(int familyCustNo) {
		return sqlSession.selectList("dashboard.listRecentProfitCredit", familyCustNo);
	}

	@Override
	public int getTotalCredit(int familyCustNo) {
		return sqlSession.selectOne("dashboard.getTotalCredit", familyCustNo);
	}

	@Override
	public int getTotalMileage(int familyCustNo) {
		return sqlSession.selectOne("dashboard.getTotalMileage", familyCustNo);
	}

	@Override
	public int getTotalExp(int familyCustNo) {
		return sqlSession.selectOne("dashboard.getTotalExp", familyCustNo);
	}

	@Override
	public List<MileageVO> listRecentMileage(int familyCustNo) {
		return sqlSession.selectList("dashboard.listRecentProfitMileage", familyCustNo);
	}

	@Override
	public int getCustLevel(int familyCustNo) {
		return sqlSession.selectOne("dashboard.getCustLevel", familyCustNo);
	}

	@Override
	public List<CreditVO> listCreditTable(int familyCustNo) {
		return sqlSession.selectList("dashboard.listCreditTable", familyCustNo);
	}

	@Override
	public List<MileageVO> listMileageTable(int familyCustNo) {
		return sqlSession.selectList("dashboard.listMileageTable", familyCustNo);
	}

	@Override
	public List<ExpVO> listRecentExp(int familyCustNo) {
		return sqlSession.selectList("dashboard.listRecentProfitExp", familyCustNo);
	}

	@Override
	public List<MileageVO> listExpTable(int familyCustNo) {
		return sqlSession.selectList("dashboard.listExpTable", familyCustNo);
	}

	@Override
	public List<SportsVO> listBettingGroup(int familyCustNo) {
		return sqlSession.selectList("dashboard.listBettingGroup", familyCustNo);
	}

	@Override
	public int getBoardCnt(int familyCustNo) {
		return sqlSession.selectOne("dashboard.getBoardCnt", familyCustNo);
	}

	@Override
	public int getCommentCnt(int familyCustNo) {
		return sqlSession.selectOne("dashboard.getCommentCnt", familyCustNo);
	}

	@Override
	public int getProductCommentCnt(int familyCustNo) {
		return sqlSession.selectOne("dashboard.getProductCommentCnt", familyCustNo);
	}

	@Override
	public List<BoardVO> listRegisteredBoard(int familyCustNo) {
		return sqlSession.selectList("dashboard.listRegisteredBoard", familyCustNo);
	}

	@Override
	public List<CommentVO> listRegisteredComment(int familyCustNo) {
		return sqlSession.selectList("dashboard.listRegisteredComment", familyCustNo);
	}

	@Override
	public List<ProductCommentVO> listRegisteredProductComment(int familyCustNo) {
		return sqlSession.selectList("dashboard.listRegisteredProductComment", familyCustNo);
	}

	@Override
	public List<TotoSportsVO> listShowBettingTotoMatch(SportsBettingVO vo) {
		return sqlSession.selectList("dashboard.listShowBettingTotoMatch", vo);
	}

	@Override
	public List<ProductVO> listRegisteredProduct(int familyCustNo) {
		return sqlSession.selectList("dashboard.listRegisteredProduct", familyCustNo);
	}	
}
