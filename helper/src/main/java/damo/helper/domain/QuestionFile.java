package damo.helper.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionFile extends DateEntity{

	@Id
	@GeneratedValue
	@Column(name = "question_file_id")
	private Long id;
	
	private String filePath;
	private String realName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private Question question; 
	
	public QuestionFile(String filePath, String realName, Question question) {
		this.filePath = filePath;
		this.realName = realName;
		this.question = question;
	}
	
	public static QuestionFile createFile(String filePath, String realName, Question question) {
		QuestionFile questionFile = new QuestionFile(filePath, realName, question);
		return questionFile;
	}
}
