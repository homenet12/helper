package damo.helper.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager extends DateEntity {

	@Id
	@GeneratedValue
	@Column(name = "manager_id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;
	
	private Manager(Member member, Company company) {
		this.member = member;
		this.company = company;
	}

	public static Manager createManager(Member member, Company company) {
		if(member.getRole() != Role.admin) {
			throw new IllegalStateException("관리자 권한을 가진 사람만 담당자로 지정할 수 있습니다.");
		}
		Manager manager = new Manager(member, company);
		return manager;
	}
}
