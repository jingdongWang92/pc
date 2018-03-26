/**
 * 
 */
package com.jcble.jcparking.common.model;

import java.io.Serializable;

import baseproj.common.dto.SimpleDto;
import baseproj.common.mybatis.page.PageParameter;

/**
 * The Class BaseDto.
 * 
 * @author Jingdong Wang
 */
public class BaseDto implements Serializable, SimpleDto {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 9071310834176274046L;

	/** The page. */
	protected PageParameter page;

	public PageParameter getPage() {
		return page;
	}

	public void setPage(PageParameter page) {
		this.page = page;
	}

}
