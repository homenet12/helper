package damo.helper.repository.querydsl;

import java.util.List;

import damo.helper.response.MemberResponse;

public interface MemberCustomRepo {

	List<MemberResponse> findByAdmin();
}
