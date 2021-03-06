package com.familytoto.familytotoProject.basket.dao;

import java.util.List;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;

public interface BasketDAO {
	// 장바구니에 넣어져있는지 확인
	boolean isCheckBeforeBasket(BasketVO vo);

	// 장바구니 담기
	int insertBasket(BasketVO vo);
	
	// 상품 수량 확인
	boolean checkProductAmount(ProductBuyVO vo);
	
	// 장바구니 리스트 보기
	List<BasketVO> listBasket(int familyCustNo);
	
	// 장바구니 선택 구매, 선택안된건 대기
	int updateChooseBuyBasket(BasketVO vo);
	
	// 장바구니 상태 대기 > 원상태
	int updateOriginBasket(int familyCustNo);
	
	// 장바구니 수량 변경
	int updateBasketAmount(BasketVO vo);
}
