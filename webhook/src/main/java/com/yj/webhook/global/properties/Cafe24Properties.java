package com.yj.webhook.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "cafe24")
@Getter
@Setter
public class Cafe24Properties {
	private String webhookAuthenticationKey;
}
