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
                .addPathPatterns("/charge/**/")
                .addPathPatterns("/itemShop/**/")
        		.addPathPatterns("/changeCust/**/")
        		.addPathPatterns("/productBuy/**/")
        		.addPathPatterns("/toto/**/")
        		.addPathPatterns("/basketBuy/**/")
        		// 바스켓 페이지는 비회원도 접속해서 장바구니 클릭할수 있으니 페이지만 예외처리
        		.addPathPatterns("/basket")
        		.addPathPatterns("/dashboard/**/")
        		.addPathPatterns("/productBuyList/**/"); 
    }
}
