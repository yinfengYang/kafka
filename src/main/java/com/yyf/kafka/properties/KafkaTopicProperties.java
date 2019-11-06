package com.yyf.kafka.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * 壹 * @Author: yang
 * 貮 * @Date: 2019/11/5 10:47
 */
@Data
@ConfigurationProperties("kafka.topic")
public class KafkaTopicProperties implements Serializable {

	private String groupId;

	private String[] topicName;


}
