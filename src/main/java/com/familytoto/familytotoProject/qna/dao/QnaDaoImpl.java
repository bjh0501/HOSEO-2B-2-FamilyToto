package com.familytoto.familytotoProject.qna.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.familytoto.familytotoProject.qna.domain.QnaVO;

@Repository
public class QnaDaoImpl implements QnaDao {
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertCustQna(QnaVO vo) {
		return sqlSession.insert("qna.insertQnaCust",vo);
	}

	@Override
	public List<QnaVO> listQna(QnaVO vo) {
		return sqlSession.selectList("qna.listQna",vo);
	}

	@Override
	public int insertAnnoQna(QnaVO vo) {
		return sqlSession.insert("qna.insertQnaAnno",vo);
	}
}
