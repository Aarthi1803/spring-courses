package com.telusko.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.telusko.model.Product;

@RepositoryRestResource(path = "products") // Exposes the repository as a REST API. This automatically creates REST APIs for the Product entity.
public interface ProductRepository extends JpaRepository<Product, Long> { // Enables CRUD operations on Product.
	
}

/*
Spring Data REST is a built-in feature of Spring Boot that automatically creates RESTful APIs for your JPA repositories without writing a controller.

✅ It saves time because you don’t need to manually write CRUD APIs.
✅ Spring Data REST exposes repository methods as RESTful APIs automatically.

Imagine you are building an E-commerce website and have a Product entity.
Instead of writing a full controller with GET, POST, PUT, and DELETE APIs,
Spring Data REST will generate those APIs automatically.

Summary:
Feature							Explanation
Spring Data REST				Automatically creates REST APIs from JPA repositories.
No Need for a Controller		You don’t need to manually write a controller.
Automatic Endpoints				Exposes GET, POST, PUT, DELETE APIs automatically.
Connects to MySQL				Stores product data in a MySQL database.

💡 When to use Spring Data REST?

    ✅ When you need quick CRUD APIs.
    ✅ When you don't need custom business logic.
    ✅ For rapid prototyping.

💡 When NOT to use Spring Data REST?

    ❌ When you need custom business logic (e.g., validations, authentication).
    ❌ When you want custom response formats.
 
 Spring Data REST saves time by automatically creating CRUD APIs. If you just need a simple API to manage data, use it. If you need custom logic, you should write your own controllers.
*/