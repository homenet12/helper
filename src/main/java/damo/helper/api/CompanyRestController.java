package damo.helper.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import damo.helper.domain.Company;
import damo.helper.service.CompanyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CompanyRestController {

	private final CompanyService companyService;
	
	@GetMapping("/companys")
	public List<Company> allList(){
		return companyService.findAll();
	}
	
	@PostMapping("/company")
	public void saveCompany(String name) {
		companyService.save(name);
	}
}
