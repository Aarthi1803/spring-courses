package com.telusko.JobApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.JobApp.model.JobPost;
import com.telusko.JobApp.repo.JobRepo;

@Service
public class JobService { // 2 methods - one to add data and one to view data
	
	@Autowired
	public JobRepo repo;
	
	// method to add a jobPost
	public void addJob(JobPost jobPost) {
		repo.addJobPost(jobPost);
	}
	
	//method to return all JobPosts
	public List<JobPost> returnAllJobPosts(){
		return repo.returnAllJobPosts();
		
	}
}
