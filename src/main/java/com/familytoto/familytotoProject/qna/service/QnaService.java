package com.familytoto.familytotoProject.qna.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.familytoto.familytotoProject.qna.domain.QnaVO;

public interface QnaService {
	int insertCustQna(QnaVO vo, HttpSession session, HttpServletRequest request);
	
	List<QnaVO> listQna(QnaVO vo);
}
