package damo.helper.domain;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum QuestionStatus {
	wait, complete;
	
	public static List<String> statusList() {
		List<String> statusList = new ArrayList<String>();
		for(QuestionStatus q : EnumSet.allOf(QuestionStatus.class)) {
			statusList.add(q.name());
		}
		return statusList;
	}
}
