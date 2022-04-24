package damo.helper.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Company extends DateEntity {

	@Id
	@GeneratedValue
	@Column(name = "company_id")
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;
	
	@OneToMany(mappedBy = "company")
	private List<Manager> managers = new ArrayList<>();

	public Company(String name) {
		this.name = name;
	} 
	
}
