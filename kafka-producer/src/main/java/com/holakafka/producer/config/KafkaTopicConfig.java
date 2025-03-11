package com.holakafka.producer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	@Value("${spring.kafka.producer.topic.name}")
    private String topic;
	
	@Bean
	public NewTopic generateTopic() {
		Map<String, String> configuration = new HashMap<String, String>();
		configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
		configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); //Retention time ms
		configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); //Message max size - default 1MB
		
		return TopicBuilder.name(topic)
				.partitions(1)
				.replicas(1)
				.configs(configuration)
				.build();
	}
}
