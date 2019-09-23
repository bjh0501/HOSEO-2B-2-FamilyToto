package com.familytoto.familytotoProject.basket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.familytoto.familytotoProject.basket.dao.BasketDAO;
import com.familytoto.familytotoProject.basket.domain.BasketVO;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;

@Service
public class BasketServiceImpl implements BasketService{
	@Autowired
	BasketDAO basketDao;
	
	@Override
	public int insertBasket(BasketVO vo) {
		if(basketDao.isCheckBeforeBasket(vo) == false) {
			return basketDao.insertBasket(vo);			
		} else {
			return -95;
		}
	}

	@Override
	public boolean checkProductAmount(ProductBuyVO vo) {
		return basketDao.checkProductAmount(vo);
	}

	@Override
	public List<BasketVO> listBasket(int familyCustNo) {
		return basketDao.listBasket(familyCustNo);
	}

	@Override
	@Transactional
	public int updateChooseBuyBasket(BasketVO vo) {
		String[] sBasketNo = vo.getBasketNoStr().split(",");
		
		for(int i = 0; i < sBasketNo.length; i++) {
			if(!sBasketNo[i].equals("")) {
				vo.setBasketNo(Integer.parseInt(sBasketNo[i]));
				basketDao.updateChooseBuyBasket(vo);
			}
		}
		
		return 1;
	}

	@Override
	public int updateOriginBasket(int familyCustNo) {
		return basketDao.updateOriginBasket(familyCustNo);
	}

	@Override
	public int updateBasketAmount(BasketVO vo) {
		return basketDao.updateBasketAmount(vo);
	}
}
