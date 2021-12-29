package damo.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Company;
import damo.helper.domain.Member;
import damo.helper.dto.request.JoinRequest;
import damo.helper.service.CompanyService;
import damo.helper.service.MemberService;

@SpringBootTest
@Transactional
public class MemberTest {

	@Autowired
	MemberService memberService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	EntityManager em;
	
	@Test
	void save() {
		
		/*
		 * //given JoinDto sd = new JoinDto(); sd.setEmail("");
		 * sd.setName(""); sd.setPassword("123456"); sd.setPhone("");
		 * 
		 * Company company = new Company(""); em.persist(company);
		 * 
		 * Member mem = sd.of(); mem.setCompany(company);
		 * 
		 * //when Long saveId = memberService.save(mem);
		 * 
		 * //then assertEquals(mem, memberService.findOne(saveId));
		 */
	}
	
	@Test
	void maildupli() {
		//given
		/*
		 * JoinDto sd = new JoinDto(); sd.setEmail("chd@damonet.com");
		 * sd.setName("최현덕"); sd.setPassword("123456"); sd.setPhone("010-3234-5435");
		 * 
		 * JoinDto sd2 = new JoinDto(); sd2.setEmail("chd@damonet.com");
		 * sd2.setName("최현덕"); sd2.setPassword("123456"); sd2.setPhone("010-3234-5435");
		 * 
		 * Company company = new Company("다모넷"); em.persist(company);
		 * 
		 * Member mem = sd.of(); Member mem2 = sd2.of(); mem.setCompany(company);
		 * mem2.setCompany(company);
		 * 
		 * //when memberService.save(mem);
		 * 
		 * //then assertThrows(IllegalStateException.class, () -> {
		 * memberService.save(mem2); });
		 */
	}
	
	@Test
	void admin() {
		/*
		 * //given JoinDto sd = new JoinDto(); sd.setEmail("");
		 * sd.setName("최현덕"); sd.setPassword("123456"); sd.setPhone("010-3234-5435");
		 * 
		 * Company company = new Company(""); em.persist(company);
		 * 
		 * Member mem = sd.of();
		 * 
		 * mem.setCompany(company);
		 * 
		 * //when Long id = memberService.save(mem);
		 * 
		 * mem.authorization();
		 * 
		 * //then assertEquals(mem.getRole(), memberService.findOne(id).getRole());
		 */
		
	}
	
}
