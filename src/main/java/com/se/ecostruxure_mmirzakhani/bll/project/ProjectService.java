package com.se.ecostruxure_mmirzakhani.bll.project;

import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;

import java.util.List;

public class ProjectService {
    private final Project project;
    private final List<Assignment> assignments;

    public ProjectService(Project project, List<Assignment> assignments){
        this.project = project;
        this.assignments = assignments;
    }
}
