package com.familytoto.familytotoProject.mybatis.webLog.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.mybatis.webLog.domain.WebLogVO;
import com.familytoto.familytotoProject.mybatis.webLog.mapper.WebLogMapper;

@Controller
public class WebLogController {
	
	private final WebLogMapper webLogMapper;
	
	public WebLogController(WebLogMapper webLogMapper) {
		this.webLogMapper = webLogMapper;
	}
	
	public int insertWebLog(HttpServletRequest request){
		WebLogVO webLogVO = new WebLogVO();
		
		System.out.println("진입@!#$#@$@#");
		webLogVO.setIp(request.getRemoteAddr());
		webLogVO.setCurrentUrl(getURL(request));
		webLogVO.setPrevUrl(request.getHeader("referer"));
		webLogVO.setBoardNo(0); // 임시로0
		webLogVO.setCustNo(0);// 임시로0
		
		return webLogMapper.insertWebLog(webLogVO);
	}
	
	@GetMapping("getWebLogList")
	@ResponseBody         
	public List<WebLogVO> getWebLogList(){
		return webLogMapper.getWebLogList();
	}

	public String getURL(HttpServletRequest request) {
		Enumeration<?> param = request.getParameterNames();

		StringBuffer strParam = new StringBuffer();
		StringBuffer strURL = new StringBuffer();

		if (param.hasMoreElements()) {
			strParam.append("?");
		}

		while (param.hasMoreElements()) {
			String name = (String) param.nextElement();
			String value = request.getParameter(name);

			strParam.append(name + "=" + value);

			if (param.hasMoreElements()) {
				strParam.append("&");
			}
		}

		strURL.append(request.getRequestURI());
		strURL.append(strParam);

		return strURL.toString();
	}

}
