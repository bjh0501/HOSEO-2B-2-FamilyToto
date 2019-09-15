package com.familytoto.familytotoProject.productbuy.service;

import java.util.List;
import java.util.Map;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;

import lombok.Builder.Default;

public interface ProductBuyService {
	// 상품 즉시구매
	int insertProductDirectBuy(ProductBuyVO vo, String gubun);
	
	// 구입할 상품보기
	ProductVO getProductBuy(ProductBuyVO vo);
	
	// 구입할 상품들보기
	List<ProductVO> listProductBuy(int familyCustNo);
	
	Map<String, Object> getCustInfo(int nFamilyNo);
	
	// 구입상품 그룹하기
	int insertProductGrp(ProductBuyVO vo);
	
	// 장바구니 삭제
	int updateDeleteBasket(BasketVO vo);
}
