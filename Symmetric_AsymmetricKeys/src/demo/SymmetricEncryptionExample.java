package demo;

import java.util.Base64; // Encodes encrypted data to make it readable (as a string).

import javax.crypto.Cipher; // Provides encryption and decryption functionality.
import javax.crypto.KeyGenerator; // Generates a random Secret Key for AES encryption.
import javax.crypto.SecretKey; // Represents the secret key used for encryption & decryption.

// We’ll use AES (Advanced Encryption Standard), one of the most secure encryption methods.
public class SymmetricEncryptionExample {

	public static void main(String[] args) throws Exception {
		// Step 1: Generate a Secret Key 
	    KeyGenerator keyGenerator = KeyGenerator.getInstance("AES"); //  Creates an AES encryption key generator.
	    keyGenerator.init(256); // AES key size (128, 192, 256 bits) - Sets the key length (256 bits is the most secure).
	    SecretKey secretKey = keyGenerator.generateKey(); //  Generates a random secret key for encryption.
	
	    // Step 2: Encrypt the data
	    String originalText = "Hello, Symmetric Encryption!";
	    Cipher cipher = Cipher.getInstance("AES"); // Creates an instance of AES cipher
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey); // Set cipher to encryption mode
	    byte[] encryptedBytes = cipher.doFinal(originalText.getBytes()); // Encrypts the original text into byte format.
	    String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes); // Converts the encrypted bytes into a readable string.
	    System.out.println("🔒 Encrypted Text : " + encryptedText);
	
	    // Step 3: Decrypt the data
	    cipher.init(Cipher.DECRYPT_MODE, secretKey); // Uses the same secret key to decrypt the data.
	    byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText)); // Decodes and decrypts the encrypted text.
	    String decryptedText = new String(decryptedBytes); // Converts the decrypted bytes back into a readable string.
	    System.out.println("🔓 Decrypted Text: " + decryptedText);
	
	}
}

/*

Symmetric vs. Asymmetric Cryptography in Java
Cryptography is used to secure data by encrypting and decrypting it. There are two main types:
    Symmetric Cryptography → Same key is used for both encryption and decryption.
    Asymmetric Cryptography → Uses two keys (public & private) for encryption and decryption.

1. Symmetric Cryptography (Single Key)
🔑 What is it?
    Uses one key to both encrypt and decrypt data.
    Fast and used for bulk encryption (e.g., securing files, databases).
    Example algorithms: AES, DES, Blowfish.

🛠 Real-World Example: Lock & Key 🔐
Imagine a locker with a single key:
    You lock (encrypt) it with the same key.
    You unlock (decrypt) it with the same key.
    If someone steals the key, they can access the data.

✅ Advantages
✔ Fast & Efficient
✔ Good for large amounts of data
✔ Used in database encryption, SSL/TLS, file encryption

❌ Disadvantages
❌ Key must be kept secret
❌ If the key is stolen, all data is compromised

*/