package damo.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Company;
import damo.helper.domain.Member;
import damo.helper.domain.Question;
import damo.helper.repository.QuestionRepository;
import damo.helper.service.CompanyService;
import damo.helper.service.MemberService;
import damo.helper.service.QuestionService;

@SpringBootTest
@Transactional
public class QuestionTest {

	@Autowired
	QuestionService questionService;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EntityManager em;
	
	@Test
	void save() {

	}
	
	@Test
	void list() {
		
		
		//Slice<Question> list = questionRepository.findAllBy(PageRequest.of(0, 5));
		//questionRepository.findByTitleAndContents("title","contents");
		//questionRepository.findByTitleAndContents("title",null);
		//questionRepository.findAll("title");
		
		
		
		
		
	}
	
	//@Test
	void update() {
		/*
		 * //given Company company = new Company("다모넷"); em.persist(company); Member
		 * member= Member.createMember("최현덕", "chd@damonet.com", "123456657",
		 * "010-0100-0101"); member.setCompany(company); em.persist(member);
		 * 
		 * Question question = Question.createQuestion("제목", "내용", member); Long saveId
		 * = questionService.save(question);
		 * 
		 * //when question.complete(); Question findQuestion =
		 * questionService.findOne(saveId); //then
		 * 
		 * assertEquals(question.getStatus(), findQuestion.getStatus());
		 */
	}
}
