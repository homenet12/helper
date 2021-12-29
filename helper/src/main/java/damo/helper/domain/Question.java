package damo.helper.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ch.qos.logback.classic.Logger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends DateEntity{

	@Id
	@GeneratedValue
	@Column(name = "question_id")
	private Long id;
	
	@Column(nullable = true)
	private String title;
	
	@Lob
	@Column(nullable = true)
	private String contents;
	
	@Enumerated(EnumType.STRING)
	private QuestionStatus status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "writer_id")
	private Member writer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	private Member manager;
	
	private LocalDateTime completeDate;
	
	@OneToMany(mappedBy = "question")
	private List<QuestionFile> files = new ArrayList<>();
	
	@OneToMany(mappedBy = "question")
	private List<QuestionReply> replys = new ArrayList<>();

	private Question(String title, String contents, Member writer, QuestionStatus status) {
		this.title = title;
		this.contents = contents;
		this.writer = writer;
		this.status = status;
	}
	
	public static Question createQuestion(String title, String contents, Member writer) {
		Question question = new Question(title, contents, writer, QuestionStatus.wait);
		return question;
	}
	
	public void setStatus(QuestionStatus status) {
		if(this.manager == null) {
			throw new IllegalStateException("담당자를 먼저 지정해주세요.");
		}
		
		if(status.equals(QuestionStatus.complete)) {
			completeDate = LocalDateTime.now();
		}
		this.status = status;
	}
	
	public void setManager(Member manager) {
		this.manager = manager;
	}
	
	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
	
	public void selfCompanyCheck(Long companyId) {
		if(!getWriter().getCompany().getId().equals(companyId)) {
			throw new IllegalStateException("자사 문의만 확인 하실 수 있습니다.");
		}
	}
	
	public void selfAuthentication(Long memberId) {
		if(!getWriter().getId().equals(memberId)) {
			throw new IllegalStateException("본인만 수정할 수 있습니다.");
		}
	}
	
}
