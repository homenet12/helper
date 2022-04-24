package damo.helper.response;

import damo.helper.domain.Company;
import lombok.Data;

@Data
public class CompanyResponse {

	private Long id;
	private String name;
	
	public CompanyResponse(Company company) {
		this.id = company.getId();
		this.name = company.getName();
	}
}
