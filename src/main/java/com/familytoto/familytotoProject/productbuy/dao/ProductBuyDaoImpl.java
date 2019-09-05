package com.familytoto.familytotoProject.productbuy.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;

@Repository
public class ProductBuyDaoImpl implements ProductBuyDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertProductBuy(ProductBuyVO vo) {
		return sqlSession.insert("productBuy.insertProductBuy", vo);
	}

	@Override
	public int insertProductBuyGrp(ProductBuyVO vo) {
		return sqlSession.insert("productBuy.insertproductBuyGrp", vo);
	}

	@Override
	public boolean isCustCredit(ProductBuyVO vo) {
		return sqlSession.selectOne("productBuy.getCustCredit", vo);
	}

	@Override
	public boolean isProductAmount(ProductBuyVO vo) {
		return sqlSession.selectOne("productBuy.isProductAmount", vo);
	}

	@Override
	public int updateProductAmount(ProductBuyVO vo) {
		return sqlSession.update("productBuy.updateProductAmount", vo);
	}

	@Override
	public int insertMileage(MileageVO vo) {
		return sqlSession.insert("mileage.insertMileage",vo);
	}

	@Override
	public ProductVO getProductBuy(ProductBuyVO vo) {
		return sqlSession.selectOne("productBuy.getProductBuy", vo);
	}

	@Override
	public int getCustCredit(int familyCustNo) {
		return sqlSession.selectOne("productBuy.getCustCreditInfo", familyCustNo);
	}

	@Override
	public int getCustMileage(int familyCustNo) {
		return sqlSession.selectOne("productBuy.getCustMileageInfo", familyCustNo);
	}	
}
