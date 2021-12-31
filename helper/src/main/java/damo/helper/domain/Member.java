package damo.helper.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends DateEntity {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;
	
	@Column(nullable = true)
	private String name;
	@Column(nullable = true, unique = true)
	private String email;
	@Column(nullable = true)
	private String password;
	@Column(nullable = true)
	private String phone;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@OneToMany(mappedBy = "member")
	private List<Manager> managers = new ArrayList<>(); 
	
	private Member(String name, String email, String password, String phone, Company company) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.role = Role.member;
		this.company = company;
	}
	
	public static Member createMember(String name, String email, String password, String phone, Company company) {
		Member member = new Member(name, email, password, phone, company);
		return member;
	}
	
	public void authorization() {
		this.role = Role.admin;
	}
	
	public void mySelfCheck(Long memberId) {
		if(!this.id.equals(memberId)) {
			throw new IllegalStateException("작성자 본인이 아닙니다.");
		}
	}
}
