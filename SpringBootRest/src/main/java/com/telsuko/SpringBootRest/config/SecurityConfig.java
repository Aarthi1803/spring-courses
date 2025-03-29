package com.telsuko.SpringBootRest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/*@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// If we want to change configuration we have to return object of SecurityFilterChain
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // Defines security rules using HttpSecurity.
		
		http.csrf(customizer -> customizer.disable()); // disabling CSRF because if session is stateful we can use same CSRF token but here session is stateless which will not maintain same CSRF token everytime for same session.
		http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); // enabling security all request
//		http.formLogin(Customizer.withDefaults()); // Adds a login page (/login by default). .formLogin() → Like a web-based login screen (username/password).
		http.httpBasic(Customizer.withDefaults()); // Allows authentication via Postman (Basic Auth). .httpBasic() → Like a security gate where you must show ID before entering.
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // By default session is stateful we are making session as stateless. stateless here means in postman when you use /hello and keep sending same request several times we will get different SessionID
		
		return http.build();
	}
	
}*/

/*
Now why we are trying to secure this is because if you are trying to send a request from a malicious website or some third party place, uh, basically the third party will not be having the token, right? So we have seen the example before, and this basically solves the problem. So we have two things here. The first one is what if you don't even allow for anyone outside the same website to access your server? So basically what I'm trying to say is what if you don't allow someone else to access it if you simply disable the cross site access?
What if only one single site access is provided? And the way you can do that is by setting in application.property add - server.servlet.session.cookie.same-site=strict
Now this will make sure that you can only access from the same website.

So this is a good idea to put it strict and no one can access it now from the outside world. Now let's talk about the second part. The second part is when we talk about rest API.
This can be two types of rest API. First is stateless and second is stateful. Now in the stateful basically you maintain session. And that's why every time you log in for the subsequent request, it uses the same session ID. If you open your browser when you request for /hello, you can see it will give you a login page. And here you have to enter your username and you have to enter your password. And every time you send a request let's say I'm sending a request for /about. And if I say enter you have the same session ID even if you use for /students.
Every request will have the same session ID. what if you make it stateless and most of the time when we use a Rest application, basically they will be stateless.
Now in that case, we don't even have to use a CSRF token because in the stateless you have to send the request with the username password every time. So we don't have to maintain the session. So we don't even need CSRF. So what we have to do is in this particular code let's make it stateless. And then we don't even need to use CSRF token then. And basically we have to disable it.
*/

/**********************************************************/

/*@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// If we want to change configuration we have to return object of SecurityFilterChain
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//		Below code is build using lambda expression
//		http.csrf(customizer -> customizer.disable()); // disabling CSRF because if session is stateful we can use same CSRF token but here session is stateless which will not maintain same CSRF token everytime for same session.
//		http.authorizeHttpRequests(request -> request.anyRequest().authenticated()); // enabling security all request
//		http.httpBasic(Customizer.withDefaults());
//		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // By default session is stateful we are making session as stateless. stateless here means in postman when you use /hello and keep sending same request several times we will get different SessionID
	
		
		
//		Below code - how lambda is build by using imperative way
//		Customizer<CsrfConfigurer<HttpSecurity>> customizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//
//			@Override
//			public void customize(CsrfConfigurer<HttpSecurity> configurer) {
//				configurer.disable();
//			}
//			
//		};
//		http.csrf(customizer); // above customizer can be written in lambda way
		
//		Customizer<CsrfConfigurer<HttpSecurity>> customizer = configurer -> configurer.disable(); // using lambda way
//		http.csrf(customizer);
		
//		Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> request = new Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry>() {
//
//			@Override
//			public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
//				registry.anyRequest().authenticated();
//			}
//			
//		};
//		http.authorizeHttpRequests(request); // above request can be written in lambda way
		
//		Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> request =  registry -> registry.anyRequest().authenticated(); // using lambda way
//		http.authorizeHttpRequests(request); 
		
//		http.httpBasic(Customizer.withDefaults());
		
//		Customizer<SessionManagementConfigurer<HttpSecurity>> sessionManagementCustomizer = new Customizer<SessionManagementConfigurer<HttpSecurity>> () {
//			
//			@Override
//			public void customize(SessionManagementConfigurer<HttpSecurity> session) {
//				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//			}
//			
//		};
//		http.sessionManagement(sessionManagementCustomizer); // above session can be written in lambda way
		
//		Customizer<SessionManagementConfigurer<HttpSecurity>> sessionManagementCustomizer = session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // using lambda way
//		http.sessionManagement(sessionManagementCustomizer);
		
//		Can also write like below code
		http.csrf(customizer -> customizer.disable())
			.authorizeHttpRequests(request -> request.anyRequest().authenticated()) 
			.httpBasic(Customizer.withDefaults())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
	
}*/

