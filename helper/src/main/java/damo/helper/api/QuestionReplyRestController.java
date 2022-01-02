package damo.helper.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import damo.helper.aop.annotation.MailSend;
import damo.helper.login.MemberDto;
import damo.helper.request.QuestionReplyRequest;
import damo.helper.service.QuestionReplyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionReplyRestController {

	private final QuestionReplyService replyService;
	
	@MailSend
	@PostMapping("/question/reply")
	public void questionReplySave(@AuthenticationPrincipal MemberDto memberDto ,QuestionReplyRequest replyDto) {
		replyService.save(memberDto.getId(), replyDto);
	}
	
	@MailSend
	@PostMapping("/question/reply/{replyId}")
	public void questionReplySave(@PathVariable Long replyId, @AuthenticationPrincipal MemberDto memberDto ,QuestionReplyRequest replyDto) {
		replyService.update(replyId, memberDto.getId(), replyDto.getContents());
	}
}
