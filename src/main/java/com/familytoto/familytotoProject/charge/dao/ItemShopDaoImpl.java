package com.familytoto.familytotoProject.charge.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.exp.domain.ExpVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Repository
public class ItemShopDaoImpl implements ItemShopDao{
	@Autowired
	SqlSession sqlSession;

	@Override
	public boolean checkBuyCharge(CreditVO vo) {
		return sqlSession.selectOne("itemShop.checkBuyCharge", vo);
	}

	@Override
	public boolean isCheckNickname(String nickname) {
		return sqlSession.selectOne("itemShop.checkNickname", nickname);
	}
	
	@Override
	public int updateNickname(CustVO cVo) {
		return sqlSession.update("itemShop.updateNickname", cVo);
	}

	@Override
	public int insertCredit(CreditVO vo) {
		return sqlSession.insert("itemShop.insertCreditHistory", vo);
	}

	@Override
	public int insertVipTicket(ExpVO vo) {
		return sqlSession.insert("itemShop.insertVipTicket", vo);
	}

	@Override
	public boolean isVipTicket(int familyCustNo) {
		return sqlSession.selectOne("itemShop.isVipTicket", familyCustNo);
	}

	@Override
	public int updateVipExp(int familyCustNo) {
		return sqlSession.update("itemShop.updateVipExp", familyCustNo);
	}
}

