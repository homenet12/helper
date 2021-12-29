package damo.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Company;
import damo.helper.service.CompanyService;

@SpringBootTest
@Transactional
public class CompanyTest {

	
	@Autowired
	CompanyService companyService;
	
	
	@Test
	void save() throws Exception{
		//given
		/*
		 * Company company = new Company("");
		 * 
		 * //when Long saveId = companyService.save(company);
		 * 
		 * //then assertEquals(company, companyService.findOne(saveId));
		 */
	}
	
	@Test
	void dupli() throws Exception{
		//given
		/*
		 * Company company = new Company(""); Company company2 = new Company("");
		 * 
		 * //when Long saveId = companyService.save(company);
		 * 
		 * //then assertThrows(IllegalStateException.class, () -> {
		 * companyService.save(company2); });
		 */
	}
}
