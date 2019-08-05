package com.familytoto.familytotoProject.mybatis.webLog.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.mybatis.webLog.domain.WebLogVO;
import com.familytoto.familytotoProject.mybatis.webLog.mapper.WebLogMapper;

@Controller
public class WebLogController {
	
	private final WebLogMapper webLogMapper;
	
	public WebLogController(WebLogMapper webLogMapper) {
		this.webLogMapper = webLogMapper;
	}
	
	@PostMapping("/insertWebLog")
	@ResponseBody
	public int insertWebLog(@RequestBody WebLogVO webLogVO){
		System.out.println(webLogVO+"tests");
		return webLogMapper.insertWebLog(webLogVO);
	}
	
	@GetMapping("getWebLogList")
	@ResponseBody         
	public List<WebLogVO> getWebLogList(){
		return webLogMapper.getWebLogList();
	}

}
