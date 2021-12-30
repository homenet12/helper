package damo.helper.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponse {

	private Long id;
	private String name;
	
	public MemberResponse(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
