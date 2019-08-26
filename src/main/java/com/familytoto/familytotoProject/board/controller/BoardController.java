package com.familytoto.familytotoProject.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.PagingVO;
import com.familytoto.familytotoProject.board.domain.SearchVO;
import com.familytoto.familytotoProject.board.service.BoardService;
import com.familytoto.familytotoProject.comment.domain.CommentVO;
import com.familytoto.familytotoProject.comment.service.CommentService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
    public String boardList(Model model
    		, @RequestParam(required = false, defaultValue = "1") int page
			, @RequestParam(required = false, defaultValue = "1") int range
			, @RequestParam(required = false, defaultValue = "title") String searchType
			, @RequestParam(required = false) String keyword
    		) throws Exception {
		//전체 게시글 개수
		
		SearchVO search = new SearchVO();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		
		int listCnt = boardService.getBoardListCnt(search);

		search.pageInfo(page, range, listCnt);

	    // Pagination 객체생성		
		search.pageInfo(page, range, listCnt);

		model.addAttribute("pagination", search);
		model.addAttribute("boardList", boardService.getBoardList(search));
		
        return "board/boardList";
    }
	
	@RequestMapping(value = {"/registerBoard", "/registerBoard/{boardNo}"} )
    public ModelAndView registerBoard(HttpSession session,
    		ModelAndView mv,
    		@Nullable @PathVariable("boardNo") String sBoardNo) {
		mv.addObject("replyNo", sBoardNo);	
		mv.setViewName("/board/registerBoard");
		mv.addObject("loginGubun", session.getAttribute("cust"));
		
        return mv;
    }
	
	@RequestMapping(value = "registerBoard/insert")
    public String insertBoard(@ModelAttribute BoardVO vo, HttpServletRequest request, HttpSession session) {
		vo.setRegIp(request.getRemoteAddr());
		
		if(session.getAttribute("cust") != null) {
			CustVO cVo = (CustVO) session.getAttribute("cust");
			vo.setRegCustNo(cVo.getCustNo());
			
			int nResult = boardService.insertCustBoard(vo);
			
			if(nResult == -99) {
				return "-99";
			} else if(nResult == -98) {
				return "-98";
			}
		} else {
			if(vo.getBoardAnnoId() != null && vo.getBoardAnnoPw() != null) {
				if(vo.getBoardAnnoId().trim().equals("") || vo.getBoardAnnoId().length() <= 3 && vo.getBoardAnnoId().length() >= 21 ) {
					return "-99";
				}
			}
		}
		
        return "redirect:/boardList";
    }
	
	@RequestMapping("showEditor")
    public String showEditor() {
        return "board/editor/showEditor";
    }
	
	@RequestMapping(value = {"/showBoard/{boardNo}"})
    public ModelAndView showBoard(HttpSession session, @PathVariable ("boardNo") String sBoardNo, ModelAndView mv) {
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.parseInt(sBoardNo));
		CustVO custVo = (CustVO) session.getAttribute("cust");
		int nCommentCnt = boardService.getCommentCnt(vo);
		int nCustNo = -1;
		
		vo.setBoardNo(Integer.parseInt(sBoardNo));
		vo = boardService.getShowBoard(vo);
		
		if(custVo != null) {
			nCustNo = custVo.getCustNo();
		} 
				
		List<CommentVO> listCommentVo = commentService.getListComment(vo);

		String sGubun = (String) session.getAttribute("social"); 
		
		if(sGubun != null) {
			if(sGubun.equals("KA")) {
				sGubun = "/img/social/icon/kakaoMiniIcon.jpg";
			} else if(sGubun.equals("FA")) {
				sGubun = "/img/social/icon/facebookMiniIcon.jpg";
			} else if(sGubun.equals("NA")) {
				sGubun = "/img/social/icon/naverMiniIcon.jpg";
			} 
		} else {
			sGubun = "/img/social/icon/onesportsMiniIcon.jpg";
		}

		mv.addObject("cust", nCustNo);
		mv.addObject("board", vo);
		mv.addObject("custComment", custVo);
		mv.addObject("comment", listCommentVo);
		mv.addObject("socialImg", sGubun);
		mv.addObject("commentCnt", nCommentCnt);
		
		mv.setViewName("board/showBoard");
		
		return mv;
    }
	
	@RequestMapping(value = {"/deleteBoard/{boardNo}"})
	public String deleteBoard(HttpSession session, @PathVariable ("boardNo") String sNo, HttpServletRequest request) {
		if(boardService.updateDeleteBoard(sNo, session, request) == 1) {
			return "redirect:/boardList";			
		} else {
			return null;
		}
    }
	
	@RequestMapping(value = {"/deleteAnnoBoard/{boardNo}"})
	@ResponseBody
    public int deleteAnnoBoard(@PathVariable ("boardNo") String sNo, HttpServletRequest request, @ModelAttribute BoardVO vo) {
		int nResult = boardService.updateDeleteAnnoBoard(sNo, request,vo);
		
		if(nResult == 1) {
			return 0;			
		} else if(nResult == -99) {
			return -99;
		} else {
			return -98;
		}
    }
	
	@RequestMapping(value = "/registerBoard/anno/insert")
    public String insertAnnoBoard(@ModelAttribute BoardVO vo, HttpServletRequest request, HttpSession session) {
		vo.setRegIp(request.getRemoteAddr());
		
		if(session.getAttribute("cust") == null) {
			int nResult = boardService.insertAnnoBoard(vo);
			
//			if(nResult == -99) {
//				return "-99";
//			} else if(nResult == -98) {
//				return "-98";
//			}
		} 
		
        return "redirect:/boardList";
    }
	
	@RequestMapping("/updateBoard/check")
	@ResponseBody
    public String updateCheckBoard(BoardVO bVo, Model model) {
		CustVO cVo = new CustVO();
		String sOriginalPass = bVo.getBoardAnnoPw();
		
		bVo = boardService.getUpdateBoard(bVo);
		cVo.setCustPassword(sOriginalPass);
		
		if(cVo.isDecodePassword(cVo, bVo.getBoardAnnoPw())) {
			model.addAttribute("board", bVo);
			
	        return "0";
		} else { // 비번틀린경우
			
			return "-98";
		}		
    }
	
	@RequestMapping("/updateBoard/{boardNo}")
	public String showUpdateBoard(BoardVO bVo, Model model) {
		// 비밀번호 체크 && 리퍼러 체크하기> 실패하면 원래있던 보드로 이동 
		// 성공하면 수정창이동
		BoardVO resultBoardVo = boardService.getUpdateBoard(bVo);
		
		
		model.addAttribute("board", resultBoardVo);
		return "/board/updateBoard";
    }
	
	@RequestMapping("/updateBoard/{boardNo}/update")
	@ResponseBody
	public int updateBoard(@PathVariable ("boardNo") String sBoardNo,
			@ModelAttribute BoardVO bVo, Model model,
			HttpServletRequest request, HttpSession session) {
		bVo.setChgIp(request.getRemoteAddr());

		return boardService.updateBoard(bVo, session);
    }
	
	
}
