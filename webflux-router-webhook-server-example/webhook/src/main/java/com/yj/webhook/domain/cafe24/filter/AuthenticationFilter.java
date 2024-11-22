package com.yj.webhook.domain.cafe24.filter;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFilterFunction;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.yj.webhook.global.constant.CustomErrorType;
import com.yj.webhook.global.properties.Cafe24Properties;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {
	private final Cafe24Properties cafe24Properties;

	public Mono<ServerResponse> filter(ServerRequest serverRequest, HandlerFunction<ServerResponse> handlerFunction) {
		String xApiKey = serverRequest.headers().firstHeader("X-API-Key");
		String xTraceId = serverRequest.headers().firstHeader("X-Trace-ID");

		// X-API-KEY 가 동일한지 확인한다. 다를 경우 이상 요청으로 간주한다.
		if(!cafe24Properties.getWebhookAuthenticationKey().equals(xApiKey)) {
			return serverRequest.bodyToMono(String.class)
				.flatMap(body -> {
					log.error("[FILTER] ERROR : header X-API-KEY is wrong, ORIGIN X-API-KEY : {}, X-TRACE-ID : {}, BODY : {} ", xApiKey, xTraceId, body);
					return ServerResponse.status(HttpStatus.FORBIDDEN.value()).build();
				});
		}

		// X-Trace-ID 가 있는지 확인한다. 없을 경우 이상 요청으로 간주한다.
		if(!StringUtils.hasText(xTraceId)) {
			return ServerResponse.status(CustomErrorType.NO_X_TRACE_ID_ERROR.getStatus())
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(CustomErrorType.NO_X_TRACE_ID_ERROR));
		}

		return handlerFunction.handle(serverRequest);
	}
}
