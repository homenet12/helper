package damo.helper.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import damo.helper.domain.Member;
import lombok.Data;

@Data
public class JoinRequest {

	@NotBlank(message = "이름을 입력해주세요.")
	private String name;
	@NotBlank(message = "메일을 입력해주세요.")
	@Email(message = "메일 형식으로 입력해주세요.")
	private String email;
	@NotBlank(message = "패스워드를 입력해주세요.")
	private String password;
	@NotBlank(message = "휴대전화번호를 입력해주세요.")
	private String phone;
	
	@NotNull(message = "소속 회사를 선택해주세요.")
	private Long companyId;
}
