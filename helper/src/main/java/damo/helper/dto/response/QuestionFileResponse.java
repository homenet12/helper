package damo.helper.dto.response;

import damo.helper.domain.QuestionFile;
import lombok.Getter;

@Getter
public class QuestionFileResponse {

	private Long id;
	private String realName;
	private String filePath;
	
	public QuestionFileResponse(QuestionFile file) {
		this.id = file.getId();
		this.realName = file.getRealName();
		this.filePath = file.getFilePath();
	}
}
