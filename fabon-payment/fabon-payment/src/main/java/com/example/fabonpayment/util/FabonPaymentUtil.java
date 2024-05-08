package com.example.fabonpayment.util;

import java.util.Random;

public class FabonPaymentUtil {

	public static long generateRandomTransactionId() {
		Random random = new Random();
		// Generate a random number between 100000000000 and 999999999999
		long randomNumber = random.nextLong() % 900000000000L + 100000000000L;
		return Math.abs(randomNumber); // Ensure it's positive

	}
}
