package com.kinexcs.demo.trace.application;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.kinexcs.demo.trace.controller" })
@ApiIgnore
@EnableSwagger2
public class AppConfiguration {

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "TRACE-MS is Up and Running";
	}

	@Bean
	public Docket SwaggerApi() {
		ApiInfo apiInfo = new ApiInfo("TRACE - MS => REST API", "Spring Boot REST API for trace", "1.0.0", "Terms of service", new Contact(
				"AShu Kanaujia", "http://kinexcs.com/", "Ashu@abc.com"), "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo);
	}
}
