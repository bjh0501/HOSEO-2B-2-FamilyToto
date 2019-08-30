package com.familytoto.familytotoProject.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.comment.dao.CommentDao;
import com.familytoto.familytotoProject.comment.domain.CommentVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentDao commentDao;
	
	@Override
	public int insertComment(CommentVO vo) {
		
		if(vo.getRegCustNo() == 0) {
			CustVO cVo = new CustVO();
			if(vo.getCommentAnnoId().equals("")) {
				return -99;
			}
			
			if(vo.getCommentAnnoPw().equals("")) {
				return -98;
			}
			
			if(vo.getCommentContents().equals("")) {
				return -97;
			}
			
			vo.setCommentAnnoPw(cVo.toEncodePassword(vo.getCommentAnnoPw()));
		} else {
			if(vo.getCommentContents().equals("")) {
				return -96;
			}
		}
		
		int nResult = commentDao.insertComment(vo);
		
		if(nResult == 1) {
			return commentDao.updateInsertComment(vo);
		} else {
			return -95;
		}
	}
	
	@Override
	public int insertReplyComment(CommentVO vo) {
		CustVO cVo = new CustVO();
		
		String sPass = cVo.toEncodePassword(vo.getCommentAnnoPw());
		
		vo.setCommentAnnoPw(sPass);
		
		
		commentDao.updateBeforeReplyOrder(vo);
		
		int nInsertReplyCommentResult = commentDao.insertReplyComment(vo);
		
		if(nInsertReplyCommentResult == 1) {
			return 1;
		} else {
			return -99;
		}
	}

	@Override
	public List<CommentVO> getListComment(BoardVO vo) {
		return commentDao.getListComment(vo);
	}

	@Override
	public int updateDeleteComment(CommentVO vo) {
		return commentDao.updateDeleteComment(vo);
	}

	@Override
	public int updateDeleteAnnoComment(CommentVO vo) {
		CustVO cVo = new CustVO();
		CommentVO vo2 = commentDao.checkAnnoCommentPass(vo);
		
		//비밃번호틀림
		if(vo2 == null) {
			return -99;
		}
		
		String sHashPass = vo2.getCommentAnnoPw();
		cVo.setCustPassword(vo.getCommentAnnoPw());
		
		if(cVo.isDecodePassword(cVo, sHashPass) == true) {
			return commentDao.updateDeleteAnnoComment(vo);
		} else {
			return -99;
		}
	}

	@Override
	public int updateComment(CommentVO vo) {
		// 비회원
		if(vo.getCommentAnnoPw() != null) {
			CustVO cVo = new CustVO();
			CommentVO vo2 = commentDao.checkAnnoCommentPass(vo);
			
			//비밃번호틀림
			if(vo2 == null) {
				return -99;
			}
			
			String sHashPass = vo2.getCommentAnnoPw();
			cVo.setCustPassword(vo.getCommentAnnoPw());
			
			if(cVo.isDecodePassword(cVo, sHashPass) == true) {
				return commentDao.updateComment(vo);
			} else { //비밃번호틀림
				return -99;
			}
		} else {
			return commentDao.updateComment(vo);
		}
	}	
}
