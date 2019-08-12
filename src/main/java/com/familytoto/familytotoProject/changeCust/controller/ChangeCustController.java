package com.familytoto.familytotoProject.changeCust.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.changeCust.service.ChangeCustService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Controller
public class ChangeCustController {
	@Autowired
	ChangeCustService changeCustService;
	
	private String sCurrentPassword = ""; 
	
	@RequestMapping("changeCust")
    public ModelAndView changeCust(ModelAndView mv, HttpSession session) {
		CustVO vo = (CustVO) session.getAttribute("cust");
		
		Map<String, Object> map = changeCustService.getCustInfo(vo); 
		sCurrentPassword = (String) map.get("custPassword");
		
		mv.setViewName("loginInfo/changeCust");
		mv.addObject("list", map);
		
        return mv;
    }
	
	@RequestMapping("changeCust/change")
	@ResponseBody
    public int change(ModelAndView mv, HttpSession session, 
    		@ModelAttribute CustVO cVo,@ModelAttribute RegisterCustVO rVo,
    		HttpServletRequest request) {
			String sFrontCurrentPass = request.getParameter("currentPassword");
			String sFrontNewPass = request.getParameter("newPassword");
		
		cVo.setCustPassword(sFrontCurrentPass);
		
		if(cVo.isDecodePassword(cVo, sCurrentPassword)) {
			CustVO vo = (CustVO) session.getAttribute("cust");
			
			cVo.setChgCustNo(vo.getCustNo());
			cVo.setChgIp(request.getRemoteAddr());
			cVo.setFamilyCustNo(vo.getFamilyCustNo());
			
			// 임시방편
			// 마이바티스 if문 쓸줄알면 나중에 변경예정
			if(!sFrontNewPass.equals("")) {
				cVo.setCustPassword(cVo.toEncodePassword(sFrontNewPass));
			} else {
				cVo.setCustPassword(cVo.toEncodePassword(sFrontCurrentPass));
			}
			
			rVo.setChgIp(request.getRemoteAddr());
			rVo.setFamilyCustNo(vo.getFamilyCustNo());
			
			return changeCustService.updateCustInfo(cVo, rVo);
		} else {
			return -99;
		}
	}
}
