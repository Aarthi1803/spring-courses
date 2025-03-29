package com.telusko.HttpMethods;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/*
✅ Handles all HTTP methods (GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS).
✅ Uses @RequestBody to read JSON data from requests.
✅ Uses @PathVariable to extract user ID from URL.
✅ Uses ResponseEntity to send status codes & responses.

CRUD API in Spring Boot with all HTTP methods:
✔ GET - Retrieve user
✔ POST - Create user
✔ PUT - Update entire user
✔ PATCH - Partially update user
✔ DELETE - Remove user
✔ HEAD - Check if user exists
✔ OPTIONS - List allowed methods

What is ResponseEntity? - ResponseEntity<T> is a Spring Boot class that helps send HTTP status codes and custom responses to the client.
✅ It provides:
    Status codes (200 OK, 404 Not Found, 400 Bad Request)
    Response headers
    Response body (JSON object, text, etc.)

Example:
return ResponseEntity.ok(user);  // 200 OK + User Data
return ResponseEntity.notFound().build(); // 404 Not Found
return ResponseEntity.badRequest().body("Invalid input"); // 400 Bad Request

ResponseEntity controls status codes & response data.
PUT replaces entire user.
PATCH updates only specified fields.
HEAD checks existence.
OPTIONS lists allowed methods.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // 🔹 GET - Retrieve All Users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // 🔹 GET - Retrieve Specific User by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        User user = service.getUserById(id);
        return (user != null) ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // 🔹 POST - Create New User
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User createdUser = service.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // 🔹 PUT - Update Entire User
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User user) {
        User updatedUser = service.updateUser(id, user);
        return (updatedUser != null) ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    // 🔹 PATCH - Update Only Email
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateEmail(@PathVariable int id, @RequestBody Map<String, String> updates) { // @RequestBody Map<String, String> updates → Accepts partial updates like {"email":"alice.updated@example.com"} in JSON format.
        if (!updates.containsKey("email")) {
            return ResponseEntity.badRequest().build();
        }

        User updatedUser = service.updateEmail(id, updates.get("email"));
        return (updatedUser != null) ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    // 🔹 DELETE - Remove User
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        return (service.deleteUser(id)) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // 🔹 HEAD - Check If User Exists
    @RequestMapping(value = "/{id}", method = RequestMethod.HEAD) // Checks if a user exists without returning the full user data.
    public ResponseEntity<Void> checkUserExists(@PathVariable int id) {
        return (service.getUserById(id) != null) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // 🔹 OPTIONS - Show Allowed Methods
    @RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS) // Used to get allowed HTTP methods for a resource. Specifies that GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS methods are allowed.
  //  ✅ The response includes: Allow: GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS
    public ResponseEntity<?> getAllowedMethods() {
        return ResponseEntity.ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.HEAD, HttpMethod.OPTIONS)
                .build();
    }
}

/*
 Difference Between PUT and PATCH in HTTP Methods:
Both PUT and PATCH are used to update resources, but they work differently.
✅ PUT (Full Update):
    Replaces the entire resource with a new one.
    If a field is not provided, it will be reset to default/null.
    Example: If updating user details, all fields must be sent.

Example:

    Before Updating:
	{
	  "id": 2,
	  "name": "Aarthi",
	  "email": "aarthi@example.com",
	  "age": 24
	}
	
	Request Body (PUT with only email):
	{
	  "email": "newaarthi@example.com"
	}
	
	After Update (Wrong behavior if PUT is used)

    {
      "id": null,
      "name": null,
      "email": "newalice@example.com",
      "age": null
    }
	❌ PUT overwrites missing fields with null!
	
	PATCH (Partial Update)

    Only updates specific fields without affecting others.
    Example: If only email is provided, other fields remain unchanged.

🔹 Example:

    Before Updating:
	{
	  "id": 2,
	  "name": "Aarthi",
	  "email": "aarthi@example.com",
	  "age": 24
	}
	
	Request Body (PATCH with only email)
	{
	  "email": "newaarthi@example.com"
	}

	After Update (Correct behavior with PATCH)
    {
      "id": 1,
      "name": "Aarthi",
      "email": "newaarthi@example.com",
      "age": 24
    }

✔ Other fields remain unchanged!

PATCH (Partial Update of a Resource)
✅ When to Use?
    Used when only a part of the resource needs to be updated instead of the entire object.
    Example: Updating only email without modifying the name or age.
    
HEAD (Fetch Headers Without Body)
✅ When to Use?
    Used when you only need response headers (e.g., checking if a resource exists before downloading).
    Similar to GET but does not return the response body.

OPTIONS (Check Allowed Methods)
✅ When to Use?
    Used to determine which HTTP methods (GET, POST, PUT, etc.) are allowed on a resource.
    Example: Before making a POST request, a client can check if POST is allowed.

Summary:
Method		Purpose
PATCH		Partially updates a resource (only some fields).
HEAD		Checks if a resource exists by fetching only headers.
OPTIONS		Gets allowed HTTP methods for a resource.

✅ When to Use?
✔ PATCH → Updating only specific fields of an object (e.g., updating email without changing the name).
✔ HEAD → Checking if a file or resource exists before downloading it.
✔ OPTIONS → Checking which methods (GET, POST, etc.) are allowed for an API.

Summary
HTTP Method				Purpose
PUT						Replaces the entire resource (full update).
PATCH					Updates specific fields only (partial update).
HEAD					Checks if the resource exists (only headers, no body).
OPTIONS					Returns allowed HTTP methods for the resource.

✔ Use PUT when updating all fields.
✔ Use PATCH when updating some fields.
✔ Use HEAD to check if a resource exists without loading data.
✔ Use OPTIONS to check which methods are allowed.

 */
