package com.example.fabonuser.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class FabonUserReaderUtil {

	public static UUID generateFabonOrderId() {
		UUID randomUUID = UUID.randomUUID();
        System.out.println("Random UUID: " + randomUUID);
		return randomUUID;
	}
	
	public static UUID generateFabonOrderIdBasedonName() {
        // Generate a UUID based on a name-based namespace (e.g., DNS)
        String namespace = "example.com";
        String name = "example";
        UUID nameBasedUUID = UUID.nameUUIDFromBytes((namespace + name).getBytes());
        System.out.println("Name-based UUID: " + nameBasedUUID);
		return nameBasedUUID;
	}

}
