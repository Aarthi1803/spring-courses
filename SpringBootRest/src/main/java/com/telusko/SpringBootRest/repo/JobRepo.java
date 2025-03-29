package com.telusko.SpringBootRest.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telusko.SpringBootRest.model.JobPost;


// public class JobRepo {

	// arrayList to store store JobPost objects
	//	List<JobPost> jobs = new ArrayList<>(Arrays.asList(
	//			new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2, List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),
	//			new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3, List.of("HTML", "CSS", "JavaScript", "React")),
	//			new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4, List.of("Python", "Machine Learning", "Data Analysis")),
	//			new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5, List.of("Networking", "Cisco", "Routing", "Switching")),
	//			new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3, List.of("iOS Development", "Android Development", "Mobile App"))
	//			));
	
	// method to return all JobPosts
	//	public List<JobPost> returnAllJobPosts(){
	//		return jobs;
	//	}
	
	// method to save a job post object into arrayList
	//	public void addJobPost(JobPost job) {
	//		jobs.add(job);
	//		System.out.println(jobs);
	//	}

	/* method to find specific jobPost using postId
		public JobPost getJob(int postId) {
			for (JobPost job : jobs) {
				if(job.getPostId() == postId)
					return job;
			}
			return null;
		}
		
	//	Alternative approach using Stream API to find specific jobPost using postId
		 public JobPost getJob(int postId) {
		        return jobs.stream()
		                   .filter(job -> job.getId() == postId)
		                   .findFirst()
		                   .orElse(null);
		 }
	*/

	// method to update job with jobPost object
	//	public void updateJob(JobPost jobPost) {
	//		for (JobPost jobPost1 : jobs) {
	//			if(jobPost1.getPostId() == jobPost.getPostId()) {
	//				jobPost1.setPostProfile(jobPost.getPostProfile());
	//				jobPost1.setPostDesc(jobPost.getPostDesc());
	//				jobPost1.setReqExperience(jobPost.getReqExperience());
	//				jobPost1.setPostTechStack(jobPost.getPostTechStack());
	//			}
	//		}
	//	}

	// Alternative approach using Stream API to update job with jobPost object
	//public void updateJob(JobPost jobPost) {
	//    jobs.stream()
	//        .filter(job -> job.getPostId() == jobPost.getPostId())
	//        .findFirst()
	//        .ifPresent(job -> {
	//            job.setPostProfile(jobPost.getPostProfile());
	//            job.setPostDesc(jobPost.getPostDesc());
	//            job.setReqExperience(jobPost.getReqExperience());
	//            job.setPostTechStack(jobPost.getPostTechStack());
	//        });
	//}


	// method to delete jobPost by id 
	//	public void deleteJob(int postId) {
	//		for (JobPost jobPost : jobs) {
	//			if(jobPost.getPostId() == postId)
	//				jobs.remove(jobPost);
	//		}	
	//	}

// }

/************************************************************************/

//@Repository
//public interface JobRepo extends JpaRepository<JobPost,Integer>{
//	
//}

/************************************************************************/

@Repository
public interface JobRepo extends JpaRepository<JobPost,Integer>{
	
	List<JobPost> findByPostProfileContainingOrPostDescContaining(String postProfile, String postDesc);

}

// (DSL) Domain Specific Language in JPA will have certain queries for you pre-created (already created), which you can use with the help of method names, but you have to make sure that it is used along with "findBy".
