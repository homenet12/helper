package damo.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Answer;
import damo.helper.domain.AnswerStatus;
import damo.helper.domain.Company;
import damo.helper.domain.Manager;
import damo.helper.domain.Member;
import damo.helper.domain.Question;
import damo.helper.service.AnswerService;
import damo.helper.service.CompanyService;
import damo.helper.service.ManagerService;
import damo.helper.service.MemberService;
import damo.helper.service.QuestionService;

@SpringBootTest
@Transactional
public class AnswerTest {

	
	@Autowired
	QuestionService questionService;
	
	@Autowired
	AnswerService answerService;
	
	@Autowired
	MemberService memberService; 
	
	@Autowired
	ManagerService managerService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EntityManager em;
	
	@Test
	void save() {
		//given
		/*
		 * Company company = new Company("다모넷"); em.persist(company);
		 * 
		 * Member member= Member.createMember("최현덕", "chd@damonet.com", "123456657",
		 * "010-0100-0101"); member.setCompany(company); em.persist(member);
		 * 
		 * Question question = Question.createQuestion("제목", "내용", member); Long saveId
		 * = questionService.save(question);
		 * 
		 * Manager manager = Manager.createManager(member, company);
		 * em.persist(manager); //managerService.save(manager); Answer answer =
		 * Answer.createAnswer("답변입니다.", AnswerStatus.complete, question, member);
		 * 
		 * //when Long id = answerService.save(answer);
		 * 
		 * //then assertEquals(answer.getId(), id);
		 */
	}
	
	@Test
	void list() {
		//given
		
		//when
								
		//then
	}
	
	@Test
	void auth() {
		//given
		/*
		 * Question question = null; Company company = new Company("다모넷");
		 * em.persist(company); Member member= Member.createMember("최현덕",
		 * "chd@damonet.com", "123456657", "010-0100-0101"); member.setCompany(company);
		 * em.persist(member); Answer answer = Answer.createAnswer("답변입니다.",
		 * AnswerStatus.complete, question, member); //when
		 * 
		 * 
		 * //then assertThrows(IllegalStateException.class, () -> {
		 * answerService.save(answer); });
		 */
	}
}
