package com.familytoto.familytotoProject.productsell.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.familytoto.familytotoProject.charge.dao.ChargeDao;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.productsell.dao.ProductBuyDao;
import com.familytoto.familytotoProject.productsell.domain.ProductBuyVO;

@Service
public class ProductBuyServiceImpl implements ProductBuyService {
	@Autowired
	ProductBuyDao productBuyDao;
	
	@Autowired
	ChargeDao chargeDao;
	
	/**
	 * 
	 * 상품 재고있는지 확인 >
	 * 돈있는지 확인 >
	 * 상품구입 >
	 * 돈 소모 >
	 * 상품소모>
	 * 마일리지 적립 > ** 수정해야함 마일리지 적립매퍼는 Custom매퍼 >마일리지매퍼로 이동하는게 맞는듯 
	 * 상품 그룹으로 묶기 >
	 * 
	 */
	
	@Transactional
	public int insertProductDirectBuy(ProductBuyVO vo) {
		if(productBuyDao.isProductAmount(vo) == true) {
			if(productBuyDao.isCustCredit(vo) == true) {
				if(productBuyDao.insertProductBuy(vo) == 1) {
					CreditVO creditVo = new CreditVO();
					creditVo.setCreditValue(vo.getProductBuyCredit()*-1);
					creditVo.setCreditState("PDB");
					creditVo.setRegCustNo(vo.getRegCustNo());
					creditVo.setFamilyCustNo(vo.getFamilyCustNo());
					creditVo.setRegIp(vo.getRegIp());
					
					chargeDao.doCharge(creditVo);
					productBuyDao.updateProductAmount(vo);
					
					return productBuyDao.insertProductBuyGrp(vo);
				} else {   // 돈없는경우
					return -97;
				}
			} else {
				return -1;
			}
		} else { // 상품재고초과한경우
			return -96;
		}
		
	}
}
