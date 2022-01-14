package damo.helper.response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import damo.helper.domain.Question;
import damo.helper.domain.QuestionFile;
import damo.helper.domain.QuestionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionsResponse {

	private Long id;
	private String title;
	private QuestionStatus status;
	private String writerName;
	private String companyName;
	
	private Long managerId;
	private String managerName;
	
	private String writeDate;
	private String updateDate;
	private String completeDate;
	private int replyCount;
	private int fileCount;
	
	public QuestionsResponse(Question question) {
		this.id = question.getId();
		this.title = question.getTitle();
		this.status = question.getStatus();
		this.writerName = question.getWriter().getName();
		this.companyName = question.getWriter().getCompany().getName();
		if(question.getManager() != null) {
			this.managerId = question.getManager().getId();
			this.managerName = question.getManager().getName();
		}
		this.writeDate = question.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		this.updateDate = question.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
	}

	public QuestionsResponse(Long id, String title, QuestionStatus status, String writerName, String companyName,
			LocalDateTime writeDate, LocalDateTime completeDate, String managerName, int fileCount, int replyCount) {
		this.id = id;
		this.title = title;
		this.status = status;
		this.writerName = writerName;
		this.companyName = companyName;
		this.writeDate = writeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		if(completeDate != null) {
			this.completeDate = completeDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		}
		this.managerName = managerName;
		this.fileCount = fileCount;
		this.replyCount = replyCount;
	}
}
