package com.yj.webhook.domain.cafe24.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeCustomerGroup {
	private String mallId;
	private int eventShopNo;
	private String memberId;
	private String afterMemberGroupName;
}
