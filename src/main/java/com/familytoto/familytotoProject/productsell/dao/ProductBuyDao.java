package com.familytoto.familytotoProject.productsell.dao;

import com.familytoto.familytotoProject.productsell.domain.ProductBuyVO;

public interface ProductBuyDao {
	// 구입할 돈있는지 체크
	boolean isCustCredit(ProductBuyVO vo);
	
	// 구입할 재고있는지 체크
	boolean isProductAmount(ProductBuyVO vo);
	
	// 상품개수 줄이기
	int updateProductAmount(ProductBuyVO vo);
	
	// 상품 즉시구매
	int insertProductBuy(ProductBuyVO vo);
	
	// 상품 묶기
	int insertProductBuyGrp(ProductBuyVO vo);
}
