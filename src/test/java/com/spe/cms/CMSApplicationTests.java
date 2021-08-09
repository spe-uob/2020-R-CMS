package com.spe.cms;

import com.spe.cms.controller.WebController;
import com.spe.cms.model.ProjectEntity;
import com.spe.cms.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CMSApplicationTests {

    @Autowired
    WebController webController;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void testSaveProject() {
        //test to see if system prevents itself from adding two projects with the SAME NAME
        //attempt to add first
        String response = webController.doGetAddNewProject("TEST PROJECT 999", "TEST PROJECT DESCRIPTION", "Test Client", "Client999@email.com", "github.com/testuser999/userrepo999");
        //assert success
        assertEquals(response, "successfuladd");

        //attempt to add second
        String secondResponse = webController.doGetAddNewProject("TEST PROJECT 999", "TEST PROJECT DESCRIPTION", "Test Client", "Client999@email.com", "github.com/testuser999/userrepo999");
        //assert failure
        assertEquals(secondResponse, "unsuccessfuladd");

        //delete first project
        Iterable<ProjectEntity> projects = projectRepository.findAll();
        ProjectEntity savedProject = null;
        for (ProjectEntity p : projects) {
            if (p.getProjectName().equals("TEST PROJECT 999")) {
                //get the project instance
                savedProject = p;
                break;
            }
        }
        //assert that first project was found
        assertNotNull(savedProject);
        //delete first project by id
        Long initialID = savedProject.getId();
        projectRepository.deleteById(initialID);

        //attempt to delete non-existent second project
        Iterable<ProjectEntity> projectsUpdated = projectRepository.findAll();
        ProjectEntity savedProject2 = null;
        for (ProjectEntity p : projectsUpdated) {
            //look for project with exact name as first project
            if (p.getProjectName().equals("TEST PROJECT 999")) {
                savedProject2 = p;
                break;
            }
        }
        //assert no second project with same name was found
        assertNull(savedProject2);
    }
    @Test
    void contextLoads() {
    }

}
