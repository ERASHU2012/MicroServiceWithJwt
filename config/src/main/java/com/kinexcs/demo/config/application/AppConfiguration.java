package com.kinexcs.demo.config.application;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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

import com.kinexcs.demo.common.constant.ApiConstent;
import com.kinexcs.demo.common.filter.LoggingFilter;
import com.kinexcs.demo.config.data.ApiConfigData;

@Configuration
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.kinexcs.demo.config.controller" })
@ApiIgnore
@EnableSwagger2
public class AppConfiguration {
	private static final String SERVICE_NAME = "CONFIG_MS";

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "CONFIG-MS is Up and Running";
	}

	@Bean
	public Docket swaggerApi() {
		ApiInfo apiInfo = new ApiInfo("CONFIG - MS => REST API", "Spring Boot REST API for config", "1.0.0", "Terms of service", new Contact(
				"AShu Kanaujia", "http://kinexcs.com/", "Ashu@abc.com"), "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).
				paths(PathSelectors.any()).build()
				.apiInfo(apiInfo);
		
		
		
	}

	@Bean
	public FilterRegistrationBean loggingFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new LoggingFilter(SERVICE_NAME, ApiConfigData.getConfig(ApiConstent.Config.TRACE_API_PUT_LOG_URL)));
		registrationBean.addUrlPatterns("/config/*");
		return registrationBean;
	}
}
