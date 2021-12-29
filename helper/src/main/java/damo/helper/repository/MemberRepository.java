package damo.helper.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import damo.helper.domain.Company;
import damo.helper.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

	private final EntityManager em;
	
	public void save(Member member) {
		em.persist(member);
	}
	
	public List<Member> findAll(){
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}
	
	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}
	
	public List<Member> findByEmail(String email) {
		return em.createQuery("select m from Member m where email = :email", Member.class)
				.setParameter("email", email)
				.getResultList();
	}

	public List<Member> findByCompany(Company company) {
		return em.createQuery("select m from Member m where company = :company", Member.class)
				.setParameter("company", company)
				.getResultList();
	}
}
