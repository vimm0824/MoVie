package com.MoVie.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.MoVie.common.FileManagerService;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
		.addResourceHandler("/images/**")		
		.addResourceLocations("file://" + FileManagerService.FILE_UPLOAD_PATH);	// 실제 파일 위치 맥은/두개 윈도우는/세개
	}
}
