package com.demo.codetest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CustomCorsConfiguration {

	 @Bean
	    public CorsFilter corsFilter() {
	        CorsConfiguration corsConfig = new CorsConfiguration();
	        corsConfig.addAllowedOriginPattern("*"); 
	        corsConfig.addAllowedHeader("*");
	        corsConfig.addAllowedMethod("*");
	        corsConfig.setAllowCredentials(true); // Allow cookies if needed

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", corsConfig);

	        return new CorsFilter(source);
	    }
}
