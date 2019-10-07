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
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

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
	public int insertProductDirectBuy(ProductBuyVO vo, String gubun,
			int sellerFmailyCustNo) {
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
		
		// 크레딧 테이블에 insert할 크레딧
		int nRealUsedCredit = vo.getProductBuyCredit();
		
		// 상품 구입한 테이블에 insert할 크레딧 
		// 상품가격 * 상품개수한걸 그대로 나누는거라 이렇게해야함 
		vo.setProductBuyCredit(vo.getProductBuyCredit() / vo.getProductBuyAmount());
		
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
				creditVo.setCreditValue(nRealUsedCredit*-1);
				creditVo.setCreditState("PDB");
				creditVo.setRegCustNo(vo.getRegCustNo());
				creditVo.setFamilyCustNo(vo.getFamilyCustNo());
				creditVo.setRegIp(vo.getRegIp());
				
				// 크레딧 소모
				if(chargeDao.doCharge(creditVo) < 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return -2;
				}
				
				creditVo.setCreditValue((int) (nRealUsedCredit*0.95));
				creditVo.setCreditState("IPP");
				creditVo.setRegCustNo(vo.getRegCustNo());
				
				// 상품 판매자의 패밀리번호
				creditVo.setFamilyCustNo(sellerFmailyCustNo);
				creditVo.setRegIp(vo.getRegIp());
				
				// 상품 등록자의 크레딧 등록
				if(chargeDao.doCharge(creditVo) < 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return -7;
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
			
			// 장바구니 경우
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

	@Override
	public List<ProductBuyVO> listBoughtProduct(ProductBuyVO vo) {
		return productBuyDao.listBoughtProduct(vo);
	}

	@Override
	public RegisterCustVO getFamilyCustDefaultAddr(int familyCustNo) {
		return productBuyDao.getFamilyCustDefaultAddr(familyCustNo);
	}

	@Override
	@Transactional
	public int insertProduct(ProductVO vo, String[] productImgUrls)  {
		if(productBuyDao.insertProduct(vo) != 1) {
			throw new RuntimeException("상품 추가 실패");
		}
		
		if(productImgUrls != null) {
			vo.setProductImgPrimary("Y");
			
			for(String str : productImgUrls) {
				vo.setProductImgUrl(str);
				
				if(productBuyDao.insertProductImgUrl(vo) != 1) {
					throw new RuntimeException("이미지 추가 실패");
				} else {
					vo.setProductImgPrimary("N");
				}
			}
		}
		
		return 1;
	}	
}
