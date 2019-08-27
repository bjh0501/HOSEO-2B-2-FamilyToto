package com.familytoto.familytotoProject.qna.dao;

import java.util.List;

import com.familytoto.familytotoProject.qna.domain.QnaVO;

public interface QnaDao {
	int insertCustQna(QnaVO vo);
	
	List<QnaVO> listQna(QnaVO vo);
}
