package com.familytoto.familytotoProject.findIdPw.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.familytoto.familytotoProject.findIdPw.service.FindIdPwService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Controller
public class FindIdPwController {
	
	@Autowired
	FindIdPwService findIdPwService;
	
	@RequestMapping("findID_PW")
    public String findId_Pw() {
        return "loginInfo/findID_PW";
    }
	
	@RequestMapping("findIdList")
	@ResponseBody
	public List<Map<String, Object>> sendId(@ModelAttribute RegisterCustVO vo) {
		return findIdPwService.findId(vo);
	}
	
	@RequestMapping("findPw")
	@ResponseBody
	public int findPw(@ModelAttribute RegisterCustVO rcVo,@ModelAttribute CustVO cVo) {
		return findIdPwService.findPassword(rcVo, cVo);
	}
}
