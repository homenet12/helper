package damo.helper.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Company;
import damo.helper.domain.Member;
import damo.helper.domain.Question;
import damo.helper.domain.QuestionFile;
import damo.helper.domain.QuestionStatus;
import damo.helper.dto.request.PageParam;
import damo.helper.dto.request.QuestionRequest;
import damo.helper.dto.request.QuestionSearchDto;
import damo.helper.dto.response.QuestionFileResponse;
import damo.helper.dto.response.QuestionViewResponse;
import damo.helper.dto.response.QuestionsResponse;
import damo.helper.login.MemberDto;
import damo.helper.repository.MemberRepository;
import damo.helper.repository.QuestionFileRepository;
import damo.helper.repository.QuestionRepository;
import damo.helper.repository.question.dto.QuestionDtoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final MemberRepository memberRepository;
	private final QuestionFileRepository questionFileRepository;
	
	private final QuestionDtoRepository questionDtoRepository;
	
	public Long save(Question question) {
		questionRepository.save(question);
		return question.getId();
	}
	
	public Page<QuestionsResponse> findAll(MemberDto userDto, QuestionSearchDto search, Pageable pageable){
		return questionDtoRepository.findAll(userDto, pageable, search);
	}
	
	public Question findOne(Long id) {
		return questionRepository.findOne(id);
	}

	public QuestionViewResponse findResponseQuestion(Long questionId, MemberDto userDto) {
		Question question = questionRepository.findOne(questionId);
		if(!userDto.getAuthorities().contains(new SimpleGrantedAuthority("admin"))){
			question.selfCompanyCheck(userDto.getCompany().getId());
		}
		List<QuestionFileResponse> questionFile = questionFileRepository.findByQuestion(questionId)
												.stream().map(q -> new QuestionFileResponse(q)).toList();
		QuestionViewResponse questionViewDto = new QuestionViewResponse(question, questionFile); 
		return questionViewDto;
	}
	
	public QuestionRequest findRequestQuestion(Long questionId, Long memberId) {
		Question question = questionRepository.findOne(questionId);
		memberRepository.findOne(memberId).mySelfCheck(question.getWriter().getId());
		QuestionRequest questionDto = new QuestionRequest(question); 
		return questionDto;
	}
	
	@Transactional
	public Long save(QuestionRequest questionDto, Long memberId) {
		Member member = memberRepository.findOne(memberId);
		Question question = Question.createQuestion(questionDto.getTitle(), questionDto.getContents(), member);
		questionRepository.save(question);
		return question.getId();
	}
	
	@Transactional
	public void update(QuestionRequest questionDto, Long memberId) {
		Question question = questionRepository.findOne(questionDto.getId());
		memberRepository.findOne(memberId).mySelfCheck(question.getWriter().getId());
		question.update(questionDto.getTitle(), questionDto.getContents());
	}
	
	@Transactional
	public void updateStatus(Long questionId, QuestionStatus status) {
		Question question = questionRepository.findOne(questionId);
		question.setStatus(status);
	}

	@Transactional
	public void updateManager(Long questionId, Long managerId) {
		Member member= null;
		if(managerId != null) {
			member = memberRepository.findOne(managerId);
		}
		Question question = questionRepository.findOne(questionId);
		question.setManager(member);
	}

	public int getMonthComplete(MemberDto memberDto) {
		Member member = memberRepository.findOne(memberDto.getId());
		return questionDtoRepository.getMonthComplete(member.getCompany().getId());
	}
	
}
