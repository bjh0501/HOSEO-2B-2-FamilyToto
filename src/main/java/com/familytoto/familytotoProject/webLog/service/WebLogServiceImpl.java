package com.familytoto.familytotoProject.webLog.service;

import java.util.Enumeration;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.webLog.dao.WebLogDAO;
import com.familytoto.familytotoProject.webLog.domain.WebLogVO;

@Service
public class WebLogServiceImpl implements WebLogService {
	
	@Inject
	WebLogDAO webLogDao;
	
	public void insertWebLog(WebLogVO vo, HttpServletRequest request){		
		vo.setBoardNo(0); // 임시로0
		vo.setCustNo(0);// 임시로0		
		
		webLogDao.insert(vo);
	}
	
//	@GetMapping("getWebLogList")
//	@ResponseBody         
//	public List<WebLogVO> getWebLogList(){
//		return webLogService.getWebLogList();
//	}

}
