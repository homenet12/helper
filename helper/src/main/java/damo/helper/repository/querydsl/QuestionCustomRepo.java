package damo.helper.repository.querydsl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import damo.helper.config.security.MemberDto;
import damo.helper.request.QuestionSearchDto;
import damo.helper.response.QuestionViewResponse;
import damo.helper.response.QuestionsResponse;

public interface QuestionCustomRepo {

	Page<QuestionsResponse> findAll(MemberDto userDto,Pageable pageable, QuestionSearchDto search);
	QuestionViewResponse findResponseQuestion(Long questionId, MemberDto userDto);
	int getMonthComplete(Long companyId);
}
