package com.familytoto.familytotoProject.basket.service;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

public interface BasketService {
	// 장바구니 담기
	int insertBasket(BasketVO vo);
	
	// 상품 수량 확인
	ProductVO checkProductAmount(BasketVO vo);
}
