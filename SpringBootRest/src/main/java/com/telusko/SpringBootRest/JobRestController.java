package com.telusko.SpringBootRest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.SpringBootRest.model.JobPost;
import com.telusko.SpringBootRest.service.JobService;


//@Controller
/*@RestController
public class JobRestController {
	
	@Autowired
	private JobService service;
	
	@GetMapping("jobPosts")
//	@ResponseBody //By default @Controller thinks we need to return jsp page and not data. Tells Spring not to return a view (like JSP, Thymeleaf, etc.) but to return data directly.
//	The returned List<JobPost> will be automatically converted to JSON. Better Approach – @RestController (No Need for @ResponseBody) - If your entire controller is returning JSON responses, use @RestController instead
//	@RestController automatically applies @ResponseBody to all methods in this class.
	public List<JobPost> getAllJobs() {
		return service.returnAllJobPosts();
	}
	
	// If in PostMan we use post method then we get Error - 405 - Method not allowed
	
}*/

/**********************************************************/

/*@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend React to call this API
public class JobRestController {
	
	@Autowired
	private JobService service;
	
	@GetMapping("jobPosts")
//	@ResponseBody 
	public List<JobPost> getAllJobs() {
		return service.returnAllJobPosts();
	}
	
	// If in PostMan we use post method then we get Error - 405 - Method not allowed
	
}*/


/*
@CrossOrigin(origins = "http://localhost:3000") – Explanation and Purpose
In Spring Boot, the @CrossOrigin annotation is used to enable Cross-Origin Resource Sharing (CORS). It allows a frontend application running on a different origin (domain/port) to make HTTP requests to your Spring Boot backend.
CORS (Cross-Origin Resource Sharing) is a security feature in browsers that blocks requests from different origins to prevent cross-site scripting attacks.
If your frontend (React, Angular, Vue) is hosted on http://localhost:3000 and your backend is on http://localhost:8080, the browser will block the request unless CORS is enabled.
 
Adding CORS at the Global Level (Recommended):
Instead of adding @CrossOrigin to each controller, you can enable CORS for all APIs in WebMvcConfigurer. 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Allow all APIs
                        .allowedOrigins("http://localhost:3000") // Allow frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specific methods
                        .allowedHeaders("*"); // Allow all headers
            }
        };
    }
}

// This will allow all APIs (/**) to be accessed from http://localhost:3000.
// You can modify it to allow specific origins, methods, or headers.

Final Takeaways: 
✅ CORS is needed when frontend & backend run on different origins (domain/port).
✅ @CrossOrigin(origins = "http://localhost:3000") allows the frontend to call backend APIs.
✅ For global CORS handling, use CorsConfig in WebMvcConfigurer.

 */

/**********************************************************/

/*@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class JobRestController {
	
	@Autowired
	private JobService service;
	
	@GetMapping("jobPosts")
	public List<JobPost> getAllJobs() {
		return service.returnAllJobPosts();
	}
	
	@GetMapping("jobPost/{postId}")
	public JobPost getJob(@PathVariable("postId") int postId) { // @PathVariable is used to extract values from the URL path and use them as method parameters in a Spring Boot controller. {postId} is a placeholder for a dynamic value. Extracts the value from {postId} in the URL and assigns it to the postId parameter. Example: If the URL is jobpost/101, then postId = 101.
		return service.getJob(postId);
	}
}*/

/**********************************************************/
/*@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class JobRestController {
	
	@Autowired
	private JobService service;
	
	@GetMapping("jobPosts")
	public List<JobPost> getAllJobs() {
		return service.returnAllJobPosts();
	}
	
	@GetMapping("jobPost/{postId}")
	public JobPost getJob(@PathVariable("postId") int postId) { 
		return service.getJob(postId);
	}
	
	@PostMapping("jobPost") // It expects JSON data in the request body.
	public JobPost addJob(@RequestBody JobPost jobPost) { // To return data from server to client we use @ResponseBody. When you want to post data to server we use @RequestBody. @RequestBody - Converts JSON from request body into a Java object. 
		 service.addJob(jobPost);
		 return service.getJob(jobPost.getPostId());
	}
	
}

//	@RequestBody is used to convert the incoming JSON data from the client into a Java object.
//	It is mainly used in POST and PUT requests to send data from the client to the server.
//	@RequestBody JobPost jobPost - Converts the incoming JSON request into a Java object (JobPost) automatically.
//    Example: If the client sends this JSON:
// {
//   "id": 103,
//   "title": "Backend Developer",
//   "company": "Tech Firm"
// }
// It will be converted into a Java object like:
// JobPost jobPost = new JobPost(103, "Backend Developer", "Tech Firm");

*/

