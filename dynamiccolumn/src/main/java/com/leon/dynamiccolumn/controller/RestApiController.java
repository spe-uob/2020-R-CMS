package com.leon.dynamiccolumn.controller;

import com.leon.dynamiccolumn.projectentity.ProjectEntity;
import com.leon.dynamiccolumn.repository.ProjectRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Optional;

@RestController
public class RestApiController {

    @Autowired
    private ProjectRepository projectsRepo;

    @RequestMapping("/getAllProjects")
    public ArrayList<ProjectEntity> getAllProjects(){
        //String response = "";
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
}
