package com.example.databasetest;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="project")
public class ProjectEntity {
    @Id @GeneratedValue @Column(name="id") @Getter @Setter
    Long id;
    @Column(name="project_name") @Getter @Setter
    String projectName;
    @Column(name="project_brief") @Getter @Setter
    String projectBrief;
    @Column(name="client_name") @Getter @Setter
    String clientName;
    @Column(name="client_email") @Getter @Setter
    String clientEmail;
    @Column(name="github_url") @Getter @Setter
    String gitHubURL;

    @Override
    public String toString() {
        return "ProjectEntity{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectBrief='" + projectBrief + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", gitHubURL='" + gitHubURL + '\'' +
                '}';
    }

    public ProjectEntity(String projectName, String projectBrief, String clientName, String clientEmail, String gitHubURL) {

        this.projectName = projectName;
        this.projectBrief = projectBrief;
        this.clientName = clientName;
        this.clientEmail = clientEmail;
        this.gitHubURL = gitHubURL;
    }
    public ProjectEntity(){
    }

}
