package com.familytoto.familytotoProject.creditShop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.creditShop.domain.CategoryVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

@Repository
public class CreditShopDaoImpl implements CreditShopDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public ProductVO getShowProduct(ProductVO vo) {
		return sqlSession.selectOne("creditShop.showProduct", vo);
	}

	@Override
	public List<ProductVO> listCreditShop(int nParameter) {
		return sqlSession.selectList("creditShop.listCreditShop", nParameter);
	}

	@Override
	public int insertProductComment(ProductCommentVO vo) {
		return sqlSession.insert("creditShop.insertProductComment", vo);
	}

	@Override
	public boolean isBoughtProduct(ProductCommentVO vo) {
		return sqlSession.selectOne("creditShop.isBoughtProduct", vo);
	}

	@Override
	public boolean isDupleProductComment(ProductCommentVO vo) {
		return sqlSession.selectOne("creditShop.isDupleProductComment", vo);
	}

	@Override
	public List<ProductCommentVO> listProductComment(ProductVO vo) {
		return sqlSession.selectList("creditShop.listProductComment", vo);
	}

	@Override
	public int productCommentCnt(ProductVO vo) {
		return sqlSession.selectOne("creditShop.productCommentCnt", vo);
	}

	@Override
	public List<ProductVO> listProductImgs(ProductVO vo) {
		return sqlSession.selectList("creditShop.listProductImgs", vo);
	}

	@Override
	public List<CategoryVO> listProductCategory() {
		return sqlSession.selectList("creditShop.listProductCategory");
	}

	@Override
	public int getRandomMileage(MileageVO vo) {
		return sqlSession.insert("mileage.insertMileage", vo);
	}
}
