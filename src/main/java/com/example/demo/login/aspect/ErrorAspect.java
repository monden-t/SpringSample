package com.example.demo.login.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorAspect {
	Logger logger = LoggerFactory.getLogger(getClass());

	@AfterThrowing(value = "execution(* *..*..*(..)) && (bean(*Controller) || bean(*Service) || bean(*Repository))", throwing = "ex")
	public void throwingNull(DataAccessException ex) {
		logger.error("DataAccessException Occurs !!" + ex);
	}
}
