package damo.helper.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import damo.helper.mail.MailService;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class MailAspect {

	private final MailService mailService;
	
	
	@After("execution(* damo.helper.controller.QuestionController.questionSave(..))")
	public void notify(JoinPoint jp) throws Throwable{
		
	}
	
}
