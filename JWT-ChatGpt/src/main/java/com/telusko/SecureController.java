package com.telusko;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/secure") // Creates a protected API endpoint under /secure - We will create a secure API that only authenticated users (JWT holders) can access.
public class SecureController { // This API requires a valid JWT token to access.
	
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/data")
    public String secureData(@RequestHeader("Authorization") String token) { // Extracts JWT token from the request header.
        // Extract token from "Bearer <TOKEN>"
        token = token.substring(7); // Extracts JWT token from the request header - Removes "Bearer "prefix from Authorization header.

        // Step 1: Validate JWT
        if (!jwtUtil.validateToken(token)) { //  If JWT is invalid, returns "Invalid Token!".
            return "Invalid Token!";
        }

        // Step 2: Get Username from Token
        String username = jwtUtil.extractUsername(token); // Extracts username from JWT and returns a success message.

        return "Hello " + username + ", you accessed a secure endpoint!";
    }
    
}

/*

 Real-World Example:
    This is like showing a boarding pass at the airport.
    If the boarding pass is valid, you can board. ✅
    If the boarding pass is fake, you are denied entry. ❌

✔ JWT is used for authentication in APIs.
✔ It consists of a Header, Payload, and Signature.
✔ We implemented login, token generation, validation, and a secure API.


*/