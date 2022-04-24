package damo.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Member;
import damo.helper.domain.Question;
import damo.helper.domain.QuestionReply;
import damo.helper.repository.MemberRepository;
import damo.helper.repository.QuestionReplyRepository;
import damo.helper.repository.QuestionRepository;
import damo.helper.repository.jpa.MemberJpaRepository;
import damo.helper.repository.jpa.QuestionJpaRepository;
import damo.helper.repository.jpa.QuestionReplyJpaRepository;
import damo.helper.request.QuestionReplyRequest;
import damo.helper.response.QuestionReplyResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionReplyService {

	private final QuestionRepository questionRepository;
	private final QuestionReplyRepository questionReplyRepository;
	private final MemberRepository memberRepository;
	
	@Transactional
	public Long save(Long memberId, QuestionReplyRequest replyDto) {
		Member member = memberRepository.findById(memberId).orElseThrow();
		Question question = questionRepository.findById(replyDto.getQuestionId()).orElseThrow();
		QuestionReply reply = QuestionReply.createReply(replyDto.getContents(), member, question);
		questionReplyRepository.save(reply);
		return reply.getId();
	}
	
	public List<QuestionReplyResponse> findAll(Long questionId){
		Question question = questionRepository.findById(questionId).orElseThrow();
		return null;  
	}

	@Transactional 
	public void update(Long replyId, Long memberId, String contents) {
		QuestionReply reply = questionReplyRepository.findById(replyId).orElseThrow();
		if(!reply.getMember().getId().equals(memberId)) {
			throw new IllegalStateException("본인만 수정할 수 있습니다.");
		}
		reply.setContents(contents); 
	}
}
