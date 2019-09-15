package com.familytoto.familytotoProject.login.service.facebook;

import org.springframework.stereotype.Component;

import com.familytoto.familytotoProject.config.SecretGlobalVariable;

@Component
public class FacebookLoginVO {
	private final String APP_KEY =  "380846455933511";
	private final String APP_SECRET =  "2a85a2889a2d89a6524aa41975ad5402";
	
	public String getLoginLink() {
		return "http://www.facebook.com/dialog/oauth?client_id=" +
			APP_KEY+"&redirect_uri=" + SecretGlobalVariable.DOMAIN_URL + "/login/social/facebook&scope=publish_stream,offline_access";
	}
}
