package com.familytoto.familytotoProject.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.board.dao.BoardDao;
import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.SearchVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDao;
	
	public int insertCustBoard(BoardVO vo) {
		if(vo.getBoardTitle() == null || vo.getBoardTitle().equals("")) {
			return -99;
		}
		
		if(vo.getBoardContents() == null || vo.getBoardContents().equals("")) {
			return -98;
		}
		
		return boardDao.insertCustBoard(vo);
	}

	public int updateBoard(BoardVO vo, HttpSession session) {
		// 익명
		if(vo.getBoardAnnoId() == null) {
			CustVO cVo = new CustVO();
			String sPass = cVo.toEncodePassword(vo.getBoardAnnoPw());
			vo.setBoardAnnoPw(sPass);
					
			return boardDao.updateBoard(vo);
		} else {
			CustVO sessionVo = (CustVO) session.getAttribute("cust");
			vo.setChgCustNo(sessionVo.getCustNo());
			return boardDao.updateSocialBoard(vo);
		}
	}
	
	// 페이징
	public int getBoardListCnt(SearchVO search) throws Exception {
		return boardDao.getBoardListCnt(search);
	}
	
	public List<BoardVO> getBoardList(SearchVO search) throws Exception {		
		List<BoardVO> list =boardDao.getBoardList(search);
		
		int i = 0;
		
		for(BoardVO vo :list) {
			if(vo.getRegCustNo() == 0) {
				vo.setCustGubun("");
			} else if(vo.getCustGubun().equals("NA")) {
				vo.setCustGubun("/img/social/icon/naverMiniIcon.jpg");
			} else if(vo.getCustGubun().equals("KA")) {
				vo.setCustGubun("/img/social/icon/kakaoMiniIcon.jpg");
			} else if(vo.getCustGubun().equals("FA")) {
				vo.setCustGubun("/img/social/icon/facebookMiniIcon.jpg");
			} else if(vo.getCustGubun().equals("ON")) {
				vo.setCustGubun("/img/social/icon/onesportsMiniIcon.jpg");
			}  else {
				vo.setCustGubun("");
			}
			
			vo.diffTime(vo.getRegDt());
			
			list.set(i, vo);
			i++;
		}
		
		return list;
	}

	public BoardVO getShowBoard(BoardVO vo) {
		return boardDao.getShowBoard(vo);
	}

	public int updateDeleteBoard(String sNo, HttpSession session, HttpServletRequest request) {
		BoardVO vo = new BoardVO();
		CustVO cVo = (CustVO) session.getAttribute("cust");
	
		vo.setBoardNo(Integer.parseInt(sNo));
		vo.setChgCustNo(cVo.getCustNo());
		vo.setChgIp(request.getRemoteAddr());
		
		return boardDao.updateDeleteBoard(vo);	
	}
	
	public int updateDeleteAnnoBoard(String sNo, HttpServletRequest request, BoardVO vo) {
		CustVO cVo = new CustVO();
		vo.setBoardNo(Integer.parseInt(sNo));
		vo.setChgIp(request.getRemoteAddr());
		cVo.setCustPassword(vo.getBoardAnnoPw());
		
		String sPass = boardDao.checkAnnoBoardPass(vo);
		
		if(cVo.isDecodePassword(cVo, sPass)==true) {
			return boardDao.updateDeleteAnnoBoard(vo);
		} else { // 비밀번호틀림
			return -99;
		}
	}

	public int insertAnnoBoard(BoardVO vo) {
		CustVO cVo = new CustVO();
		vo.setBoardAnnoPw(cVo.toEncodePassword(vo.getBoardAnnoPw()));
		
		return boardDao.insertAnnoBoard(vo);
	}

	public BoardVO getUpdateBoard(BoardVO vo) {
		return boardDao.getUpdateBoard(vo) ;
	}

	@Override
	public int getCommentCnt(BoardVO vo) {
		return boardDao.getCommentCnt(vo);
	}
}
