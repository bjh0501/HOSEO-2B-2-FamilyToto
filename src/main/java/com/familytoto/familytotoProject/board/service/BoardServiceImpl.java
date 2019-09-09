package com.familytoto.familytotoProject.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.familytoto.familytotoProject.board.dao.BoardDao;
import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.FileVO;
import com.familytoto.familytotoProject.board.domain.SearchVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	BoardDao boardDao;
	
	@Transactional
	public int insertCustBoard(BoardVO vo, int nGubun) {
		if(vo.getBoardTitle() == null || vo.getBoardTitle().equals("")) {
			return -99;
		}
		
		if(vo.getBoardContents() == null || vo.getBoardContents().equals("")) {
			return -98;
		}
		
		if(nGubun == 0) { 							// 새글
			if(boardDao.insertCustBoard(vo) == 1) {
				return boardDao.updateBoardGrpNo(vo);				
			} else {
				return -97;
			}
		} else {
			boardDao.updateBeforeReply(vo);
			
			if(boardDao.insertCustBoard(vo) == 1) {
				return 1;
			} else {
				return -97;
			}
		}	
	}

	@Transactional
	public int insertAnnoBoard(BoardVO vo, int nGubun ) {
		CustVO cVo = new CustVO();
		vo.setBoardAnnoPw(cVo.toEncodePassword(vo.getBoardAnnoPw()));
		
		if(vo.getBoardTitle() == null || vo.getBoardTitle().equals("")) {
			return -99;
		}
		
		if(vo.getBoardContents() == null || vo.getBoardContents().equals("")) {
			return -98;
		}
		
		if(nGubun == 0) { 							// 새글
			if(boardDao.insertAnnoBoard(vo) == 1) {
				return boardDao.updateBoardGrpNo(vo);				
			} else {
				return -97;
			}
		} else {
			boardDao.updateBeforeReply(vo);
			
			if(boardDao.insertAnnoBoard(vo) == 1) {
				return 1;
			} else {
				return -97;
			}
		}	
	}
	
	public int updateBoard(BoardVO vo, HttpSession session) {
		// 익명
		if(vo.getBoardAnnoId() == null) {
			CustVO cVo = new CustVO();
					
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
			
			list.set(i, vo);
			i++;
		}
		
		return list;
	}

	public BoardVO getShowBoard(BoardVO vo) {
		BoardVO bVo = boardDao.getShowBoard(vo);
		String contents = bVo.getBoardContents();
		bVo.setBoardContents(contents);
		
		return bVo;
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

	public BoardVO getUpdateBoard(BoardVO vo) {
		return boardDao.getUpdateBoard(vo) ;
	}

	@Override
	public int getCommentCnt(BoardVO vo) {
		return boardDao.getCommentCnt(vo);
	}

	@Override
	public int insertFile(FileVO vo) {
		return boardDao.insertFile(vo);
	}

	@Override
	public int updateFile(FileVO vo) {
		return boardDao.updateFile(vo);
	}

	@Override
	public int updateVisitLog(int boardNo) {
		return boardDao.updateVisitLog(boardNo);
	}

	@Override
	public FileVO getUploadedFile(int boardNo) {
		return boardDao.getUploadedFile(boardNo);
	}
}
