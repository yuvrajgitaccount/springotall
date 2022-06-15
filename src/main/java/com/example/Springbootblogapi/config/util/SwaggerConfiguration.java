package com.example.Springbootblogapi.config.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	


public static final String AUTHORIZATION_HEADER="Authorization";
	private ApiKey apikey()
	{
		return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
	}
	
	private List<springfox.documentation.spi.service.contexts.SecurityContext>securityContexts()
	{
		return Arrays.asList( springfox.documentation.spi.service.contexts.SecurityContext.builder().securityReferences(sf()).build());
	}
	
	
	private List<SecurityReference>sf()
	{
		
		AuthorizationScope scope=new AuthorizationScope("global", "accessEverything");
		return Arrays.asList(new SecurityReference("JWT",new AuthorizationScope[] {scope}));
	}
	
	
	
	
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).
				securityContexts(securityContexts()).
				securitySchemes(Arrays.asList(apikey())).
				select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
	}
	
	
	
	
	
	
	

//	private ApiInfo getInfo() {
//		// TODO Auto-generated method stub
//		return new ApiInfo("BLOGGING APP","THIS PROJECT DEVLOP BY", "1.O", "TERM OF SERVICE", new Contact("yuvraj","jubraj@gmail.cim","https.www.xxx") , "linaceofapi","api lince",Collections.emptyList());
//	}

}
