package com.yj.webhook.domain.cafe24.model;

import org.springframework.web.reactive.function.server.ServerRequest;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CollectorTopicMessageDto {
	private String actionCode;
	@JsonProperty("x_trace_id")
	private String xTraceId;
	private String mallId;
	private int shopNo;
	private String[] customerId;
	private int eventNo;

	public static CollectorTopicMessageDto fromChangeCustomerGroupCafe24Webhook(Cafe24WebhookDto<ChangeCustomerGroup> webhook, ServerRequest serverRequest) {
		return CollectorTopicMessageDto.builder()
			.actionCode("webhook-customer-group")
			.xTraceId(serverRequest.headers().firstHeader("X-Trace-ID"))
			.mallId(webhook.getResource().getMallId())
			.shopNo(webhook.getResource().getEventShopNo())
			.customerId(webhook.getResource().getMemberId().replace(" ","").split(","))
			.eventNo(webhook.getEventNo())
			.build();
	}

	public static CollectorTopicMessageDto fromChangeCustomerInfoCafe24Webhook(Cafe24WebhookDto<ChangeCustomerInfo> webhook, ServerRequest serverRequest) {
		String[] customerIdArr = new String[1];
		customerIdArr[0] = webhook.getResource().getMemberId();

		return CollectorTopicMessageDto.builder()
				.actionCode("webhook-customer-info")
				.xTraceId(serverRequest.headers().firstHeader("X-Trace-ID"))
				.mallId(webhook.getResource().getMallId())
				.shopNo(webhook.getResource().getEventShopNo())
				.customerId(customerIdArr)
				.eventNo(webhook.getEventNo())
				.build();
	}
}
