package com.familytoto.familytotoProject.comment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.comment.domain.CommentVO;
import com.familytoto.familytotoProject.comment.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	CommentService commentService; 
	
	@RequestMapping("/board/insertComment")
	@ResponseBody
	public int insertComment(CommentVO vo, HttpServletRequest request) {
		vo.setRegIp(request.getRemoteAddr());
		
		return commentService.insertComment(vo);
	}
}
