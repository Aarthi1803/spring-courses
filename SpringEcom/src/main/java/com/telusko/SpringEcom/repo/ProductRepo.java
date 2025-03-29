package com.telusko.SpringEcom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.telusko.SpringEcom.model.Product;
import java.util.List;


//@Repository
//public interface ProductRepo extends JpaRepository<Product, Integer>{
//
//}

/*****************************************************************************/

/*@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
    // Custom query to fetch only the imageData by product ID
	@Query("SELECT p.imageData FROM Product p WHERE p.id = :id") // Fetches only the imageData column instead of the entire Product entity.
    byte[] findImageDataById(@Param("id") Long id); // Images are stored in the database as binary data (not text or numbers), so we use byte[] to handle them. This annotation links the method parameter (id) to the query's :id placeholder. 

}*/

// Since images are stored as byte arrays (byte[]), we can fetch them directly using JPA.
// ✔ findImageDataById(Long id): Fetches only the image data from the database based on product ID.
// ✔ This method works because Spring Data JPA automatically understands queries based on method naming.

/******************************************************************************/

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
	 // Custom query to fetch only the imageData by product ID
		@Query("SELECT p.imageData FROM Product p WHERE p.id = :id") // Fetches only the imageData column instead of the entire Product entity.
	    byte[] findImageDataById(@Param("id") Long id); // Images are stored in the database as binary data (not text or numbers), so we use byte[] to handle them. This annotation links the method parameter (id) to the query's :id placeholder. 

    @Query("SELECT p from Product p WHERE " +
            "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.description) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Product> searchProducts(String keyword);
    
}

/*
You can use JPAQL to define your query here. So whenever someone hit this particular method, it will execute the query, and whatever results you get, it will convert that into this Java objects.
And in this query you can specify your query not in SQL format but in JPAQL format which is which is JPA query language.
So what is different in SQL and JPAQL. Basically you don't use table names.
You use class name and then you use a certain specific JPAQL, JPA specific methods and syntax. So whatever keyword you are passing should match with your variable name like p.name, p.category etc. 
And that's why we got a colon there. (:keyword). So it means this is a variable. Fetch the data from variable. And we are using LIKELOWER(CONCAT('%', :keyword, '%')) so that it will also match the start and end.

*/