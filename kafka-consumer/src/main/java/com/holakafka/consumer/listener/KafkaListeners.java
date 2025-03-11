package com.holakafka.consumer.listener;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.holakafka.consumer.model.User;
import com.holakafka.consumer.util.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaListeners {
	@KafkaListener(topics = "hola-kafka-user-topic", groupId = "my-group", containerFactory = "myAvroConsumerFactory") 
	public void read(ConsumerRecord<String, GenericRecord> userRecord) {
		String key = userRecord.key();
		String topic = userRecord.topic();
		User user = UserMapper.mapAvroToUser(userRecord.value());
		log.info("Message received: topic: {}, key: {}, value: {}", topic, key, user.toString());
	}
}
