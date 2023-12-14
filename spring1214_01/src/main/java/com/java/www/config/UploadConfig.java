package com.java.www.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //ioc컨테이너에 넣어줌, 뉴객체를 자동으로 만들어줌
public class UploadConfig implements WebMvcConfigurer{
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:///c:/upload/");
		//("/upload/**") 이형태로 들어오면 ("file:///c:/upload/") c드라이브에 있는 업로드에서 파일을 찾아줘
		
	}
}
