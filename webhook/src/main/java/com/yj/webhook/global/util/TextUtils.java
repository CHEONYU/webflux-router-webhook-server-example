package com.yj.webhook.global.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TextUtils {
	private static final ObjectMapper objectMapper = new ObjectMapper()
		.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

	private TextUtils(){
		throw new IllegalStateException("TEXT UTIL IS A PRIVATE CLASS");
	}

	/**
	 * objectMapper.writeValueAsString 메소드 try/catch 처리
	 **/
	public static String convertObjectToJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			log.error("[CONVERT-OBJECT-TO-JSON-STRING] Object Type : {}", object.getClass());
		}
		return StringUtil.EMPTY_STRING;
	}

	/**
	 * objectMapper.readValue 메소드 try/catch 처리
	 **/
	public static <T> T convertStringToObject(String str, TypeReference<T> typeReference) {
		try {
			return objectMapper.readValue(str, typeReference);
		} catch (IOException e) {
			log.error("[CONVERT-STRING-TO-OBJECT] String : {} -> Object Class : {}", str, typeReference.getClass().getName());
		}
		return null;
	}

}
