package damo.helper.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerRequest {

	private Long memberId;
	private List<Long> companyId;
}
