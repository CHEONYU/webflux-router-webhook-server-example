package com.yj.webhook.domain.cafe24.handler;

import static com.yj.webhook.domain.cafe24.model.CollectorTopicMessageDto.*;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.yj.webhook.domain.cafe24.model.Cafe24WebhookDto;
import com.yj.webhook.domain.cafe24.model.ChangeCustomerGroup;
import com.yj.webhook.domain.cafe24.model.ChangeCustomerInfo;
import com.yj.webhook.domain.cafe24.model.CollectorTopicMessageDto;
import com.yj.webhook.global.kafka.service.PublishMessageService;
import com.yj.webhook.global.util.TextUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RequiredArgsConstructor
@Component
public class Cafe24CustomerHandler {

	private final PublishMessageService publishMessageService;

	@Value("${kafka.topic.name}")
	private String TOPIC_WEBHOOK_CUSTOMER;

	// 회원 등급 변경 > kafka 메시지 발행
	public Mono<ServerResponse> changeCustomerGroup(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(String.class)
				.publishOn(Schedulers.boundedElastic())
				.flatMap(body -> {
					log.info("[WEBHOOK-START] Change Customer Group : {}", body);
					Cafe24WebhookDto<ChangeCustomerGroup> changeCustomerGroupCafe24Webhook = TextUtils.convertStringToObject(body, new TypeReference<>() {});
					CollectorTopicMessageDto collectorTopicMessage = fromChangeCustomerGroupCafe24Webhook(Objects.requireNonNull(changeCustomerGroupCafe24Webhook), serverRequest);

					publishMessageService.publishMessage(TOPIC_WEBHOOK_CUSTOMER, TextUtils.convertObjectToJson(collectorTopicMessage));

					return ServerResponse.status(HttpStatus.OK.value()).build();
				}).onErrorResume(e->{
					return Mono.error(e.getCause());
				});
	}

	// 회원 정보 변경 > kafka 메시지 발행
	public Mono<ServerResponse> changeCustomerInformation(ServerRequest serverRequest) {
		return serverRequest.bodyToMono(String.class)
				.publishOn(Schedulers.boundedElastic())
				.flatMap(body -> {
					log.info("[WEBHOOK-START] Change Customer Info : {}", body);
					Cafe24WebhookDto<ChangeCustomerInfo> changeCustomerInfoCafe24Webhook = TextUtils.convertStringToObject(body, new TypeReference<>() {});
					CollectorTopicMessageDto collectorTopicMessage = fromChangeCustomerInfoCafe24Webhook(Objects.requireNonNull(changeCustomerInfoCafe24Webhook), serverRequest);

					publishMessageService.publishMessage(TOPIC_WEBHOOK_CUSTOMER, TextUtils.convertObjectToJson(collectorTopicMessage));

					return ServerResponse.status(HttpStatus.OK.value()).build();
				}).onErrorResume(e->{
					return Mono.error(e.getCause());
				});
	}
}
