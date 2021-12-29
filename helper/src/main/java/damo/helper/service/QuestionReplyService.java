package damo.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Member;
import damo.helper.domain.Question;
import damo.helper.domain.QuestionReply;
import damo.helper.dto.request.QuestionReplyRequest;
import damo.helper.dto.response.QuestionReplyResponse;
import damo.helper.repository.MemberRepository;
import damo.helper.repository.QuestionReplyRepository;
import damo.helper.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionReplyService {

	private final QuestionRepository questionRepository;
	private final QuestionReplyRepository questionReplyRepository;
	private final MemberRepository memberRepository;
	
	public Long save(QuestionReply questionReply) {
		questionReplyRepository.save(questionReply);
		return questionReply.getId();
	}
	
	@Transactional
	public Long save(Long memberId, QuestionReplyRequest replyDto) {
		Member member = memberRepository.findOne(memberId);
		Question question = questionRepository.findOne(replyDto.getQuestionId());
		QuestionReply reply = QuestionReply.createReply(replyDto.getContents(), member, question);
		questionReplyRepository.save(reply);
		return reply.getId();
	}
	
	public List<QuestionReply> findAll(Question question){
		return questionReplyRepository.findByQuestion(question);
	}
	
	public List<QuestionReplyResponse> findAll(Long questionId){
		Question question = questionRepository.findOne(questionId);
		return questionReplyRepository.findByQuestion(question).stream().map(qr -> new QuestionReplyResponse(qr)).toList();
	}
	
	public QuestionReply findOne(Long id) {
		return questionReplyRepository.findOne(id);
	}

	@Transactional
	public void update(Long replyId, Long memberId, String contents) {
		QuestionReply reply = questionReplyRepository.findOne(replyId);
		if(!reply.getMember().getId().equals(memberId)) {
			throw new IllegalStateException("본인만 수정할 수 있습니다.");
		}
		reply.setContents(contents);
	}
}
