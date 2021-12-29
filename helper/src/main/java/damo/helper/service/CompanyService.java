package damo.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Company;
import damo.helper.dto.response.CompanyResponse;
import damo.helper.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

	private final CompanyRepository companyRepository;
	
	@Transactional
	public Long save(Company company) {
		validateDuplicateCompany(company);
		companyRepository.save(company);
		return company.getId();
	}
	
	private void validateDuplicateCompany(Company company) {
		List<Company> findCompany = companyRepository.findByName(company.getName());
		if(!findCompany.isEmpty()) {
			throw new IllegalStateException("해당 회사명이 이미 존재합니다.");
		}
	}

	public Company findOne(Long id) {
		return companyRepository.findOne(id);
	}
	
	public List<Company> findAll(){
		return companyRepository.findAll();
	}
	
	public List<CompanyResponse> findAllDto(){
		return companyRepository.findAll().stream().map(comp -> new CompanyResponse(comp)).toList();
	}
}
