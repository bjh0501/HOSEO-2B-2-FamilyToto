package com.familytoto.familytotoProject.productbuy.service;

import java.util.List;
import java.util.Map;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

import lombok.Builder.Default;

public interface ProductBuyService {
	// 상품 즉시구매
	int insertProductDirectBuy(ProductBuyVO vo, String gubun, int sellerFmailyCustNo);
	
	// 구입할 상품보기
	ProductVO getProductBuy(ProductBuyVO vo);
	
	// 구입할 상품들보기
	List<ProductVO> listProductBuy(int familyCustNo);
	
	Map<String, Object> getCustInfo(int nFamilyNo);
	
	// 구입상품 그룹하기
	int insertProductGrp(ProductBuyVO vo);
	
	// 장바구니 삭제
	int updateDeleteBasket(BasketVO vo);
	
	// 구입한상품 리스트
	List<ProductBuyVO> listBoughtProduct(ProductBuyVO vo);
	
	// 기본 배송지 가져오기
	RegisterCustVO getFamilyCustDefaultAddr(int familyCustNo);
	
	// 상품추가
	int insertProduct(ProductVO vo, String[] productImgUrls, String[] productImgServer) ;
	
	// 상품수정
	int updateProduct(ProductVO vo, String[] productImgUrls, String[] productImgServer) ;
	
	// 상품 사진 삭제했을 때
	int updateDeleteProductImgs(ProductVO vo);
	
	// 상품 이미지 리스트
	List<ProductVO> listProductImg(int productNo);
	
	// 대표이미지 선택
	int updateChooseKingImg(ProductVO vo);
	
	// 상품삭제
	int updateDeleteProduct(ProductVO vo);
}
