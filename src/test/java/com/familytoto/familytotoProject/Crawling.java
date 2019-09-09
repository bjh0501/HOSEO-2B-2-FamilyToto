package com.familytoto.familytotoProject;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class Crawling {
	@Test
	public void inSoccer() throws IOException {
		String URL = "http://kleague.com/schedule/get_lists?datatype=html&select_league=1&select_league_year=2019&month=09";
		Document doc = Jsoup.connect(URL).get();
		Elements matchInfo = doc.select(".data-body .table"); //첫 번쨰 팀이름
		
		for(Element e: matchInfo) {
			String time = e.select("th[scope=\"col\"]").text().replaceAll("\\s.*", "");
			Elements tbody = e.select(".team-match");
			
			for(Element e2: tbody) {
				String team1 = e2.select(".team-1 .club").text();
				String team2 = e2.select(".team-2 .club").text();
				String score = e2.select(".score").text();
				
				if(score.length() >= 5) {
					System.out.println(team1 + "=" + team2 + "==" + time+ " " + score);					
				} else {
					System.out.println(team1 + "=" + team2 + "==" + score + "==" + time);
				}
				
			}
			
		}
	}
	
	@Test
	public void ExSoccer() throws IOException {
		String URL = "http://kleague.com/schedule/get_lists?datatype=html&select_league=1&select_league_year=2019&month=09";
		Document doc = Jsoup.connect(URL).get();
		Elements matchInfo = doc.select(".data-body .table"); //첫 번쨰 팀이름
		
		for(Element e: matchInfo) {
			String time = e.select("th[scope=\"col\"]").text().replaceAll("\\s.*", "");
			Elements tbody = e.select(".team-match");
			
			for(Element e2: tbody) {
				String team1 = e2.select(".team-1 .club").text();
				String team2 = e2.select(".team-2 .club").text();
				String score = e2.select(".score").text();
				
				if(score.length() >= 5) {
					System.out.println(team1 + "=" + team2 + "==" + time+ " " + score);					
				} else {
					System.out.println(team1 + "=" + team2 + "==" + score + "==" + time);
				}
				
			}
			
		}
	}
}
