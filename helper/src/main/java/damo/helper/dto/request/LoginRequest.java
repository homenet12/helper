package damo.helper.dto.request;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class LoginRequest {

	@NotEmpty(message = "email을 입력해주세요.")
	private String email;
	@NotEmpty(message = "password를 입력해주세요.")
	private String password;
}
