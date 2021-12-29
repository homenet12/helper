package damo.helper.repository;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import damo.helper.domain.Answer;
import damo.helper.domain.Member;
import damo.helper.domain.Question;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AnswerRepository {

	private final EntityManager em;
	
	public void save(Answer answer) {
		if(answer.getId() == null) {
			em.persist(answer);
		}else {
			em.merge(answer);
		}
	}
	
	public Answer findByQuestion(Question question) {
		return em.createQuery("select a from Answer a where question = :question", Answer.class)
				.setParameter("question", question)
				.getSingleResult();
	}
	
	public Answer findByMember(Member member) {
		return em.createQuery("select a from Answer a where member = :member", Answer.class)
				.setParameter("member", member)
				.getSingleResult();
	}
}
