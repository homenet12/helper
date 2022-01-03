package damo.helper.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import damo.helper.aop.annotation.AdminMailSend;
import damo.helper.request.QuestionReplyRequest;
import damo.helper.response.MemberDto;
import damo.helper.service.QuestionReplyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionReplyRestController {

	private final QuestionReplyService replyService;
	
	@AdminMailSend
	@PostMapping("/question/reply")
	public void questionReplySave(@AuthenticationPrincipal MemberDto memberDto, QuestionReplyRequest replyDto) {
		replyService.save(memberDto.getId(), replyDto);
	}
	
	@AdminMailSend
	@PostMapping("/question/reply/{replyId}")
	public void questionReplySave(@PathVariable Long replyId, @AuthenticationPrincipal MemberDto memberDto, QuestionReplyRequest replyDto) {
		replyService.update(replyId, memberDto.getId(), replyDto.getContents());
	}
}
