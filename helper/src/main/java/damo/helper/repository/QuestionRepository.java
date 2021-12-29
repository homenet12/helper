package damo.helper.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import damo.helper.domain.Company;
import damo.helper.domain.QCompany;
import damo.helper.domain.QMember;
import  damo.helper.domain.QQuestion;
import damo.helper.domain.Question;
import damo.helper.dto.request.QuestionSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class QuestionRepository {

	private final JPAQueryFactory queryFactory;
	private final EntityManager em;
	
	public void save(Question question) {
		em.persist(question);
	}
	
	public Page<Question> findAll(Pageable pageable, QuestionSearchDto search){
		
		JPAQuery<Question> query = queryFactory.select(QQuestion.question)
				.from(QQuestion.question)
				.join(QMember.member).on(QQuestion.question.writer.id.eq(QMember.member.id))
				.fetchJoin()
				.join(QCompany.company).on(QMember.member.company.id.eq(QCompany.company.id))
				.fetchJoin()
				.where(companyNameLike(search.getCompanyName()));
		
		log.info("===============================");
		log.info("size : "+ query.fetch().size());
		log.info("===============================");
		int count = query.fetch().size();
		
		List<Question> question = query.offset(pageable.getOffset())
									.limit(pageable.getPageSize())
									.fetch();
		log.info("===============================");
		log.info("size : "+ query.fetch().size());
		log.info("===============================");
		
		return new PageImpl<>(question, pageable, count);
	}
	
	public Page<Question> findByCompany(Pageable pageable, Company company){
		
		int pageSize = pageable.getPageSize();
		int startRow = (pageable.getPageNumber()-1) * pageSize;
		
		JPAQuery<Question> query = queryFactory.select(QQuestion.question)
				.from(QQuestion.question)
				.join(QMember.member).on(QQuestion.question.writer.id.eq(QMember.member.id))
				.where(QCompany.company.id.eq(company.getId()));
		int count = query.fetch().size();
		
		List<Question> question = query.offset(startRow)
									.limit(pageSize)
									.fetch();
		log.info("size : "+ query.fetch().size());
		return new PageImpl<>(question, pageable, count);
	}
	
	private BooleanExpression companyNameLike(String companyName) {
		if(!StringUtils.hasText(companyName)) {
			return null;
		}
		return QCompany.company.name.like(companyName);
	}
	
	public Question findOne(Long id) {
		return em.find(Question.class, id);
	}
}
