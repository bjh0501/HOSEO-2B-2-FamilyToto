package com.familytoto.familytotoProject.creditShop.dao;

import java.util.List;

import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

public interface CreditShopDao {
	// 상품보기
	ProductVO getShowProduct(ProductVO vo);
	
	// 상품리스트
	List<ProductVO> listCreditShop();
}
