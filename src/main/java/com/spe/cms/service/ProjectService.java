package com.spe.cms.service;


import com.spe.cms.model.ProjectEntity;

import java.util.ArrayList;

public interface ProjectService {

    ArrayList<ProjectEntity> getAllProjectEntities();
    //getting project fields by project name
    ProjectEntity getProjectEntityDetails(String projectEntityName);
   // String getProjectEntityName(String projectEntityName);
    String getProjectEntityClientName(String projectEntityName);
    String getProjectEntityEmail(String projectEntityName);
    String getProjectGitHubURL(String projectEntityName);




}
