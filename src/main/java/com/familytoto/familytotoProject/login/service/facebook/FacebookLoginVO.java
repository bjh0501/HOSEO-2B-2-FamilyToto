package com.familytoto.familytotoProject.login.service.facebook;

import org.springframework.stereotype.Component;

import com.familytoto.familytotoProject.config.SecretGlobalVariable;

@Component
public class FacebookLoginVO {
	private final String APP_KEY =  SecretGlobalVariable.FACEBOOK_APP_KEY;
	private final String APP_SECRET =  SecretGlobalVariable.FACEBOOK_APP_SECRET;
	
	public String getLoginLink() {
		return "http://www.facebook.com/dialog/oauth?client_id=" +
			APP_KEY+"&redirect_uri=" + SecretGlobalVariable.DOMAIN_URL + "/login/social/facebook&scope=publish_stream,offline_access";
	}
}
