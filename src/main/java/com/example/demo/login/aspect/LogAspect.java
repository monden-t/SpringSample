package com.example.demo.login.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Around("execution(* *..*.*Controller.*(..))")
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
		logger.info("Controller Method Start !" + jp.getSignature());
		try {
			Object result = jp.proceed();
			logger.info("Controller Method End !" + jp.getSignature());
			return result;
		} catch (Exception e) {
			logger.error("Controller Method Abnormal End !" + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}

	@Around("execution(* *..*.*Dao.*(..))")
	public Object daoLog(ProceedingJoinPoint jp) throws Throwable {
		logger.info("Dao Method Start !" + jp.getSignature());
		try {
			Object result = jp.proceed();
			logger.info("Dao Method End !" + jp.getSignature());
			return result;
		} catch (Exception e) {
			logger.error("Dao Method Abnormal End !" + jp.getSignature());
			e.printStackTrace();
			throw e;
		}
	}

}
