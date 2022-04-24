package damo.helper.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Company;
import damo.helper.repository.CompanyRepository;
import damo.helper.repository.jpa.CompanyJpaRepository;
import damo.helper.response.CompanyResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

	private final CompanyRepository companyRepository;

	@Transactional
	public Long save(String name) {
		validateDuplicateCompany(name);
		Company company = new Company(name);
		companyRepository.save(company);
		return company.getId();
	}
	
	@PostConstruct
	public void dummy() {
		validateDuplicateCompany("새회사");
		validateDuplicateCompany("새회사2");
		companyRepository.save(new Company("새회사"));
		companyRepository.save(new Company("새회사2"));
	}
	
	private void validateDuplicateCompany(String name) {
		List<Company> findCompany = companyRepository.findByName(name);
		if(!findCompany.isEmpty()) {
			throw new IllegalStateException("해당 회사명이 이미 존재합니다.");
		}
	}

	public Company findOne(Long id) {
		return companyRepository.findById(id).orElseThrow();
	}
	
	public List<Company> findAll(){
		return companyRepository.findAll();
	}
	
	public List<CompanyResponse> findAllDto(){
		return null;
	}
}
