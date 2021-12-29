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
import damo.helper.domain.Manager;
import damo.helper.domain.Member;
import damo.helper.service.CompanyService;
import damo.helper.service.ManagerService;
import damo.helper.service.MemberService;

@SpringBootTest
@Transactional
public class ManagerTest {

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
		
		/*
		 * //given Company company = new Company("다모넷"); em.persist(company); Member
		 * member= Member.createMember("최현덕", "chd@damonet.com", "123456657",
		 * "010-0100-0101"); member.setCompany(company); em.persist(member); Manager
		 * manager = Manager.createManager(member, company);
		 * 
		 * //when Long id = managerService.save(manager);
		 * 
		 * //then assertEquals(manager.getId(), id);
		 */
		
	}
	
	
	@Test
	void authcheck() {
		/*
		 * //given Company company = new Company("다모넷"); em.persist(company); Member
		 * member= Member.createMember("최현덕", "chd@damonet.com", "123456657",
		 * "010-0100-0101"); member.setCompany(company); em.persist(member); Manager
		 * manager = Manager.createManager(member, company); Manager manager2 =
		 * Manager.createManager(member, company);
		 * 
		 * //when Long id = managerService.save(manager);
		 * 
		 * 
		 * //then assertThrows(IllegalStateException.class, () -> { Long id2 =
		 * managerService.save(manager2); });
		 */
	}
}
