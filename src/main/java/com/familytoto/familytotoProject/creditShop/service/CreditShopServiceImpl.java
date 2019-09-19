package com.familytoto.familytotoProject.creditShop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.familytoto.familytotoProject.creditShop.dao.CreditShopDao;
import com.familytoto.familytotoProject.creditShop.domain.CategoryVO;
import com.familytoto.familytotoProject.creditShop.domain.MileageVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductCommentVO;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

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
	@Transactional
	public int insertProductComment(ProductCommentVO vo, CustVO cVo, HttpServletRequest request) {
		if(creditShopDao.isBoughtProduct(vo) == false) {
			return -99;
		}
		
		if(creditShopDao.isDupleProductComment(vo) == true) {
			return -98;
		}
		
		double randomVal = Math.random();
		// 100 ~ 300
		int nValue = (int)(randomVal*200)+100;
		
		MileageVO mVo = new MileageVO();
		mVo.setFamilyCustNo(cVo.getFamilyCustNo());
		mVo.setMileageValue(nValue);
		mVo.setMileageState("CRM");
		mVo.setRegCustNo(cVo.getCustNo());
		mVo.setRegIp(request.getRemoteAddr());
		
		if(creditShopDao.getRandomMileage(mVo) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -1;
		}
		
		if(creditShopDao.insertProductComment(vo) == 1) {
			return nValue;
		} else {
			return -2;
		}
	}

	@Override
	public List<ProductCommentVO> listProductComment(ProductVO vo) {
		return creditShopDao.listProductComment(vo);
	}

	@Override
	public int productCommentCnt(ProductVO vo) {
		return creditShopDao.productCommentCnt(vo);
	}

	@Override
	public List<ProductVO> listProductImgs(ProductVO vo) {
		return creditShopDao.listProductImgs(vo);
	}

	@Override
	public List<CategoryVO> listProductCategory() {
		return creditShopDao.listProductCategory();
	}
}
