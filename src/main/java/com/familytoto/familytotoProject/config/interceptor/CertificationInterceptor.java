package com.familytoto.familytotoProject.config.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.webLog.domain.WebLogVO;
import com.familytoto.familytotoProject.webLog.service.WebLogService;

@Component
public class CertificationInterceptor implements HandlerInterceptor{	
	private final int loginSesion = 60 * 30; 

	@Autowired
	WebLogService webLogService;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
 
        if(session.getAttribute("cust") == null){
        	response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인후에 이용할 수 있습니다.'); location.href='/login'</script>");
            out.flush();
            
            return false;
        }else{ //로그인세션
            session.setMaxInactiveInterval(loginSesion);
            return true;
        }
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
}