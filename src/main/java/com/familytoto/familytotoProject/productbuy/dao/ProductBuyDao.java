package com.familytoto.familytotoProject.productbuy.dao;

import java.util.List;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

public interface ProductBuyDao {
	// 구입할 돈있는지 체크
	boolean isCustCredit(ProductBuyVO vo);
	
	// 구입할 재고있는지 체크
	boolean isProductAmount(ProductBuyVO vo);
	
	// 구입할 상품들보기
	List<ProductVO> listProductBuy(int familyCustNo);
	
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
	
	// 장바구니 삭제
	int updateDeleteBasket(BasketVO vo);
	
	// 장바구니로 구입한거 장바구니 삭제
	int updateUsedBasket(BasketVO vo);
	
	// 마일리지 사용
	int insertUseMileage(MileageVO vo);
	
	// 마일리지 사용
	boolean isCustMileage(ProductBuyVO vo);
	
	// 구입한상품 리스트
	List<ProductBuyVO> listBoughtProduct(ProductBuyVO vo);
	
	// 기본 배송지 가져오기
	RegisterCustVO getFamilyCustDefaultAddr(int familyCustNo);
	
	// 상품추가
	int insertProduct(ProductVO vo);
	
	// 상품이미지추가
	int insertProductImgUrl(ProductVO vo);
}
