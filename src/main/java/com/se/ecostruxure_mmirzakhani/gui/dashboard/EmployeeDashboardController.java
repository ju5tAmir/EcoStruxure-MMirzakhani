package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EmployeeDashboardController implements IController, Initializable {
    @FXML
    private TableView<Employee> employeeTable;
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

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private Model model;
    private Employee employeeToEdit;

    @FXML
    private Label dashboardLabel;

    @FXML
    private TableView<Employee> employeeTableView;

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

        employeeType.setCellFactory(column -> new TableCell<Employee, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Overhead Cost" : "Production Resource");
                }
            }
        });
        employeeTableView.setItems(employees);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateEmployeeTable();
        initEmployeeTableColumns();
        //employees.setAll(model.getProfiles());
        //employeesTable.setItems(employees);
    }
    @FXML
    private void onCreateEmployee() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        CreateEmployeeController createEmployeeController = loader.getController();
        createEmployeeController.setModel(this.model);

    }
    @FXML
    private void onEditProfile(ActionEvent actionEvent) throws IOException{
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        EditEmployee editEmployee = loader.getController();
        editEmployee.setEmployeeToEdit(employeeToEdit);
    }
    @FXML
    private void onDeleteEmployee() {
        Employee selectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        Alert alert = null;
        if (selectedEmployee != null) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete Employee");
            alert.setHeaderText("Are you sure you want to delete this employee?");
            alert.setContentText("Deleting an employee will remove them from the company.");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    boolean success = model.deleteEmployee(selectedEmployee);
                    if (success) {
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Success");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Employee deleted successfully.");
                        successAlert.showAndWait();
                        refreshEmployeeList();
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Error");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Failed to delete employee.");
                        errorAlert.showAndWait();
                    }
                }
            });
        } else {
            Alert noEmployeeSelectedAlert = new Alert(Alert.AlertType.ERROR);
            noEmployeeSelectedAlert.setTitle("Error");
            noEmployeeSelectedAlert.setHeaderText(null);
            alert.setContentText("Please select an employee to delete.");
            noEmployeeSelectedAlert.showAndWait();
        }
    }
    public void refreshEmployeeList() {
        this.employees.setAll(model.getEmployees());
    }
    @FXML
    public void calculateHourlyRate(ActionEvent actionEvent) throws IOException{

    }

    @FXML
    public void calculateDailyRate(ActionEvent actionEvent) throws IOException{

    }

    public void createEmployee(ActionEvent actionEvent) throws ExceptionHandler {
        Window.createStage(WindowType.CREATE_EMPLOYEE, null, null, false, employeeTableView);
    }
}
