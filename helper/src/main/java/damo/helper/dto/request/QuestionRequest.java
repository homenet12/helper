package damo.helper.dto.request;

import javax.validation.constraints.NotEmpty;

import damo.helper.domain.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuestionRequest {

	private Long id;
	
	@NotEmpty(message = "제목을 입력해주세요.")
	private String title;
	@NotEmpty(message = "내용을 입력해주세요.")
	private String contents;
	
	public QuestionRequest(Question question) {
		this.id = question.getId();
		this.title = question.getTitle();
		this.contents = question.getContents();
	}
}
