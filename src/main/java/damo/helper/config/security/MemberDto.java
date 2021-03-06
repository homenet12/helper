package damo.helper.config.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import damo.helper.domain.Company;
import lombok.Getter;

@Getter
public class MemberDto extends User{

	private Long id;
	private String name;
	private Company company;
	
	public MemberDto(Long id, String name, Company company, String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = id;
		this.name = name;
		this.company = company;
	}
	
	public boolean checkAdmin() {
		if(!this.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
			return false;
		}
		return true;
	}
}
