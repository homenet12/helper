package damo.helper.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageParam {

	private int page = 1;
	private int pageSize = 10;
	private int startRow;
	private int endRow;
	
	public int getStartRow() {
		if(page == 1) {
			return 1;
		}
		return (page-1) * pageSize;
	}
	
	public int getEndRow() {
		return startRow + pageSize;
	}
}
