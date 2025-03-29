package com.telusko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login").permitAll() // Allow homepage & login for public access without using credentials. Allows public access to homepage ("/") and login page ("/login"). Anyone can access these URLs without logging in.
                .anyRequest().authenticated() // Protect all other endpoints. All other endpoints require authentication. If a user tries to access /welcome, they must log in first.
            )
            .oauth2Login(oauth2 -> oauth2 // Enables GitHub OAuth2 login.
                .defaultSuccessUrl("/welcome", true) // Redirect after successful login
                .failureUrl("/login?error=true") // Redirect on failure
            );

        return http.build();
    }
}


/*
 
  Code Flow Summary:
    User clicks "Login with GitHub" → Redirects to GitHub.
    User logs in on GitHub → Grants permission.
    GitHub redirects the user back to your app → Sends an authorization code.
    Spring Boot exchanges this code for an access token.
    Spring Security verifies the token and authenticates the user.
    User is redirected to /welcome where their details are displayed.
  
 */

/*
  
 
Complete Code Flow:
1️. User Visits App (http://localhost:8080)
    Publicly accessible homepage or login page.
    No authentication needed yet.

2️. User Clicks "Login with GitHub"
    User is redirected to GitHub for authentication.

3️. GitHub Asks User to Log In & Grant Permissions
    If the user approves, GitHub sends an authorization code back to our app.

4️. Spring Boot Exchanges Code for Access Token
    Spring Security automatically fetches an access token from GitHub.

5️. Spring Boot Verifies Token & Fetches User Details
    If authentication succeeds, user is redirected to /welcome.
    If login fails, user is sent to /login?error=true.

6️. User Details are Displayed (/welcome)
    Spring Boot extracts GitHub user details.
    Username (login) & GitHub ID (id) are displayed.


*/