package damo.helper.service;

import org.springframework.stereotype.Service;

import damo.helper.domain.Answer;
import damo.helper.domain.Question;
import damo.helper.repository.jpa.AnswerJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerService {

	private final AnswerJpaRepository answerRepository;
	
	public Long save(Answer answer) {
		//담당자만 답변이 가능
		
		
		answerRepository.save(answer);
		return answer.getId();
	}
	
	public Answer findOne(Question question) {
		return answerRepository.findByQuestion(question);
	}
}
