package com.holakafka.consumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;

@Configuration
public class KafkaConsumerConfig {
	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServer;
	
	public Map<String, Object> consumerConfig(){
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
		properties.put("schema.registry.url", "http://127.0.0.1:8081");
		properties.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, false);
		return properties;
	}
	
	@Bean
	public ConsumerFactory<String, KafkaAvroDeserializer> consumerAvroFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerConfig());
	}
	
	@Bean(name = "myAvroConsumerFactory")
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, KafkaAvroDeserializer>> factory (
			ConsumerFactory<String, KafkaAvroDeserializer> consumerAvroFactory){
		ConcurrentKafkaListenerContainerFactory<String, KafkaAvroDeserializer> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
		factory.setConsumerFactory(consumerAvroFactory);
		return factory;
	} 

} 
