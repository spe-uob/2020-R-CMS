package com.example.databasetest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@SpringBootApplication

@RestController

public class DatabaseTestApplication {

	@Autowired
	private ProjectRepository projectsRepo;

	@RequestMapping("/getAllProjects")
	public ArrayList<ProjectEntity> getAllProjects(){
		String response = "";
		Iterable<ProjectEntity> projects = projectsRepo.findAll();
		/*
		for (ProjectEntity proj: projects) {
			response += "\n" +proj.getId() + " " + proj.getProjectName() + " " +
					    proj.getProjectBrief() + " " + proj.getClientName() + " " +
					    proj.getClientEmail() + " " + proj.getGitHubURL() + "\n";
		}*/
		ArrayList<ProjectEntity> projectsList = new ArrayList<ProjectEntity>();
		for (ProjectEntity proj: projects) {
			projectsList.add(proj);
		}
		return projectsList;
	}

	@RequestMapping("/getProjectDetails")
	public ProjectEntity getProjectDetails(@RequestParam("id") Long projectID) {
		Optional<ProjectEntity> result = projectsRepo.findById(projectID);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			return new ProjectEntity();
		}
	}

	@RequestMapping("/getProjectName")
	public String getProjectName(@RequestParam("id") Long projectID) {
		Optional<ProjectEntity> result = projectsRepo.findById(projectID);
		if(result.isPresent()) {
			return result.get().getProjectName();
		}
		else {
			return "Name not found: project does not exist";
		}
	}

	@RequestMapping("/getClientName")
	public String getClientName(@RequestParam("id") Long projectID) {
		Optional<ProjectEntity> result = projectsRepo.findById(projectID);
		if(result.isPresent()) {
			return result.get().getClientName();
		}
		else {
			return "Client not found: project does not exist";
		}
	}

	@RequestMapping("/getClientEmail")
	public String getClientEmail(@RequestParam("id") Long projectID) {
		Optional<ProjectEntity> result = projectsRepo.findById(projectID);
		if(result.isPresent()) {
			return result.get().getClientEmail();
		}
		else {
			return "Email not found: project does not exist";
		}
	}

	@RequestMapping("/getProjectURL")
	public String getProjectURL(@RequestParam("id") Long projectID) {
		Optional<ProjectEntity> result = projectsRepo.findById(projectID);
		if(result.isPresent()) {
			return result.get().getGitHubURL();
		}
		else{
			return "URL not found: project does not exist";
		}
	}
	/*
	@RequestMapping("/getStudent")
	public String getStudent(@RequestParam("id") Long studentID) {
		Optional<Student> result = studentsRepo.findById(studentID);
		if(result.isPresent()) {
			Student student = result.get();
			String fullName = student.getFirstName() + " " +
					student.getLastName();
			Project project = student.getProject();
			return fullName + " works on " + project.getProjectName();
		}
		else return "Unknown Student";
	}
	 */
	public static void main(String[] args) {
		SpringApplication.run(DatabaseTestApplication.class, args);
	}

}
