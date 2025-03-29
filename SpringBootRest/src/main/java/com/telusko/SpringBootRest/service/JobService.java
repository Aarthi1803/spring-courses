package com.telusko.SpringBootRest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.SpringBootRest.model.JobPost;
import com.telusko.SpringBootRest.repo.JobRepo;

@Service
public class JobService { 
	
	@Autowired
	public JobRepo repo;
	
	
// method to add a jobPost using JpaRepository
	public void addJob(JobPost jobPost) {
		repo.save(jobPost);
	}		

//	public void addJob(JobPost jobPost) {
//		repo.addJobPost(jobPost);
//	}
	
	
	
// method to return all JobPosts using JpaRepository
	public List<JobPost> returnAllJobPosts(){
		return repo.findAll();
		
	}
	
//	public List<JobPost> returnAllJobPosts(){
//		return repo.returnAllJobPosts();
//		
//	}
	
	
	
// method to find specific jobPost using JpaRepository
	public JobPost getJob(int postId) {
//		int num = 10/0; // Adding this line for AOP to call @AfterThrowing method
		return repo.findById(postId).orElse(new JobPost());
	}
	
//	public JobPost getJob(int postId) {
//		return repo.getJob(postId);
//	}
	
	
	
// method to update job with jobPost object using JpaRepository
	public void updateJob(JobPost jobPost) {
		repo.save(jobPost);
	}
	
//	public void updateJob(JobPost jobPost) {
//		repo.updateJob(jobPost);
//	}
	
	

// method to delete jobPost by id using JpaRepository
	public void deleteJob(int postId) {
		repo.deleteById(postId);
	}
	
//	public void deleteJob(int postId) {
//		repo.deleteJob(postId);
//	}
	

	
	public void load() {
// arrayList to store JobPost objects
		List<JobPost> jobs = new ArrayList<>(Arrays.asList(
				new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2, List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
				new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3, List.of("HTML", "CSS", "JavaScript", "React")),
				new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4, List.of("Python", "Machine Learning", "Data Analysis")),
				new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5, List.of("Networking", "Cisco", "Routing", "Switching")),
				new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3, List.of("iOS Development", "Android Development", "Mobile App"))
				));
		repo.saveAll(jobs);
	}

	public List<JobPost> search(String keyword) {
		return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
	}
	
}
