package com.telusko.SpringEcom.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.telusko.SpringEcom.model.Product;
import com.telusko.SpringEcom.repo.ProductRepo;

/*@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
}	
*/
/*******************************************************/
/*@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	
	public Product getProductById(int id) {
//		return productRepo.findById(id).orElse(null);
		return productRepo.findById(id).orElse(new Product(-1)); // -1 means there is no product found so returns -1. In Controller it checks if ID > 0 it gives product details. If ID < 0 it gives return new ResponseEntity<> (HttpStatus.NOT_FOUND);  When using API in Postman we get 404 - Not Found
//		return productRepo.findById(id).get(); // get() - If a value is present, returns the value, otherwise throws NoSuchElementException. When using API in Postman we get 500 - Internal Server Error
	}
}*/
/******************************************************************/
/*@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	
	public Product getProductById(int id) {
//		return productRepo.findById(id).orElse(null);
		return productRepo.findById(id).orElse(new Product(-1)); 
//		return productRepo.findById(id).get(); 
	}

// Since addProduct and updateProduct both have same code we are creating it in one method name as - addOrUpdateProduct
//	public Product addProduct(Product product, MultipartFile imageFile) throws IOException { // This method is responsible for processing the product details and image file. It stores the image metadata (name, type, and binary data) in the database alongside the product details.
//		product.setImageName(imageFile.getOriginalFilename()); // Sets the imageName field of the Product object to the original filename of the uploaded image.
//		product.setImageType(imageFile.getContentType()); // Sets the imageType field of the Product object to the MIME type of the uploaded image (e.g., image/jpeg).
//		product.setImageData(imageFile.getBytes()); // Sets the imageData field of the Product object to the byte array representation of the uploaded image.
//		
//		return productRepo.save(product);
//	}

	public byte[] getProductImageById(Long id) {
		 return productRepo.findImageDataById(id);
	}

	public String uploadImage(Integer id, MultipartFile imageFile) throws IOException  {
        Optional<Product> optionalProduct = productRepo.findById(id); // Fetches the product by its ID.
        
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get(); // get() - If a value is present, returns the value, otherwise throws NoSuchElementException. When using API in Postman we get 500 - Internal Server Error
            product.setImageData(imageFile.getBytes()); // Converts the image file into a byte array (byte[]).
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            productRepo.save(product); //  Saves the updated product in the database.
            return "Image uploaded successfully for product ID: " + id;
        } else {
            return "Product not found with ID: " + id;
        }
        
	}
	
// Since addProduct and updateProduct both have same code we are creating it in one method name as - addOrUpdateProduct
//	public Product updateProduct(Product product, MultipartFile imageFile) throws IOException { // Add Product and Save Product code both are same
//		product.setImageName(imageFile.getOriginalFilename()); // Sets the imageName field of the Product object to the original filename of the uploaded image.
//		product.setImageType(imageFile.getContentType()); // Sets the imageType field of the Product object to the MIME type of the uploaded image (e.g., image/jpeg).
//		product.setImageData(imageFile.getBytes()); // Sets the imageData field of the Product object to the byte array representation of the uploaded image.
//		
//		return productRepo.save(product);
//	}

// Since addProduct and updateProduct both have same code we are creating it in one method name as - addOrUpdateProduct	
	public Product addOrUpdateProduct(Product product, MultipartFile imageFile) throws IOException { // Add Product and Save Product code both are same
		product.setImageName(imageFile.getOriginalFilename()); // Sets the imageName field of the Product object to the original filename of the uploaded image.
		product.setImageType(imageFile.getContentType()); // Sets the imageType field of the Product object to the MIME type of the uploaded image (e.g., image/jpeg).
		product.setImageData(imageFile.getBytes()); // Sets the imageData field of the Product object to the byte array representation of the uploaded image.
		
		return productRepo.save(product);
	}

	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}
	
}*/

/******************************************************************/
@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}
	
	public Product getProductById(int id) {
//		return productRepo.findById(id).orElse(null);
		return productRepo.findById(id).orElse(new Product(-1)); 
//		return productRepo.findById(id).get(); 
	}


	public byte[] getProductImageById(Long id) {
		 return productRepo.findImageDataById(id);
	}

	public String uploadImage(Integer id, MultipartFile imageFile) throws IOException  {
        Optional<Product> optionalProduct = productRepo.findById(id); // Fetches the product by its ID.
        
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get(); // get() - If a value is present, returns the value, otherwise throws NoSuchElementException. When using API in Postman we get 500 - Internal Server Error
            product.setImageData(imageFile.getBytes()); // Converts the image file into a byte array (byte[]).
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            productRepo.save(product); //  Saves the updated product in the database.
            return "Image uploaded successfully for product ID: " + id;
        } else {
            return "Product not found with ID: " + id;
        }
        
	}
	
// Since addProduct and updateProduct both have same code we are creating it in one method name as - addOrUpdateProduct	
	public Product addOrUpdateProduct(Product product, MultipartFile imageFile) throws IOException { // Add Product and Save Product code both are same
		product.setImageName(imageFile.getOriginalFilename()); // Sets the imageName field of the Product object to the original filename of the uploaded image.
		product.setImageType(imageFile.getContentType()); // Sets the imageType field of the Product object to the MIME type of the uploaded image (e.g., image/jpeg).
		product.setImageData(imageFile.getBytes()); // Sets the imageData field of the Product object to the byte array representation of the uploaded image.
		
		return productRepo.save(product);
	}

	public void deleteProduct(int id) {
		productRepo.deleteById(id);
	}

	public List<Product> searchProducts(String keyword) {
		return productRepo.searchProducts(keyword);
	}
	
}
