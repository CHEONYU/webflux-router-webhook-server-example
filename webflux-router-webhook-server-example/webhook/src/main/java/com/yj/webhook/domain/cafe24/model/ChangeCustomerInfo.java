package com.yj.webhook.domain.cafe24.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeCustomerInfo {
	private String mallId;
	private int eventShopNo;
	private String memberId;
}
