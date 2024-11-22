package com.yj.webhook.domain.cafe24.router;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.yj.webhook.domain.cafe24.filter.AuthenticationFilter;
import com.yj.webhook.domain.cafe24.handler.Cafe24CustomerHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebhookRouter {

	private final AuthenticationFilter authenticationFilter;
	private final Cafe24CustomerHandler cafe24CustomerHandler;

	@Bean
	public RouterFunction<ServerResponse> CustomerRouter(){
		return route()
			.path("/webhook/cafe24/customer", builder -> builder
				.nest(accept(APPLICATION_JSON), nestedBuilder -> nestedBuilder
					// 회원 등급 변경
					.POST("/change/group", cafe24CustomerHandler::changeCustomerGroup)
					// 회원 정보 변경
					.POST("/change/info", cafe24CustomerHandler::changeCustomerInformation)
					// 받아야 할 웹훅이 늘어난다면 아래에 추가
					)
				.filter(authenticationFilter))
			.build();
	}
}
