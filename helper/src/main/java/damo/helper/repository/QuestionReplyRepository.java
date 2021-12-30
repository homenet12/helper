package damo.helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import damo.helper.domain.Question;
import damo.helper.domain.QuestionReply;

public interface QuestionReplyRepository extends JpaRepository<QuestionReply, Long> {

	List<QuestionReply> findByQuestion(Question question);

}
