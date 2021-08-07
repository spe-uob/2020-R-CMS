package com.leon.dynamiccolumn.service.impl;

import com.leon.dynamiccolumn.model.ProjectEntity;
import com.leon.dynamiccolumn.repository.ProjectRepository;
import com.leon.dynamiccolumn.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    //get a single row by name
    @Override
    public ProjectEntity getProjectEntityDetails(String projectEntityName) {
        Iterable<ProjectEntity> projects = projectsRepository.findAll();
        for (ProjectEntity project: projects) {
            if (project.getProjectName().equals(projectEntityName)) {
                return project;
            }
        }
        return new ProjectEntity();
    }

    //get project name by id
    /*
    @Override
    public String getProjectEntityName(Long projectID) {
        Optional<ProjectEntity> result = projectsRepository.findById(projectID);
        if(result.isPresent()) {
            return result.get().getProjectName();
        }
        else {
            return "Name not found: project does not exist";
        }
    }*/

    //get client name by project name
    @Override
    public String getProjectEntityClientName(String projectEntityName){
        /*
        Optional<ProjectEntity> result = projectsRepository.findById(projectID);
        if(result.isPresent()) {
            return result.get().getClientName();
        }
        else {
            return "Client not found: project does not exist";
        }*/
        Iterable<ProjectEntity> projects = projectsRepository.findAll();
        for (ProjectEntity project: projects) {
            if (project.getProjectName().equals(projectEntityName)) {
                return project.getClientName();
            }
        }
        return "Client not found: project does not exist";
    }

    //get client email by project id
    @Override
    public String getProjectEntityEmail(String projectEntityName) {
        /*
        Optional<ProjectEntity> result = projectsRepository.findById(projectID);
        if(result.isPresent()) {
            return result.get().getClientEmail();
        }
        else {
            return "Email not found: project does not exist";
        }*/
        Iterable<ProjectEntity> projects = projectsRepository.findAll();
        for (ProjectEntity project: projects) {
            if (project.getProjectName().equals(projectEntityName)) {
                return project.getClientEmail();
            }
        }
        return "Email not found: project does not exist";
    }

    //get GitHub URL by project id
    @Override
    public String getProjectGitHubURL(String projectEntityName) {
        /*
        Optional<ProjectEntity> result = projectsRepository.findById(projectID);
        if(result.isPresent()) {
            return result.get().getGitHubURL();
        }
        else{
            return "URL not found: project does not exist";
        }*/
        Iterable<ProjectEntity> projects = projectsRepository.findAll();
        for (ProjectEntity project: projects) {
            if (project.getProjectName().equals(projectEntityName)) {
                return project.getGitHubURL();
            }
        }
        return "URL not found: project does not exist";
    }

}
