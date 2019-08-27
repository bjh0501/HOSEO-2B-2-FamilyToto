package com.familytoto.familytotoProject.creditShop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

@Repository
public class CreditShopDaoImpl implements CreditShopDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public ProductVO getShowProduct(ProductVO vo) {
		return sqlSession.selectOne("creditShop.showProduct", vo);
	}
	
}
