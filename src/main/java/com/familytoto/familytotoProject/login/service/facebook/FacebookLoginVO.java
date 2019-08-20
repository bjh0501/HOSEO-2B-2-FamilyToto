package com.familytoto.familytotoProject.login.service.facebook;

import org.springframework.stereotype.Component;

@Component
public class FacebookLoginVO {
	final String APP_KEY =  "406668109960506";
	final String APP_SECRET =  "c0d30efc5f209311e2ddb05c985d1818";
	
	final String DOMAIN =  "http://test.onesports.ga:80";
	
	public String getLoginLink() {
		return "http://www.facebook.com/dialog/oauth?client_id=" +
			APP_KEY+"&redirect_uri=" + DOMAIN + "/login/social/facebook&scope=publish_stream,offline_access";
	}
}
