package com.telusko.SpringEcom.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.telusko.SpringEcom.model.Product;
import com.telusko.SpringEcom.service.ProductService;

/*@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public List<Product> getProducts() {
		return productService.getAllProducts();
	}
}*/

/*********************************************************/
/*@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
//	@GetMapping("/products")
//	public List<Product> getProducts() {
//		return productService.getAllProducts();
//	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
//		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED); // 202 Accepted - Request accepted for processing, but the processing is not completed.
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK); // 200 OK - The request has succeeded.
	}
	
}*/

/*
HTTP Status Codes:
200 - Success:
200 OK - The request has succeeded.
201 Created - New Resource Created. (i.e posting data onto server using @PostMapping)
202 Accepted - Request accepted for processing, but the processing is not completed.

400 - Client Errors:
404 Not Found - The server cannot find the requested resource.
401 Unauthorized - The request requires user authentication.(Authentication Failure)
405 Method Not Allowed - The request method is known by the server but disabled and cannot be used for the requested resource. (Suppose we are passing @GetMapping but server expects @PostMapping we get error as 405)

500 - Server Errors:
500 Internal Server Error - A generic error message, given when an unexpected condition was encountered.
502 Bad Gateway - The server while acting as a gateway or proxy, received an invalid response from the upstream server it accessed to fulfill the request.
*/

/***********************************************************/
/*@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
//		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED); // 202 Accepted - Request accepted for processing, but the processing is not completed.
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK); // 200 OK - The request has succeeded.
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id){
		Product product = productService.getProductById(id);
		
//		if(product !=  null)
		if(product.getId() > 0)	
			return new ResponseEntity<> (product, HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
}*/

/*************************************************************/
/*@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
//		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED); // 202 Accepted - Request accepted for processing, but the processing is not completed.
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK); // 200 OK - The request has succeeded.
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id){
		Product product = productService.getProductById(id);
		
//		if(product !=  null)
		if(product.getId() > 0)	
			return new ResponseEntity<> (product, HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/product") // This method is used to add a new product details with image to the system.
	public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){ // The <?> means the response body can be of any type (e.g., String, Object, List, etc.).
//		@RequestPart: Used to handle multipart requests (e.g., forms with files and JSON data). MultipartFile: Represents an uploaded file in Spring. ResponseEntity: Used to customize the HTTP response (status code, headers, body). Error Handling: Catches and handles exceptions (e.g., IOException) gracefully.
		Product savedProduct;
		try {
			savedProduct = productService.addProduct(product, imageFile);
			return new ResponseEntity<> (savedProduct, HttpStatus.CREATED); // Returns a 201 CREATED response with the saved product in the response body.
		} catch (IOException e) { // Catches any IOException that occurs during file handling.
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Returns a 500 INTERNAL SERVER ERROR response with the error message in the response body.
		}
		
	}
	
// Took this code from ChatGPT- Specifies that the endpoint retrieves an image and returns it in JPEG format.
	@GetMapping(value = "/product/{id}/image", produces = "image/jpeg")
	public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
		byte[] imageData = productService.getProductImageById(id);
		return imageData != null ? ResponseEntity.ok().body(imageData) : ResponseEntity.notFound().build(); // Sends binary image data to the client.  If no image is found, it returns 404 Not Found.
	}
	
// Took this code from ChatGPT - Since you already have products in the database but without images, you need an API to upload images for existing products. Create a POST API that accepts an image file and updates the existing product’s imageData field. Store the image as a byte array (byte[]) in the database.
	@PostMapping("/product/uploadImage/{id}") // Allows uploading an image for an existing product. The image is converted into a byte array (byte[]) and stored in the database.
    public ResponseEntity<String> uploadImage( @PathVariable Integer id, @RequestPart("imageFile") MultipartFile imageFile) { // @RequestPart("imageFile") MultipartFile imageFile → Allows uploading an image file.
        try {
            String response = productService.uploadImage(id, imageFile);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error uploading image.");
        } 
	}
	
	@GetMapping("product/{productId}/image") // Fetch Product Image
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
		Product product = productService.getProductById(productId);
//		return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
		if(product.getId() > 0)	
			return new ResponseEntity<> (product.getImageData(), HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
}*/

