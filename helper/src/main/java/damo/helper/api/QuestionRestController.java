package damo.helper.api;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/updateQuestionStatus")
	public void updateQuestionStatus(Long questionId, QuestionStatus status, @AuthenticationPrincipal MemberDto memberDto) {
		if(!memberDto.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
			throw new IllegalStateException("관리자만 수정할 수 있습니다.");
		}
		
		questionService.updateStatus(questionId, status);
	}
	
	@PostMapping("/updateQuestionManager")
	public void updateQuestionManager(Long questionId, Long managerId, @AuthenticationPrincipal MemberDto memberDto) {
		if(!memberDto.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
			throw new IllegalStateException("관리자만 수정할 수 있습니다.");
		}
		
		questionService.updateManager(questionId, managerId);
	}
	
	
}