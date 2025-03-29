package com.telusko.SpringInterviewQues;

public class SpringBootActuatorExample {

}

/*

What is Spring Boot Actuator?

Spring Boot Actuator is a module that provides built-in monitoring and management features for Spring Boot applications.

It allows you to:
✅ Monitor application health
✅ View system metrics (CPU, memory, uptime, etc.)
✅ Check application properties and environment details
✅ Manage logging levels dynamically
✅ Expose custom application endpoints

Why Use Spring Boot Actuator?

Spring Boot Actuator is useful for:
✔ Developers → Debugging and monitoring the application during development.
✔ Operations Team → Checking application health and performance in production.
✔ Microservices → Helps monitor and manage microservices easily.

How to Enable Spring Boot Actuator?
Step 1: Add Actuator Dependency

In Maven (pom.xml):
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

In Gradle (build.gradle):
implementation 'org.springframework.boot:spring-boot-starter-actuator'

Configure Actuator Endpoints:
By default, only the /actuator/health and /actuator/info endpoints are enabled.
To enable all Actuator endpoints, add this in application.properties or application.yml:
✅ For application.properties

management.endpoints.web.exposure.include=*

Available Actuator Endpoints:
Once enabled, access these endpoints in a browser or using curl:
Endpoint					Purpose
/actuator/health			Check if the application is running
/actuator/info				Displays custom application info
/actuator/metrics			Provides system metrics (CPU, memory, requests, etc.)
/actuator/loggers			Manage logging levels dynamically
/actuator/env				View environment properties and configurations
/actuator/beans				List all registered Spring beans
/actuator/mappings			Show all available endpoints and their mappings
/actuator/threaddump		Provides a snapshot of all running threads
/actuator/shutdown			Gracefully shuts down the application (must be enabled separately)

Example Usage:
Check Application Health:  curl http://localhost:8080/actuator/health

Response:
{
  "status": "UP"
}

Check Available Metrics:  curl http://localhost:8080/actuator/metrics

Response (Example):

{
  "names": [
    "jvm.memory.used",
    "jvm.memory.max",
    "system.cpu.usage",
    "process.cpu.usage",
    "http.server.requests"
  ]
}

Check Application Info:
In application.properties, define:

info.app.name=My Spring Boot App
info.app.version=1.0.0

Then, access: curl http://localhost:8080/actuator/info

Response:
{
  "app": {
    "name": "My Spring Boot App",
    "version": "1.0.0"
  }
}

Securing Actuator Endpoints:
By default, Actuator endpoints are public. To secure them, add this in application.properties:

management.endpoints.web.exposure.include=*
management.endpoint.health.show-details=always
spring.security.user.name=admin
spring.security.user.password=admin123

Now, accessing Actuator endpoints requires authentication.

Key Takeaways:
✔ Spring Boot Actuator provides built-in monitoring & management
✔ Easy to enable with spring-boot-starter-actuator dependency
✔ Multiple endpoints available (/health, /metrics, /info, etc.)
✔ Can be secured with authentication

example of a custom Actuator endpoint?
ChatGPT said:
Custom Actuator Endpoint in Spring Boot

Spring Boot allows you to create custom Actuator endpoints using the @Endpoint annotation. This is useful when you need to expose application-specific monitoring or management functionality.

Steps to Create a Custom Actuator Endpoint:
Step 1: Add Actuator Dependency
Make sure you have this dependency in pom.xml:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

Step 2: Create a Custom Actuator Endpoint
We will create an endpoint called /actuator/custom that returns application statistics.

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component // Registers this as a Spring-managed bean
@Endpoint(id = "custom") // Defines a new custom Actuator endpoint - Creates an Actuator endpoint accessible at /actuator/custom
public class CustomActuatorEndpoint {

    @ReadOperation // HTTP GET request
    public Map<String, Object> customEndpoint() {
        Map<String, Object> response = new HashMap<>();
        response.put("appStatus", "Running Smoothly");
        response.put("activeUsers", 120);
        response.put("version", "1.0.0");
        return response;
    }

    @WriteOperation // HTTP POST request
    public void updateStatus(@Selector String status) {
        System.out.println("Application status updated to: " + status);
    }
}

Step 3: Enable Custom Actuator Endpoint
Add this in application.properties:

management.endpoints.web.exposure.include=*

This ensures that all Actuator endpoints, including our custom one, are exposed.

Step 4: Test the Custom Endpoint
1️. Check Custom Endpoint

Run the application and open your browser or use curl:

curl http://localhost:8080/actuator/custom

Response:

{
  "appStatus": "Running Smoothly",
  "activeUsers": 120,
  "version": "1.0.0"
}

2️. Update Application Status (POST Request)

curl -X POST http://localhost:8080/actuator/custom/test

Output in Console:

Application status updated to: test

Explanation:
    @Endpoint(id = "custom") → Defines a new Actuator endpoint at /actuator/custom.
    @ReadOperation → Handles GET requests to retrieve data.
    @WriteOperation → Handles POST requests to modify data.
    @Selector → Allows passing dynamic values in the request.

Key Takeaways:
✔ Custom Actuator endpoints allow monitoring application-specific data
✔ Use @Endpoint(id = "custom") to create a new Actuator endpoint
✔ @ReadOperation for GET, @WriteOperation for POST requests
✔ Accessible at /actuator/custom just like built-in Actuator endpoints

 @ReadOperation // Exposes this method for HTTP GET requests
    public Map<String, Object> customEndpoint() {
        Map<String, Object> response = new HashMap<>();
        response.put("appStatus", "Running Smoothly");
        response.put("activeUsers", 120);
        response.put("version", "1.0.0");
        return response;
 }
 
 @ReadOperation → Exposes this method as a GET request endpoint.
✔ Returns a Map with three key-value pairs:

    "appStatus" → "Running Smoothly"
    "activeUsers" → 120
    "version" → "1.0.0"
    ✔ When accessed at /actuator/custom, it returns this JSON response.

📌 Test with curl or browser:
curl http://localhost:8080/actuator/custom

Response:
{
  "appStatus": "Running Smoothly",
  "activeUsers": 120,
  "version": "1.0.0"
}

 @WriteOperation // Exposes this method for HTTP POST requests
    public void updateStatus(@Selector String status) {
        System.out.println("Application status updated to: " + status);
    }
}

✔ @WriteOperation → Exposes this method as a POST request endpoint.
✔ @Selector String status → Allows passing a value in the URL (/actuator/custom/{status}).
✔ Prints the updated status instead of storing it (for simplicity).

📌 Test with curl or Postman:

curl -X POST http://localhost:8080/actuator/custom/MAINTENANCE

Console Output:

Application status updated to: MAINTENANCE

Securing Actuator Endpoints - Line-by-Line Explanation

By default, Actuator endpoints are public. To secure them, we use Spring Security.
Step 1: Add Spring Security Dependency

📌 Add this dependency in pom.xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>

✔ This enables authentication for all endpoints, including Actuator.

Configure Security in application.properties

# Expose all Actuator endpoints
management.endpoints.web.exposure.include=*

# Show detailed health status
management.endpoint.health.show-details=always

# Define a default user with username and password
spring.security.user.name=admin
spring.security.user.password=admin123

✔ Enables all Actuator endpoints (*)
✔ Allows detailed health status
✔ Defines a default Spring Security user (admin/admin123)

Access Secure Endpoints

📌 Try accessing /actuator/custom now:

curl -u admin:admin123 http://localhost:8080/actuator/custom

🔐 Now, authentication is required! If credentials are correct, you get:

{
  "appStatus": "Running Smoothly",
  "activeUsers": 120,
  "version": "1.0.0"
}

📌 Unauthorized request (without credentials):

curl http://localhost:8080/actuator/custom

❌ Response:

{
  "timestamp": "2025-02-07T12:00:00Z",
  "status": 401,
  "error": "Unauthorized",
  "message": "Unauthorized",
  "path": "/actuator/custom"
}

Key Takeaways

✔ @Endpoint(id = "custom") → Defines a custom Actuator endpoint at /actuator/custom.
✔ @ReadOperation → Handles GET requests for reading data.
✔ @WriteOperation → Handles POST requests for modifying data.
✔ @Selector → Allows passing parameters dynamically in URLs.
✔ spring-boot-starter-security → Enables authentication for all Actuator endpoints.
✔ Use curl -u username:password to access secured endpoints.

*/