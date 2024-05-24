package com.se.ecostruxure_mmirzakhani.utils;

public enum WindowType {
    MAIN("/com/se/ecostruxure-mmirzakhani/main/MainView.fxml"),
    DASHBOARD("/com/se/ecostruxure-mmirzakhani/dashboard/DashboardView.fxml"),
    EMPLOYEE_MAIN("/com/se/ecostruxure-mmirzakhani/employee/view/EmployeeMainView.fxml"),
    TEAM_MAIN("/com/se/ecostruxure-mmirzakhani/project/TeamView.fxml"),
    PROJECT_MANAGER("/com/se/ecostruxure-mmirzakhani/project/management/ProjectManagement.fxml"),
    CREATE_EMPLOYEE("/com/se/ecostruxure-mmirzakhani/employee/create/CreateEmployee.fxml"),
    ASSIGNMENT("/com/se/ecostruxure-mmirzakhani/employee/create/AssignEmployeeProject.fxml"),
    PROJECT_MAIN("/com/se/ecostruxure-mmirzakhani/project/view/ProjectMainView.fxml"),
    CREATE_PROJECT("/com/se/ecostruxure-mmirzakhani/project/create/CreateProject.fxml"),

    private final String path;


    WindowType(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
