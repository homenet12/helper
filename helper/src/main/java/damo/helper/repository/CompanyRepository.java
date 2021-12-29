package damo.helper.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import damo.helper.domain.Company;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {

	private final EntityManager em;
	
	public void save(Company company) {
		em.persist(company);
	}
	
	public List<Company> findAll(){
		return em.createQuery("select c from Company c", Company.class)
				.getResultList();
	}
	
	public Company findOne(Long id) {
		return em.find(Company.class, id);
	}

	public List<Company> findByName(String name) {
		return em.createQuery("select c from Company c where name = :name", Company.class)
				.setParameter("name", name)
				.getResultList();
	}
}