/**********************************************************/
/*@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class JobRestController {
	
	@Autowired
	private JobService service;
	
	@GetMapping("jobPosts")
	public List<JobPost> getAllJobs() {
		return service.returnAllJobPosts();
	}
	
	@GetMapping("jobPost/{postId}")
	public JobPost getJob(@PathVariable("postId") int postId) { 
		return service.getJob(postId);
	}
	
	@PostMapping("jobPost") 
	public JobPost addJob(@RequestBody JobPost jobPost) { 
		 service.addJob(jobPost);
		 return service.getJob(jobPost.getPostId());
	}
	
	@PutMapping("jobPost")
	public JobPost updateJob(@RequestBody JobPost jobPost) {
		service.updateJob(jobPost);
		return service.getJob(jobPost.getPostId());
	}
	
	@DeleteMapping("jobPost/{postId}")
	public String deleteJob(@PathVariable("postId") int postId) {
		service.deleteJob(postId);
		return "Deleted Successfully";
	}
}*/

/**********************************************************/
/*@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class JobRestController {
	
	@Autowired
	private JobService service;
	
// 	@GetMapping("jobPosts") 
//	public List<JobPost> getAllJobs() { // After adding "Jackson Dataformat XML library" dependency in pom.xml, if we try to get all JobPosts in PostMan by adding in Headers - Accept:application/xml. Jackson Dataformat XML library converts your Java object into xml format 
//		return service.returnAllJobPosts();
//	}
	
	@GetMapping(path="jobPosts", produces = {"application/json"}) // Produces - Returns only JSON as response and not XML. If in Postman we request using Accept : application/xml we get 406 Error saying xml format not acceptable
	public List<JobPost> getAllJobs() {
		return service.returnAllJobPosts();
	}
	
	@GetMapping("jobPost/{postId}")
	public JobPost getJob(@PathVariable("postId") int postId) { 
		return service.getJob(postId);
	}
	
	@PostMapping(path="jobPost", consumes = {"application/xml"}) // Consumes - Accepts only XML as request and not JSON. If in Postman we request using Content-Type : application/json and give in body JSON data like {"postId" : 101, "postProfile":"Java Automation"....} we get Error 415 - Unsupported Media Type 
	public JobPost addJob(@RequestBody JobPost jobPost) { 
		 service.addJob(jobPost);
		 return service.getJob(jobPost.getPostId());
	}
	
	@PutMapping("jobPost")
	public JobPost updateJob(@RequestBody JobPost jobPost) {
		service.updateJob(jobPost);
		return service.getJob(jobPost.getPostId());
	}
	
	@DeleteMapping("jobPost/{postId}")
	public String deleteJob(@PathVariable("postId") int postId) {
		service.deleteJob(postId);
		return "Deleted Successfully";
	}
} */

// Jackson library is responsible to convert Java object into JSON. By default Spring provides Jackson library that converts objects into JSON only and not objects into XML.
// Jackson Dataformat XML library - Data format extension for Jackson to offer alternative support for serializing POJOs (Java objects) as XML and deserializing XML as POJOs (Java objects).
// For Jackson Dataformat XML library we need to add dependency in pom.xml - jackson-dataformat-xml

/**********************************************************/

/*@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class JobRestController {
	
	@Autowired
	private JobService service;
	
// 	@GetMapping("jobPosts") 
//	public List<JobPost> getAllJobs() { // After adding "Jackson Dataformat XML library" dependency in pom.xml, if we try to get all JobPosts in PostMan by adding in Headers - Accept:application/xml. Jackson Dataformat XML library converts your Java object into xml format 
//		return service.returnAllJobPosts();
//	}
	
	@GetMapping(path="jobPosts", produces = {"application/json"}) // Produces - Returns only JSON as response and not XML. If in Postman we request using Accept : application/xml we get 406 Error saying xml format not acceptable
	public List<JobPost> getAllJobs() {
		return service.returnAllJobPosts();
	}
	
	@GetMapping("jobPost/{postId}")
	public JobPost getJob(@PathVariable("postId") int postId) { 
		return service.getJob(postId);
	}
	
	@PostMapping(path="jobPost", consumes = {"application/xml"}) // Consumes - Accepts only XML as request and not JSON. If in Postman we request using Content-Type : application/json and give in body JSON data like {"postId" : 101, "postProfile":"Java Automation"....} we get Error 415 - Unsupported Media Type 
	public JobPost addJob(@RequestBody JobPost jobPost) { 
		 service.addJob(jobPost);
		 return service.getJob(jobPost.getPostId());
	}
	
	@PutMapping("jobPost")
	public JobPost updateJob(@RequestBody JobPost jobPost) {
		service.updateJob(jobPost);
		return service.getJob(jobPost.getPostId());
	}
	
	@DeleteMapping("jobPost/{postId}")
	public String deleteJob(@PathVariable("postId") int postId) {
		service.deleteJob(postId);
		return "Deleted Successfully";
	}
	
//  This method loads data into MySQL table for first time. 
//	Since we converted our project from SpringRest to SpringDataJPA we have connected to database and loaded data into MySQL table.
	@GetMapping("load")
	public String loadData() { 
		service.load();
		return "success";
	}
	
}*/ 