/******************************************************************/
/*@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(customizer -> customizer.disable())
			.authorizeHttpRequests(request -> request.anyRequest().authenticated()) 
			.httpBasic(Customizer.withDefaults())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService() { // Spring by default uses UserDetailsServic,e we are creating our own implementation by adding this method. username and password in app.properties is not being used and is overriden by this method
		
		UserDetails user = User
								.withDefaultPasswordEncoder() // no password is encoded here
								.username("aarthi")
								.password("a@123")
								.roles("USER")
								.build();
		
		UserDetails admin = User
								.withDefaultPasswordEncoder() // no password is encoded here
								.username("admin")
								.password("admin@789")
								.roles("ADMIN")
								.build();
		
		return new InMemoryUserDetailsManager(user, admin); // Creates a user directly in memory (for testing).
	}
	
}*/

/*
 
The first thing we will do is let's hardcode the username password values not from database, but let's hardcode them here in the code.And I don't want to use this username password from application.properties. And if you don't mention anything in property file, by default this spring security uses something called a User Details service.
Now it uses this class to basically check for your application.properties and see do you have username password there? If yes, it will use it, but I don't want to use that.
I want to define my own User Detail Service. So what we will do is let's create @Bean which will return a User Detail Service object. And I can say this is User Detail Service method.
So basically I want to return the object of User Detail Service. And I want this to be @Bean. Now what will happen is your spring security will look at this particular object to get the data for the user. So whatever data you're going to return in this will be your actual data where a spring security will check.
But how do you return the data? How do I specify the users? And basically how can I return this user detail service? Now if you go back to user detail service, it's a interface, right.
And it has a method called loadUserByUsername. So this is a method which is getting used behind the scenes. Our concern is this particular interface. And it return type which is UserDetails. If I go back here and if I try to understand how do I create an object for this? Now there is a class which we can use.
So I can simply say return InMemoryUserDetailsManager(). InMemoryUserDetailsManager() implements UserDetailsManager which extends UserDetailsService. So indirectly this particular class which is InMemoryUserDetailsManager() implements the UserDetailsService. So our job is done because if you can return the object of InMemoryUserDetailsManager() indirectly, you're getting the object of user detail service. But then we don't want to return the empty object of InMemoryUserDetailsManager(). We have to specify some values. So basically the username password which you have mentioned in the application properties is not getting read. So now uh let's specify the values here. See in this constructor of InMemoryUserDetailsManager(), if you click here you can see it has multiple constructors. One of the constructor is this a collection of users - InMemoryUserDetailsManager(Collection<UserDetails> users).
If you don't want to pass a collection, you can also pass a variable length arguments, which is varargs - InMemoryUserDetailsManager(UserDetails... users) and you can pass multiple users.
So I want to use this particular constructor - InMemoryUserDetailsManager(Collection<UserDetails> users) And I can pass any number of users I want. And you want to return UserDetails object. So that means here, even if you want to create one particular user, you have to give an object of UserDetails.
I will go back here and there's a class called User. So the way we have UserDetails we also have User and this particular class User has multiple methods.
So the first thing we have to mention is the password encoder. As I mentioned before you don't want to store your password in a plain text. You want to store that in an encoded format.
So let's not do any encoding. I will go for a default password encoder - .withDefaultPasswordEncoder(). Now basically this is a method which says I don't want to go for any encoder at this point. So basically whatever username password you mention here it will work. But again this is not coming from a database. But at this point what we are doing is we are not going for a default implementation of UserDetailsService. We are defining it by ourselves. And the way you can do that is by returning the object of InMemoryUserDetailsManager().

*/

