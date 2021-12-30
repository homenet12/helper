package damo.helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import damo.helper.domain.Company;
import damo.helper.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	List<Member> findByCompany(Company company);

	List<Member> findByEmail(String email);

}
