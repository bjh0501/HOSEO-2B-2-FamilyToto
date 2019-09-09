package com.familytoto.familytotoProject.scheduler.serivce;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytoto.familytotoProject.scheduler.dao.SportsTotoSchedulerDao;
import com.familytoto.familytotoProject.scheduler.domain.TotoSportsVO;

@Service
public class SportsTotoSchedulerServiceImpl implements SportsTotoSchedulerService {
	@Autowired
	SportsTotoSchedulerDao sportsTotoSchedulerDao;

	public void inSoccer() {
		TotoSportsVO vo = new TotoSportsVO();
		String league = "1";	// 1 or 2
		String year = "2019";
		String month = "09";
		
		if(league.equals("1")) {
			vo.setSportsLeagueName("K리그1");
		} else {
			vo.setSportsLeagueName("K리그2");
		}
		try {
			String URL = "http://kleague.com/schedule/get_lists?datatype=html&select_league=" + league
					+ "&select_league_year=" + year
					+ "&month=" + month;
			Document doc;
		
			doc = Jsoup.connect(URL).get();
		
			Elements matchInfo = doc.select(".data-body .table"); //첫 번쨰 팀이름
			
			for(Element e: matchInfo) {
				String time = e.select("th[scope=\"col\"]").text().replaceAll("\\s.*", "");
				Elements tbody = e.select(".team-match");
				
				for(Element e2: tbody) {
					String team1 = e2.select(".team-1 .club").text();
					String team2 = e2.select(".team-2 .club").text();
					String score = e2.select(".score").text();
					
					vo.setSportsGubun("ISO");
					
					vo.setSportsTeam1(team1);
					vo.setSportsTeam2(team2);
					vo.setSportsWinBet(1.01);
					vo.setSportsLoseBet(2.03);
					vo.setSportsDrawBet(4.99);
					vo.setRegCustNo(100000001);
					vo.setRegIp("127.0.0.1");
					
					if(score.length() >= 5) {
						String timeM = score;
						String fullTime = time + " " + timeM;
						Timestamp convertTime = convertStringToTimestamp(fullTime);
						
						System.out.println(team1 + "=" + team2 + "==" + fullTime);
						
						vo.setSportsSchedule(convertTime);
						sportsTotoSchedulerDao.inSoccer(vo);
					} else {
						System.out.println(team1 + "=" + team2 + "==" + score + "==" + time);
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