/*
 
Admin should be able to add a new product with image. So now we have to work with two things add product details along with image.
So before you send this request the first thing you have to change is since we want to work with image now I have to add some new fields. 
Now working with image is a bit tricky. So the first thing is in image there are multiple ways of doing this.
One way is using the base 64 encoder.

You take your JSON from the from the client. You also take the image from the client, and when you send this data from client to server, 
the server has to accept the JSON data and the image as well. Now the tricky part is how will you get this image? There are two ways.
First is you can convert this image into text format, send it to the server and server.
Server will decode it. The next way is you can send the JSON and image separately, and on the server side you can accept them separately. 
So here we are going to go for the second approach where you are sending the image separately.
first create a field for the image name, because every image will have a name as well.
Then we have to work with the image type. And the third is very important the actual image.
And then you can store this data in the byte stream. Because ultimately in database you have to convert that image. 
So we'll store that in a byte stream. We will say image data.
You have to tag it with @Lob So lob means large objects. 
And since we are sending an image not a simple text or our numbers, we have to convert that into large binary object.

private String imageName;
private String imageType;
@Lob
private byte[] imageData;
 
 ResponseEntity<?> is a class in Spring Framework that represents an HTTP response, including the response body, status code, and headers. 
 The <?> is a generic type in Java, which means it can hold any type of object as the response body. 
 The ? is a wildcard that indicates the type is unknown or can be any type.
 
 What Does <?> Mean?
    The ? is a wildcard in Java generics. It means the type is unknown or can be any type.
    In ResponseEntity<?>, the ? indicates that the response body can be of any type (e.g., String, Integer, List, custom object, etc.).

    For example:
        ResponseEntity<String>: The response body is a String.
        ResponseEntity<User>: The response body is a User object.
        ResponseEntity<?>: The response body can be any type.
     ResponseEntity<?> is a flexible way to return HTTP responses in Spring.

    It allows you to specify the response body, status code, and headers. The ? wildcard makes it generic, so it can handle any type of response body.

    Common methods for building responses:
        ResponseEntity.ok(body): Returns a 200 OK response with the specified body.
        ResponseEntity.status(HttpStatus.CREATED).body(body): Returns a custom status code (e.g., 201 CREATED) with the specified body.
        ResponseEntity.noContent(): Returns a 204 No Content response with no body.
        
    @RequestPart Product product: Binds the product data from the request body to a Product object.
    @RequestPart is used for multipart requests (e.g., forms with files and JSON data).  
    
    @RequestPart MultipartFile imageFile: Binds the uploaded image file from the request to a MultipartFile object.
    MultipartFile is used to handle file uploads in Spring.  
    
    try { ... } catch (IOException e) { ... }:
    Wraps the service call in a try-catch block to handle potential IOException (e.g., file upload errors).
    
    This method is used in an e-commerce application to add a new product to the catalog.
    The product details (e.g., name, price, description) are sent as JSON, and the product image is uploaded as a file.
    If the file upload fails (e.g., due to network issues or invalid file format), the server responds with an error message.
    
    How It Works Together:
    i. The client sends a multipart request to the /product endpoint with:
        A JSON payload containing product details.
        An image file.
    ii. The controller method (addProduct) receives the request and delegates the processing to the service layer.
    iii. The service method (addProduct) processes the product and image, saves them to the database, and returns the saved product.
    iv. The controller returns a 201 CREATED response with the saved product or a 500 INTERNAL SERVER ERROR if an exception occurs.
    
    What is @RequestPart?
	@RequestPart is used in Spring Boot when you need to handle multipart requests (file uploads) along with other JSON data in the same request.
	✔ It is used for handling files (MultipartFile) in multipart/form-data requests.
	✔ Used when sending JSON + File together in a request.
	✔ Works only with @PostMapping or @PutMapping (not @GetMapping).
	
	What is MultipartFile?
	✔ MultipartFile is an interface provided by Spring to handle file uploads.
	✔ It allows you to read file content, get file name, size, and convert it into byte[].
	✔ Used when sending files in multipart/form-data format.
	
	How @RequestPart Works Internally?
    Spring Boot parses the multipart request.
    Extracts JSON data (product object).
    Extracts binary file (imageFile).
    Converts file to byte array and saves it in DB.
    
🔹  @RequestPart is used for handling JSON + file uploads in one request.
🔹 	MultipartFile helps read file data and convert it to a byte array.
🔹  Used when storing images, PDFs, or any file in a database.
🔹  Works with @PostMapping using multipart/form-data requests.
    
*/

/*******************************************************************/

