package damo.helper.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import damo.helper.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	List<Company> findByName(String name);

}
