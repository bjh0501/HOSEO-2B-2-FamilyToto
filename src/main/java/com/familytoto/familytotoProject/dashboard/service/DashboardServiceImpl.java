package com.familytoto.familytotoProject.dashboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.comment.domain.CommentVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.dashboard.dao.DashboardDao;
import com.familytoto.familytotoProject.exp.domain.ExpVO;
import com.familytoto.familytotoProject.scheduler.domain.TotoSportsVO;
import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;
import com.familytoto.familytotoProject.toto.domain.SportsVO;

@Service
public class DashboardServiceImpl implements DashboardService {
	@Autowired
	DashboardDao dashboardDao;
	
	@Override
	public List<CreditVO> listRecentCredit(int familyCustNo) {
		return dashboardDao.listRecentCredit(familyCustNo);
	}

	@Override
	public Map<String, ?> getDefaultInfo(int familyCustNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		int credit = dashboardDao.getTotalCredit(familyCustNo);
		int mileage = dashboardDao.getTotalMileage(familyCustNo);
		int exp = dashboardDao.getTotalExp(familyCustNo);
		int level = dashboardDao.getCustLevel(familyCustNo);
		int boardCnt = dashboardDao.getBoardCnt(familyCustNo);
		int commentCnt = dashboardDao.getCommentCnt(familyCustNo);
		int productCommentCnt = dashboardDao.getProductCommentCnt(familyCustNo);
		
		map.put("credit", credit);
		map.put("mileage", mileage);
		map.put("exp", exp);
		map.put("level", level);
		
		map.put("board", boardCnt);
		map.put("comment", commentCnt);
		map.put("productComment", productCommentCnt);
		
		return map;
	}

	@Override
	public List<MileageVO> listRecentMileage(int familyCustNo) {
		return dashboardDao.listRecentMileage(familyCustNo);
	}

	@Override
	public List<CreditVO> listCreditTable(int familyCustNo) {
		return dashboardDao.listCreditTable(familyCustNo);
	}

	@Override
	public List<MileageVO> listMileageTable(int familyCustNo) {
		return dashboardDao.listMileageTable(familyCustNo);
	}

	@Override
	public List<ExpVO> listRecentExp(int familyCustNo) {
		return dashboardDao.listRecentExp(familyCustNo);
	}

	@Override
	public List<MileageVO> listExpTable(int familyCustNo) {
		return dashboardDao.listExpTable(familyCustNo);
	}

	@Override
	public List<SportsVO> listBettingGroup(int familyCustNo) {
		return dashboardDao.listBettingGroup(familyCustNo);
	}

	@Override
	public List<BoardVO> listRegisteredBoard(int familyCustNo) {
		return dashboardDao.listRegisteredBoard(familyCustNo);
	}

	@Override
	public List<CommentVO> listRegisteredComment(int familyCustNo) {
		return dashboardDao.listRegisteredComment(familyCustNo);
	}

	@Override
	public List<ProductCommentVO> listRegisteredProductComment(int familyCustNo) {
		return dashboardDao.listRegisteredProductComment(familyCustNo);
	}

	@Override
	public List<TotoSportsVO> listShowBettingTotoMatch(SportsBettingVO vo) {
		return dashboardDao.listShowBettingTotoMatch(vo);
	}

	@Override
	public List<ProductVO> listRegisteredProduct(int familyCustNo) {
		return dashboardDao.listRegisteredProduct(familyCustNo);
	}

	@Override
	public List<ProductVO> listSellCnt(ProductVO vo) {
		return dashboardDao.listSellCnt(vo);
	}
}
