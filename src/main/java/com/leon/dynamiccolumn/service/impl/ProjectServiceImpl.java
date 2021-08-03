package com.leon.dynamiccolumn.service.impl;

import com.leon.dynamiccolumn.projectentity.ProjectEntity;
import com.leon.dynamiccolumn.repository.ProjectRepository;
import com.leon.dynamiccolumn.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectsRepository;

    //Return all table rows
    @Override
    public ArrayList<ProjectEntity> getAllProjectEntities(){
        Iterable<ProjectEntity> projects = projectsRepository.findAll();
        ArrayList<ProjectEntity> projectsList = new ArrayList<ProjectEntity>();
        for (ProjectEntity project: projects) {
            projectsList.add(project);
        }
        return projectsList;
    }

    //get a single row by id
    @Override
    public ProjectEntity getProjectEntityDetails(Long projectID) {
        Optional<ProjectEntity> result = projectsRepository.findById(projectID);
        if(result.isPresent()) {
            return result.get();
        }
        else {
            return new ProjectEntity();
        }
    }

    //get project name by id
    @Override
    public String getProjectEntityName(Long projectID) {
        Optional<ProjectEntity> result = projectsRepository.findById(projectID);
        if(result.isPresent()) {
            return result.get().getProjectName();
        }
        else {
            return "Name not found: project does not exist";
        }
    }

    //get client name by project id
    @Override
    public String getProjectEntityClientName(Long projectID){
        Optional<ProjectEntity> result = projectsRepository.findById(projectID);
        if(result.isPresent()) {
            return result.get().getClientName();
        }
        else {
            return "Client not found: project does not exist";
        }
    }

    //get client email by project id
    @Override
    public String getProjectEntityEmail(Long projectID) {
        Optional<ProjectEntity> result = projectsRepository.findById(projectID);
        if(result.isPresent()) {
            return result.get().getClientEmail();
        }
        else {
            return "Email not found: project does not exist";
        }
    }

    //get GitHub URL by project id
    @Override
    public String getProjectGitHubURL(Long projectID) {
        Optional<ProjectEntity> result = projectsRepository.findById(projectID);
        if(result.isPresent()) {
            return result.get().getGitHubURL();
        }
        else{
            return "URL not found: project does not exist";
        }
    }
}
