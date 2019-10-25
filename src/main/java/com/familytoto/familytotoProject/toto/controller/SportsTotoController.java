package com.familytoto.familytotoProject.toto.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.charge.dao.ChargeDao;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.config.GlobalVariable;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.toto.dao.CommonDao;
import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;
import com.familytoto.familytotoProject.toto.domain.SportsVO;
import com.familytoto.familytotoProject.toto.service.SportsTotoService;

@Controller
public class SportsTotoController {
	@Autowired
	SportsTotoService sportsTotoService; 
	
	@Autowired
	CommonDao commonDao;
	
	@Autowired
	ChargeDao chargeDao;
	
	@RequestMapping("/toto/sports/soccer")
	public ModelAndView goSportSoccer(HttpSession session,
			ModelAndView mv,
			HttpServletResponse response) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		cVo.getCustNo();
		
		if(cVo.getFamilyCustNo() == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('연동이 안된 소셜아이디는 토토를 할 수 없습니다. "
						+ "원스포츠 아이디로 연동해주세요.');location.replace('/');</script>");
				out.flush();
				
				mv.setViewName("/");
				
				return mv;
			} catch(Exception e) {}
		}
		
		mv.addObject("totoList", sportsTotoService.listSportsTotoInnerSoccer(cVo.getFamilyCustNo()));		
		mv.addObject("cust", cVo);
		mv.addObject("boughtList", sportsTotoService.listBoughtSportsNo(cVo.getFamilyCustNo()));
		mv.setViewName("/toto/sports/soccer");
		return mv;
	}
	
	
	/**
	 * 
	 * 
	 * for --
	 * 	돈 확인
	 * 	돈 소모 / 크레딧id get(배열)
	 * --
	 * 
	 * 
	 *  베팅그룹 insert
	 * 
	 * for --
	 *  
	 *  종목베팅 배당값get
	 *  해당종목 배팅했는지 체크
	 *  해당종목이 시작전 30분인지 체크
	 *   크레딧id get
	 *  배팅그룹 get
	 *  
	 *  베팅 insert
	 * --
	 * 
	 * 베팅그룹 update (전체 배당)
	 * 
	 * 
	 */
	@Transactional
	@RequestMapping("/toto/sports/soccer/betting")
	@ResponseBody
	public int choiceBetting(HttpServletRequest request,
			HttpSession session,
			@ModelAttribute @Valid SportsBettingVO vo,
			@ModelAttribute @Valid CreditVO creVo,
			int nSportsNo[],
			int nChoiceTeam[]) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		
		if(creVo.getCreditValue() > 100000 || creVo.getCreditValue() < 1000) {
			return -1;
		}
		
		vo.setFamilyCustNo(cVo.getFamilyCustNo());
		vo.setRegCustNo(cVo.getCustNo());
		vo.setRegIp(request.getRemoteAddr());
		
		creVo.setRegIp(request.getRemoteAddr());
		creVo.setRegCustNo(cVo.getCustNo());
		creVo.setFamilyCustNo(cVo.getFamilyCustNo());
		creVo.setCreditState("ISB"); // inner soccer bet
		
		double betValue = 1.0;
		
		if(commonDao.isHaveCredit(creVo) == false) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -2;
		}
		
		
		creVo.setCreditValue(creVo.getCreditValue() * -1); // 크레딧 -로
		if(chargeDao.doCharge(creVo) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -3;
		}
		
		vo.setCreditId(creVo.getCreditId());
		
		// 배팅그룹 insert
		if(sportsTotoService.insertSportsBettingGroup(vo) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -4;
		}
		
		int betGroupId = vo.getSportsBettingGroupNo();
		
		SportsVO sVo = new SportsVO();
		
		for(int i = 0; i < nSportsNo.length; i++) {
			sVo.setSportsNo(nSportsNo[i]);
			sVo.setSportsTeam1Bet(nChoiceTeam[i]);
			
			// 종목배팅 배당값get
			double matchSportsBet = sportsTotoService.getSportsMatchBet(sVo);// 배팅 얻기 1 or 2
			
			if(matchSportsBet < 1.00) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return -5;
			}
			
			betValue *= matchSportsBet; 
			
			vo.setSportsNo(nSportsNo[i]);
			
			// 해당 종복 배팅했는지 체크
			if(sportsTotoService.isDupleBet(vo) == true) {
				throw new RuntimeException("중복 배팅했습니다.");
			}
			
			// 시작전 n이상인지 체크 시작 n분전이면 배팅실패
			if(sportsTotoService.isCanSportsTotoSChedule(vo) == false) {
				throw new RuntimeException("너무 늦게 배팅했습니다.");
			}
			
			vo.setSportsBettingGroupNo(betGroupId);
			vo.setBettingTeamChoice(nChoiceTeam[i]); // 1 or 2 ㅇ임시 처리
			vo.setBettingBet(matchSportsBet);
			
			if(sportsTotoService.insertSportsBetting(vo) != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return -7;
			}
		}
		
		// 배팅그룹 업데이트
		vo.setSportsBettingGroupNo(betGroupId);
		vo.setBettingGroupBet(GlobalVariable.toTwoFixed(betValue));
		
		if(sportsTotoService.updateSportsGrpSumBet(vo) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -8;
		}
		
		return 0;
	}
}
