package com.familytoto.familytotoProject.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.board.domain.BoardVO;
import com.familytoto.familytotoProject.board.domain.PagingVO;
import com.familytoto.familytotoProject.board.service.BoardService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class BorderController {
	
	@Autowired
	BoardService boardService;  
	
	@RequestMapping(value = "/boardList", method = RequestMethod.GET)
    public String boardList(Model model
    		, @RequestParam(required = false, defaultValue = "1") int page
			, @RequestParam(required = false, defaultValue = "1") int range) throws Exception {
		//전체 게시글 개수

		int listCnt = boardService.getBoardListCnt();

	    // Pagination 객체생성
		PagingVO pagination = new PagingVO();
		pagination.pageInfo(page, range, listCnt);					
		
		model.addAttribute("pagination", pagination);
		model.addAttribute("boardList", boardService.getBoardList(pagination));
		
        return "board/boardList";
    }
	
	@RequestMapping(value = "registerBoard")
    public ModelAndView registerBoard(HttpSession session, ModelAndView mv) {
		mv.setViewName("board/registerBoard");
		mv.addObject("loginGubun", session.getAttribute("cust"));
		
        return mv;
    }
	
	@RequestMapping(value = "registerBoard/insert")
    public String insertBoard(@ModelAttribute BoardVO vo, HttpServletRequest request, HttpSession session) {
		vo.setRegIp(request.getRemoteAddr());
		
		if(session.getAttribute("cust") != null) {
			CustVO cVo = (CustVO) session.getAttribute("cust");
			vo.setRegCustNo(cVo.getCustNo());
			
			int nResult = boardService.insertCustBorder(vo);
			
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
		CustVO custVo = (CustVO) session.getAttribute("cust");
		int nCustNo = 0;
		
		vo.setBoardNo(Integer.parseInt(sBoardNo));
		vo = boardService.getShowBoard(vo);
		
		if(custVo != null) {
			nCustNo = custVo.getCustNo();
		} 
		
		mv.addObject("cust", nCustNo);
		mv.addObject("board", vo);
		mv.setViewName("board/showBoard");
		
		return mv;
    }
	
	@RequestMapping(value = {"/deleteBoard/{boardNo}"})
    public String deleteBoard(HttpSession session, @PathVariable ("boardNo") String sNo,HttpServletRequest request) {
		boardService.updateDeleteBoard(sNo, session, request);
		
		return "redirect:/boardList";
    }
	
	@RequestMapping(value = "registerBoard/anno/insert")
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
}
