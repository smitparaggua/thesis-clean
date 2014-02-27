package com.csg.warrior;

import com.csg.warrior.domain.MobileKey;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;

public class KeyStringGenerator {
	
	static KeyPairGenerator keyGen = null;
	static SecureRandom random = null;
	
	public KeyStringGenerator() {
		try {
			if (keyGen == null && random == null) {
				keyGen = KeyPairGenerator.getInstance("RSA");
				random = SecureRandom.getInstance("SHA1PRNG", "SUN");
				keyGen.initialize(MobileKey.KEY_STRING_LENGTH, random);
			}
		}
		catch (Exception e) {
			System.err.println("Caught exception " + e.toString());
		}
	}
	
	public static String generateKeyString() {
        try {          
            KeyPair pair = keyGen.generateKeyPair();
            PrivateKey priv = pair.getPrivate();

            //print as hex string
            byte[] publicKey = priv.getEncoded();
            StringBuffer retString = new StringBuffer();
            for (int i = 0; i < publicKey.length; ++i) {
                retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
            }
            return retString.toString();
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
            return null;
        }
    }
}