package com.example.demo.trySpring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	private final Logger logger = LoggerFactory.getLogger(LogAdvice.class);

	@Before("execution(* com.example.demo.trySpring..HelloController.postRequest(..))")
	public void invokeBefore(JoinPoint joinPoint) {
		methodLog(joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), "start");
	}
	
	@After("execution(* com.example.demo.trySpring..HelloController.postRequest(..))")
	public void invokeAfter(JoinPoint joinPoint) {
		methodLog(joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), "end");
	}

	@Before("@annotation(com.example.demo.trySpring.aspect.annotation.AopTargetAnnotation)")
	public void loggingAnnotatedMethod(JoinPoint joinPoint) throws Throwable {
		methodLog(joinPoint.getTarget().getClass().toString(), joinPoint.getSignature().getName(), "start");
	}

	private void methodLog(String className, String methodName, String message){
		logger.info(className + "." + methodName + "() " + message + ".");
	}
}
