package com.familytoto.familytotoProject.productbuy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

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
		return sqlSession.insert("productBuy.insertProductBuyGrp", vo);
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

	@Override
	public List<ProductVO> listProductBuy(int familyCustNo) {
		return sqlSession.selectList("productBuy.listProductBuy", familyCustNo);
	}

	@Override
	public int updateDeleteBasket(BasketVO vo) {
		return sqlSession.update("productBuy.updateDeleteBasket", vo);
	}

	@Override
	public int updateUsedBasket(BasketVO vo) {
		return sqlSession.update("productBuy.updateUsedBasket", vo);
	}

	@Override
	public int insertUseMileage(MileageVO vo) {
		return sqlSession.update("productBuy.insertUseMileage", vo);
	}

	@Override
	public boolean isCustMileage(ProductBuyVO vo) {
		return sqlSession.selectOne("productBuy.getCustMileage", vo);
	}

	@Override
	public List<ProductBuyVO> listBoughtProduct(ProductBuyVO vo) {
		return sqlSession.selectList("productBuy.listBoughtProduct", vo);
	}

	@Override
	public RegisterCustVO getFamilyCustDefaultAddr(int familyCustNo) {
		return sqlSession.selectOne("productBuy.getFamilyCustDefaultAddr", familyCustNo);
	}	
}
