package damo.helper.api;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import damo.helper.aop.annotation.MailSend;
import damo.helper.domain.QuestionStatus;
import damo.helper.login.MemberDto;
import damo.helper.service.QuestionService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionRestController {

	private final QuestionService questionService;
	
	@GetMapping("/api/v1/status")
	public List<String> statusList() {
		List<String> statusList = new ArrayList<String>();
		for(QuestionStatus q : EnumSet.allOf(QuestionStatus.class)) {
			statusList.add(q.name());
		}
		return statusList; 
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/updateQuestionStatus")
	public void updateQuestionStatus(Long questionId, QuestionStatus status, @AuthenticationPrincipal MemberDto memberDto) {
		questionService.updateStatus(questionId, status);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/updateQuestionManager")
	public void updateQuestionManager(Long questionId, Long managerId, @AuthenticationPrincipal MemberDto memberDto) {
		questionService.updateManager(questionId, managerId);
	}
	
	
}
