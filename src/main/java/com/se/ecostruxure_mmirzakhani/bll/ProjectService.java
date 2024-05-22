package com.se.ecostruxure_mmirzakhani.bll;

import com.se.ecostruxure_mmirzakhani.be.Project;
import com.se.ecostruxure_mmirzakhani.be.ProjectMember;

import java.util.List;

public class ProjectService {
    private final Project project;
    private final List<ProjectMember> projectMembers;

    public ProjectService(Project project, List<ProjectMember> projectMembers){
        this.project = project;
        this.projectMembers = projectMembers;
    }
}
