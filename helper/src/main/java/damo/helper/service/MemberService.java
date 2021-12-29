package damo.helper.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Company;
import damo.helper.domain.Member;
import damo.helper.dto.request.JoinRequest;
import damo.helper.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;
	
	@Transactional
	public Long save(JoinRequest joinDto, Company company) {
		Member member = Member.createMember(joinDto.getName(), joinDto.getEmail(), joinDto.getPassword(), joinDto.getPhone());
		validationDuplicateMember(member);
		member.setCompany(company); //여기까지 member 생성
		memberRepository.save(member);
		return member.getId();
	}
	
	@Transactional
	public Long save(Member member) {
		validationDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validationDuplicateMember(Member member) {
		List<Member> findMember = memberRepository.findByEmail(member.getEmail());
		if(!findMember.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 EMAIL 입니다.");
		}
	}
	
	public List<Member> findByCompany(Company company){
		return memberRepository.findByCompany(company);
	}

	public Member findOne(Long id) {
		return memberRepository.findOne(id);
	}
	
	public List<Member> findAll(){
		return memberRepository.findAll();
	}

	public List<Member> findByEmail(String email){
		return memberRepository.findByEmail(email);
	}
}