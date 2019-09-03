package com.familytoto.familytotoProject.basket.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

@Repository
public class BasketDaoImpl implements BasketDAO {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertBasket(BasketVO vo) {
		return sqlSession.insert("basket.insertBasket", vo);
	}

	@Override
	public ProductVO checkProductAmount(BasketVO vo) {
		return sqlSession.selectOne("creditShop.productAmountCheck", vo);
	}
}
