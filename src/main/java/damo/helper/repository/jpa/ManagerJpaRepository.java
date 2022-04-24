package damo.helper.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import damo.helper.domain.Company;
import damo.helper.domain.Manager;
import damo.helper.domain.Member;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ManagerJpaRepository {

	private final EntityManager em;
	
	public void save(Manager manager) {
		em.persist(manager);
	}
	
	public List<Manager> findByCompany(Company company){
		return em.createQuery("select m from Manager m where m.company = :company", Manager.class)
				.setParameter("company", company)
				.getResultList();
	}
	
	public List<Manager> findByMember(Member member){
		return em.createQuery("select m from Manager m where m.member = :member", Manager.class)
				.setParameter("member", member)
				.getResultList();
	}
	
	public Manager findOne(Long id) {
		return em.find(Manager.class, id);
	}
	
	public void delete(Manager manager) {
		em.remove(manager);
	}

	public List<Manager> findByMemberAndCompany(Member member, Company company) {
		return	em.createQuery("select m from Manager m where m.member = :member and m.company = :company", Manager.class)
				.setParameter("member", member)
				.setParameter("company", company)
				.getResultList();
	}
}
