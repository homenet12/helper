package damo.helper.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import damo.helper.mail.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class MailAspect {

	private final MailService mailService;
	
	
	@After("execution(* damo.helper.controller.QuestionController.questionSave(..))")
	public void notify(JoinPoint jp) throws Throwable{
		
	}
	
	
	
}
