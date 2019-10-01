package com.familytoto.familytotoProject.config;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.familytoto.familytotoProject.registerCust.domain.CustVO;

public class GlobalVariable {
	private static class TIME_MAXIMUM {
		public static final int SEC = 60;
		public static final int MIN = 60;
		public static final int HOUR = 24;
		public static final int DAY = 3;  //30 
		public static final int MONTH = 12;
	}

	public static String formatTimeString(Date tempDate) {
		long curTime = System.currentTimeMillis();
		long regTime = tempDate.getTime();
		long diffTime = (curTime - regTime) / 1000;

		String msg = null;
		if (diffTime < TIME_MAXIMUM.SEC) {
			// sec
			msg = "방금 전";
		} else if ((diffTime /= TIME_MAXIMUM.SEC) < TIME_MAXIMUM.MIN) {
			// min
			msg = diffTime + "분 전";
		} else if ((diffTime /= TIME_MAXIMUM.MIN) < TIME_MAXIMUM.HOUR) {
			// hour
			msg = (diffTime) + "시간 전";
		} else if ((diffTime /= TIME_MAXIMUM.HOUR) < TIME_MAXIMUM.DAY) {
			// day
			msg = (diffTime) + "일 전";
		} else  {
			msg = "출력";
		}

		return msg;
	}
	
	public static int radnomValue(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}
	
	public static int getWinCredit(int creditValue) {
		int nRandomExp = 0;
		
		if(creditValue >= 50001) {
			nRandomExp = GlobalVariable.radnomValue(310, 350);
		} else if(creditValue >= 10001) {
			nRandomExp = GlobalVariable.radnomValue(210, 300);
		} else if(creditValue >= 1000) {
			nRandomExp = GlobalVariable.radnomValue(110, 200);
		}
		
		return nRandomExp;
	}
	
	public static double toTwoFixed(double value) {
		double per = Double.parseDouble(String.format("%.2f",value));
		return per;
	}
}
