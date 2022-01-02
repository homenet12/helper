package damo.helper.repository.querydsl;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import damo.helper.domain.QCompany;
import damo.helper.domain.QMember;
import damo.helper.domain.QQuestion;
import damo.helper.domain.QQuestionFile;
import damo.helper.domain.QQuestionReply;
import damo.helper.domain.QuestionStatus;
import damo.helper.login.MemberDto;
import damo.helper.request.QuestionSearchDto;
import damo.helper.response.QuestionViewResponse;
import damo.helper.response.QuestionsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionDtoRepository {

	private final JPAQueryFactory queryFactory;
	
	public Page<QuestionsResponse> findAll(MemberDto userDto,Pageable pageable, QuestionSearchDto search){
		
		QQuestion question = QQuestion.question;
		
		JPAQuery<QuestionsResponse> query = queryFactory.select(Projections
				.constructor(
						QuestionsResponse.class, 
						question.id,
						question.title,
						question.status,
						question.writer.name,
						question.writer.company.name,
						question.createdDate,
						question.completeDate,
						question.manager.name,
						question.files.size(),
						question.replys.size()))
				.from(question)
				.leftJoin(question.manager, QMember.member)
				.innerJoin(question.writer.company, QCompany.company)
				.where(companyNameLike(search.getCompanyName()),statusEq(search.getStatus()))
				.orderBy(question.createdDate.desc());
		
		
		if(!userDto.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			query = query.where(QCompany.company.id.eq(userDto.getCompany().getId()));
		}

		long count = queryFactory.select(question.count())
								.from(question)
								.leftJoin(question.manager, QMember.member)
								.innerJoin(question.writer.company, QCompany.company)
								.where(companyNameLike(search.getCompanyName()),statusEq(search.getStatus()))
								.fetchOne();
		
		
		List<QuestionsResponse> questions = query.offset(pageable.getOffset())
									.limit(pageable.getPageSize())
									.fetch();

		
		return new PageImpl<>(questions, pageable, count);
	}
	
	private BooleanExpression companyNameLike(String companyName) {
		if(!StringUtils.hasText(companyName)) {
			return null;
		}
		return QCompany.company.name.contains(companyName);
	}
	
	private BooleanExpression statusEq(String status) {
		if(!StringUtils.hasText(status)) {
			return null;
		}
		return QQuestion.question.status.eq(QuestionStatus.valueOf(status));
	}

	
	public QuestionViewResponse findResponseQuestion(Long questionId, MemberDto userDto) {
		QQuestion question = QQuestion.question;
		QQuestionFile questionFile = QQuestionFile.questionFile;
		
		return queryFactory.select(Projections
						.constructor(QuestionViewResponse.class, 
									question,
									question.files))
					.from(question)
					.leftJoin(question.files, questionFile)
					.where(question.id.eq(questionId))
					.fetchOne();
	}

	public int getMonthComplete(Long companyId) {
		QQuestion question = QQuestion.question;
		LocalDateTime now = LocalDateTime.now();
		
		return queryFactory.select(question)
							.from(question)
							.innerJoin(question.writer.company, QCompany.company)
							.where(question.writer.company.id.eq(companyId)
									.and(question.completeDate.between(now.with(firstDayOfMonth()), now.with(lastDayOfMonth()))))
							.fetch()
							.size();
	}
}
