package com.familytoto.familytotoProject.comment.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.comment.domain.CommentVO;
import com.familytoto.familytotoProject.comment.service.CommentService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class CommentController {
	@Autowired
	CommentService commentService; 
	
	@RequestMapping("/board/insertComment")
	@ResponseBody
	public int insertComment(CommentVO vo, HttpServletRequest request, HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		vo.setRegIp(request.getRemoteAddr());
		
		// 회원
		if(cVo != null) {
			vo.setRegCustNo(cVo.getCustNo());
		}
		
		return commentService.insertComment(vo);
	}
}