package damo.helper.repository.querydsl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import damo.helper.domain.QMember;
import damo.helper.domain.Role;
import damo.helper.response.MemberResponse;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDtoRepository {

	private final JPAQueryFactory queryFactory;

	public List<MemberResponse> findByAdmin() {
		QMember member = QMember.member;
		return queryFactory.select(
				Projections.constructor(MemberResponse.class,
				member.id,
				member.name))
				.from(member)
				.where(member.role.eq(Role.admin)).fetch();
	}
}