/***************************************************************/

/*@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService; //Injects an UserDetailsService instance (used to fetch user details from the database). Spring will automatically inject MyUserDetailsService as it implements UserDetailsService interface - class MyUserDetailsService class implements UserDetailsService
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(customizer -> customizer.disable())
			.authorizeHttpRequests(request -> request.anyRequest().authenticated()) 
			.httpBasic(Customizer.withDefaults())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() { // Spring by default uses UserDetailsService, we are creating our own implementation by adding this method. username and password in app.properties is not being used and is overriden by this method
//		
//		UserDetails user = User
//								.withDefaultPasswordEncoder() // no password is encoded here
//								.username("aarthi")
//								.password("a@123")
//								.roles("USER")
//								.build();
//		
//		UserDetails admin = User
//								.withDefaultPasswordEncoder() // no password is encoded here
//								.username("admin")
//								.password("admin@789")
//								.roles("ADMIN")
//								.build();
//		
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
	@Bean // Creates a Bean for authentication provider, so Spring Security can use it.
	public AuthenticationProvider authProvider() { // implements authentication by verifying users from a database instead of in-memory users.
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); //DAO = Data Access Object. Used to verify user credentials from the database instead of in-memory. To verify user from database we have used DaoAuthenticationProvider
		provider.setUserDetailsService(userDetailsService); // Tells DaoAuthenticationProvider to use MyUserDetailsService to fetch users.
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); // NoOpPasswordEncoder = No encryption (Just for testing). In real-world apps, use BCryptPasswordEncoder.
		return provider; // This AuthenticationProvider is now used to authenticate users.
		
	}
	
//	Real-World Example: Bank Login System
//	   A user tries to log in.
//	   Instead of checking a fixed password, we check against bank records (database).
//	   The authProvider() method acts like a security guard, ensuring only valid users get access.
	
//	How This Works - Step by Step :
//	1️. User enters login details (username/password).
//	2️. Spring calls loadUserByUsername() in MyUserDetailsService.
//	3️. The database is searched for the username (repo.findByUsername(username)).
//	4️. If found, the user is wrapped inside UserPrincipal.
//	5️. Spring Security verifies the password.
//	6️. If correct, the user is logged in successfully.
	
//	✔ authProvider() → Tells Spring Security to use database authentication.
//	✔ MyUserDetailsService → Fetches user details from the database.
//	✔ UserPrincipal → Converts the user into UserDetails format.
//
//	Now, your application authenticates users from the database instead of in-memory storage.
	
//	Creating user table and db properties:
//  We need Authentication Provider in our code. when we pass username and password as un-authenticated object. This object is checked by Authentication Provider and changes from un-authenticated object to authenticated object. Authentication Provider connects with database of DAO layer. we need to connect our Java class with table using @Entity for user table and repository layer also needed.
	
}*/

/***************************************************/

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService; //Injects an UserDetailsService instance (used to fetch user details from the database). Spring will automatically inject MyUserDetailsService as it implements UserDetailsService interface - class MyUserDetailsService class implements UserDetailsService
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { // SecurityFilterChain: It defines security rules (authentication & authorization). HttpSecurity http: This is Spring Security’s main configuration object, used to define security policies.

		http.csrf(customizer -> customizer.disable()) // Disables CSRF (Cross-Site Request Forgery) protection. CSRF prevents unauthorized form submissions but is unnecessary for APIs that don’t use sessions. If you are building a REST API (e.g., Mobile App Backend), CSRF is not needed because users don’t submit forms. If you are building a banking website, DO NOT disable CSRF (because CSRF protection prevents unauthorized fund transfers).
			.authorizeHttpRequests(request -> request.anyRequest().authenticated()) // Requires authentication for all requests (every API needs login). This ensures that only logged-in users can access any endpoint.
			.httpBasic(Customizer.withDefaults()) // Enables Basic Authentication. Basic Authentication requires users to send a username & password in every request. Useful for simple APIs or testing but not ideal for production. If you use Postman to test an API, you can send Basic Auth credentials (username:password).
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Disables server-side sessions (stateless authentication). Every request is independent (server does not store user sessions).
		
		return http.build(); // Builds the security configuration and returns the SecurityFilterChain. This tells Spring Security to apply the custom rules defined above. Once this method runs, the security configuration is applied to your entire application.
		
	}
	
	@Bean // Creates a Bean for authentication provider, so Spring Security can use it.
	public AuthenticationProvider authProvider() { // implements authentication by verifying users from a database instead of in-memory users.
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); //DAO = Data Access Object. Used to verify user credentials from the database instead of in-memory. To verify user from database we have used DaoAuthenticationProvider
		provider.setUserDetailsService(userDetailsService); // Tells DaoAuthenticationProvider to use MyUserDetailsService to fetch users.
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12)); // adding 12 because it creates password as 2^12. Ensure that authentication works with encrypted passwords. During login, BCrypt automatically matches encrypted passwords.
		
		return provider; // This AuthenticationProvider is now used to authenticate users.
		
	}
	
}

