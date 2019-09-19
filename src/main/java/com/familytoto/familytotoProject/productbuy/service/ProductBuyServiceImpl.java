package com.familytoto.familytotoProject.productbuy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.familytoto.familytotoProject.basket.domain.BasketVO;
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
	 * 돈있는지 확인 >			or 마일리지 확인
	 * 상품구입 >
	 * 상품소모>
	 * 돈 소모 >				or 마일리지 소모
	 * 마일리지 적립 >  			or 마일리지 소모
	 * 상품 그룹으로 묶기(insert) >
	 * 장바구니 목록 N으로 변경
	 * 
	 */
	
	@Transactional
	public int insertProductDirectBuy(ProductBuyVO vo, String gubun) {
		if(productBuyDao.isProductAmount(vo) == false) {	// 상품재고초과한경우
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -96;
		}
		
		if(gubun.equals("CREDIT")) {
			if(productBuyDao.isCustCredit(vo) == false) {		// 돈없는경우
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return -97;
			}
		} else {
			if(productBuyDao.isCustMileage(vo) == false) {		// 마일리지 없는경우
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return -95;
			}
		}
		
		MileageVO mVo = new MileageVO();
		mVo.setFamilyCustNo(vo.getFamilyCustNo());
		mVo.setRegCustNo(vo.getRegCustNo());
		mVo.setRegIp(vo.getRegIp());
		
		if(productBuyDao.insertProductBuy(vo) == 1) {
			CreditVO creditVo = new CreditVO();
			BasketVO bVo = new BasketVO();
			
			bVo.setChgCustNo(vo.getRegCustNo());
			bVo.setFamilyCustNo(vo.getFamilyCustNo());
			bVo.setBasketNo(vo.getBasketNo());
			
			if(productBuyDao.updateProductAmount(vo) == 0) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return -5;
			}
			
			if(gubun.equals("CREDIT")) {	// 크레딧으로 구입	
				creditVo.setCreditValue(vo.getProductBuyCredit()*-1);
				creditVo.setCreditState("PDB");
				creditVo.setRegCustNo(vo.getRegCustNo());
				creditVo.setFamilyCustNo(vo.getFamilyCustNo());
				creditVo.setRegIp(vo.getRegIp());
				
				if(chargeDao.doCharge(creditVo) < 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return -2;
				}
				
				// 10퍼센트적립
				int nMileageAccum = (int) (vo.getProductBuyCredit() / 10);
				
				mVo.setMileageValue(nMileageAccum);
				mVo.setMileageState("MIP");
	
				if(productBuyDao.insertMileage(mVo) < 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return -3;
				}
			} else {						// 마일리지로 구입
				mVo.setMileageValue(vo.getProductBuyCredit()*-1);
				mVo.setMileageState("PDS");
				
				productBuyDao.insertUseMileage(mVo);
			}
			
			// 장박구니 경우
			if(bVo.getBasketNo() != 0) {
				if(productBuyDao.updateUsedBasket(bVo) < 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return -4;
				}
			}
			
			return 1;
		} else {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
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

	@Override
	public int insertProductGrp(ProductBuyVO vo) {
		return productBuyDao.insertProductBuyGrp(vo);
	}

	@Override
	public List<ProductVO> listProductBuy(int familyCustNo) {
		return productBuyDao.listProductBuy(familyCustNo);
	}

	@Override
	public int updateDeleteBasket(BasketVO vo) {
		String[] sBasketNo = vo.getBasketNoStr().split(",");
		
		for(int i = 0; i < sBasketNo.length; i++) {
			vo.setBasketNo(Integer.parseInt(sBasketNo[i]));
			productBuyDao.updateDeleteBasket(vo);
		}
		
		return 1;
	}
}
