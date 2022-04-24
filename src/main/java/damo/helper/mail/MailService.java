package damo.helper.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailService {
	
	private JavaMailSender mailSender;
	private static final String FROM_ADDRESS = "oom177@naver.com";

	public void mailSend(MailDto mailDto) throws Exception{
		MailHandlr mailHandlr = new MailHandlr(mailSender);
		mailHandlr.setTo(mailDto.getAddress());
		mailHandlr.setFrom(FROM_ADDRESS);
		mailHandlr.setSubject(mailDto.getTitle());
		String htmlContent = "<p> " + mailDto.getMessage() + " </p>";
		mailHandlr.setText(htmlContent, true);
		
		// 첨부 파일
		//mailHandlr.setAttach("newTest.txt", "static/originTest.txt");
		// 이미지 삽입
		//mailHandlr.setInline("sample-img", "static/sample1.jpg");

		mailHandlr.send();
	}
}
