package damo.helper.aop;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import damo.helper.domain.Member;
import damo.helper.domain.Role;
import damo.helper.mail.MailDto;
import damo.helper.mail.MailService;
import damo.helper.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class MailAspect {

	private final MailService mailService;
	private final MemberRepository memberRepository;
	
	@AfterReturning("@annotation(damo.helper.aop.annotation.AdminMailSend)")
	public void mailSend(JoinPoint jp) throws Throwable{
		log.info("===메일 발송===");//tetddddㅊ
		
		List<Member> admins = memberRepository.findByRole(Role.admin);
		
		for(Member m : admins) {
			mailService.mailSend(new MailDto("질의 등록이 되었습니다! 답변 해주세요!", m.getEmail().trim(), "질의 등록이 되었습니다! 답변 해주세요!"));
		}
		
	}
}
