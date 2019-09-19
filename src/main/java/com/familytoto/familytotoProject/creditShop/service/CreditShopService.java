package com.familytoto.familytotoProject.creditShop.service;

import java.util.List;

import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

public interface CreditShopService {
	// 상품보기
	ProductVO getShowProduct(ProductVO vo);
	
	// 상품리스트
	List<ProductVO> listCreditShop(int nParameter);
	
	// 상품평달기
	int insertProductComment(ProductCommentVO vo);
	
	// 상품평 리스트
	List<ProductCommentVO> listProductComment(ProductVO vo);
	
	// 상품평 갯수
	int productCommentCnt(ProductVO vo);
}
