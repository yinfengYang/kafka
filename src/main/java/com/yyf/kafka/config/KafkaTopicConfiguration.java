package com.yyf.kafka.config;

import com.yyf.kafka.properties.KafkaTopicProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 壹 * @Author: yang
 * 貮 * @Date: 2019/11/5 10:52
 */
@Configuration
@EnableConfigurationProperties(KafkaTopicProperties.class)
public class KafkaTopicConfiguration {

	private final KafkaTopicProperties properties;


	public KafkaTopicConfiguration(KafkaTopicProperties properties) {
		this.properties = properties;
	}

	@Bean
	public String[] kafkaTopicName() {
		return properties.getTopicName();
	}

	@Bean
	public String topicGroupId() {
		return properties.getGroupId();
	}
}
