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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import damo.helper.aop.annotation.MailSend;
import damo.helper.domain.QuestionStatus;
import damo.helper.login.MemberDto;
import damo.helper.mail.MailDto;
import damo.helper.mail.MailService;
import damo.helper.repository.querydsl.MemberDtoRepository;
import damo.helper.repository.querydsl.QuestionDtoRepository;
import damo.helper.request.QuestionRequest;
import damo.helper.request.QuestionSearchDto;
import damo.helper.response.QuestionFileResponse;
import damo.helper.response.QuestionReplyResponse;
import damo.helper.response.QuestionViewResponse;
import damo.helper.response.QuestionsResponse;
import damo.helper.service.QuestionFileService;
import damo.helper.service.QuestionReplyService;
import damo.helper.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
	
	private final QuestionService questionService; 
	private final QuestionFileService fileService;
	private final QuestionReplyService questionReplyService;
	
	private final MemberDtoRepository memberDtoRepository;

	@GetMapping("/questions")
	public String questions(@ModelAttribute("search") QuestionSearchDto search, 
							@AuthenticationPrincipal MemberDto memberDto, 
							@PageableDefault(page = 0, size = 10) Pageable pageable,
							Model model) {
		
		Page<QuestionsResponse> questions = questionService.findAll(memberDto, search, pageable);
		int monthComplete = questionService.getMonthComplete(memberDto);
		List<String> statusList = new ArrayList<String>();
		for(QuestionStatus q : EnumSet.allOf(QuestionStatus.class)) {
			statusList.add(q.name());
		}
		
		model.addAttribute("status", statusList);
		model.addAttribute("questions", questions);
		model.addAttribute("monthComplete", monthComplete);
		
		return "/question/questionList";
	}
	
	@GetMapping("/question")
	public String questionForm(Model model) {
		
		model.addAttribute("questionDto", new QuestionRequest());
		model.addAttribute("files", new ArrayList<>());
		
		return "/question/questionForm";
	}
	
	@MailSend
	@PostMapping("/question")
	public String questionSave(@Valid QuestionRequest questionDto, 
								BindingResult result, 
								@RequestParam(name = "files[]") List<MultipartFile> files, 
								@AuthenticationPrincipal MemberDto memberDto) {
		
		if(result.hasErrors()) {
			return "/question/questionForm";
		}
		Long questionId = questionService.save(questionDto, memberDto.getId());
		fileService.saveFiles(files, questionId);
		
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
		List<QuestionFileResponse> files = fileService.findByQuestion(questionId);
		model.addAttribute("questionDto", questionDto);
		model.addAttribute("files", files);
		
		return "/question/questionForm";
	}
	
	@MailSend
	@PostMapping("/question/{id}/edit")
	public String questionEdit(@PathVariable(name = "id") Long questionId, @Valid QuestionRequest questionDto, BindingResult result, @RequestParam(name = "files[]") List<MultipartFile> files, @AuthenticationPrincipal MemberDto memberDto) {
		
		if(result.hasErrors()) {
			return "/question/questionForm";
		}
		
		questionService.update(questionId, questionDto, memberDto.getId());
		fileService.saveFiles(files, questionId);
		
		return "redirect:/questions";
	}
}