/**********************************************************/
@RestController
@CrossOrigin(origins = "http://localhost:3000") 
public class JobRestController {
	
	@Autowired
	private JobService service;
	
// 	@GetMapping("jobPosts") 
//	public List<JobPost> getAllJobs() { // After adding "Jackson Dataformat XML library" dependency in pom.xml, if we try to get all JobPosts in PostMan by adding in Headers - Accept:application/xml. Jackson Dataformat XML library converts your Java object into xml format 
//		return service.returnAllJobPosts();
//	}
	
	@GetMapping(path="jobPosts", produces = {"application/json"}) // Produces - Returns only JSON as response and not XML. If in Postman we request using Accept : application/xml we get 406 Error saying xml format not acceptable
	public List<JobPost> getAllJobs() {
		return service.returnAllJobPosts();
	}
	
	@GetMapping("jobPost/{postId}")
	public JobPost getJob(@PathVariable("postId") int postId) { 
		return service.getJob(postId);
	}
	
	//@PostMapping(path="jobPost", consumes = {"application/xml"}) // Consumes - Accepts only XML as request and not JSON. If in Postman we request using Content-Type : application/json and give in body JSON data like {"postId" : 101, "postProfile":"Java Automation"....} we get Error 415 - Unsupported Media Type 
	@PostMapping("jobPost")
	public JobPost addJob(@RequestBody JobPost jobPost) { 
		 service.addJob(jobPost);
		 return service.getJob(jobPost.getPostId());
	}
	
	@PutMapping("jobPost")
	public JobPost updateJob(@RequestBody JobPost jobPost) {
		service.updateJob(jobPost);
		return service.getJob(jobPost.getPostId());
	}
	
	@DeleteMapping("jobPost/{postId}")
	public String deleteJob(@PathVariable("postId") int postId) {
		service.deleteJob(postId);
		return "Deleted Successfully";
	}
	
	@GetMapping("load")
	public String loadData() { 
		service.load();
		return "success";
	}
	
	@GetMapping("jobPosts/keyword/{keyword}")
	public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
		return service.search(keyword);
	}
	
} 

/* search by keyword helps us to filter out jobs by searching specific keyword in either postProfile or postDesc
 * If we hit this url in PostMan for search by keyword - http://localhost:8080/jobPosts/keyword/Developer - we get below output
 * [
    {
        "postId": 1,
        "postProfile": "Java Developer",
        "postDesc": "Must have good experience in core Java and advanced Java",
        "reqExperience": 2,
        "postTechStack": [
            "Core Java",
            "J2EE",
            "Spring Boot",
            "Hibernate"
        ]
    },
    {
        "postId": 2,
        "postProfile": "Frontend Developer",
        "postDesc": "Experience in building responsive web applications using React",
        "reqExperience": 3,
        "postTechStack": [
            "HTML",
            "CSS",
            "JavaScript",
            "React"
        ]
    },
    {
        "postId": 5,
        "postProfile": "Mobile App Developer",
        "postDesc": "Experience in mobile app development for iOS and Android",
        "reqExperience": 3,
        "postTechStack": [
            "iOS Development",
            "Android Development",
            "Mobile App"
        ]
    }
]
Query we get in console for searchBy Keyword:
Hibernate: select jp1_0.post_id,jp1_0.post_desc,jp1_0.post_profile,jp1_0.post_tech_stack,jp1_0.req_experience from job_post jp1_0 where jp1_0.post_profile like ? escape '\\' or jp1_0.post_desc like ? escape '\\'
 */

/**********************************************************/