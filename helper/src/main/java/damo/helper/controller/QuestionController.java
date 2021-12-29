package damo.helper.controller;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import damo.helper.domain.QuestionStatus;
import damo.helper.dto.request.QuestionRequest;
import damo.helper.dto.request.QuestionSearchDto;
import damo.helper.dto.response.QuestionReplyResponse;
import damo.helper.dto.response.QuestionViewResponse;
import damo.helper.dto.response.QuestionsResponse;
import damo.helper.login.MemberDto;
import damo.helper.mail.MailDto;
import damo.helper.mail.MailService;
import damo.helper.repository.member.dto.MemberDtoRepository;
import damo.helper.repository.question.dto.QuestionDtoRepository;
import damo.helper.service.QuestionFileService;
import damo.helper.service.QuestionReplyService;
import damo.helper.service.QuestionService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QuestionController {
	
	private final QuestionService questionService; 
	private final QuestionFileService fileService;
	private final QuestionReplyService questionReplyService;
	
	private final MemberDtoRepository memberDtoRepository;
	private final MailService mailService;
	

	@GetMapping("/questions")
	public String questions(@ModelAttribute("search") QuestionSearchDto search, 
							@AuthenticationPrincipal MemberDto memberDto, 
							@PageableDefault(page = 0, size = 10) Pageable pageable,
							Model model) {
		
		Page<QuestionsResponse> questions = questionService.findAll(memberDto, search, pageable);
		int monthComplete = questionService.getMonthComplete(memberDto);
		model.addAttribute("questions", questions);
		model.addAttribute("monthComplete", monthComplete);
		
		return "/question/questionList";
	}
	
	@GetMapping("/question")
	public String questionForm(Model model) {
		
		model.addAttribute("questionDto", new QuestionRequest());
		
		return "/question/questionForm";
	}
	
	@PostMapping("/question")
	public String questionSave(@Valid QuestionRequest questionDto, BindingResult result, List<MultipartFile> files, @AuthenticationPrincipal MemberDto memberDto) {
		
		if(result.hasErrors()) {
			return "/question/questionForm";
		}
		Long questionId = questionService.save(questionDto, memberDto.getId());
		fileService.saveFiles(files, questionId);
		
		try {
			mailService.mailSend(new MailDto());
		}catch(Exception e) {
			
		}
		
		
		return "redirect:/questions";
	}
	
	@GetMapping("/question/{id}")
	public String questionView(@PathVariable(name = "id") Long questionId, @AuthenticationPrincipal MemberDto memberDto, Model model) {
		
		QuestionViewResponse questionViewDto = questionService.findResponseQuestion(questionId, memberDto);
		List<QuestionReplyResponse> questionReplyResponseDtos = questionReplyService.findAll(questionId);
		
		List<String> statusList = new ArrayList<String>();
		for(QuestionStatus q : EnumSet.allOf(QuestionStatus.class)) {
			statusList.add(q.name());
		}
		
		model.addAttribute("status", statusList);
		model.addAttribute("admins", memberDtoRepository.findByAdmin());
		model.addAttribute("memberId", memberDto.getId());
		model.addAttribute("questionView", questionViewDto);
		model.addAttribute("questionReply", questionReplyResponseDtos);
		
		return "/question/questionView";
	}
	
	@GetMapping("/question/{id}/edit")
	public String questionEditForm(@PathVariable(name = "id") Long questionId, @AuthenticationPrincipal MemberDto memberDto, Model model) {
		
		QuestionRequest questionDto = questionService.findRequestQuestion(questionId, memberDto.getId());
		model.addAttribute("questionDto", questionDto);
		
		return "/question/questionForm";
	}
	
	@PostMapping("/question/{id}/edit")
	public String questionEdit(@Valid QuestionRequest questionDto, BindingResult result, @AuthenticationPrincipal MemberDto memberDto) {
		
		if(result.hasErrors()) {
			return "/question/questionForm";
		}
		questionService.update(questionDto, memberDto.getId());
		
		return "redirect:/questions";
	}
}
