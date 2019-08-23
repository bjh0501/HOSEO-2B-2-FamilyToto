package com.familytoto.familytotoProject.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.comment.dao.CommentDao;
import com.familytoto.familytotoProject.comment.domain.CommentVO;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	CommentDao commentDao;
	
	@Override
	public int insertComment(CommentVO vo) {
		if(vo.getRegCustNo() == 0) {
			if(vo.getCommentAnnoId().equals("")) {
				return -99;
			}
			
			if(vo.getCommentAnnoPw().equals("")) {
				return -98;
			}
			
			if(vo.getCommentContents().equals("")) {
				return -97;
			}
		} else {
			if(vo.getCommentContents().equals("")) {
				return -96;
			}
		}
		
		
 		return commentDao.insertComment(vo);
	}

	@Override
	public List<CommentVO> getListComment(BoardVO vo) {
		return commentDao.getListComment(vo);
	}

	@Override
	public int updateDeleteComment(CommentVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
