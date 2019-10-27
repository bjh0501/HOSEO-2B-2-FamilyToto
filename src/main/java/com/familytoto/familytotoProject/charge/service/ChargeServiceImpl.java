package com.familytoto.familytotoProject.charge.service;

import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.charge.dao.ChargeDao;
import com.familytoto.familytotoProject.charge.domain.CreditVO;
import com.familytoto.familytotoProject.findIdPw.service.EmailService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;

@Service
public class ChargeServiceImpl implements ChargeService {
	@Autowired
	ChargeDao chargeDao; 
	
	@Autowired
	EmailService emailService;
	
	@Override
	public Map<String, Object> getCreditInfo(CustVO vo) {
		return chargeDao.getCreditInfo(vo);
	}

	@Override
	public int doCharge(CreditVO vo) {
		// 오늘 충전한거 갯수 예외처리하기
		
		if(vo.getCreditState().equals("FRE")) {
			Map<String, Object> map = chargeDao.todayCharge(vo);
			
			//  하루에 최고 5번충전가능
			if(Integer.parseInt(map.get("todayChargeCnt").toString()) >= 5) {
				return -98;			
			} else if( chargeDao.getCurrentCredit(vo.getFamilyCustNo()) >= 5000) { // 5천크레딧 미만이여야 충전가능 
				return -97;
			}
		} else {
			if(chargeDao.isCardChargeCheck(vo.getFamilyCustNo()) == true) {
				return -96;
			}
		}
		
		return chargeDao.doCharge(vo);
	}

	@Override
	public void sendHistoryEmail(String to, int credit, String gubun, String nickname) {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy년 MM월dd일 HH시mm분ss초");
		String format_time1 = format1.format (System.currentTimeMillis());
		
		String sTitle = "[원스포츠] 충전내역안내";
		String sContents = "";
		
		sContents += "안녕하세요. OneSports입니다.<br>";
		sContents += "<br>";
		sContents += "회원(" + nickname + ")님이 충전하신 내역을 안내해드립니다.<br>";
		sContents += "<br>";
		sContents += "<h2>OneSports 충전 내역</h2><br>";
		sContents += "<table border=1>";
		sContents += "	<tr bgcolor='gray'>";
		sContents += "		<th>충전일시</th>";
		sContents += "		<th>충전금액</th>";
		sContents += "		<th>결제방식</th>";
		sContents += "	</tr>";
		sContents += "	<tr>";
		sContents += "		<td>" + format_time1 + "</td>";
		sContents += "		<td>" + credit + "</td>";
		sContents += "		<td>" + gubun + "</td>";
		sContents += "	</tr>";
		sContents += "</table><br><br>";
		
		sContents += "크레딧 환불은 절대 안됩니다.<br><br>";
		sContents += "충전, 환불, 구매 등의 대한 약관은 충전페이지 내에서 확인해 주시길바랍니다.<br><br>";
		sContents += "회사와 회원간 권리, 의무에 관한 문제가 발생한 경우, 관련 법령이 정한 절차에 따릅니다.<br><br>";
		sContents += "해당메일은 발신전용 메일이오니 문의는 홈페이지 내에서 이용해주시기 바랍니다.<br><br>";
		
		emailService.sendEmail(to, sTitle, sContents);
	}

	@Override
	public int getCurrentCredit(int familyCustNo) {
		return chargeDao.getCurrentCredit(familyCustNo);
	}

	@Override
	public CreditVO getCardInfo(int familyCustNo) {
		return chargeDao.getCardInfo(familyCustNo);
	}
	
}
