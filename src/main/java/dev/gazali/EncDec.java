package dev.gazali;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Scanner;
import static java.lang.System.*;
import static java.util.Base64.*;
import static javax.crypto.Cipher.getInstance;

class EncDec {
	
	private final Scanner scanner;
	private final String key = "TheQuickBrownFoxTheQuickBrownFox";
	private static byte[] IV = new byte[16];
	
	EncDec() {
		scanner = new Scanner(System.in);
		getChoice();
	}
	
	@SuppressWarnings("InfiniteLoopStatement")
	private void getChoice() {
		try {
			while (true) {
				out.print("\nWhat would you like to do? (e)ncrypt, (d)ecrypt, (q)uit:\n");
				var response = Character.toLowerCase(scanner.nextLine().charAt(0));
				switch (response) {
					case 'e' -> encrypt();
					case 'd' -> decrypt();
					case 'q' -> quit();
					default -> out.println("Invalid choice.");
				}
			}
		} catch (Exception e) {
			err.println("Error: " + e);
		}
	}
	
	private void decrypt() {
		out.print("\nEnter message to decrypt:\n");
		var msgToDecrypt = scanner.nextLine();
		
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new javax.crypto.spec.IvParameterSpec(IV));
			byte[] decryptedData = cipher.doFinal(getDecoder().decode(msgToDecrypt));
			err.println("\nDecrypted Message: " + new String(decryptedData));
			out.println("\nEncrypted Key: " + getEncoder().encodeToString(key.getBytes()));
			out.println("Encrypted IV : " + getEncoder().encodeToString(IV));
		} catch (Exception e) {
			err.println("Error while decrypting: " + e);
			err.println("Please try again. (d, e, q)");
		}
	}
	
	private void encrypt() {
		out.print("\nEnter message to encrypt:\n");
		var msgToEncrypt = scanner.nextLine();
		
		try {
			SecretKeySpec secretkeySpec = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretkeySpec);
			IV = cipher.getIV();
			String encryptedData = getEncoder().encodeToString(cipher.doFinal(msgToEncrypt.getBytes()));
			err.println("\nEncrypted Message: " + encryptedData);
			out.println("\nEncrypted Key: " + getEncoder().encodeToString(key.getBytes()));
			out.println("Encrypted IV : " + getEncoder().encodeToString(IV));
		} catch (Exception e) {
			err.println("Error while encrypting: " + e);
			err.println("Please try again. (d, e, q)");
		}
	}
	
	private void quit() {
		out.println("\nGoodbye!");
		exit(0);
	}
}