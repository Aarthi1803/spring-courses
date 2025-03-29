package com.telusko.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service // Declares this class as a Spring Service so it can be autowired in other parts of the application.
public class JwtService { // JWT Generation & Validation 
	
	private static final String SECRET = "TmV3U2VjcmV0S2V5Rm9ySldUU2lnbmluZ1B1cnBvc2VzMTIzNDU2Nzg=\r\n"; // Secret key used for signing JWTs. This key ensures that the token is valid and not tampered. either use hardcoded secretkey - "SECRET" or use below method - generateSecretKey() generates a new one dynamically.

	private String secretKey;
	
	public JwtService() {
		secretKey = generateSecretKey(); // either use hardcoded secretkey - "SECRET" or use method - generateSecretKey() generates a new one dynamically.
	}
	
	public String generateSecretKey() { // either use hardcoded secretkey - "SECRET" or use method - generateSecretKey() generates a new one dynamically.
		
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256"); //  Generates a random secret key using HMAC SHA-256.
			SecretKey secretKey = keyGenerator.generateKey();
			System.out.println("Secret key : " + secretKey.toString()); 
			return Base64.getEncoder().encodeToString(secretKey.getEncoded()); //  Encodes the key into Base64 format (for secure storage).
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error generating secret key", e);
		}
		
	}
	
	// Generates a JWT for everytime user logging
	public String generateToken(String username) { //  Creates a JWT token with: Username, Issued time, Expiration (1 hour), Signature using secret key
		Map<String, Object> claims = new HashMap<>(); // payload in JWT have some data about the user which is claim. A claim will have username, expiry date of JWT, time of issuing of JWT etc.
		return Jwts.builder()
				   .setClaims(claims)
				   .setSubject(username) // Creates a JWT token with: Username as the subject.
				   .setIssuedAt(new Date(System.currentTimeMillis())) // Creates a JWT token with: Issued time (iat).
				   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // setting expiration for 1 hour. Creates a JWT token with: Expiration time (exp).
				   .signWith(getKey(), SignatureAlgorithm.HS256) // Signs the token with a secret key using HS256 algorithm.
				   .compact();
	}
	
	private Key getKey() {
//		byte[] keyBytes = Decoders.BASE64.decode(SECRET); // either use hardcoded secretkey - "SECRET" or use method - generateSecretKey() generates a new one dynamically.
		byte[] keyBytes = Decoders.BASE64.decode(secretKey); // either use hardcoded secretkey - "SECRET" or use method - generateSecretKey() generates a new one dynamically.
		return Keys.hmacShaKeyFor(keyBytes); // Converts the Base64 secret key into a secure HMAC key.
	}

	public String extractUserName(String token) { // extract the username from jwt token
		return extractClaim(token, Claims::getSubject); // getSubject gives username. Uses getSubject() because the username is stored as the subject of the token.
	} 

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) { // Extracting Any Claim from a Token  (like username, expiration time).
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
	
	private Claims extractAllClaims(String token) { // Extracting All Claims from a Token - Parses the JWT token and retrieves all claims.
		return Jwts.parserBuilder()
					.setSigningKey(getKey())
					.build().parseClaimsJws(token).getBody();
	}
	
	public boolean validateToken(String token, UserDetails userDetails) { // Validating a JWT Token. Checks if token is valid  and belongs to the correct user.
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) { // Checking if a Token is Expired -  Returns true if the token’s expiration time has passed.
        return extractExpiration(token).before(new Date()); // If expiration time is before the current time it will return true
    }

    private Date extractExpiration(String token) { // Extracting Expiration Time - Gets the expiration time from the JWT token.
        return extractClaim(token, Claims::getExpiration);
    }

}


/*

What is JwtService.java?
✔ Handles JWT (JSON Web Token) creation and validation.
✔ Generates a token when a user logs in.
✔ Extracts username and expiration time from a JWT.
✔ Checks if the token is valid.

Real-World Analogy: Boarding Pass at an Airport
    When a passenger checks in, they get a boarding pass (JWT Token).
    The boarding pass contains passenger details (claims like username, issued time, expiration).
    Before boarding, the security team scans the pass to verify the passenger.
    If the pass is expired or belongs to another passenger, access is denied.

✔ In our application, JWT acts like a boarding pass, and JwtService is responsible for issuing and verifying it.


How This Works in the Application:

1️. User Logs In (POST /login)
    If credentials are correct, JWT is generated and sent to the user.

2️. User Accesses Secure API (GET /secure-data)
    User sends JWT in the request header (Authorization: Bearer <TOKEN>).

3️. JwtFilter Intercepts Request
    Extracts username, validates token, and authenticates user.

4️. User is Granted Access if JWT is Valid

✔ JWT is like a boarding pass that grants access to secure resources.
✔ JWT is stateless (no need for server-side sessions).
✔ Tokens contain user details, issue time, and expiration time.
✔ JwtService.java handles token generation and validation.

*/