package damo.helper.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Deprecated
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends DateEntity {

	@Id
	@GeneratedValue
	@Column(name = "answer_id")
	private Long id;
	
	private String contents;
	
	@Enumerated(EnumType.STRING)
	private AnswerStatus status;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	private Answer(String contents, AnswerStatus status, Question question, Member member) {
		this.contents = contents;
		this.status = status;
		this.question = question;
		this.member = member;
	}
	
	public static Answer createAnswer(String contents, AnswerStatus status, Question question, Member member) {
		Answer answer = new Answer(contents, status, question, member);
		return answer;
	}
}
