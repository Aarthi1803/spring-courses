package com.telusko.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.telusko.service.JwtService;
import com.telusko.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // Makes this class a Spring Bean so that it is automatically used.
public class JwtFilter extends OncePerRequestFilter{ // Ensures the filter runs once per request. This filters every request and checks if a valid JWT token exists. Imagine a security guard at the entrance of a building. The guard checks every visitor’s ID card (JWT token). If the ID is valid, the visitor is allowed inside.
	
	@Autowired
	JwtService jwtService; //  Used to extract and validate JWT tokens. Handles JWT creation, validation, and extraction.

	@Autowired
	ApplicationContext context; // Helps fetch beans dynamically, useful for loading MyUserDetailsService.
	
	@Override // This method runs before every request and checks for JWT tokens.  This method is automatically called for every request.
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException { // filterChain → Used to pass the request to the next filter.
		
		String authHeader = request.getHeader("Authorization"); // Gets the Authorization header from the request. If the token exists, extracts it.
		String token = null;
		String userName = null;
		
		if(authHeader != null && authHeader.startsWith("Bearer ")) { // Checks if the token is in the correct format (Bearer <TOKEN>)
			token = authHeader.substring(7); //  Extracts the actual token 
			userName = jwtService.extractUserName(token); // Retrieves the username from token.
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null ) { // If a valid username exists but the user is not yet authenticated, proceed with authentication. Ensures the user is NOT already authenticated --> SecurityContextHolder.getContext().getAuthentication() == null
			
			UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(userName); // Fetch user details from the database using MyUserDetailsService. Loads user details (username, password, roles) from the database using MyUserDetailsService based on the username. Why do we use ApplicationContext instead of just @Autowired? Filters (JwtFilter) are created before Spring initializes @Autowired beans. ApplicationContext helps fetch MyUserDetailsService dynamically. ApplicationContext is used instead of @Autowired because filters are created before Spring initializes beans.
			 
			if(jwtService.validateToken(token, userDetails)) { // If the JWT is valid, create an authentication token, the user is authenticated.  Calls validateToken(token, userDetails) to check if the JWT is valid.  Ensures that the token belongs to the correct user and is not expired.
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); //  Creates an authentication token for Spring Security.  Stores user details & roles inside the authentication object.  Creates an authentication token (authToken) that stores user details & roles. Does NOT store the password (null) since JWT has already verified identity.
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Adds extra authentication details (e.g., IP address, session details).  Helps in tracking authentication events (e.g., failed login attempts).
				SecurityContextHolder.getContext().setAuthentication(authToken); //  Set authentication in SecurityContextHolder → This tells Spring Security the user is authenticated. Stores authentication in Spring Security's context so that the user stays logged in. Stores authentication in SecurityContextHolder so that: The user does not need to log in again for each request. Other parts of the application can check if the user is authenticated.
			}
		}
		
		filterChain.doFilter(request, response); //  Passes the request to the next filter. Continuing filter chain to move to next filter. Continues request processing (moves to the next filter/controller). Ensures the user can access protected APIs.
	}

}

/*
This is what we get in Authorization Headers of PostMan. We are removing "Bearer " word and space after it by fetching only JWT token 
Headers:
Authorization: Bearer <JWT_TOKEN_FROM_STEP_1>

*/


/*

Imagine a high-security office building where only employees with valid ID cards (JWT tokens) can enter.
✅ JwtFilter acts as a security guard.
✅ It checks the ID card (JWT token) of every visitor before allowing access.
✅ If the ID card (JWT) is valid, the visitor is allowed in (authenticated).
✅ If invalid or missing, the visitor is denied access.


What Does JwtFilter Do?
✔ Intercepts all incoming requests before they reach the controller.
✔ Checks for a JWT token in the request header.
✔ If valid, extracts the username & verifies it.
✔ Authenticates the user and grants access.

 How It Works in the Application

1️. A user logs in (POST /login).
    The server generates a JWT and sends it to the user.

2️. User sends a request to a protected API (GET /secure-data).
    The request includes Authorization: Bearer <TOKEN> in the header.

3️. JwtFilter intercepts the request.
    Extracts JWT, verifies it, and authenticates the user.

4️. If valid, the user is granted access to the API.

------------------------
if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null ) { // If a valid username exists but the user is not yet authenticated, proceed with authentication.
			
	UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(userName); // Fetch user details from the database using MyUserDetailsService.  Loads user details from the database based on the username. Why do we use ApplicationContext instead of just @Autowired? Because we fetch MyUserDetailsService dynamically inside the filter. Eg: The security guard asks the HR department (ApplicationContext) for employee details when verifying an ID.
	 
	if(jwtService.validateToken(token, userDetails)) { // If the JWT is valid, create an authentication token, the user is authenticated.
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); //  Creates an authentication token for Spring Security.  Stores user details & roles inside the authentication object.
		authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authToken); //  Set authentication in SecurityContextHolder → This tells Spring Security the user is authenticated. Stores authentication in Spring Security's context so that the user stays logged in.
	}

}
		
filterChain.doFilter(request, response);

Checks if a user is already authenticated.
✔ Loads user details from the database.
✔ Validates the JWT token.
✔ If valid, authenticates the user and stores authentication in SecurityContextHolder (so they don’t need to log in again).
✔ Continues request processing if authentication is successful.


Imagine a high-security office building:

    A visitor arrives and shows their ID card (JWT token).
    The security guard checks if they are already registered in the system (SecurityContextHolder).
    If not yet registered, the guard looks up the visitor’s details in the HR database (UserDetailsService).
    If the ID card (JWT) is valid, the visitor is granted a security badge (Authentication).
    The visitor can now move freely in the building (authenticated for further requests).

✔ In our application, JWT acts like an ID card, and SecurityContextHolder stores authentication information to prevent repeated checks.

--------------------------
UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(userName);
✔ Loads user details (username, password, roles) from the database using MyUserDetailsService.
✔ Why use ApplicationContext instead of @Autowired?

    Filters (JwtFilter) are created before Spring initializes @Autowired beans.
    ApplicationContext helps fetch MyUserDetailsService dynamically.


How This Works in the Application:
1️. User Sends a Request with a JWT Token (GET /secure-data)
    The JWT is included in the Authorization header.

2️. JwtFilter Intercepts the Request
    Extracts username from the JWT.
    Checks if the user is already authenticated (SecurityContextHolder).

3️. If Not Authenticated
    Loads user details from the database.
    Validates the JWT token.
    Creates authentication and stores it in SecurityContextHolder.

4️. Request Continues to the Controller
    If valid, access is granted.
    If invalid, access is denied (401 Unauthorized).

*/