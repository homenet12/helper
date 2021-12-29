package damo.helper.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionReply extends DateEntity{

	@Id
	@GeneratedValue
	@Column(name = "question_reply_id")
	private Long id;
	
	private String contents;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;
	
	private QuestionReply(String contents, Member member, Question question) {
		this.contents = contents;
		this.member = member;
		this.question = question;
	}
	
	public static QuestionReply createReply (String contents, Member member, Question question) {
		return new QuestionReply(contents, member, question);
	}
	
	public void setContents(String contents) {
		this.contents = contents;
	}
}
