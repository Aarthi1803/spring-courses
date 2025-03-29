package demo;

import java.security.KeyPair;
import java.security.KeyPairGenerator; // Generates a public-private key pair for encryption.
import java.security.PrivateKey; // Used for decryption.
import java.security.PublicKey; // Used for encryption.
import java.util.Base64;

import javax.crypto.Cipher;

// We’ll use RSA (Rivest-Shamir-Adleman), a common asymmetric encryption method.
public class AsymmetricEncryptionExample {

	public static void main(String[] args) throws Exception {
		// Step 1: Generate RSA Key Pair 🔑
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA"); // Generates a public-private key pair for encryption - Creates an RSA key pair generator.
        keyPairGenerator.initialize(2048); // Generates a 2048-bit key (secure for encryption).
        KeyPair keyPair = keyPairGenerator.generateKeyPair(); // Generates a public-private key pair.
        PublicKey publicKey = keyPair.getPublic(); // Used for encryption - Retrieves the public key.
        PrivateKey privateKey = keyPair.getPrivate(); // Used for decryption - Retrieves the private key.

        // Step 2: Encrypt the data using the Public Key
        String originalText = "Hello, Asymmetric Encryption!";
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); // Encrypts data using the public key.
        byte[] encryptedBytes = cipher.doFinal(originalText.getBytes()); // Encrypts the message.
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes); // Converts the encrypted bytes into readable text.
        System.out.println("🔒 Encrypted Text: " + encryptedText);

        // Step 3: Decrypt the data using the Private Key
        cipher.init(Cipher.DECRYPT_MODE, privateKey); // Decrypts data using the private key.
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText)); // Decrypts the message back into original form.
        String decryptedText = new String(decryptedBytes);
        System.out.println("🔓 Decrypted Text: " + decryptedText);
	}

}


/*

Asymmetric Cryptography (Two Keys)
🔑 What is it?
    Uses two keys:
        Public Key → Used for encryption (can be shared).
        Private Key → Used for decryption (kept secret).
    Slower than symmetric but more secure.
    Example algorithms: RSA, ECC, Diffie-Hellman.

🛠 Real-World Example: Mailbox System 📬
    Public Key: Anyone can drop a letter in the mailbox (encrypt data).
    Private Key: Only the owner (you) can unlock the mailbox and read the letter (decrypt data).
    Even if someone intercepts the letter, they can’t read it without the private key.

✅ Advantages
✔ Highly secure (since private key is never shared)
✔ Used in digital signatures, secure emails, HTTPS, JWT, cryptocurrency

❌ Disadvantages
❌ Slower than symmetric encryption
❌ Not ideal for large files


Symmetric vs. Asymmetric Cryptography: Key Differences
Feature					Symmetric Encryption (AES)							Asymmetric Encryption (RSA)
Keys					Same key for encryption & decryption				Uses Public & Private Keys
Speed					Fast ⚡												Slower 🐢
Security				Less Secure (key must be shared)					More Secure (private key is secret)
Use Cases				File encryption, SSL/TLS, database encryption		Digital signatures, HTTPS, secure email

🔹 When to Use Which?
Scenario								Best Choice
Encrypting files						Symmetric (AES)
Encrypting database passwords			Symmetric (AES)
HTTPS (SSL/TLS)	Combination 			(Asymmetric for handshake, Symmetric for data)
Sending encrypted emails				Asymmetric (RSA)
Digital Signatures (JWT, Blockchain)	Asymmetric (RSA, ECC)

🚀 Summary
✅ Symmetric Cryptography (AES) is fast & efficient, used for encrypting files & databases.
✅ Asymmetric Cryptography (RSA) is highly secure, used for digital signatures, HTTPS, and authentication.
✅ In real-world applications, we combine both:

    Use Asymmetric (RSA) for secure key exchange.
    Use Symmetric (AES) for fast encryption.
*/