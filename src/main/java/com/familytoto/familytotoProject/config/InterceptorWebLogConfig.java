package com.familytoto.familytotoProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.familytoto.familytotoProject.config.interceptor.WebLogInterceptor;

@Configuration
public class InterceptorWebLogConfig extends WebMvcConfigurerAdapter{
	 /*
     * 로그인 인증 Interceptor 설정
     * */
    @Autowired
    WebLogInterceptor webLogInterceptor;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webLogInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**/")
                .excludePathPatterns("/daumeeditor/**/")
                .excludePathPatterns("/img/**/")
                .excludePathPatterns("/js/**/")
                .excludePathPatterns("/scss/**/")
                .excludePathPatterns("/vendor/**/");
    }
}

