package com.familytoto.familytotoProject.creditShop.dao;

import java.util.List;

import com.familytoto.familytotoProject.creditShop.domain.CategoryVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

public interface CreditShopDao {
	// 상품보기
	ProductVO getShowProduct(ProductVO vo);
	
	// 상품이미지보기
	List<ProductVO> listProductImgs(ProductVO vo);
	
	// 상품리스트
	List<ProductVO> listCreditShop(int nParameter);
	
	// 상품평달기
	int insertProductComment(ProductCommentVO vo);
	
	// 구입한 상품 체크
	boolean isBoughtProduct(ProductCommentVO vo);
	
	// 상품평 중복체크
	boolean isDupleProductComment(ProductCommentVO vo);
	
	// 상품평 리스트
	List<ProductCommentVO> listProductComment(ProductVO vo);
	
	// 상품평 갯수
	int productCommentCnt(ProductVO vo);
	
	// 카테고리 리스트
	List<CategoryVO> listProductCategory();
	
	// 상품평 달았을때 랜덤 마일리지 얻기
	int getRandomMileage(MileageVO vo);
}
