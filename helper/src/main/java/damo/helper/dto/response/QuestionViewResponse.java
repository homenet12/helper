package damo.helper.dto.response;

import java.util.ArrayList;
import java.util.List;

import damo.helper.domain.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionViewResponse extends QuestionsResponse{

	private Long writerId;
	private String contents;
	private List<QuestionFileResponse> files = new ArrayList<QuestionFileResponse>();
	
	public QuestionViewResponse(Question question, List<QuestionFileResponse> questionFileDto) {
		super(question);
		this.writerId = question.getWriter().getId();
		this.contents = question.getContents();
		this.files = questionFileDto;
	}
}
