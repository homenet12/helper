package damo.helper;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Company;
import damo.helper.domain.Member;

@SpringBootTest
@Transactional
public class DummyData {

	
	@Autowired
	EntityManager em;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	void dummy() {
		/*
		 * Company company = new Company(""); em.persist(company); Member member=
		 * Member.createMember("", "@.com",
		 * passwordEncoder.encode("12345"), "010-0100-0101");
		 * member.setCompany(company); em.persist(member);
		 */
	}
}
