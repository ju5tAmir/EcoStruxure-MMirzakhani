package com.se.ecostruxure_mmirzakhani.be;

public class ProjectMemberLinker {
    private Project project;
    private ProjectMember projectMember;

    public ProjectMemberLinker(Project project, ProjectMember projectMember) {
        this.project = project;
        this.projectMember = projectMember;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectMember getProjectMember() {
        return projectMember;
    }

    public void setProjectMember(ProjectMember projectMember) {
        this.projectMember = projectMember;
    }
}
