package damo.helper.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import damo.helper.domain.Question;
import damo.helper.domain.QuestionReply;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuestionReplyJpaRepository {

	private final EntityManager em;
	
	public void save(QuestionReply questionReply) {
		em.persist(questionReply);
	}
	
	public List<QuestionReply> findByQuestion(Question question){
		return em.createQuery("select q from QuestionReply q where question = :question", QuestionReply.class)
				.setParameter("question", question)
				.getResultList();
	}
	
	public QuestionReply findOne(Long id) {
		return em.find(QuestionReply.class, id);
	}
}
