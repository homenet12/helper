package damo.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import damo.helper.domain.Manager;
import damo.helper.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ManagerService {

	private final ManagerRepository managerRepository;
	
	public Long save(Manager manager) {
		validateDuplicateManager(manager);
		managerRepository.save(manager);
		return manager.getId();
	}

	private void validateDuplicateManager(Manager manager) {
		List<Manager> findManager = managerRepository.findByMemberAndCompany(manager.getMember(), manager.getCompany());
		if(!findManager.isEmpty()) {
			throw new IllegalStateException("이미 권한이 부여되었습니다.");
		}
	}
	
	public void delete(Manager manager) {
		managerRepository.delete(manager);
	}
	
}
