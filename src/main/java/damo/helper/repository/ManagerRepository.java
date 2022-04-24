package damo.helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import damo.helper.domain.Company;
import damo.helper.domain.Manager;
import damo.helper.domain.Member;

public interface ManagerRepository extends JpaRepository<Manager, Long>{

	List<Manager> findByMemberAndCompany(Member member, Company company);

}
