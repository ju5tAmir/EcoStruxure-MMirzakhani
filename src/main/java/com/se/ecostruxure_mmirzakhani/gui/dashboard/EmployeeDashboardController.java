package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeDashboardController implements IController, Initializable {
    @FXML
    private Label dashboardLabel;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, String> employeeName;

    @FXML
    private TableColumn<Employee, Double> employeeAnnualSalary;

    @FXML
    private TableColumn<Employee, Double> employeeOverheadMultiplier;

    @FXML
    private TableColumn<Employee, Double> employeeFixedAnnualAmount;

    @FXML
    private TableColumn<Employee, String> employeeCountry;

    @FXML
    private TableColumn<Employee, String> employeeTeam;

    @FXML
    private TableColumn<Employee, Double> employeeAnnualWorkingHours;

    @FXML
    private TableColumn<Employee, Double> employeeUtilization;

    @FXML
    private TableColumn<Employee, Boolean> employeeType;
    @Override
    public void setModel(Object model) {
        // Here we will implement loading the Employee Table once the Dashboard label is clicked, just like in the website
        dashboardLabel.setOnMouseClicked(event -> {
            System.out.println("Label clicked!");
        });
    }

    public void initEmployeeTableColumns(){
        employeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeAnnualSalary.setCellValueFactory(new PropertyValueFactory<>("annualSalary"));
        employeeOverheadMultiplier.setCellValueFactory(new PropertyValueFactory<>("overheadMultiplier"));
        employeeFixedAnnualAmount.setCellValueFactory(new PropertyValueFactory<>("fixedAnnualAmount"));
        employeeCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        employeeTeam.setCellValueFactory(new PropertyValueFactory<>("team"));
        employeeAnnualWorkingHours.setCellValueFactory(new PropertyValueFactory<>("annualWorkingHours"));
        employeeUtilization.setCellValueFactory(new PropertyValueFactory<>("utilization"));
        employeeType.setCellValueFactory(new PropertyValueFactory<>("employeeType"));
    }

    public void populateEmployeeTable() {

        ObservableList<Employee> employees = FXCollections.observableArrayList();
        Employee e1 = new Employee("John Doe", 50000.0, 1.2, 40000.0, "USA", "Engineering", 1800.0, 85.0, true);
        Employee e2 = new Employee("Jane Smith", 60000.0, 1.1, 45000.0, "UK", "Marketing", 1900.0, 90.0, false);
        employees.addAll(e1, e2);
        employeeTableView.setItems(employees);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateEmployeeTable();
        initEmployeeTableColumns();
    }
}
