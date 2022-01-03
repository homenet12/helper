package damo.helper.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import damo.helper.domain.Company;
import damo.helper.domain.Member;
import damo.helper.repository.MemberRepository;
import damo.helper.request.JoinRequest;
import damo.helper.response.MemberDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService{

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Long save(JoinRequest joinDto, Company company) {
		Member member = Member.createMember(joinDto.getName(), joinDto.getEmail(), passwordEncoder.encode(joinDto.getPassword()), joinDto.getPhone(), company);
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

	public Member findOne(Long id) {
		return memberRepository.findById(id).orElseThrow();
	}

	public List<Member> findByEmail(String email){
		return memberRepository.findByEmail(email);
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<Member> members = memberRepository.findByEmail(email);
		if(members.isEmpty()) {
			throw new UsernameNotFoundException("회원이 존재하지 않습니다.");
		}
		
		Member findMember = members.get(0);
		
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_" +findMember.getRole().name().toUpperCase()));
		
		return new MemberDto(findMember.getId(), findMember.getName(), findMember.getCompany(), findMember.getEmail(), findMember.getPassword(), roles);
	}
}
