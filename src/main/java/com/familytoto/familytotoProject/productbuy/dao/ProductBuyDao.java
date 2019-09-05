package com.familytoto.familytotoProject.productbuy.dao;

import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;

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
	
	// 마일리지 추가
	int insertMileage(MileageVO vo);
	
	// 프로덕트 사이트보기
	ProductVO getProductBuy(ProductBuyVO vo);
	
	// 사용자 총 크레딧가져오기
	int getCustCredit(int familyCustNo);
	
	// 사용자 총 마일리지가져오기
	int getCustMileage(int familyCustNo);
}
