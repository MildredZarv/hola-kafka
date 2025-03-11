package com.holakafka.producer.config;

import java.util.HashMap;
import java.util.Map;

import com.holakafka.producer.dto.User;

import io.confluent.kafka.serializers.KafkaAvroSerializer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {
	@Value("${spring.kafka.producer.bootstrap-servers}")
	private String bootstrapServers;
	
	public Map<String, Object> producerConfig(){
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
		properties.put("schema.registry.url", "http://127.0.0.1:8081");
		return properties;
	}
	
	@Bean
	public ProducerFactory<String, User> producerFactory(){
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}
	
	@Bean
	public KafkaTemplate<String, User> kafkaTemplate(ProducerFactory<String, User> producerFactory){
		return new KafkaTemplate<>(producerFactory);
	} 
} 
