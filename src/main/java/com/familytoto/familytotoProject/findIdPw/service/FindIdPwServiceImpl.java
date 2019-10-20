package com.familytoto.familytotoProject.findIdPw.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.findIdPw.dao.FindIdPwDao;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.registerCust.domain.RegisterCustVO;

@Service
public class FindIdPwServiceImpl implements FindIdPwService {
	@Autowired
	FindIdPwDao findIdPwDao;
	
	@Autowired
	EmailService emailService;

	@Override
	public List<Map<String, Object>> findId(RegisterCustVO vo) {
		return findIdPwDao.findId(vo);
	}

	@Override
	public int findPassword(RegisterCustVO rcVo, CustVO cVo) {
		Map<String, Object> map = findIdPwDao.findPassword(cVo.getCustId(), rcVo.getFamilyCustEmail());
		
		if(map != null) {
	        String uuid = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다. 
	        uuid = uuid.substring(0, 10); //uuid를 앞에서부터 10자리 잘라줌.
			
			String sTempPassword = cVo.toEncodePassword(uuid);
			
			// 이메일에 묶인 모든 아디비번 업뎃 체크
			if(setPassword(map.get("custId").toString(), sTempPassword) >= 1) {	
				String contents = "";
				
				contents += "안녕하세요 OneSports입니다.<br>";
				contents += "<br>";
				contents += "<b>"+cVo.getCustId() + "</b>님의 임시 비밀번호는 아래와 같습니다.";
				contents += "<br>";
				contents += "임시비밀번호 : <b>" + uuid + "</b><br>";
				contents += "<br>";
				contents += "발송된 임시 비밀번호는 보안을 위해 꼭 홈페이지에 접속하셔서 로그인 후 비밀번호를 변경해주시기 바랍니다.";
				contents += "<br>";
				contents += "1. <a href='https://onesports.ga' target='_blank'>OneSports홈페이지</a>접속<br>";
				contents += "2. 아이디/임시 비밀번호 입력 후 로그인<br>";
				contents += "3. 내정보보기 > 비밀번호 변경";
				contents += "<br>";
				contents += "<br>";
				contents += "해당 메일은 발신전용 메일이오니 문의는 홈페이지 내에서 이용해주시기 바랍니다.";
				contents += "<br>";
				contents += "<br>";
				
				emailService.sendEmail(map.get("familyCustEmail").toString(), "[원 스포츠] 임시 비밀번호 발급", contents);
			}
		} else { // 존재하지않는 정보
			return -99;
		}
		
		return 0;
	}

	@Override
	public int setPassword(String sId, String sPassword) {
		return findIdPwDao.setPassword(sId, sPassword);
	}
}
