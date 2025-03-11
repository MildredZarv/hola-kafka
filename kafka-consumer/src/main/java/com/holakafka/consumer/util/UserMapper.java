package com.holakafka.consumer.util;

import org.apache.avro.generic.GenericRecord;

import com.holakafka.consumer.model.User;

public class UserMapper {
	public static User mapAvroToUser(GenericRecord payload) {
		User user = new User();
		user.setId(Long.valueOf(payload.get("id").toString()));
		user.setName(payload.get("name").toString());
		user.setEmail(payload.get("email").toString());
		user.setPhoneNumber(payload.get("phoneNumber").toString());
		return user;
	}
}
