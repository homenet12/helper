package damo.helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import damo.helper.domain.Company;
import damo.helper.domain.Member;
import damo.helper.domain.Role;
import damo.helper.repository.querydsl.MemberCustomRepo;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepo{

	List<Member> findByCompany(Company company);

	List<Member> findByEmail(String email);
	
	List<Member> findByRole(Role role);

}
