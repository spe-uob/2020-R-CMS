package com.leon.dynamiccolumn.controller;

import com.leon.dynamiccolumn.model.ProjectEntity;
import com.leon.dynamiccolumn.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RestApiController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/getAllProjects")
    public ArrayList<ProjectEntity> getAllProjects(){
        return projectService.getAllProjectEntities();
    }
    
    @RequestMapping("/getProjectDetails")
    public ProjectEntity getProjectDetails(@RequestParam("project_name") String projectName) {
        return projectService.getProjectEntityDetails(projectName);
    }

    /*
    @RequestMapping("/getProjectName")
    public String getProjectName(@RequestParam("project_name") String projectName) {
        return projectService.getProjectEntityName(projectName);
    }
    */
    @RequestMapping("/getClientName")
    public String getClientName(@RequestParam("project_name") String projectName) {
        return projectService.getProjectEntityClientName(projectName);
    }

    @RequestMapping("/getClientEmail")
    public String getClientEmail(@RequestParam("project_name") String projectName) {
        return projectService.getProjectEntityEmail(projectName);
    }

    @RequestMapping("/getProjectURL")
    public String getProjectURL(@RequestParam("project_name") String projectName) {
        return projectService.getProjectGitHubURL(projectName);
    }
}
