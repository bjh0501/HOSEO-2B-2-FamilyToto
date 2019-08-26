package com.familytoto.familytotoProject.config.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.webLog.domain.WebLogVO;
import com.familytoto.familytotoProject.webLog.service.WebLogService;

@Component
public class WebLogInterceptor implements HandlerInterceptor{	
	private final int loginSesion = 60 * 30; 

	@Autowired
	WebLogService webLogService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
 
        insertWebLog(request, session);
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        
    }
    
    public void insertWebLog(HttpServletRequest request, HttpSession session) {
    	WebLogVO vo = new WebLogVO();
    	
    	webLogService.insertWebLog(vo, request, session);
    }
}