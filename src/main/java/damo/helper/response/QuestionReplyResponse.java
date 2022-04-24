package damo.helper.response;

import java.time.format.DateTimeFormatter;

import damo.helper.domain.QuestionReply;
import lombok.Data;

@Data
public class QuestionReplyResponse {

	
	private Long questionReplyId;
	private String contents;
	private Long writerId;
	private String writerName;
	private String writeDate;
	private String updateDate;
	
	public QuestionReplyResponse(QuestionReply qr) {
		this.questionReplyId = qr.getId();
		this.contents = qr.getContents();
		this.writerId = qr.getMember().getId();
		this.writerName = qr.getMember().getName();
		this.writeDate = qr.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss"));
		this.updateDate = qr.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:ss"));
	}
}
