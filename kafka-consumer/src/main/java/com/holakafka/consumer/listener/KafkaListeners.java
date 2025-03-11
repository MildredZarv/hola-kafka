package com.holakafka.consumer.listener;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.holakafka.consumer.model.User;
import com.holakafka.consumer.util.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaListeners {
	@KafkaListener(topics = "hola-kafka-user-topic", groupId = "my-group", containerFactory = "myAvroConsumerFactory") 
	public void read(ConsumerRecord<String, GenericRecord> userRecord, Acknowledgment acknowledgment) {
		User user = UserMapper.mapAvroToUser(userRecord.value());
		log.info("Message received with topic: {}, key: {}, value: {}", userRecord.topic(), userRecord.key(), user.toString());
		acknowledgment.acknowledge();
        log.info("acknowledged partition: {} offset: {}", userRecord.partition(), userRecord.offset());
	}
}
