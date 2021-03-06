package com.familytoto.familytotoProject.dashboard.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.familytoto.familytotoProject.charge.dao.ChargeDao;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.config.GlobalVariable;
import com.familytoto.familytotoProject.creditShop.domain.ProductVO;
import com.familytoto.familytotoProject.dashboard.service.DashboardService;
import com.familytoto.familytotoProject.exp.service.ExpService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.scheduler.dao.SportsTotoSchedulerDao;
import com.familytoto.familytotoProject.scheduler.serivce.SportsTotoSchedulerService;
import com.familytoto.familytotoProject.toto.domain.SportsBettingVO;
import com.google.gson.Gson;

@Controller
public class DashboardController {
	@Autowired
	DashboardService dashboardService;

	@Autowired
	SportsTotoSchedulerDao sportsTotoSchedulerDao;

	@Autowired
	ExpService expService;

	@Autowired
	ChargeDao chargeDao;
	
	@Autowired
	SportsTotoSchedulerService sportsTotoSchedulerService;

	@RequestMapping("/dashboard")
	public ModelAndView dashboard(HttpSession session, ModelAndView mv, HttpServletResponse response) {
		CustVO vo = (CustVO) session.getAttribute("cust");

		if (vo.getFamilyCustNo() == 0) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('연동이 안된 소셜아이디는 대시보드를 볼 수 없습니다. "
						+ "원스포츠 아이디로 연동해주세요.');location.replace('/');</script>");
				out.flush();
				mv.setViewName("/");
				return mv;
			} catch (Exception e) {
			}
		}

		if (vo.getFamilyCustCompanyNumber() != null) {
			mv.addObject("productList", dashboardService.listRegisteredProduct(vo.getFamilyCustNo()));
		}

		mv.addObject("list", dashboardService.getDefaultInfo(vo.getFamilyCustNo()));
		
		if(vo.getCustOperatorGubun() != null && vo.getCustOperatorGubun().equals("Y")) {
			mv.addObject("admin", "Y");
		} else {
			mv.addObject("admin", "N");
		}
		
		mv.addObject("totoBetList", dashboardService.listBettingGroup(vo.getFamilyCustNo()));
		mv.setViewName("/loginInfo/dashboard");

		return mv;
	}

	@RequestMapping("/dashboard/getCredit")
	@ResponseBody
	public String getCredit(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listRecentCredit(cVo.getFamilyCustNo()));

		return json;
	}

	@RequestMapping("/dashboard/getMileage")
	@ResponseBody
	public String getMileage(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listRecentMileage(cVo.getFamilyCustNo()));

		return json;
	}

	@RequestMapping("/dashboard/getExp")
	@ResponseBody
	public String getExp(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listRecentExp(cVo.getFamilyCustNo()));

		return json;
	}

	@RequestMapping("/dashboard/getCreditTable")
	@ResponseBody
	public String getCreditTable(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listCreditTable(cVo.getFamilyCustNo()));

		return json;
	}

	@RequestMapping("/dashboard/getMileageTable")
	@ResponseBody
	public String getMileageTable(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listMileageTable(cVo.getFamilyCustNo()));

		return json;
	}

	@RequestMapping("/dashboard/getExpTable")
	@ResponseBody
	public String getExpTable(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listExpTable(cVo.getFamilyCustNo()));

		return json;
	}

	@RequestMapping("/dashboard/getRegisteredBoard")
	@ResponseBody
	public String getRegisteredBoard(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listRegisteredBoard(cVo.getFamilyCustNo()));

		return json;
	}

	@RequestMapping("/dashboard/getRegisteredComment")
	@ResponseBody
	public String getRegisteredComment(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listRegisteredComment(cVo.getFamilyCustNo()));

		return json;
	}

	@RequestMapping("/dashboard/getRegisteredProductComment")
	@ResponseBody
	public String getRegisteredProductComment(HttpSession session) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listRegisteredProductComment(cVo.getFamilyCustNo()));

		return json;
	}

	@RequestMapping("/dashboard/listShowBettingTotoMatch/{sportsBetingGroupNo}")
	@ResponseBody
	public String listShowBettingTotoMatch(HttpSession session,
			@PathVariable("sportsBetingGroupNo") int sportsBetingGroupNo) {
		CustVO cVo = (CustVO) session.getAttribute("cust");

		SportsBettingVO sbVo = new SportsBettingVO();
		sbVo.setSportsBettingGroupNo(sportsBetingGroupNo);
		sbVo.setFamilyCustNo(cVo.getFamilyCustNo());

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listShowBettingTotoMatch(sbVo));

		return json;
	}

	/*
	 * 
	 * 사용자가 선택한 경기날짜 < 해당경기 결과있는지 체크 사용자가 선택한 답과 경기 결과 비교 같다면 db에넣은 크레딧ID의 value *
	 * 배당 경험치 추가
	 * 
	 * 
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("/dashboard/resultToto")
	public int sportsResult(HttpSession session, HttpServletRequest request, int sportsBettingGroupNo) {
		SportsBettingVO sbVo = new SportsBettingVO();
		CustVO cVo = (CustVO) session.getAttribute("cust");

		sbVo.setFamilyCustNo(cVo.getFamilyCustNo());
		sbVo.setSportsBettingGroupNo(sportsBettingGroupNo);

		List<SportsBettingVO> list = sportsTotoSchedulerDao.isSportsTotoWin(sbVo);

		// 예외처리
		for (SportsBettingVO vo : list) {
			if (vo.getSportsResult() == null) { // 결과 아직 안나옴
				return -99;
			}

			// 0이 1개라도 포함된경우
			// 배팅 lose
			if (vo.getBettingGroupResult().equals("0")) {
				sbVo.setBettingGroupResult("L");

				if (sportsTotoSchedulerDao.updateCustSportsTotoResult(sbVo) != 1) {
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return -1;
				}

				return 1;
			}
		}

		// 이긴경우
		sbVo.setBettingGroupResult("W");
		if (sportsTotoSchedulerDao.updateCustSportsTotoResult(sbVo) != 1) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -2;
		}

		CreditVO creVo = new CreditVO();

		// 계산하기, 같다면 db에넣은 크레딧ID의 value * 배당
		int operateCreditValue = sportsTotoSchedulerDao.getCreditValueByBettingGroup(sbVo);

		creVo.setCreditValue(operateCreditValue); // 크레딧 세팅
		creVo.setCreditState("STG"); // 상태값
		creVo.setRegCustNo(cVo.getCustNo()); // 넣기
		creVo.setRegIp(request.getRemoteAddr()); // 넣기
		creVo.setFamilyCustNo(cVo.getFamilyCustNo()); // 넣기

		if (chargeDao.doCharge(creVo) != 1) { // 크레딧 추가
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -3;
		}

		/*
		 * 1개 200 2개 500 3~4개 900~1200 5~6개 1500~2500 7개~ 3500~5000
		 */

		int nRightCnt = list.size();
		int nExpValue = 0;

		if (nRightCnt == 1) {
			nExpValue = 200;
		} else if (nRightCnt == 2) {
			nExpValue = 500;
		} else if (nRightCnt >= 3 && nRightCnt <= 4) {
			nExpValue = GlobalVariable.radnomValue(900, 1200);
		} else if (nRightCnt >= 5 && nRightCnt <= 6) {
			nExpValue = GlobalVariable.radnomValue(1500, 2500);
		} else {
			nExpValue = GlobalVariable.radnomValue(3500, 5000);
		}

		if (expService.insertExp(cVo, "STW", nExpValue, request) != 1) { // 경험치 추가
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return -4;
		}

		return 0;
	}
	
	@RequestMapping("/dashboard/getSellProductDetailList")
	@ResponseBody
	public String getSellProductDetailList(HttpSession session,
			int productNo) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
		ProductVO productVo = new ProductVO();
		productVo.setProductNo(productNo);
		productVo.setFamilyCustNo(cVo.getFamilyCustNo());

		Gson gson = new Gson();
		String json = gson.toJson(dashboardService.listSellCnt(productVo));

		return json;
	}
	
	 @GetMapping("/dashboard/download.xls")
	 public String getExcelByExt(Model model, HttpSession session) {
	 	CustVO cVo = (CustVO) session.getAttribute("cust");
	 	
	 	if(cVo == null || cVo.getFamilyCustNo() == 0) {
	 		throw new RuntimeException("패밀리 회원만 엑셀다운 가능");
	 	}
	 	
	 	Map<String, ?> map = dashboardService.getDefaultInfo(cVo.getFamilyCustNo());
	 	
	 	model.addAttribute("haveCredit", map.get("credit"));
	 	model.addAttribute("haveMileage", map.get("mileage"));
	 	model.addAttribute("haveLevel", map.get("level"));
	 	model.addAttribute("haveExp", map.get("exp"));
	 	model.addAttribute("haveBoard", map.get("board"));
	 	model.addAttribute("haveComment", map.get("comment"));
	 	model.addAttribute("haveProductComment", map.get("productComment"));
	 	model.addAttribute("nickname", cVo.getFamilyCustNick());
	 	model.addAttribute("creditInfo", dashboardService.listCreditTable(cVo.getFamilyCustNo()));
	 	model.addAttribute("mileageInfo", dashboardService.listMileageTable(cVo.getFamilyCustNo()));
	 	model.addAttribute("expInfo", dashboardService.listExpTable(cVo.getFamilyCustNo()));
	 	
	 	return "statXls";
	 }

	@RequestMapping("/dashboard/schedularToto")
	@ResponseBody
	public int schedularToto(HttpSession session,
			String year,
			String month,
			HttpServletRequest request) {
		CustVO cVo = (CustVO) session.getAttribute("cust");
	 	
	 	if(cVo == null || !cVo.getCustOperatorGubun().equals("Y")) {
	 		throw new RuntimeException("운영자만 갱신가능");
	 	}

	 	sportsTotoSchedulerService.inSoccer("1", year, month, request, cVo);
	 	sportsTotoSchedulerService.inSoccer("2", year, month, request, cVo);
	 	
	 	return 0;
	}	 
}