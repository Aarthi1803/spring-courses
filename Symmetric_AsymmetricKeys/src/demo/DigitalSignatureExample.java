package demo;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class DigitalSignatureExample {

	public static void main(String[] args)  throws Exception {
		// Step 1: Generate RSA Key Pair 🔑 - This is like a university having a unique secret stamp
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA"); // RSA Algorithm is used for encryption & signing.
        keyPairGen.initialize(2048); // Strong security with 2048-bit key
        KeyPair keyPair = keyPairGen.generateKeyPair(); // Generates public-private key pair
        PrivateKey privateKey = keyPair.getPrivate(); // Private Key → Used for signing.
        PublicKey publicKey = keyPair.getPublic(); // Public Key → Used for verification.

        // Step 2: Create Data to Sign 📄 - This is like a university creating a certificate document.
        String message = "This is a confidential document.";
        byte[] messageBytes = message.getBytes(); // The message (data) is converted to bytes so it can be signed.

        // Step 3: Sign the Data with the Private Key ✍️ - This is like a university stamping the certificate with a secret seal 
        Signature signature = Signature.getInstance("SHA256withRSA"); // Using SHA-256 hashing with RSA encryption
        signature.initSign(privateKey); // Use private key to sign
        signature.update(messageBytes); // Loads the message into the signature algorithm.
        byte[] digitalSignature = signature.sign(); // Generate the digital  signature

        // Convert to Base64 for easy reading
        String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);
        System.out.println("🔏 Digital Signature: " + encodedSignature);

        // Step 4: Verify the Signature with the Public Key 🔍 - This is like an employer checking a university stamp to verify if the certificate is genuine 
        signature.initVerify(publicKey); // Use public key to verify
        signature.update(messageBytes); //  Loads the original data.
        boolean isVerified = signature.verify(digitalSignature); // Check if signature is valid or fake 

        System.out.println("✅ Signature Verified: " + isVerified); //  If true, the document is authentic. If false, the document was tampered.
    }

}


/*

A Digital Signature is a cryptographic technique used to verify the authenticity and integrity of digital data. 
It ensures that the data has not been tampered with and was sent by a trusted source.
🔍 What is a Digital Signature?

A digital signature is similar to a handwritten signature or a seal but much more secure.
It uses asymmetric cryptography to:
    Sign Data → Using a private key (only the sender has it).
    Verify Signature → Using a public key (anyone can verify).

Real-World Example: Document Verification:
Imagine a university issuing digital certificates to students:

    The university signs the certificate using its private key.
    Students receive the signed certificate.
    Employers verify the signature using the university’s public key.
        If the signature is valid, the certificate is genuine. ✅
        If the signature is invalid, the certificate was forged. ❌

Steps in Java Digital Signature
    Generate Key Pair (Public & Private Keys)
    Sign the Data using the Private Key
    Verify the Signature using the Public Key
    
Summary
    Digital Signatures ensure data authenticity & integrity.
    Real-world use cases: University Certificates, Banking, Software Updates, Secure Emails.
    Java Implementation:
        Sign Data using Signature.sign() with the private key.
        Verify Signature using Signature.verify() with the public key.
*/