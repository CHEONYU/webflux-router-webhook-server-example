package com.yj.webhook;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class WebhookApplication {
	public static void main(String[] args) {
		new SpringApplicationBuilder(WebhookApplication.class)
			.properties("spring.config.location=classpath:/application-${spring.profiles.active}.yml")
			.run(args);
	}

}
