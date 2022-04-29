package com.ict.aop;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect
@Log4j
@Component
public class LogAdvice {
	
	@Before("execution(* com.ict.service.SampleService*.*(..))")
	public void logBefore() {
		log.info("=================");
	}
	@Before("execution(* com.ict.service.SampleService*.doAdd(String,String))&&args(str1,str2)")
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
	@AfterThrowing(pointcut = "execution(* com.ict.service.SampleService*.*(..))", throwing="exception")
	public void logException(Exception exception) {
		log.info("Exception..!!!!!!!!!!!!!!");
		log.info("exception : " + exception);
	}
	@Around("execution(* com.ict.service.SampleService*.*(..))")
				// 앞으로 실행될 메서드(pointcut)에 대한 정보를 pjp에 저장중
	
	public Object logTime(ProceedingJoinPoint pjp) {
		
		// 메서드 실행 직전 시간 저장. 
		long start = System.currentTimeMillis();
		log.info("start : " + start);
		
		log.info("Target: " + pjp.getTarget()); // 해당 메서드 명칭
		log.info("Param" + Arrays.toString(pjp.getArgs())); // 해당 메서드의 파라미터들 
		
		Object result = null;
		
		//////////////////// 이전까지 핵심로직은 실행안됌//////////////////////////////////////////////
		try {
			result = pjp.proceed(); // 핵심로직 실행
		} catch(Throwable e) {
			// 예외 발생시 실행
			e.printStackTrace();
		}
		long end = System.currentTimeMillis(); // 메서드 실행이 모두 끝난 직후 시간 저장.
		log.info("end : "+ end);
		
		log.info("Time : " + (end - start)); // 소요시간 계산해 로그에 처리 
		
		return result;
		
	}

}
