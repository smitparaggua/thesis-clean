package com.csg.warrior;

import com.csg.warrior.domain.MobileKey;

import java.security.SecureRandom;

public class KeyStringGenerator {

    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	private static final SecureRandom random = new SecureRandom();

	public String generateKeyString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < MobileKey.KEY_STRING_LENGTH; ++i) {
            int index = random.nextInt(ALLOWED_CHARACTERS.length());
            builder.append(ALLOWED_CHARACTERS.charAt(index));
        }
        return builder.toString();
    }
}