package com.kinexcs.demo.product.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

import com.kinexcs.demo.common.Util.GetPropertyFromConfig;
import com.kinexcs.demo.common.constant.ApiConstent;
import com.kinexcs.demo.common.filter.LoggingFilter;

@Configuration
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.kinexcs.demo.product.application", "com.kinexcs.demo.product.controller" })
@ApiIgnore
@EnableSwagger2
public class AppConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);
	private static final String SERVICE_NAME = "PRODUCT_MS";

	@Value("${config.api.url}")
	private String configApiUrl;

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "PRODUCT-MS is Up and Running";
	}

	@Bean
	public FilterRegistrationBean loggingFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new LoggingFilter(SERVICE_NAME, getTraceApiURL()));
		registrationBean.addUrlPatterns("/product/*");
		return registrationBean;
	}

	@Bean
	public Docket api() {

		ApiInfo apiInfo = new ApiInfo("PRODUCT - MS => REST API", "Spring Boot REST API for Product", "1.0.0", "Terms of service", new Contact(
				"AShu Kanaujia", "http://kinexcs.com/", "Ashu@abc.com"), "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo);
	}

	private String getTraceApiURL() {
		String traceApiUrl = null;
		try {
			traceApiUrl = GetPropertyFromConfig.getConfigPeopertyValue(configApiUrl, ApiConstent.Config.TRACE_API_PUT_LOG_URL);
			LOGGER.info("trace API url is ok" + traceApiUrl);
		} catch (Exception e) {
			LOGGER.error("Unable to fetch Trace API URL", e);
		}
		return traceApiUrl;
	}

}
