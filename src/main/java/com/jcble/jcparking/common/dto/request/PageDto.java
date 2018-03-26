package com.jcble.jcparking.common.dto.request;

public class PageDto {

	private Integer pageIndex;
	
	private Integer pageSize;
	
	public PageDto() {}

	public PageDto(Integer pageIndex, Integer pageSize) {
		if(pageIndex == null) {
			this.pageIndex = 1;
		} else {
			this.pageIndex = pageIndex;
		}
		if(pageSize == null) {
			this.pageSize = 10;
		} else {
			this.pageSize = pageSize;
		}
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
