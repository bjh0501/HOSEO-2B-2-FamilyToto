package com.familytoto.familytotoProject.creditShop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.familytoto.familytotoProject.creditShop.domain.CategoryVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public interface CreditShopService {
	// 상품이미지보기
	List<ProductVO> listProductImgs(ProductVO vo);
		
	// 상품보기
	ProductVO getShowProduct(ProductVO vo);
	
	// 상품리스트
	List<ProductVO> listCreditShop(int nParameter);
	
	// 상품평달기
	int insertProductComment(ProductCommentVO vo, CustVO cVo, HttpServletRequest request);
	
	// 상품평 리스트
	List<ProductCommentVO> listProductComment(ProductVO vo);
	
	// 상품평 갯수
	int productCommentCnt(ProductVO vo);
	
	// 카테고리 리스트
	List<CategoryVO> listProductCategory();
}
