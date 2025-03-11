package com.holakafka.producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.holakafka.producer.dto.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holakafka.producer.service.KafkaProducerService;

@RestController
@RequestMapping("/api/v1/users")
public class KafkaProducerController {
	private final KafkaProducerService producerService;
	
	public KafkaProducerController(KafkaProducerService producerService) {
		this.producerService = producerService;
	}
	
	@PostMapping("/publish")
	public ResponseEntity<Void> send(@RequestBody User request){
		producerService.sendRequest(request);
		return ResponseEntity.accepted().build();
	}
}
