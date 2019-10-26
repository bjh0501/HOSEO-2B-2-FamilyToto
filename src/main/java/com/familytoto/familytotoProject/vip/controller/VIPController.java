package com.familytoto.familytotoProject.vip.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.vip.domain.VipVO;
import com.familytoto.familytotoProject.vip.service.VipService;
import com.google.gson.Gson;

@Controller
public class VIPController {
	@Autowired
	VipService vipService;
	
	@RequestMapping(value = { "/vipVasic"})
    public String index(HttpSession session,
    		HttpServletResponse response) {
		
		if(isCheckCust(session, response) == true) {
			return "/viproom/vipVasic";
		} else {
			return "/";
		}
    }
	
	@RequestMapping(value = { "/vipEnt"})
    public String vipEnt(HttpSession session,
    		HttpServletResponse response) {
		if(isCheckCust(session, response) == true) {
			return "/viproom/vipEnt";
		} else {
			return "/";
		}
    }
	
	@RequestMapping(value = { "/vipIntro"})
    public String vipIntro(HttpSession session,
    		HttpServletResponse response) {
		if(isCheckCust(session, response) == true) {
			return "/viproom/vipIntro";
		} else {
			return "/";
		}
    }
	
	@RequestMapping(value = { "/vipOpenrr"})
    public String vipOpenrr(Model model, HttpSession session,
    		@Param("roomNo") String roomNo,
    		@Param("roomName") String roomName,
    		HttpServletResponse response) {
		int creditValuePlus = 100000;
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(isCheckCust(session, response) == true) {
			// 크레딧 10만이상인지 체크
			CreditVO creditVo = new CreditVO(); 
			creditVo.setFamilyCustNo(custVo.getFamilyCustNo());
			creditVo.setCreditValue(creditValuePlus);
			
			if(vipService.isVipCreditValue(creditVo) == false) {
				try {
					response.setContentType("text/html; charset=UTF-8");
		            PrintWriter out = response.getWriter();
		            out.println("<script>alert('얘 배팅할 크레딧이 없단다!');location.replace('/vipEnt');</script>");
		            out.flush();
		            return "/vipEnt";
				} catch(Exception e) {}
			}
			
			if(roomNo == null) {
				roomNo = "0";
			}
			
			if(roomName == null) {
				roomName = "";
			}
			
			model.addAttribute("cust", custVo);
			model.addAttribute("roomNo", roomNo);
			model.addAttribute("roomName", roomName);
			
			
	        return "/viproom/vipOpenrr";
		} else {
			return "/";
		}
    }
	
	@RequestMapping(value = { "/vip/insertGameRoom"})
	@ResponseBody
    public int vipInsertGameRoom(@ModelAttribute VipVO vipVo,
    		HttpSession session,
    		HttpServletRequest request,
    		HttpServletResponse response) {
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(isCheckCust(session, response) == false) {
			throw new RuntimeException("VIP만 접근가능");
		}
		
		vipVo.setGameGubun("RSI");
		vipVo.setRegCustNo(custVo.getCustNo());
		vipVo.setRegIp(request.getRemoteAddr());		
		
        return vipService.insertGameRoom(vipVo);
    }
	
	@RequestMapping(value = { "/vip/insertGamePlayer"})
	@ResponseBody
	@Transactional
    public int vipInsertGamePlayer(@ModelAttribute VipVO vipVo,
    		HttpSession session,
    		HttpServletRequest request,
    		HttpServletResponse response,
    		int vipGameNo,
    		int familyCustNos[],
    		String roomOption) {
		if(isCheckCust(session, response) == false) {
			throw new RuntimeException("VIP만 접근가능");
		}
		
		if(roomOption!= null && !roomOption.equals("RSI") && !roomOption.equals("IPO")) {
			throw new RuntimeException("방 참여 에러");
		}
		
		int betPlusCredit = 100000;
		int betMinusCredit = 100000*-1;
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(custVo == null) {
			return -1;
		}
		
		for(int i = 0; i < familyCustNos.length; i++) {
			CreditVO creditVo = new CreditVO(); 
			creditVo.setFamilyCustNo(familyCustNos[i]);
			creditVo.setCreditValue(betPlusCredit);
			creditVo.setCreditState(roomOption); // RSI, IPO
			creditVo.setRegCustNo(custVo.getCustNo());
			creditVo.setRegIp(request.getRemoteAddr());
			
			if(vipService.isVipCreditValue(creditVo) == false) {
				throw new RuntimeException("VIP룸 크레딧 부족");
			}
			
			creditVo.setCreditValue(betMinusCredit);			
			
			vipVo.setVipGameNo(vipGameNo);
			vipVo.setCreditId(vipService.insertVipBet(creditVo)); // 수정
			vipVo.setFamilyCustNo(familyCustNos[i]);
			vipVo.setRegCustNo(custVo.getRegCustNo());
			vipVo.setRegIp(request.getRemoteAddr());
			
			if(vipService.insertGamePlayers(vipVo) != 1) {
				throw new RuntimeException("VIP룸 배팅 걸기 실패");
			}
		}
		
		// 방 못들어오게 방닫기
		if(vipService.updateCloseGameRoom(vipGameNo) != 1) {
			throw new RuntimeException("VIP룸 닫기 실패");
		}
		
        return 0;
    }
	
