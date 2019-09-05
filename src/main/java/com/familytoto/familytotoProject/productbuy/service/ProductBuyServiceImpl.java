package com.familytoto.familytotoProject.productbuy.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.familytoto.familytotoProject.charge.dao.ChargeDao;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.productbuy.dao.ProductBuyDao;
import com.familytoto.familytotoProject.productbuy.domain.ProductBuyVO;

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
	 * 마일리지 적립 >  
	 * 상품 그룹으로 묶기 >
	 * 
	 */
	
	@Transactional
	public int insertProductDirectBuy(ProductBuyVO vo) {
		if(productBuyDao.isProductAmount(vo) == false) {	// 상품재고초과한경우
			return -96;
		}
		
		if(productBuyDao.isCustCredit(vo) == false) {		// 돈없는경우
			return -97;
		}
		
		if(productBuyDao.insertProductBuy(vo) == 1) {
			CreditVO creditVo = new CreditVO();
			MileageVO mVo = new MileageVO();
			
			creditVo.setCreditValue(vo.getProductBuyCredit()*-1);
			creditVo.setCreditState("PDB");
			creditVo.setRegCustNo(vo.getRegCustNo());
			creditVo.setFamilyCustNo(vo.getFamilyCustNo());
			creditVo.setRegIp(vo.getRegIp());
			
			// 10퍼센트적립
			int nMileageAccum = (int) (vo.getProductBuyCredit() / 10);
			
			mVo.setFamilyCustNo(vo.getFamilyCustNo());
			mVo.setMileageValue(nMileageAccum);
			mVo.setMileageState("MIP");
			mVo.setRegIp(vo.getRegIp());
			
			chargeDao.doCharge(creditVo);
			productBuyDao.updateProductAmount(vo);
			productBuyDao.insertMileage(mVo);
			
			return productBuyDao.insertProductBuyGrp(vo);
		} else {
			return -1;
		}
		
	}

	@Override
	public ProductVO getProductBuy(ProductBuyVO vo) {
		return productBuyDao.getProductBuy(vo);
	}

	@Override
	public Map<String, Object> getCustInfo(int nFamilyCustNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("credit", productBuyDao.getCustCredit(nFamilyCustNo));
		map.put("mileage", productBuyDao.getCustMileage(nFamilyCustNo));
		
		return map;
	}
}
