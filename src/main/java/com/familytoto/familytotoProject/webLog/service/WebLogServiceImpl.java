package com.familytoto.familytotoProject.webLog.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.webLog.dao.WebLogDAO;
import com.familytoto.familytotoProject.webLog.domain.WebLogVO;

@Service
public class WebLogServiceImpl implements WebLogService {
	@Autowired
	WebLogDAO webLogDao;
	
	public void insertWebLog(WebLogVO vo, HttpServletRequest request, HttpSession session){		
		String referer = "";
    	int nCustNo = 0;
    	
    	if(session.getAttribute("cust") != null) {
    		CustVO cVo = (CustVO)session.getAttribute("cust");
    		nCustNo = cVo.getCustNo();
    	}
    	
    	if(request.getHeader("REFERER") != null) {
    		referer = (String)request.getHeader("REFERER");
    	}
    	
		vo.setCurrentUrl(request.getRequestURI());
		
		int nBoardNo = 0;
		
		if(request.getRequestURI().indexOf("/showBoard/") >= 0) {
			nBoardNo = Integer.parseInt(request.getRequestURI().replace("/showBoard/", ""));
		} else {
			nBoardNo = 0;
		}
		
    	vo.setBoardNo(nBoardNo);
    	vo.setPrevUrl(referer);
    	vo.setIp(request.getRemoteAddr());
    	vo.setCustNo(nCustNo);
    	
		webLogDao.insert(vo);
	}
}