	@RequestMapping(value = { "/vip/winGamePlayer"})
	@ResponseBody
    public int vipWinGamePlayer(HttpSession session,
    		HttpServletRequest request,
    		int gameRoomNo,
    		HttpServletResponse response) {
		if(isCheckCust(session, response) == false) {
			throw new RuntimeException("VIP만 접근가능");
		}
		
		int betCredit = 100000;
		
		int playerCnt = vipService.getVipGamePlayersCnt(gameRoomNo);
		
		CustVO custVo = (CustVO) session.getAttribute("cust");
		CreditVO creditVo = new CreditVO(); 
		creditVo.setFamilyCustNo(custVo.getFamilyCustNo());
		creditVo.setCreditValue(playerCnt*betCredit);
		creditVo.setCreditState("RSW");
		creditVo.setRegCustNo(custVo.getCustNo());
		creditVo.setRegIp(request.getRemoteAddr());
		
		if(vipService.insertVipWinnerCredit(creditVo) != 1) {
			throw new RuntimeException("승리 크레딧받기 에러");
		}
		
		return playerCnt*betCredit;
	}
	
	public boolean isCheckCust(HttpSession session,
			HttpServletResponse response) {
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(custVo == null) {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('로그인을 해주세요.');location.replace('/');</script>");
	            out.flush();
	            return false;
			} catch(Exception e) {}
		}
		
