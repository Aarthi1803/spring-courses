package com.telusko;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

    @GetMapping("/welcome")
    public String welcome(@AuthenticationPrincipal OAuth2User user) { //  @AuthenticationPrincipal OAuth2User user - Retrieves user details from GitHub after login.
    	System.out.println("GitHub User Attributes: " + user.getAttributes()); // This will print all data received from GitHub in your console. This helps debug missing attributes (e.g., name is sometimes missing).
//    	return "Welcome, " + user.getAttribute("name") + "! Your GitHub ID is " + user.getAttribute("id"); // Returns user name & GitHub ID after successful authentication.
    	return "Welcome, " + user.getAttribute("login") + "! Your GitHub ID is " + user.getAttribute("id"); //  GitHub always returns login (username), even if name is missing.

    }
}

