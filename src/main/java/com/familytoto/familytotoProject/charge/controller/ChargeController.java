 package com.familytoto.familytotoProject.charge.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.charge.service.ChargeService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Controller
public class ChargeController {
	@Autowired
	ChargeService chargeService;

	private int nCustNo = 0;
	private int nFamilyCustNo = 0;

	@RequestMapping("/charge")
	public ModelAndView charge(ModelAndView mv, HttpSession session, HttpServletResponse response) {
		// 소셜 아이디
		CustVO vo = (CustVO) session.getAttribute("cust");

		if (vo.getFamilyCustNo() == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('연동이 안된 소셜아이디는  충전을 할 수 없습니다.'); history.go(-1);</script>");
				out.flush();
			} catch (Exception e) {
			}

			mv.setViewName("redirect:/");

			return mv;
		}

		nCustNo = vo.getCustNo();
		nFamilyCustNo = vo.getFamilyCustNo();

		Map<String, Object> map = chargeService.getCreditInfo(vo);

		mv.addObject("list", map);
		mv.addObject("currentCredit", chargeService.getCurrentCredit(nFamilyCustNo));
		mv.addObject("cardInfo", chargeService.getCardInfo(nFamilyCustNo));
		mv.addObject("custEmail", vo.getFamilyCustEmail());
		mv.addObject("custNickname", vo.getFamilyCustNick());
		
		mv.setViewName("/shop/charge");

		return mv;
	}

	@RequestMapping(value = "/charge/doCharge", method = RequestMethod.POST)
	@ResponseBody
	public int doCharge(@Valid @ModelAttribute CreditVO vo,
			HttpServletRequest request,
			String sEmail,
			String emailAddress) {
		vo.setRegCustNo(nCustNo);
		vo.setRegIp(request.getRemoteAddr());
		vo.setFamilyCustNo(nFamilyCustNo);

		String sMail = request.getParameter("mail");
		String sGubun = request.getParameter("gubun");
		
		if(!sGubun.equals("FREE") && !sGubun.equals("CARD")) {
			return -1;
		}
		
		if(sGubun.equals("FREE")) {
			vo.setCreditState("FRE");
		} else  {
			vo.setCreditState("CBC");
		}

		int nResult = chargeService.doCharge(vo);

		if (nResult == 1) {
			if (sMail.equals("Y") && emailAddress != null) {
				if(sGubun.equals("FREE")) {
					sGubun = "무료충전";
				} else {
					sGubun = "카드충전";
				}
				
				chargeService.sendHistoryEmail(emailAddress, vo.getCreditValue(), sGubun);
				return 0;
			} else {
				return 1;
			}
		} else if (nResult == -98) {
			return -98;
		} else if (nResult == -97) {
			return -97;
		} else if (nResult == -96) {
			return -96; 
		} else {
			return -95;
		}
	}
}
