package damo.helper.request;

import lombok.Data;

@Data
public class QuestionReplyRequest {

	private Long questionId;
	private String contents;
}
