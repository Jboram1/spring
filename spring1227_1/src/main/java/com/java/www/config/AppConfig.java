package com.java.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class AppConfig {
	//mybaties객체 생성
	@Bean
	public SqlSessionFactory SqlSessionFactory(DataSource dataSource) throws Exception{
		
		//db연결부분 : mybatis session연결 - application.properties에서 정보를 가져와 db의 dataSource를 가져옴.
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		//mapper연결부분 : query문이 담긴 mapper파일을 모두 가져옴.
		Resource[] res = new PathMatchingResourcePatternResolver().getResources(
				"classpath:/mapper/**/*.xml" //**모든 폴더를 다 확인후 모든xml파일을 가져와라
				);
		sessionFactory.setMapperLocations(res);
		
		return sessionFactory.getObject();  //mybatis db+mapper정보가 들어가 있는 모든 객체
	}//
	
	//mybaties객체 1개를 리턴
	@Bean
	public SqlSessionTemplate sqlsession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory); //mybatis에 사용할 1개 객체를 가져옴.
	}
	
	
	
	
}
