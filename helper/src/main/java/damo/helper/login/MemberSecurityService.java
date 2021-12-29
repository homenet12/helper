package damo.helper.login;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import damo.helper.domain.Member;
import damo.helper.service.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberSecurityService implements UserDetailsService{

	private final MemberService memberService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<Member> members = memberService.findByEmail(email);
		if(members.isEmpty()) {     
			throw new UsernameNotFoundException("회원이 존재하지 않습니다.");
		}
		
		Member findMember = members.get(0);
		
		Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(findMember.getRole().name()));
		
		return new MemberDto(findMember.getId(), findMember.getName(), findMember.getCompany(), findMember.getEmail(), findMember.getPassword(), roles);
	}

}