/* @RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
//		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED); 
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK); 
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id){
		Product product = productService.getProductById(id);
		
//		if(product !=  null)
		if(product.getId() > 0)	
			return new ResponseEntity<> (product, HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/product") 
	public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){ 
		Product savedProduct;
		try {
//			savedProduct = productService.addProduct(product, imageFile);
			savedProduct = productService.addOrUpdateProduct(product, imageFile); // Since addProduct and updateProduct both have same code we are creating it in one method name as - addOrUpdateProduct	
			return new ResponseEntity<> (savedProduct, HttpStatus.CREATED); 
		} catch (IOException e) { 
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
	}
	
// Took this code from ChatGPT- Specifies that the endpoint retrieves an image and returns it in JPEG format.
	@GetMapping(value = "/product/{id}/image", produces = "image/jpeg")
	public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
		byte[] imageData = productService.getProductImageById(id);
		return imageData != null ? ResponseEntity.ok().body(imageData) : ResponseEntity.notFound().build(); 
	}
	
// Took this code from ChatGPT - Since you already have products in the database but without images, you need an API to upload images for existing products. Create a POST API that accepts an image file and updates the existing product’s imageData field. Store the image as a byte array (byte[]) in the database.
	@PostMapping("/product/uploadImage/{id}") 
    public ResponseEntity<String> uploadImage( @PathVariable Integer id, @RequestPart("imageFile") MultipartFile imageFile) { 
        try {
            String response = productService.uploadImage(id, imageFile);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error uploading image.");
        } 
	}
	
	@GetMapping("product/{productId}/image") // Fetch Product Image
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
		Product product = productService.getProductById(productId);
//		return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
		if(product.getId() > 0)	
			return new ResponseEntity<> (product.getImageData(), HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile ){
		Product updatedProduct = null;
		try {
			// updatedProduct = productService.updateProduct(product, imageFile);
			updatedProduct = productService.addOrUpdateProduct(product, imageFile); // Since addProduct and updateProduct both have same code we are creating it in one method name as - addOrUpdateProduct	
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
        } 
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		Product product = productService.getProductById(id);
		if(product != null) {
			productService.deleteProduct(id);
			return new ResponseEntity<>("Deleted",HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
} */

/********************************************************/
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts(){
//		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED); 
		return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK); 
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable int id){
		Product product = productService.getProductById(id);
		
//		if(product !=  null)
		if(product.getId() > 0)	
			return new ResponseEntity<> (product, HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/product") 
	public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){ 
		Product savedProduct;
		try {
			savedProduct = productService.addOrUpdateProduct(product, imageFile); // Since addProduct and updateProduct both have same code we are creating it in one method name as - addOrUpdateProduct	
			return new ResponseEntity<> (savedProduct, HttpStatus.CREATED); 
		} catch (IOException e) { 
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
	}
	
// Took this code from ChatGPT- Specifies that the endpoint retrieves an image and returns it in JPEG format.
	@GetMapping(value = "/product/{id}/image", produces = "image/jpeg")
	public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
		byte[] imageData = productService.getProductImageById(id);
		return imageData != null ? ResponseEntity.ok().body(imageData) : ResponseEntity.notFound().build(); 
	}
	
// Took this code from ChatGPT - Since you already have products in the database but without images, you need an API to upload images for existing products. Create a POST API that accepts an image file and updates the existing product’s imageData field. Store the image as a byte array (byte[]) in the database.
	@PostMapping("/product/uploadImage/{id}") 
    public ResponseEntity<String> uploadImage( @PathVariable Integer id, @RequestPart("imageFile") MultipartFile imageFile) { 
        try {
            String response = productService.uploadImage(id, imageFile);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error uploading image.");
        } 
	}
	
	@GetMapping("product/{productId}/image") // Fetch Product Image
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
		Product product = productService.getProductById(productId);
//		return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
		if(product.getId() > 0)	
			return new ResponseEntity<> (product.getImageData(), HttpStatus.OK);
		else
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Product product, @RequestPart MultipartFile imageFile ){
		Product updatedProduct = null;
		try {
			updatedProduct = productService.addOrUpdateProduct(product, imageFile); // Since addProduct and updateProduct both have same code we are creating it in one method name as - addOrUpdateProduct	
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
        } 
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id){
		Product product = productService.getProductById(id);
		if(product != null) {
			productService.deleteProduct(id);
			return new ResponseEntity<>("Deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
		List<Product> products = productService.searchProducts(keyword);
		System.out.println("searching with " + keyword);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
