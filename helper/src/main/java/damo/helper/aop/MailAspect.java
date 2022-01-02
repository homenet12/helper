package damo.helper.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
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
	
	@AfterReturning("@annotation(damo.helper.aop.annotation.MailSend)")
	public void mailSend(JoinPoint jp) throws Throwable{
		log.info("===메일 발송===");
	}
}
