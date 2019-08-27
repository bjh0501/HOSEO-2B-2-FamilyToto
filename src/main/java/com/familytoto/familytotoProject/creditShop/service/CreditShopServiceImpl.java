package com.familytoto.familytotoProject.creditShop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.creditShop.dao.CreditShopDao;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

@Service
public class CreditShopServiceImpl implements CreditShopService {
	@Autowired
	CreditShopDao creditShopDao;
		
	@Override
	public ProductVO getShowProduct(ProductVO vo) {
		return creditShopDao.getShowProduct(vo);
	}
}
