package com.telusko;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) // authorize all request. Ensures that all requests require authentication.  User must log in before accessing any API.
		    .oauth2Login(Customizer.withDefaults()); // login should happen with OAuth. Enables OAuth2 login with GitHub. When a user visits /hello, they will be redirected to GitHub to log in. After login, GitHub sends the user back to the application.
		
		return http.build();
		
	}
	
}

/*
Code Flow Summary:

1️. User visits http://localhost:8000/hello → Authentication required.
2️. User is redirected to GitHub login.
3️. User logs in on GitHub & grants permission.
4️. GitHub redirects user back to your app.
5️. Spring Security verifies the token & logs in the user.
6️. User can now access /hello API

Code Flow Explanation:
1️. User Accesses /hello

    User visits http://localhost:8000/hello.
    Spring Security checks if the user is authenticated.

2️. Redirect to GitHub Login

    If not authenticated, the user is redirected to GitHub for login.
    The user logs in using their GitHub credentials.
    GitHub asks the user to grant permissions to the application.

3️. GitHub Redirects Back to App

    After successful login, GitHub redirects the user to:
    http://localhost:8000/login/oauth2/code/github
    Spring Security automatically exchanges the code for an access token.

4️. User is Authenticated

    Spring Security verifies the token.
    The user is now logged in and authorized.

5️. User Can Access /hello

    The user is now authenticated and can access /hello.
    Response: "Welcome to Telusko"

*/