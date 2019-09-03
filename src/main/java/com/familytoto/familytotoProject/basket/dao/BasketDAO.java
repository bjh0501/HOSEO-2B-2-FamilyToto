package com.familytoto.familytotoProject.basket.dao;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

public interface BasketDAO {
	// 장바구니 담기
	int insertBasket(BasketVO vo);
	
	// 상품 수량 확인
	ProductVO checkProductAmount(BasketVO vo);
}
