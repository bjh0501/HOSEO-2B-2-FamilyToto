package com.familytoto.familytotoProject.basket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.basket.dao.BasketDAO;
import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;

@Service
public class BasketServiceImpl implements BasketService{
	@Autowired
	BasketDAO basketDao;
	
	@Override
	public int insertBasket(BasketVO vo) {
		return basketDao.insertBasket(vo);
	}

	@Override
	public ProductVO checkProductAmount(BasketVO vo) {
		return basketDao.checkProductAmount(vo);
	}
}
