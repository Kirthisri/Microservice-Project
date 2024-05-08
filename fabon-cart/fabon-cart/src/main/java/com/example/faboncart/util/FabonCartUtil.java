package com.example.faboncart.util;

import java.math.BigInteger;
import java.util.Random;
import java.util.UUID;

public class FabonCartUtil {
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
	
	public static BigInteger generateRandomFabonOrderId(int bitLength, Random random) {
        BigInteger result;
        do {
            // Generate a random number with the specified bit length
            result = new BigInteger(bitLength, random);
            // Ensure the generated number is positive
        } while (result.compareTo(BigInteger.ZERO) <= 0);
        return result;
    }
}
