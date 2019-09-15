package com.familytoto.familytotoProject.qna.dao;

import java.util.List;

import com.familytoto.familytotoProject.qna.domain.QnaVO;

public interface QnaDao {
	int insertCustQna(QnaVO vo);
	
	List<QnaVO> listQna(QnaVO vo);
	
	int insertAnnoQna(QnaVO vo);
	
	// 운영자 답변
	int updateAnswerQna(QnaVO vo);
}
