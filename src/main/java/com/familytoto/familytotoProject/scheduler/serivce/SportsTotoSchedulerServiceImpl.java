package com.familytoto.familytotoProject.scheduler.serivce;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.charge.dao.ChargeDao;
import com.familytoto.familytotoProject.config.GlobalVariable;
import com.familytoto.familytotoProject.exp.service.ExpService;
import com.familytoto.familytotoProject.registerCust.domain.CustVO;
import com.familytoto.familytotoProject.scheduler.dao.SportsTotoSchedulerDao;
import com.familytoto.familytotoProject.scheduler.domain.TotoSportsVO;

/**
 * @version 	1.00 2019년 10월 2일
 * @author		jiho 
 */
@Service
public class SportsTotoSchedulerServiceImpl implements SportsTotoSchedulerService {
	@Autowired
	SportsTotoSchedulerDao sportsTotoSchedulerDao;

	@Autowired
	ChargeDao chargeDao;
	
	@Autowired
	ExpService expService;
	
	public void inSoccer(String gubun, String matchYear,
			String matchMonth,
			HttpServletRequest request,
			CustVO custVo) {
		TotoSportsVO vo = new TotoSportsVO();
		String league = gubun; // 1 or 2
		String year = matchYear;
		String month = matchMonth;
		
		Random random = new Random();

		if (league.equals("1")) {
			vo.setSportsLeagueName("K리그1");
		} else {
			vo.setSportsLeagueName("K리그2");
		}

		try {
			String URL = "http://kleague.com/schedule/get_lists?datatype=html&select_league=" + league
					+ "&select_league_year=" + year + "&month=" + month;
			Document doc;

			doc = Jsoup.connect(URL).get();

			Elements matchInfo = doc.select(".data-body .table"); // 첫 번쨰 팀이름

			for (Element e : matchInfo) {
				String time = e.select("th[scope=\"col\"]").text().replaceAll("\\s.*", "");
				Elements tbody = e.select(".team-match");

				for (Element e2 : tbody) {
					String team1 = e2.select(".team-1 .club").text();
					String team2 = e2.select(".team-2 .club").text();
					String score = e2.select(".score").text();

					vo.setSportsGubun("ISO");

					vo.setSportsTeam1Name(team1);
					vo.setSportsTeam2Name(team2);
					
					vo.setSportsTeam1Bet(GlobalVariable.toTwoFixed(GlobalVariable.radnomValue(1, 3)
							+ random.nextDouble()));
					
					vo.setSportsTeam2Bet(GlobalVariable.toTwoFixed(GlobalVariable.radnomValue(1, 3)
							+ random.nextDouble()));
					
					vo.setSportsDrawBet(GlobalVariable.toTwoFixed(GlobalVariable.radnomValue(2, 4)
							+ random.nextDouble()));
					
					vo.setRegCustNo(custVo.getCustNo());
					vo.setRegIp(request.getRemoteAddr());

					if (score.length() >= 5) { // 추가
						String timeM = score;
						String fullTime = time + " " + timeM;
						Timestamp convertTime = convertStringToTimestamp(fullTime);

						//System.out.println(team1 + "=" + team2 + "==" + fullTime);

						vo.setSportsSchedule(convertTime);

						if (sportsTotoSchedulerDao.inSoccer(vo) != 1) {
							throw new RuntimeException("국내축구 추가에러"); 
						}
					} else { // 업데이트
						//System.out.println(team1 + "=" + team2 + "==" + score + "==" + time);

						String sScore[] = score.split(":");
						String sSportsResult = "";

						int nScore1 = Integer.parseInt(sScore[0]);
						int nScore2 = Integer.parseInt(sScore[1]);

						if (nScore1 > nScore2) {
							sSportsResult = "1";
						} else if (nScore1 < nScore2) {
							sSportsResult = "2";
						} else {
							sSportsResult = "0";
						}

						vo.setSportsScore1(nScore1);
						vo.setSportsScore2(nScore2);
						vo.setSportsResult(sSportsResult);
						vo.setSportsGubun("ISO");
						// vo.setSportsLeagueName(""); // 위에서선언함
						vo.setSportsTeam1Name(team1);
						vo.setSportsTeam2Name(team2);
						
						try {
						    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
						    Date parsedDate = dateFormat.parse(time);
						    Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
						    vo.setSportsSchedule(timestamp);
						} catch(Exception e21) { //this generic but you can control another types of exception
						    // look the origin of excption 
						}
						
						sportsTotoSchedulerDao.updateSportsToto(vo);
					}

				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static Timestamp convertStringToTimestamp(String strDate) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm");
			// you can change format of date
			Date date = formatter.parse(strDate);
			Timestamp timeStampDate = new Timestamp(date.getTime());

			return timeStampDate;
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
			return null;
		}
	}
}
