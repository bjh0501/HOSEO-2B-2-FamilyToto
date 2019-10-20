package com.familytoto.familytotoProject.registerCust.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.login.service.CustLoginService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;
import com.familytoto.familytotoProject.registerCust.domain.ZipcodeVO;
import com.familytoto.familytotoProject.registerCust.service.CaptchaService;
import com.familytoto.familytotoProject.registerCust.service.CustService;
import com.familytoto.familytotoProject.registerCust.service.RegisterCustService;
import com.google.gson.Gson;

@Controller
public class RegisterCustController {
	@Autowired
	RegisterCustService registerCustService;
	
	@Autowired
	CustService custService;
	
	@Autowired
	CustLoginService custLoginService;
	
	@Autowired
	CaptchaService captchaService;
	
	@RequestMapping("/registerCust")
    public String registerCust(HttpServletRequest request, Model model) {
		model.addAttribute("delivery", registerCustService.listDelivery());
        return "loginInfo/registerCust";
    }
	
	@RequestMapping(value = "/registerCust/register", method = RequestMethod.POST)
	@ResponseBody
	@Transactional
	public int insertRegister(@Valid @ModelAttribute RegisterCustVO rcVo, @ModelAttribute CustVO cVo, 
			HttpServletRequest request, HttpServletResponse rep, HttpSession session, BindingResult br) throws Exception {
		int nCaptchaResult = captchaService.isRight(session, request);
		
		rcVo.setRegIp(request.getRemoteAddr());

		Map<String, Object> custDupleId = custService.checkId(cVo);
		Map<String, Object> custDupleNickname = registerCustService.checkNickname(rcVo);
		
		int nResult=0;
		
		if(nCaptchaResult == 0) { // 틀린캡챠
			nResult = -99;
		} else if(custDupleId != null) { // 중복 아이디
			nResult = -98;
		} else if(custDupleNickname != null) { // 중복 닉네임
			nResult = -97;
		} else if(cVo.getCustPassword().length() < 4 || cVo.getCustPassword().length() > 20) {
			nResult = -96;
		} else {
			
			if(rcVo.getFamilyCustCompanyNumber() != null) {
				Pattern p = Pattern.compile("(^[0-9]{3}-[0-9]{2}-[0-9]{4}$)");
		        
		        String inputVal = rcVo.getFamilyCustCompanyNumber();
		        Matcher m = p.matcher(inputVal);
		        
		        if(!m.find()) {
		            return -1;
		        }
			}
				
			if(rcVo.getFamilyCustRecommend() != null && !rcVo.getFamilyCustRecommend().equals("")) {
				CustVO checkVo = new CustVO();
				checkVo.setCustId(rcVo.getFamilyCustRecommend());
				
				Map<String, Object> checkDupleRecommend = registerCustService.checkRecommend(checkVo);
				
				// 추천인의 아이디가 존재하지 않으면 1
				if(checkDupleRecommend != null) {
					RegisterCustVO vo = new RegisterCustVO(); 
					vo.setRegIp(request.getRemoteAddr());
					vo.setFamilyCustNo(Integer.parseInt(checkDupleRecommend.get("familyCustNo").toString()));
					if(registerCustService.insertRecommend(vo) != 1) {
						TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
						return -4;
					}
				} else {
					nResult = 1;
				}
			}

			if(registerCustService.insertRegisterCust(rcVo, request) != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return -2;
			}
			cVo.setFamilyCustNo(rcVo.getFamilyCustNo());
			
			if(custService.insertCust(cVo, request) != 1) {
				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				return -3;
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custId", cVo.getCustId());
		
		custLoginService.login(cVo);
		
		return nResult;
	}
	
	@RequestMapping("/zipcode/getSido")
	@ResponseBody
    public String getListSido(HttpServletRequest request) {
		Gson gson = new Gson();
		String sJson = gson.toJson(registerCustService.listSido());
		
		return sJson;
    }
	
	@RequestMapping("/zipcode/getGugun")
	@ResponseBody
    public String getListGugun(HttpServletRequest request, @RequestParam("sido") String sSido) {
		Gson gson = new Gson();
		String sJson = gson.toJson(registerCustService.listGugun(sSido));
		
		return sJson;
    }
	
	@RequestMapping("/zipcode/getDong")
	@ResponseBody
    public String getListDong(HttpServletRequest request, @ModelAttribute ZipcodeVO vo) {
		Gson gson = new Gson();		
		String sJson = gson.toJson(registerCustService.listDong(vo));
		
		return sJson;
    }
	
	@RequestMapping("/zipcode/getZip")
	@ResponseBody
    public String getListZip(HttpServletRequest request, @ModelAttribute ZipcodeVO vo) {
		Gson gson = new Gson();		
		String sJson = gson.toJson(registerCustService.listZip(vo));
		
		return sJson;
    }
	
	@RequestMapping("/registerCust/checkId")
	@ResponseBody
    public String getCheckId(@ModelAttribute CustVO vo) {
		Map<String,Object> map = registerCustService.getUsedId(vo);
		if(map == null) {
			return "-99";
		} else {
			return map.get("custId").toString();
		}
    }
}