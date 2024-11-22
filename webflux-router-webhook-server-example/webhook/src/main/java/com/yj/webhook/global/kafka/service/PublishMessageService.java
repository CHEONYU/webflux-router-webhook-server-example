package com.yj.webhook.global.kafka.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublishMessageService {

	private final KafkaSender<String, String> kafkaSender;


	public void publishMessage(String topicName, String message) {
		log.info("[SEND-KAFKA-REQ] => TOPIC : {} / MESSAGE : {}", topicName, message);
		sendToKafka(Mono.just(SenderRecord.create(new ProducerRecord<>(topicName, message), "")));
	}

	private void sendToKafka(Publisher<? extends SenderRecord<String, String, String>> senderRecord){
		kafkaSender.send(senderRecord)
			.publishOn(Schedulers.boundedElastic())
			.subscribe(senderResult ->
					log.info("[SEND-KAFKA-RES] <= Topic : {} / TimeStamp : {} /  Partition : {} / Offset : {}",
						senderResult.recordMetadata().topic(),
						senderResult.recordMetadata().timestamp(),
						senderResult.recordMetadata().partition(),
						senderResult.recordMetadata().offset())
				, error -> log.error("[SEND-KAFKA-FAIL] Cause : {}", error.getMessage())
			);
	}
}
