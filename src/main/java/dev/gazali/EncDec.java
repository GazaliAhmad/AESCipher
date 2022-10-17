package dev.gazali;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Scanner;
import static java.lang.System.*;
import static java.util.Base64.*;
import static javax.crypto.Cipher.getInstance;

class EncDec {
	
	private final Scanner scanner;
	private static final String key = "TheQuickBrownFoxTheQuickBrownFox";
	private static final byte[] IV = new byte[16];
	
	EncDec() {
		scanner = new Scanner(System.in);
		new SecureRandom().nextBytes(IV);
		getChoice();
	}
	
	private void getChoice() {
		try {
			while (true) {
				out.print("\nWhat would you like to do? (e)ncrypt, (d)ecrypt, (q)uit:\n");
				var response = Character.toLowerCase(scanner.nextLine().charAt(0));
				switch (response) {
					case 'e' -> encrypt();
					case 'd' -> decrypt();
					case 'q' -> {
						out.println("Goodbye!");
						exit(0);
					}
					default -> out.println("Invalid choice.");
				}
			}
		} catch (Exception e) {
			err.println("Error: " + e);
		}
	}
	
	private void encrypt() {
		out.print("\nEnter message to encrypt:\n");
		var message = scanner.nextLine();
		getCipher(Cipher.ENCRYPT_MODE, message);
	}
	
	private void decrypt() {
		out.print("\nEnter message to decrypt:\n");
		var message = scanner.nextLine();
		getCipher(Cipher.DECRYPT_MODE, message);
	}
	
	private void getCipher(int mode, String message) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = getInstance("AES/CBC/PKCS5Padding");
			cipher.init(mode, secretKeySpec, new IvParameterSpec(IV));
			byte[] data;
			if (mode == Cipher.ENCRYPT_MODE) {
				data = cipher.doFinal(message.getBytes());
				err.println("\nEncrypted Message: " + getEncoder().encodeToString(data));
			} else {
				data = cipher.doFinal(getDecoder().decode(message));
				err.println("\nDecrypted Message: " + new String(data));
			}
			out.println("\nEncrypted Key: " + getEncoder().encodeToString(key.getBytes()));
			out.println("Encrypted IV : " + getEncoder().encodeToString(IV));
		} catch (Exception e) {
			err.println("Error while encrypting: " + e);
			err.println("Please try again. (d, e, q)");
		}
	}
}
