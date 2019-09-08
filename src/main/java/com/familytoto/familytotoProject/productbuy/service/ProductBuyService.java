package com.familytoto.familytotoProject.productbuy.service;

import java.util.Map;

import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;

public interface ProductBuyService {
	// 상품 즉시구매
	int insertProductDirectBuy(ProductBuyVO vo);
	
	// 구입할 상품보기
	ProductVO getProductBuy(ProductBuyVO vo);
	
	Map<String, Object> getCustInfo(int nFamilyNo);
	
	// 구입상품 그룹하기
	int insertProductGrp(ProductBuyVO vo);
}
