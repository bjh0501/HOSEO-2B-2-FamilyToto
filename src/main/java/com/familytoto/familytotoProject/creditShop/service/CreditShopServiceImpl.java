package com.familytoto.familytotoProject.creditShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.creditShop.dao.CreditShopDao;
import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

@Service
public class CreditShopServiceImpl implements CreditShopService {
	@Autowired
	CreditShopDao creditShopDao;
		
	@Override
	public ProductVO getShowProduct(ProductVO vo) {
		return creditShopDao.getShowProduct(vo);
	}

	@Override
	public List<ProductVO> listCreditShop(int nParameter) {
		return creditShopDao.listCreditShop(nParameter);
	}

	@Override
	public int insertProductComment(ProductCommentVO vo) {
		if(creditShopDao.isBoughtProduct(vo) == false) {
			return -99;
		}
		
		if(creditShopDao.isDupleProductComment(vo) == true) {
			return -98;
		}
		
		return creditShopDao.insertProductComment(vo);
	}

	@Override
	public List<ProductCommentVO> listProductComment(ProductVO vo) {
		return creditShopDao.listProductComment(vo);
	}

	@Override
	public int productCommentCnt(ProductVO vo) {
		return creditShopDao.productCommentCnt(vo);
	}
}