		if(custVo.getFamilyCustNo() == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('연동이 안된 소셜아이디는 VIP룸에입장할 수 없습니다. "
	            		+ "원스포츠 아이디로 연동해주세요.');location.replace('/');</script>");
	            out.flush();
	            return false;
			} catch(Exception e) {}
		}
		
		if(vipService.isFamilyCustVip(custVo.getFamilyCustNo()) == false) {
			try {
				response.setContentType("text/html; charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script>alert('VIP만 입장이 가능하다 맨이야');location.replace('/');</script>");
	            out.flush();
	            return false;
			} catch(Exception e) {}
		}
		
		return true;
	}
	
	@RequestMapping(value = { "/vipOpenip"})
    public String vipOpenip(Model model, HttpSession session,
    		@Param("roomNo") String roomNo,
    		@Param("roomName") String roomName,
    		HttpServletResponse response) {
		int creditValuePlus = 100000;
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(isCheckCust(session, response) == true) {
			// 크레딧 10만이상인지 체크
			CreditVO creditVo = new CreditVO(); 
			creditVo.setFamilyCustNo(custVo.getFamilyCustNo());
			creditVo.setCreditValue(creditValuePlus);
			
			if(vipService.isVipCreditValue(creditVo) == false) {
				try {
					response.setContentType("text/html; charset=UTF-8");
		            PrintWriter out = response.getWriter();
		            out.println("<script>alert('얘 배팅할 크레딧이 없단다!');location.replace('/vipEnt');</script>");
		            out.flush();
		            return "/vipEnt";
				} catch(Exception e) {}
			}
			
			if(roomNo == null) {
				roomNo = "0";
			}
			
			if(roomName == null) {
				roomName = "";
			}
			
			model.addAttribute("cust", custVo);
			model.addAttribute("roomNo", roomNo);
			model.addAttribute("roomName", roomName);
			
			
	        return "/viproom/vipOpenip";
		} else {
			return "/";
		}
    }
	
	@RequestMapping(value = { "/vipOpenip/win"})
    public String vipOpenipWin(Model model, HttpSession session,
    		@Param("credit") int credit,
    		HttpServletResponse response) {
		if(isCheckCust(session, response) == false) {
			throw new RuntimeException("VIP만 접근가능");
		}
		
		return "";
    }


	/*
	 * 돈체크
	 * if 돈있으면
	 * 		배팅
	 * else
	 * 		모든돈 배팅(올인또는 0원)
	 * 
	 */
	@RequestMapping(value = { "/vip/insertIPGameRoom/insertBetting"})
	@ResponseBody
    public String vipInsertIPBet(HttpSession session,
    		HttpServletRequest request,
    		HttpServletResponse response,
    		int bettingCredit,
    		String bettingState) {
		int minusCredit = bettingCredit *-1;
		int plusCredit = bettingCredit;
		
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(isCheckCust(session, response) == false) {
			throw new RuntimeException("VIP만 접근가능");
		}
		
		if(bettingState.equals("call")) {
			bettingState = "IPC";
		} else if(bettingState.equals("half")) {
			bettingState = "IPH";
		} else if(bettingState.equals("bbing")) {
			bettingState = "IPB";
		} else if(bettingState.equals("ddadang")) {
			bettingState = "IPD";
		} else if(bettingState.equals("check")) {
			bettingState = "IPE";
		} else if(bettingState.equals("die")) {
			return "";
		} else {
			bettingState = "IPA";
		}
		
		VipVO vipVo = new VipVO();
		CreditVO creditVo = new CreditVO();
		
		vipVo.setFamilyCustNo(custVo.getFamilyCustNo());
		vipVo.setBettingCredit(plusCredit);
		
		creditVo.setFamilyCustNo(custVo.getFamilyCustNo());
		creditVo.setCreditValue(plusCredit);
		creditVo.setCreditState(bettingState);
		creditVo.setRegCustNo(custVo.getCustNo());
		creditVo.setRegIp(request.getRemoteAddr());
		
		// 돈체크
		String allInUse = "N";
		
		if(vipService.isBettingCredit(vipVo) == false) { // 없으면			
			creditVo.setCreditState("IPA");
			
			// 있는돈 다가져오기 (올인)
			allInUse = "Y";
			int totalCredit = vipService.getHaveTotalCredit(custVo.getFamilyCustNo())*-1;
			creditVo.setCreditValue(totalCredit);	
		} else {
			creditVo.setCreditValue(minusCredit);
		}
		
		if(vipService.insertBettingCredit(creditVo) != 1) {
			throw new RuntimeException("배팅하기 실패");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("credit", creditVo.getCreditValue()*-1);
		map.put("allInUseYn", allInUse);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);
		
        return json;
    }
	
	@RequestMapping(value = { "/vipOpenrr/getMyCredit"})
	@ResponseBody
    public int vipGameGetMyCredit(HttpSession session,
    		HttpServletRequest request,
    		HttpServletResponse response) {
		CustVO custVo = (CustVO) session.getAttribute("cust");
		
		if(isCheckCust(session, response) == false) {
			throw new RuntimeException("VIP만 접근가능");
		}
		
		return vipService.getHaveTotalCredit(custVo.getFamilyCustNo());
	}
	
	@RequestMapping(value = { "/vip/indiaPoker/winGamePlayer"})
	@ResponseBody
    public int vipWinIPGamePlayer(HttpSession session,
    		HttpServletRequest request,
    		int betCredit,
    		HttpServletResponse response) {
		if(isCheckCust(session, response) == false) {
			throw new RuntimeException("VIP만 접근가능");
		}
		
		CustVO custVo = (CustVO) session.getAttribute("cust");
		CreditVO creditVo = new CreditVO(); 
		creditVo.setFamilyCustNo(custVo.getFamilyCustNo());
		creditVo.setCreditValue(betCredit);
		creditVo.setCreditState("IPW");
		creditVo.setRegCustNo(custVo.getCustNo());
		creditVo.setRegIp(request.getRemoteAddr());
		
		if(vipService.insertVipWinnerCredit(creditVo) != 1) {
			throw new RuntimeException("승리 크레딧받기 에러");
		}
		
		return betCredit;
	}
}
