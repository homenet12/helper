package damo.helper.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import damo.helper.domain.Question;
import damo.helper.repository.querydsl.QuestionCustomRepo;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionCustomRepo {

	Slice<Question> findAllBy(Pageable pageable);
	
	Question findByTitleAndContents(String title, String contents);
	
	@Query("select q from Question q where q.title = :title")
	@EntityGraph(attributePaths = {"manager.company", "writer"})
	List<Question> findAll(@Param("title") String title);
}
