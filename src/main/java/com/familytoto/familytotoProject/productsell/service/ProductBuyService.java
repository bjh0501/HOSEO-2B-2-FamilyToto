package com.familytoto.familytotoProject.productsell.service;

import com.familytoto.familytotoProject.productsell.domain.ProductBuyVO;

public interface ProductBuyService {
	// 상품 즉시구매
	int insertProductDirectBuy(ProductBuyVO vo);
}
