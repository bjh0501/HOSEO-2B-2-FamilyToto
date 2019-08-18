package com.familytoto.familytotoProject.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.board.dao.BoardDao;
import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.PagingVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDao;
	
	@Override
	public int insertCustBorder(BoardVO vo) {
		if(vo.getBoardTitle() == null || vo.getBoardTitle().equals("")) {
			return -99;
		}
		
		if(vo.getBoardContents() == null || vo.getBoardContents().equals("")) {
			return -98;
		}
		
		return boardDao.insertCustBoard(vo);
	}

	@Override
	public int updateBorder(BoardVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 페이징
	public int getBoardListCnt() throws Exception {
		return boardDao.getBoardListCnt();
	}
	
	public List<BoardVO> getBoardList(PagingVO pagination) throws Exception {		
		List<BoardVO> list =boardDao.getBoardList(pagination);
		
		int i = 0;
		
		for(BoardVO vo :list) {
			if(vo.getCustGubun().equals("NA")) {
				vo.setCustGubun("/img/social/icon/naverMiniIcon.png");
			} else if(vo.getCustGubun().equals("KA")) {
				vo.setCustGubun("/img/social/icon/kakaoMiniIcon.jpg");
			} else if(vo.getCustGubun().equals("FA")) {
				vo.setCustGubun("/img/social/icon/facebookMiniIcon.png");
			} else if(vo.getCustGubun().equals("ON")) {
				vo.setCustGubun("/img/social/icon/oneMiniIcon.png");
			}
			
			vo.diffTime(vo.getRegDt());
			
			list.set(i, vo);
			i++;
		}
		
		return list;
	}

	@Override
	public BoardVO getShowBoard(BoardVO vo) {
		return boardDao.getShowBoard(vo);
	}

	@Override
	public int updateDeleteBoard(BoardVO vo) {
		return boardDao.updateDeleteBoard(vo);
	}

}
