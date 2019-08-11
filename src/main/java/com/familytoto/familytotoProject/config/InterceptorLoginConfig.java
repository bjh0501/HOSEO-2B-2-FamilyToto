package com.familytoto.familytotoProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.familytoto.familytotoProject.config.interceptor.CertificationInterceptor;

@Configuration
public class InterceptorLoginConfig extends WebMvcConfigurerAdapter{
	 /*
     * 로그인 인증 Interceptor 설정
     * */
    @Autowired
    CertificationInterceptor certificationInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(certificationInterceptor)
                .addPathPatterns("/**/charge")
                .addPathPatterns("/**/itemShop")
        		.addPathPatterns("/**/changeCust");
    }
}
