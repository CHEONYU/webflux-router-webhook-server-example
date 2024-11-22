package com.yj.webhook.domain.cafe24.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cafe24WebhookDto<T> {
	private int eventNo;
	private T resource;
}
