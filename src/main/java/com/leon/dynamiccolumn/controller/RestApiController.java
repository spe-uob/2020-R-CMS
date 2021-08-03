package com.leon.dynamiccolumn.controller;

import com.leon.dynamiccolumn.projectentity.ProjectEntity;
import com.leon.dynamiccolumn.repository.ProjectRepository;
import com.leon.dynamiccolumn.service.ProjectService;
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
    private ProjectService projectService;

    @RequestMapping("/getAllProjects")
    public ArrayList<ProjectEntity> getAllProjects(){
        return projectService.getAllProjectEntities();
    }
    
    @RequestMapping("/getProjectDetails")
    public ProjectEntity getProjectDetails(@RequestParam("id") Long projectID) {
        return projectService.getProjectEntityDetails(projectID);
    }

    @RequestMapping("/getProjectName")
    public String getProjectName(@RequestParam("id") Long projectID) {
        return projectService.getProjectEntityName(projectID);
    }

    @RequestMapping("/getClientName")
    public String getClientName(@RequestParam("id") Long projectID) {
        return projectService.getProjectEntityClientName(projectID);
    }

    @RequestMapping("/getClientEmail")
    public String getClientEmail(@RequestParam("id") Long projectID) {
        return projectService.getProjectEntityEmail(projectID);
    }

    @RequestMapping("/getProjectURL")
    public String getProjectURL(@RequestParam("id") Long projectID) {
        return projectService.getProjectGitHubURL(projectID);
    }
}
