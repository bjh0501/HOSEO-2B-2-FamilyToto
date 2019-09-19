package com.familytoto.familytotoProject.creditShop.dao;

import java.util.List;

import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

public interface CreditShopDao {
	// 상품보기
	ProductVO getShowProduct(ProductVO vo);
	
	// 상품리스트
	List<ProductVO> listCreditShop();
	
	// 상품평달기
	int insertProductComment(ProductCommentVO vo);
	
	// 구입한 상품 체크
	boolean isBoughtProduct(ProductCommentVO vo);
	
	// 상품평 중복체크
	boolean isDupleProductComment(ProductCommentVO vo);
}
