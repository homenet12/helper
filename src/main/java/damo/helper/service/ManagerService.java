package damo.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import damo.helper.domain.Company;
import damo.helper.domain.Manager;
import damo.helper.domain.Member;
import damo.helper.repository.CompanyRepository;
import damo.helper.repository.ManagerRepository;
import damo.helper.repository.MemberRepository;
import damo.helper.repository.jpa.ManagerJpaRepository;
import damo.helper.request.ManagerRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerService {

	private final ManagerRepository managerRepository;
	private final MemberRepository memberRepository;
	private final CompanyRepository companyRepository;

	private void validateDuplicateManager(Manager manager) {
		List<Manager> findManager = managerRepository.findByMemberAndCompany(manager.getMember(), manager.getCompany());
		if(!findManager.isEmpty()) {
			throw new IllegalStateException("이미 권한이 부여되었습니다.");
		}
	}
	
	public void save(ManagerRequest managerRequest) {
		Member member = memberRepository.findById(managerRequest.getMemberId()).orElseThrow();
		
		for(Long companyId : managerRequest.getCompanyId()) {
			Company company = companyRepository.findById(companyId).orElseThrow();
			Manager manager = Manager.createManager(member, company);
			validateDuplicateManager(manager);
			managerRepository.save(manager);
		}
	}
}
