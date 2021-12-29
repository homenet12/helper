package damo.helper.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import damo.helper.domain.Question;
import damo.helper.domain.QuestionFile;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class QuestionFileRepository {

	private final EntityManager em;
	
	public void save(QuestionFile file) {
		em.persist(file);
	}

	public List<QuestionFile> findByQuestion(Long questionId) {
		return em.createQuery("select q from QuestionFile q where q.question.id = :questionId", QuestionFile.class)
			.setParameter("questionId", questionId)
			.getResultList();
	}
}
