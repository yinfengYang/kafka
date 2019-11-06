package com.yyf.kafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * 壹 * @Author: yang
 * 貮 * @Date: 2019/11/5 10:55
 */
@Service
@Slf4j
public class IndicatorService {

	private final KafkaTemplate<Integer,String> kafkaTemplate;

	@Autowired
	public IndicatorService(KafkaTemplate kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@KafkaListener(topics = "#{kafkaTopicName}",groupId = "#{topicGroupId}")
	public void processMessage(ConsumerRecord<Integer,String> record){
		log.info("kafka processMessage start");
		log.info("processMessage, topic = {}, msg = {}", record.topic(), record.value());

		log.info("kafka processMessage end");
	}

	public void sendMessage(String topic,String data){
		log.info("kafka sendMessage start");

		ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, data);
		future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
			@Override
			public void onFailure(Throwable throwable) {
				log.error("kafka sendMessage error, ex = {}, topic = {}, data = {}", throwable, topic, data);
			}

			@Override
			public void onSuccess(SendResult<Integer, String> integerStringSendResult) {
				log.info("kafka sendMessage success topic = {}, data = {}",topic, data);
			}
		});
		log.info("kafka sendMessage end");
	}


}
