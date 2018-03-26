package com.jcble.jcparking.common.dto.response;

/**
 * 公共响应实体
 * 
 * @author Jingdong Wang
 * 
 */
public class ResponseDto extends BaseRespDto {

	private Object payload;

	private Object meta;

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public Object getMeta() {
		return meta;
	}

	public void setMeta(Object meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return "ResponseDto [payload=" + payload + ", meta=" + meta + "]";
	}

}
