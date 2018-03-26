package com.jcble.jcparking.common.utils;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * json返回字段为null时将其设置为""
 * 
 * @author Jingdong Wang
 * 
 */
public class Object2JsonConverter extends ObjectMapper {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1007445710774196501L;

	public Object2JsonConverter() {
		super();
		// 允许单引号
		this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		// 字段和值都加引号
		this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
		this.setSerializationInclusion(Include.NON_NULL);// 空值不参加序列化
		// 空值处理为空串
		this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
			@Override
			public void serialize(Object value, JsonGenerator jg, SerializerProvider sp)
					throws IOException, JsonProcessingException {
				// jg.writeString("");
			}
		});
	}
}
