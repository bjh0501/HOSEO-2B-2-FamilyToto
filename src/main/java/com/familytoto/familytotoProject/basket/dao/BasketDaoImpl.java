package com.familytoto.familytotoProject.basket.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;

@Repository
public class BasketDaoImpl implements BasketDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertBasket(BasketVO vo) {
		return sqlSession.insert("basket.insertBasket", vo);
	}

	@Override
	public boolean checkProductAmount(ProductBuyVO vo) {
		return sqlSession.selectOne("productBuy.isProductAmount", vo);
	}

	@Override
	public List<BasketVO> listBasket(int familyCustNo) {
		return sqlSession.selectList("basket.listBasket", familyCustNo);
	}

	@Override
	public boolean isCheckBeforeBasket(BasketVO vo) {
		return sqlSession.selectOne("basket.isCheckBeforeBasket", vo);
	}
	
	
}
