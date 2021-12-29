package damo.helper.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import damo.helper.domain.Company;
import damo.helper.service.CompanyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CompanyRestController {

	private final CompanyService companyService;
	
	@GetMapping("api/v1/companys")
	public List<Company> allList(){
		return companyService.findAll();
	}
}
