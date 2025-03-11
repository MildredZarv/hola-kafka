package com.holakafka.producer.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.holakafka.producer.dto.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaProducerService {
	
	@Value("${spring.kafka.producer.topic.name}")
    private String topic;
	
	private KafkaTemplate<String, User> kafkaTemplate;
	
	public KafkaProducerService(KafkaTemplate<String, User> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendRequest(User user) {
		kafkaTemplate.send(topic, user);
		log.info("Message sent with payload: {}", user);
	}

}
