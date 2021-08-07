package com.spe.cms.controller;
import com.spe.cms.repository.ProjectRepository;
import com.spe.cms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.spe.cms.model.ProjectEntity;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class WebController {

    @Autowired
    ProjectRepository projectsRepo;

    @Autowired
    ProjectService projectService;

    @GetMapping("/")
    public String doGetRoot() {
        return "redirect:index";
    }

    @GetMapping("/index")
    public String doGetIndex() {
        return "index";
    }


    @GetMapping("/error")
    public String doGetError() {
        return "error";
    }

    @GetMapping("/allprojectstable")
    public String doGetAllProjectsTable(Model model){
        ArrayList<ProjectEntity> allProjectsAsList = projectService.getAllProjectEntities();
        model.addAttribute("projectslist", allProjectsAsList);
        return "allprojectstable";
    }

    @GetMapping("/addproject")
    public String doGetAddProjectPage() {
        return "addproject";
    }

    //id, name, brief, client, email, url
    @GetMapping("/addNewProject")
    public String doGetAddNewProject(
            //@RequestParam("id") Long id,
            @RequestParam("projectname") String projectName,
            @RequestParam("projectbrief") String projectBrief,
            @RequestParam("clientname") String clientName,
            @RequestParam("clientemail") String clientEmail,
            @RequestParam("githuburl") String gitHubURL) {
		/*Long id = 10L;
		String projectName = "test";
		String projectBrief = "test";
		String clientName = "test";
		String clientEmail = "test";
		String gitHubURL = "test";*/

        ProjectEntity newProject = new ProjectEntity(projectName, projectBrief, clientName, clientEmail, gitHubURL);
        projectsRepo.save(newProject);
        return "successfuladd";
    }

    @GetMapping("/editproject")
    public String doGetEditProject() {
        return "editproject";
    }

    @GetMapping("/editExistingProject")
    public String doGetEditExistingProject(@RequestParam("id") Long projectID,
                                           @RequestParam(value = "projectname", required = false) String projectName,
                                           @RequestParam(value = "projectbrief", required = false) String projectBrief,
                                           @RequestParam(value = "clientname", required = false) String clientName,
                                           @RequestParam(value = "clientemail", required = false) String clientEmail,
                                           @RequestParam(value = "githuburl", required = false) String gitHubURL) {

        Optional<ProjectEntity> result = projectsRepo.findById(projectID);
        if (result.isPresent()) {
            ProjectEntity existingProject = result.get();
            if (projectName != "") {
                existingProject.setProjectName(projectName);
            }
            if (projectBrief != "") {
                existingProject.setProjectBrief(projectBrief);
            }
            if (clientName != "") {
                existingProject.setClientName(clientName);
            }
            if (clientEmail != "") {
                existingProject.setClientEmail(clientEmail);
            }
            if (gitHubURL != "") {
                existingProject.setGitHubURL(gitHubURL);
            }
            projectsRepo.save(existingProject);
            return "successfuledit";
        } else {
            return "unsuccessfuledit";
        }
    }

    @GetMapping("/deleteproject")
    public String doGetDeleteProject() {
        return "deleteproject";
    }
    @GetMapping("/deleteExistingProject")
    public String doGetDeleteExistingProject(@RequestParam("id") Long projectID){
        if (projectsRepo.existsById(projectID)) {
            projectsRepo.deleteById(projectID);
            return "successfuldelete";
        } else {
            return "unsuccessfuldelete";
        }
    }
}