package com.telusko.repo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.model.JobPost;


@Repository // Normal repository annotation, but still works with Spring Data REST because we added "spring-boot-starter-data-rest" in pom.xml. This happens because Spring Data REST automatically detects all JpaRepository interfaces and exposes RESTful endpoints for them — even if you don’t explicitly use @RepositoryRestResource.
public interface JobRepo extends JpaRepository<JobPost, Integer> {
	
}

/*

Difference Between @RepositoryRestResource and @Repository in Spring Boot:
In Spring Boot, both @RepositoryRestResource and @Repository are used to interact with the database, but they serve different purposes. 
Let's understand each one in detail with examples and explanations.

@Repository (Traditional Approach)
✔ Purpose:
    Used in Spring Data JPA to mark an interface or class as a repository for data access.
    It is a specialized version of @Component, so it is automatically detected by Spring.
    Requires a separate Controller to expose REST APIs.
    
Create ProductController:

package com.example.controller;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll(); // Fetch all products from DB
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product); // Insert new product
    }
}

✔ Explanation:
    @RestController → Marks this class as a REST API Controller.
    @GetMapping("/products") → Retrieves all products.
    @PostMapping("/products") → Inserts a new product.

Testing APIs using Postman:
1️. Get all products: GET http://localhost:8080/products
2️. Add a product: POST http://localhost:8080/products

{
  "name": "Laptop",
  "category": "Electronics",
  "price": 1200.00
}

🔹 ❌ Problem with @Repository: You have to manually create a Controller to expose APIs.
    
 @RepositoryRestResource (Spring Data REST Approach)
✔ Purpose:
    Automatically exposes REST APIs without needing a controller.
    Helps to quickly create APIs for CRUD operations.

Example using @RepositoryRestResource
🔹 Step 1: Use @RepositoryRestResource in Repository

package com.example.repository;

import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "products") // Auto-creates REST API at /products
public interface ProductRepository extends JpaRepository<Product, Long> {

}
    
 Explanation:

    @RepositoryRestResource(path = "products") → Automatically exposes REST APIs.
    No need to create a Controller manually.

No Need for a ProductController

Once the application runs, Spring Data REST automatically generates APIs.
Testing APIs using Postman:

1️. Get all products: GET http://localhost:8080/products
2️. Add a product: POST http://localhost:8080/products

{
  "name": "Phone",
  "category": "Electronics",
  "price": 800.00
}

3️. Get a specific product: GET http://localhost:8080/products/1
4️. Update a product: PUT http://localhost:8080/products/1
5️. Delete a product: DELETE http://localhost:8080/products/1

🔹 ✅ Advantage of @RepositoryRestResource:
✔ No need to manually write a controller! APIs are auto-created.

@Repository vs @RepositoryRestResource (Comparison Table):
Feature					@Repository (Traditional)						@RepositoryRestResource (Spring Data REST)
Requires Controller?	✅ Yes, needs a Controller						❌ No, automatically creates REST APIs
Customization?			✅ Full control over APIs						❌ Limited customization
Use Case?				When you need custom business logic				When you need quick CRUD APIs
Flexibility?			✅ Can customize responses and add logic			❌ Uses default response format
Best For?				Complex applications							Simple, quick prototypes
📌 When to Use What?
✅ Use @RepositoryRestResource when:
    You just need basic CRUD APIs without extra logic.
    You want to quickly generate REST APIs.
    You are working on a simple prototype.

✅ Use @Repository when:
    You need to add custom business logic.
    You want full control over the API responses.
    Your application has complex operations.

📌 Conclusion
Both @Repository and @RepositoryRestResource help interact with the database, but:
✔ Use @Repository when you need custom business logic in a Controller.
✔ Use @RepositoryRestResource when you need quick CRUD APIs without writing a Controller.

----------------------------------------------------
But how in my project I have @Repository after adding REST dependency like spring-boot-starter-data-rest in pom.xml 
and it still does spring data rest api calls ? 

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {
	
}

Yes! Even though you used @Repository, Spring Data REST still exposes REST APIs when you add the dependency:

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-rest</artifactId>
</dependency>

This happens because Spring Data REST automatically detects all JpaRepository interfaces and exposes RESTful endpoints for them
— even if you don’t explicitly use @RepositoryRestResource.
✔ Why is Spring Data REST Working with @Repository?
Even though @Repository is a standard Spring annotation for repositories, Spring Data REST works at a higher level 
and does not require @RepositoryRestResource.

Once you add the Spring Data REST dependency, it:
✔ Automatically exposes CRUD endpoints for all JpaRepository interfaces.
✔ Works even if @Repository is used instead of @RepositoryRestResource.

✔ Example with Your JobRepo

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Normal repository annotation, but still works with Spring Data REST
public interface JobRepo extends JpaRepository<JobPost, Integer> {
}

Even though it has @Repository, Spring Data REST still exposes endpoints.
✔ How to Test in Postman?

Once you run the application, you can test the following APIs:

1️. Get all jobs: GET http://localhost:8080/jobPosts

2️. Get a specific job by ID: GET http://localhost:8080/jobPosts/1

3️. Create a new job: POST http://localhost:8080/jobPosts

{
  "title": "Software Engineer",
  "company": "Google",
  "salary": 90000
}

4️. Update a job: PUT http://localhost:8080/jobPosts/1

{
  "title": "Senior Software Engineer",
  "company": "Google",
  "salary": 120000
}

5️. Delete a job: DELETE http://localhost:8080/jobPosts/1

✔ How to Disable Spring Data REST Auto Exposure?
If you don’t want Spring Data REST to auto-expose APIs, add this to application.properties:

spring.data.rest.base-path=/api  # Changes base path
spring.data.rest.enabled=false    # Disables auto-exposure

📌 Conclusion
Even though you used @Repository, Spring Data REST still exposes APIs because:
    spring-boot-starter-data-rest is added.
    Spring automatically scans JpaRepository interfaces.
    Spring Data REST doesn't require @RepositoryRestResource explicitly.
    
------------------------------------------------------------

how spring uses /jobPosts here in this code?I have not mentioned anywhere - How Does Spring Use /jobPosts as the URL When You Didn't Specify It?

Great question! Even though you didn't explicitly mention /jobPosts anywhere, Spring Data REST automatically derives it from your entity class name. Here's how it works:
✔ How Does Spring Generate /jobPosts?
1️. Spring Data REST scans all JpaRepository interfaces (like JobRepo).
2️. It looks at the entity class (JobPost).
3️. It converts the entity name into a REST endpoint:
    If the entity is JobPost, the endpoint becomes /jobPosts
    If the entity is Product, the endpoint becomes /products

🔹 Naming Rule: Spring pluralizes the entity name using a default naming convention.
✔ Example Based on Your Code
🔹 Entity Class (JobPost)

@Entity
public class JobPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String company;
    private double salary;
}

🔹 Spring extracts the name JobPost and converts it into jobPosts for the API URL.
🔹 Repository Interface (JobRepo)

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {
}

🔹 Because of JpaRepository<JobPost, Integer>, Spring Data REST automatically generates /jobPosts.
✔ How to Customize the URL?
If you don’t want Spring to generate /jobPosts automatically, you can manually customize it using @RepositoryRestResource:

@RepositoryRestResource(path = "jobs")
public interface JobRepo extends JpaRepository<JobPost, Integer> {
}

Now the endpoint will be /jobs instead of /jobPosts.
✔ How to Change the Default Naming Convention?

By default, Spring pluralizes the entity name (JobPost → jobPosts).

To customize it, add this to application.properties:
spring.data.rest.base-path=/api  # Changes base path from "/" to "/api"
spring.data.rest.default-media-type=application/json

Now, instead of /jobPosts, the API will be at:
📌 GET http://localhost:8080/api/jobPosts

📌 Summary
✔ Spring Data REST automatically generates REST APIs based on JpaRepository.
✔ The default URL is derived from the entity name (JobPost → /jobPosts).
✔ You can override the default URL using @RepositoryRestResource(path = "jobs").
✔ You can change the base path using spring.data.rest.base-path=/api.   

-- -----------------------------------------
how it creates rest api for post, put, delete, patch for above code? explain in simple terms with line by line explaination
How Does Spring Data REST Automatically Create APIs for POST, PUT, DELETE, and PATCH?

Since you are using Spring Data REST with JpaRepository, it automatically generates RESTful APIs for basic CRUD operations without writing any controller methods.
✔ How Does It Work Internally?
1️. Spring detects all JpaRepository interfaces (like JobRepo).
2️. It analyzes the entity class (JobPost).
3️. Based on the entity structure, Spring Data REST exposes REST endpoints automatically.
4️. These endpoints allow you to perform GET, POST, PUT, DELETE, and PATCH operations.
✔ Endpoints Created Automatically

Assuming JobPost is your entity and JobRepo extends JpaRepository<JobPost, Integer>, Spring Data REST will expose the following APIs:
1️. GET - Retrieve All Jobs
🔹 Fetch all job posts
📌 GET http://localhost:8080/jobPosts
How it Works?
    Spring calls findAll() behind the scenes.
    Returns a JSON list of all job posts.
Internally, Spring calls:
	List<JobPost> jobs = jobRepo.findAll();
	return ResponseEntity.ok(jobs);
✔ Retrieves all job posts from the database.

Example Response:

[
  {
    "id": 1,
    "title": "Software Engineer",
    "company": "Google",
    "salary": 90000
  },
  {
    "id": 2,
    "title": "Project Manager",
    "company": "Microsoft",
    "salary": 120000
  }
]

2️. GET - Retrieve a Job by ID
🔹 Fetch job details by ID
📌 GET http://localhost:8080/jobPosts/1
How it Works?
    Spring calls findById(1) automatically.
    Returns the job details for the given ID.
Internally, Spring calls:
	JobPost job = jobRepo.findById(1).orElse(null);
	return job != null ? ResponseEntity.ok(job) : ResponseEntity.notFound().build();
✔ Finds a job by id=1.
✔ Returns 404 Not Found if the job does not exist.

Example Response:

{
  "id": 1,
  "title": "Software Engineer",
  "company": "Google",
  "salary": 90000
}

3️. POST - Create a New Job
🔹 Add a new job
📌 POST http://localhost:8080/jobPosts
📌 Body (JSON):
{
  "title": "Software Engineer",
  "company": "Google",
  "salary": 90000
}


How it Works?
    Spring calls save() to insert this data into the database.
    Returns the created job object with an auto-generated id.
Internally, Spring calls:
	JobPost job = new JobPost("Software Engineer", "Google", 90000);
	JobPost savedJob = jobRepo.save(job);
	return ResponseEntity.ok(savedJob);
✔ Inserts the new job into the database.
✔ Returns the saved job with an auto-generated id.

4️. PUT - Update a Job Completely
🔹 Replace an existing job (full update)
📌 PUT http://localhost:8080/jobPosts/1
📌 Body (JSON):

{
  "title": "Senior Software Engineer",
  "company": "Google",
  "salary": 120000
}

How it Works?
    Spring calls save(), but replaces the entire object.
    If the id exists, it updates the record.
    If the id does not exist, it creates a new record.
Internally, Spring calls:
	JobPost updatedJob = jobRepo.findById(1).orElseThrow();
	updatedJob.setTitle("Senior Software Engineer");
	updatedJob.setCompany("Google");
	updatedJob.setSalary(120000);
	jobRepo.save(updatedJob);
	return ResponseEntity.ok(updatedJob);
✔ Replaces the entire job object.
✔ If id=1 doesn’t exist, it creates a new record.

5️. PATCH - Partial Update
🔹 Modify only some fields (not full replacement)
📌 PATCH http://localhost:8080/jobPosts/1
📌 Body (JSON) (Updating only salary):

{
  "salary": 140000
}

How it Works?
    Spring calls save() but only updates the specified fields.
    Does not replace the entire object (unlike PUT).
Internally, Spring calls:
	JobPost job = jobRepo.findById(1).orElseThrow();
	job.setSalary(140000);
	jobRepo.save(job);
	return ResponseEntity.ok(job);
✔ Only updates specific fields instead of replacing the whole object.

6️. DELETE - Remove a Job
🔹 Delete a job by ID
📌 DELETE http://localhost:8080/jobPosts/1
How it Works?
    Spring calls deleteById(1).
    Removes the job from the database.

Example Response (No Content, 204 Status Code):
{}

Internally, Spring calls:
	jobRepo.deleteById(1);
	return ResponseEntity.noContent().build();
✔ Deletes the job with id=1.
✔ Returns 204 No Content if successful.

✔ How to Customize the Endpoint?
Spring automatically names the API based on the entity name.
If you want to change it, use @RepositoryRestResource:

@RepositoryRestResource(path = "jobs")
public interface JobRepo extends JpaRepository<JobPost, Integer> {
}
📌 Now, instead of /jobPosts, the API will be /jobs.

Summary:
✔ Spring Data REST automatically creates REST APIs for CRUD operations.
✔ The URLs are generated from the entity name (JobPost → /jobPosts).
✔ Spring calls JpaRepository methods internally for GET, POST, PUT, PATCH, and DELETE.
✔ You can customize the URL using @RepositoryRestResource(path = "jobs").


Even though you didn’t write a controller, Spring Data REST does the following internally:
🔹 JobRepo.java (Repository Layer)

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {
}

✅ JpaRepository provides built-in CRUD methods:
    findAll() → For GET all
    findById(id) → For GET by ID
    save() → For POST & PUT
    deleteById(id) → For DELETE

🔹 How Does Spring Handle REST Requests?
Spring Data REST automatically maps HTTP methods to repository methods:
HTTP Method				Repository Method (Internally Called)
GET /jobPosts				findAll()
GET /jobPosts/1				findById(1)
POST /jobPosts				save(jobPost)
PUT /jobPosts/1				save(updatedJobPost)
PATCH /jobPosts/1			save(partialJobPost)
DELETE /jobPosts/1			deleteById(1)

📌 Summary
✔ Spring Data REST automatically creates APIs based on JpaRepository.
✔ No need to write controllers—Spring maps HTTP methods to repository functions.
✔ You can override the behavior using @RepositoryRestResource.
✔ POST, PUT, DELETE, PATCH, and GET are automatically supported.
*/