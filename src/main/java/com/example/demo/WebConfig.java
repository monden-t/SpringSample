package com.example.demo;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class WebConfig {

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
		/*
		 * メッセージプロパティファイル名と文字コードをセット
		 */
		bean.setBasename("classpath:messages");
		bean.setDefaultEncoding("UTF-8");
		return bean;
	}

	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}

}
