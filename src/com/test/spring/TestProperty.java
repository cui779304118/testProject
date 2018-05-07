package com.test.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("jdbc.properties")
public class TestProperty {
	
	@Autowired
	Environment env;
	
	@Value("${datasource.driver}")
	private String driver;
	
	@Value("${datasource.username}")
	private String username;
	
	@Value("${datasource.password}")
	private String password;
	
	@Value("${datasource.url}")
	private String url;
	
	public String getProperty(String paramName){
		return env.getProperty(paramName);
		
	}
	
	public String toString(){
		return "["+ driver + "," + username + "," + password + "," + url+ "]";
	}
}
