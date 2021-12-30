package damo.helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import damo.helper.domain.Question;
import damo.helper.domain.QuestionFile;

public interface QuestionFileRepository extends JpaRepository<QuestionFile, Long> {

	List<QuestionFile> findByQuestion(Question question);

}
