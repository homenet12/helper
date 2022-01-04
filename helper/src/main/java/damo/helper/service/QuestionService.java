package damo.helper.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.config.security.MemberDto;
import damo.helper.domain.Company;
import damo.helper.domain.Member;
import damo.helper.domain.Question;
import damo.helper.domain.QuestionFile;
import damo.helper.domain.QuestionStatus;
import damo.helper.repository.MemberRepository;
import damo.helper.repository.QuestionFileRepository;
import damo.helper.repository.QuestionRepository;
import damo.helper.repository.jpa.MemberJpaRepository;
import damo.helper.repository.jpa.QuestionFileJpaRepository;
import damo.helper.repository.jpa.QuestionJpaRepository;
import damo.helper.repository.querydsl.QuestionRepositoryImpl;
import damo.helper.request.PageParam;
import damo.helper.request.QuestionRequest;
import damo.helper.request.QuestionSearchDto;
import damo.helper.response.QuestionFileResponse;
import damo.helper.response.QuestionViewResponse;
import damo.helper.response.QuestionsResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

	private final MemberRepository memberRepository;
	private final QuestionRepository questionRepository;
	private final QuestionFileRepository questionFileRepository;
	
	public Page<QuestionsResponse> findAll(MemberDto userDto, QuestionSearchDto search, Pageable pageable){
		return questionRepository.findAll(userDto, pageable, search);
	}

	public QuestionViewResponse findResponseQuestion(Long questionId, MemberDto userDto) {
		Question question = questionRepository.findById(questionId).orElseThrow();
		if(!userDto.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
			question.selfCompanyCheck(userDto.getCompany().getId());
		}
		List<QuestionFileResponse> questionFile = questionFileRepository.findByQuestion(question)
												.stream().map(q -> new QuestionFileResponse(q)).toList();
		QuestionViewResponse questionViewDto = new QuestionViewResponse(question, questionFile); 
		return questionViewDto;
	}
	
	public QuestionRequest findRequestQuestion(Long questionId, Long memberId) {
		Question question = questionRepository.findById(questionId).orElseThrow();
		memberRepository.findById(memberId).orElseThrow().mySelfCheck(question.getWriter().getId());
		QuestionRequest questionDto = new QuestionRequest(question); 
		return questionDto;
	}
	
	@Transactional
	public Long save(QuestionRequest questionDto, Long memberId) {
		Member member = memberRepository.findById(memberId).orElseThrow();
		Question question = Question.createQuestion(questionDto.getTitle(), questionDto.getContents(), member);
		questionRepository.save(question);
		return question.getId();
	}
	
	@Transactional
	public void update(Long questionId, QuestionRequest questionDto, Long memberId) {
		Question question = questionRepository.findById(questionId).orElseThrow();
		memberRepository.findById(memberId).orElseThrow().mySelfCheck(question.getWriter().getId());
		question.update(questionDto.getTitle(), questionDto.getContents());
	}
	
	@Transactional
	public void updateStatus(Long questionId, QuestionStatus status) {
		Question question = questionRepository.findById(questionId).orElseThrow();
		question.setStatus(status);
	}

	@Transactional
	public void updateManager(Long questionId, Long managerId) {
		Member member= null;
		if(managerId != null) {
			member = memberRepository.findById(managerId).orElseThrow();
		}
		Question question = questionRepository.findById(questionId).orElseThrow();
		question.setManager(member);
	}

	public int getMonthComplete(MemberDto memberDto) {
		Member member = memberRepository.findById(memberDto.getId()).orElseThrow();
		return questionRepository.getMonthComplete(member.getCompany().getId());
	}
	
}