/*

If you look at our database we are storing our password in a plain text but also we are transferring this data in a plain text. 
So how do you how do you secure this. So one thing you can do is you can use something called a cryptography here. 
Now in cryptography you encrypt a message and then on the other hand you decrypt it.
So basically what I'm saying is when you want to store a password, you can encrypt a password with a key. 
And then when you want to verify the password, you can decrypt the password using key and you can verify the password. 
The problem with this is when you have the encryption technique for cryptography, you have a key. 
And some way it is not that safe because if attacker gets the key you are gone. All the passwords are leaked. 

The other way you can do is you can have a one way encryption, which is called hashing. 
Basically, in hashing you don't decrypt the password. Now hash is basically one way.
So for every password you will get only one hash. There is no collision here. 
Now what will happen is you can't get it back. 
Now how do you verify this. So every time a user enters password. Then you convert password into a hash password. 
And then you compare hashed password with password that is hashed and stored in db. If they are matching that's good.
Right now what are the algorithms we have. 
So for hashing we have different algorithms available. We got MD5, we got Sha 256. But then we want a more secure way of doing it.
What if you can use Sha 256 but not just in one round in multiple rounds?
So what hashing does is basically it converts your text into a numbers using hashing algorithm. This is one of the algorithm we have. 
And then it will do only once. But what if you can repeat this process multiple times 1 or 2 times, ten times, 100 times. 
And if you can do that, it will be difficult for anyone to hack or to get the password. 
And to achieve that we are going to use some other technique and we have to use a algorithm called Bcrypt. 
Bcrypt will help you to generate a password or it will encode your password. T
o show you an example, I will open my browser. I will search for Bcrypt password generator - https://bcrypt-generator.com/
So we got this website which is browser link. And here basically you can get your password. 
So let's say I want to get the Bcrypt password. So I will enter h@123 as password.
And I can click on Bcrypt. So you can see this is the encoded format. 
So we are using a password encoder now which is Bcrypt. And this is your encoded format. 
And if you look at this format of course you have some weird values here. - $2a$12$CdOxQd1mSwq34nmOHBwIm.ruClsC4eabb.IqOC/buzprwXr8j4r0S
But if you look at the first one, so $2a is a version for decrypt. 
So the different versions available we got to $A2Y also. And then after this we have $12 which is basically your number of rounds.
So you can see I'm saying ten rounds here. So by default it will be 12 rounds. But you can you can change the rounds here like $14. 
Now when I say 12 rounds it is actually 2^12 rounds. It's not just 12 rounds okay. So it's a huge number. 
So it is 2^10 which is the number of rounds it will take to generate this text here. So it is very difficult for anyone to hack it. 
So even if they want to do a brute force they have to do this multiple times. 
And every time you do a hashing, it takes some time, it takes some computation. So that means if you do computation, 
let's say if you do it only for once, one round up, let's say. if it takes one milliseconds, imagine if you do 4000 rounds. That's huge right? 
So it will make sure that it will be less vulnerable compared to the plaintext.

*/