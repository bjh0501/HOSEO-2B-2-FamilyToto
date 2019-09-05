package com.familytoto.familytotoProject.basket.service;

import java.util.List;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;

public interface BasketService {	
	// 장바구니 담기
	int insertBasket(BasketVO vo);
	
	// 상품 수량 확인
	boolean checkProductAmount(ProductBuyVO vo);
	
	// 장바구니 리스트 보기
	List<BasketVO> listBasket(int familyCustNo);
}
