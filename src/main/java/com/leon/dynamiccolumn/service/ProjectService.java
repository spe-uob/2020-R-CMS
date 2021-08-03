package com.leon.dynamiccolumn.service;


import com.leon.dynamiccolumn.projectentity.ProjectEntity;

import java.util.ArrayList;

public interface ProjectService {

    ArrayList<ProjectEntity> getAllProjectEntities();
    ProjectEntity getProjectEntityDetails(Long projectID);
    String getProjectEntityName(Long projectID);
    String getProjectEntityClientName(Long projectID);
    String getProjectEntityEmail(Long projectID);
    String getProjectGitHubURL(Long projectID);

}
