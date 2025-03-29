package com.telusko.SpringEcom.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // It will create getters and setters, toString() and hashcode methods
@NoArgsConstructor // It will create default constructor
@AllArgsConstructor // It will create parameterized constructor with all parameters
public class Product{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrementing primary keys
	private int id;
	private String name;
	private String description;
	private String brand;
	private BigDecimal price;
	private String category;
	// @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy") // @JsonFormat annotation from Jackson library. - Commenting because in video "Update and Delete Product" frontend handles date format so commenting here 
	private Date releaseDate;
	private boolean productAvailable;
	private int stockQuantity;
	private String imageName;
	private String imageType;
	@Lob // Marks imageData as Large Object (BLOB). Tells JPA to store imageData as a large object (BLOB).
	private byte[] imageData; // Stores image as byte array (binary data)
	
	public Product(int id) {
		this.id = id;
	}
	
}

/*

@JsonFormat annotation is used in Spring Boot (with Jackson Library) to specify how Java objects should be formatted when serialized into JSON.
It is mainly used for date formatting, but it can also be used for numbers and enums.
✅ Real-World Scenario

Imagine you're building an E-commerce Application, and you have a Product entity with a release date.
    Database Format: The date is stored as YYYY-MM-DD HH:MM:SS in SQL.
    Client Format: The front-end (Angular/React) expects the date in dd-MM-yyyy format.

To solve this issue, we use @JsonFormat.

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")

✔ @JsonFormat - This annotation comes from the Jackson Library and is used to format JSON output.
✔ shape = JsonFormat.Shape.STRING - Converts Date into a String (instead of Unix timestamp).
✔ pattern = "dd-MM-yyyy" - Defines the format (Day-Month-Year, e.g., 15-03-2024).
 
 JSON Output (Without @JsonFormat)

If we don’t use @JsonFormat, the releaseDate is serialized as a Unix timestamp.

{
    "id": 1,
    "name": "iPhone 15",
    "description": "Latest Apple iPhone",
    "brand": "Apple",
    "price": 999.99,
    "category": "Electronics",
    "releaseDate": 1707849600000,   // Unix Timestamp
    "productAvailable": true,
    "stockQuantity": 50
}

Problem: Front-end needs to convert the timestamp into a readable date.

JSON Output (With @JsonFormat): After applying @JsonFormat, the output looks clean and readable.

{
    "id": 1,
    "name": "iPhone 15",
    "description": "Latest Apple iPhone",
    "brand": "Apple",
    "price": 999.99,
    "category": "Electronics",
    "releaseDate": "15-03-2024",
    "productAvailable": true,
    "stockQuantity": 50
}

✔ Easier to read
✔ Frontend gets correctly formatted data

@JsonFormat with Time (Example):
You can also format Date with Time:
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
private Date releaseDate;

Output: "releaseDate": "15-03-2024 12:30:00"

✔ Includes hours, minutes, and seconds.

@JsonFormat with LocalDate (Java 8)

For Java 8+, LocalDate is preferred:
@JsonFormat(pattern = "dd-MM-yyyy")
private LocalDate releaseDate;

🔹 Works with LocalDate instead of Date.

@JsonFormat formats date fields in JSON responses.
It converts Date into human-readable format instead of Unix timestamps.
Used in REST APIs to maintain consistent date formatting between Backend & Frontend.


*/

/*
  @Lob annotation in Java Persistence API (JPA) is used to store large objects like:
✔ Binary Data (e.g., Images, Files, Videos, PDFs)
✔ Large Text Data (e.g., Long Documents, JSON, XML Strings)

Why Use @Lob?
When you need to store large amounts of data (like an image or a long text) inside a database, you cannot use normal data types like String or byte[].
Instead, we use @Lob (Large Object) which maps to:
    BLOB (Binary Large Object) → Used for binary data like images & files.
    CLOB (Character Large Object) → Used for large text data.
 
 How @Lob Works Internally?

    The @Lob annotation ensures the binary data is stored in a BLOB column in the database.
    When retrieving the image, the binary data is converted back into an image and sent to the client.
    
  Difference Between BLOB and CLOB
Type								Stores										Example
BLOB (Binary Large Object)		Binary Data (Images, Files, Videos)			byte[] imageData
CLOB (Character Large Object)	Large Text Data (Documents, JSON)			String longText  

@Lob helps store large binary (BLOB) or text (CLOB) data in a database.
Used to store images, files, videos, long documents, JSON, XML, etc.
Works with byte[] for BLOB and String for CLOB.
Essential when handling image uploads in REST APIs.

*/