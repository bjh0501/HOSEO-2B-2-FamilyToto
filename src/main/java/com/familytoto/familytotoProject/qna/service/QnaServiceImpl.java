package com.familytoto.familytotoProject.qna.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
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
}
