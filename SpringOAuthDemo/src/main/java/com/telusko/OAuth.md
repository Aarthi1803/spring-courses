OAuth(Open Authorization) (Third party application - Telusko website): 

whenever you go to any particular web application and then you want to log in, of course they have their own particular servers, own login system. So they will ask you for username and password. But then you also see more options there. Sometimes you can see an option of login through Google or login through GitHub or login through Facebook.
And most of us log in to any application using Google because it is much faster, and I'm lazy. To enter the username password, just click on Google. I'm already logged in my browser.
It will pick up, uh, the credentials, it will pick up the details about me. I'm into the application and every time I want to log in, I will do the same thing.
It is much faster now. This is possible with the help of OAuth. 
So basically what happening is you are the resource owner. Basically you want to log in to a particular application like "Telusko website". And then this third party application says if you want to log in you have to enter username password. But then you are saying hey, I already have a Google account. What if you can check my credentials there? Because ultimately you have to prove yourself right. So you have to prove that the person you are claiming to be is real. And the way you can do that is by passing the username password Or you can say check with Google. But again, you don't want to provide the username and password for Google. So it's a big security risk.
So what you can do is the third party application can request to Google for the login or for the details, and Google will use your ID and credentials to provide the access. Now of course, this third party will not be able to get the username and password from Google. You can specify what are the features you want to share. Because let's say if you talk about Google they have multiple services. They have they have lot of data about you. But of course they will not share everything with this particular third party application. Now, depending upon what their use case it is. Maybe you want to fetch all the email address of your Gmail account, so maybe you can give the access to it. Maybe you want to share the contacts.
Example: When you buy an Android phone, you get all your Google contacts there, so that's how you provide the access. Now basically, this is possible with the help of OAuth. And the current version of OAuth is Oauth2. And we can implement that in our project.

What is OAuth2?

✔ OAuth2 (Open Authorization 2.0) is a framework that allows secure access to protected resources without sharing passwords.
✔ It is used for authentication and authorization in applications like Google, Facebook, and GitHub.
✔ Instead of sharing passwords, OAuth2 allows applications to request temporary access tokens from an authorization server.
✔ This ensures security by allowing third-party apps to access user data without storing credentials.

Real-World Example: Visiting a Hotel 
Imagine you go to a hotel and need access to your room:

    You check in at the reception (User Login).
    The hotel verifies your booking details (Authentication).
    The receptionist gives you a key card (Access Token).
    You use the key card to unlock your room (Access Protected Resources).
    Once your stay expires, the key card stops working (Token Expiry).

✔ OAuth2 works the same way! Instead of sharing passwords, users get a temporary access token that grants them access to an API.

How OAuth2 Works?

OAuth2 involves four main roles:
Component					Description
Resource Owner					The user who grants permission (e.g., you).
Client						The application that wants access (e.g., a mobile app).
Authorization Server				Issues access tokens (e.g., Google, Facebook).
Resource Server					The API that holds protected data (e.g., Gmail, GitHub API).

OAuth2 Authorization Code Flow:

1. User tries to log in with a third-party provider (e.g., Google).
2. OAuth2 provider redirects the user to a login page.
3. User enters credentials and grants permission to the app.
4. Authorization server issues an authorization code.
5. The app exchanges this code for an access token.
6. The app uses the token to access user data securely.

✔ OAuth2 is a secure way to authenticate users without sharing passwords.
✔ It uses access tokens instead of credentials.
✔ Spring Boot makes OAuth2 integration easy with spring-boot-starter-oauth2-client.
✔ OAuth2 flow ensures security by verifying users with trusted providers like Google, GitHub, etc.


What is OAuth2 with GitHub?

✔ OAuth2 allows users to log in using GitHub without entering passwords in our app.
✔ Instead of storing passwords, we redirect users to GitHub for authentication.
✔ Once authenticated, GitHub sends an access token back to our app, allowing secure access to user details.

Real-World Example: Visiting a Co-Working Space

1. You enter a co-working space and need WiFi access.
2. Instead of asking for a WiFi password, the receptionist asks you to scan a QR code (GitHub login link).
3. The QR code redirects you to GitHub, where you verify your identity.
4. Once GitHub confirms who you are, it notifies the receptionist.
5. The receptionist gives you WiFi access (GitHub API token).

✔ OAuth2 works the same way—instead of giving away passwords, we use a secure token to access GitHub APIs.


How OAuth2 Works with GitHub

1. User clicks "Login with GitHub".
2. User is redirected to GitHub for authentication.
3. After successful login, GitHub redirects back to our app with an authorization code.
4. Our app exchanges this code for an access token.
5. The access token is used to fetch user details from GitHub.

Step-by-Step Implementation in Java (Spring Boot)
Step 1: Register OAuth App in GitHub

    Go to GitHub Developer Settings → GitHub OAuth Apps.
    Click "New OAuth App".
    Fill in:
        Application Name: SpringBootOAuth2App
        Homepage URL: http://localhost:8080
        Authorization Callback URL: http://localhost:8080/login/oauth2/code/github
    Click "Register Application".
    Copy Client ID & Client Secret.