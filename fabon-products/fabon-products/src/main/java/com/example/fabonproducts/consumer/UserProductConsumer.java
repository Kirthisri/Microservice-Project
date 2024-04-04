package com.example.fabonproducts.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserProductConsumer {
	
	/*
	 * @Payload annotations are used to map the event and the userProducts object
	 * from the Kafka message. The event parameter will hold the event string, and
	 * the userProducts parameter will hold the user's cart products object
	 */	
	@KafkaListener(topics = "USER-CART-DETAIL", groupId = "group_id")
	public void consumeUserCartDetail(@Payload String event, @Payload String userProducts) {
		if ("addToCart".equals(event)) {
			// Process the userProducts object for cart addition
			System.out.println("Received deleteFromCart event: " + userProducts);			
		}
		if ("deleteFromCart".equals(event)) {
			// Process the userProducts object for cart deletion
			System.out.println("Received deleteFromCart event: " + userProducts);
		}
	}
}