package com.familytoto.familytotoProject.basket.dao;

import java.util.List;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

public interface BasketDAO {
	// 장바구니에 넣어져있는지 확인
	boolean isChecvkBeforeBasket(BasketVO vo);

	// 장바구니 담기
	int insertBasket(BasketVO vo);
	
	// 상품 수량 확인
	ProductVO checkProductAmount(BasketVO vo);
	
	// 장바구니 리스트 보기
	List<BasketVO> listBasket(int familyCustNo);
}
