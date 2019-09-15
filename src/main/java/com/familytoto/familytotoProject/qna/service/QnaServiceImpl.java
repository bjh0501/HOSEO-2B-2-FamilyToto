package com.familytoto.familytotoProject.qna.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.qna.dao.QnaDao;
import com.familytoto.familytotoProject.qna.domain.QnaVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class QnaServiceImpl implements QnaService {
	@Autowired
	QnaDao qnaDao;
	
	@Override
	public int insertCustQna(QnaVO vo, HttpSession session, HttpServletRequest request) {
		CustVO cVo = (CustVO)session.getAttribute("cust");
		
		vo.setRegIp(request.getRemoteAddr());
		
		if(cVo == null) {
			return -99;
		} else {
			vo.setQnaQuestionRegCustNo(cVo.getCustNo());			
		}
		
		
		if(vo.getQnaQuestionRegCustNo() == 0) {
			return -98;
		}
		
		if(vo.getQnaQuestionContents() == null || vo.getQnaQuestionContents().trim().equals("")) {
			return -97;
		}
		
		return qnaDao.insertCustQna(vo);
	}

	@Override
	public List<QnaVO> listQna(QnaVO vo) {
		return qnaDao.listQna(vo);
	}

	@Override
	public int insertAnnoQna(QnaVO vo, HttpServletRequest request) {
		vo.setRegIp(request.getRemoteAddr());
		
		if(vo.getQnaQuestionContents() == null || vo.getQnaQuestionContents().trim().equals("")) {
			return -99;
		}
		
		if(vo.getQnaQuestionAnnoId() == null || vo.getQnaQuestionAnnoId().trim().equals("")) {
			return -98;
		}
				
		if(vo.getQnaQuestionAnnoPw() == null || vo.getQnaQuestionAnnoPw().equals("")) {
			return -97;
		} else {
			CustVO cVo = new CustVO();
			String sPass = cVo.toEncodePassword(vo.getQnaQuestionAnnoPw());
			vo.setQnaQuestionAnnoPw(sPass);
		}
		
		return qnaDao.insertAnnoQna(vo);
	}

	@Override
	public int updateAnswerQna(QnaVO vo) {
		return qnaDao.updateAnswerQna(vo);
	}
}
