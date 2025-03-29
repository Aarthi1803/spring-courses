package com.telusko.JobApp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.telusko.JobApp.model.JobPost;
import com.telusko.JobApp.service.JobService;


@Controller
public class JobController {
	
	@Autowired
	private JobService service;
	
//	@RequestMapping({"/", "home"})
	@GetMapping ({"/", "home"})  // can use either @GetMapping or @RequestMapping. @RequestMapping does default mapping for get method.
	// it maps for requests "localhost:8080/" and "localhost:8080/home/"
	public String home() {
		return "home";
	}
	
//	@RequestMapping("addJob")
	@GetMapping ("addjob")  // can use either @GetMapping or @RequestMapping. @RequestMapping does default mapping for get method.
	public String addJob() {
		return "addjob";
	}
	
	@PostMapping("handleForm") // @RequestMapping does default mapping for get method. So we must mention @PostMapping
//	@RequestMapping(value = "handleForm", method = RequestMethod.POST) // either use annotation like this or like mentioned above - @PostMapping("handleForm")
	public String handleAddJobForm(JobPost jobPost) {
		service.addJob(jobPost);
		return "success";
	}
	
	@GetMapping("viewalljobs")
	public String viewJobs(Model model) {
		List<JobPost> jobs = service.returnAllJobPosts();
		model.addAttribute("jobPosts",jobs);
		return "viewalljobs";
	}
	
}
