package damo.helper.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import damo.helper.domain.Member;

public interface MemberJpaRepository extends JpaRepository<Member, Long>{

}
