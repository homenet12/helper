package damo.helper.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import damo.helper.repository.querydsl.MemberDtoRepository;
import damo.helper.response.MemberResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

	private final MemberDtoRepository memberRepository;
	
	@GetMapping("/api/v1/member/admin")
	public ResponseEntity<List<MemberResponse>> findAdminMember(){
		return new ResponseEntity<>(memberRepository.findByAdmin(), HttpStatus.OK);
	}
}
