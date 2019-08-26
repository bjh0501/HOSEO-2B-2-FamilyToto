package com.familytoto.familytotoProject.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class MybatisConfig {
	
	 @Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		Properties mybatisProperties = new Properties();
		mybatisProperties.setProperty("mapUnderscoreToCamelCase", "true");
		sqlSessionFactoryBean.setConfigurationProperties(mybatisProperties);
		sqlSessionFactoryBean.setVfs(SpringBootVFS.class);
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.familytoto.familytotoProject.registerCust.domain");
		sqlSessionFactoryBean.setTypeAliasesPackage("com.familytoto.familytotoProject.login.domain");
		sqlSessionFactoryBean.setTypeAliasesPackage("com.familytoto.familytotoProject.qna.domain");
		
		// 이건 매핑이안댄다.
		sqlSessionFactoryBean.setTypeAliasesPackage("com.familytoto.familytotoProject.comment.domain");
		
		
		// 보드 도메인이 맨아래여야 에러가안난다.. 무슨 문제있는듯
		sqlSessionFactoryBean.setTypeAliasesPackage("com.familytoto.familytotoProject.board.domain");
		

		
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	
}
