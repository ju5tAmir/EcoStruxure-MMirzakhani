package com.se.ecostruxure_mmirzakhani.gui.dashboard;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.lang.Double;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeDashboardController implements IController{
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableView<Employee, String> employeeName;
    @FXML
    private TableView<Employee, Double> employeeAnnualSalary;
    @FXML
    private TableView<Employee, Double> employeeOverheadMultiplier;
    @FXML
    private TableView<Employee, Double> employeeFixedAnnualAmount;
    @FXML
    private TableView<Employee, String> employeeCountry;
    @FXML
    private TableView<Employee, String> employeeTeam;
    @FXML
    private TableView<Employee, Double> employeeAnnualWorkingHours;
    @FXML
    private TableView<Employee, Double> employeeUtilization;
    @FXML
    private TableView<Employee, Boolean> employeeType;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();
    private Model model;

    @Override
    public void setModel(Object model) {
    }
    /*@Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeAnnualSalary.setCellValueFactory(new PropertyValueFactory<>("annualSalary"));
        employeeOverheadMultiplier.setCellValueFactory(new PropertyValueFactory<>("overheadMultiplier"));
        employeeFixedAnnualAmount.setCellValueFactory(new PropertyValueFactory<>("fixedAnnualAmount"));
        employeeCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        employeeTeam.setCellValueFactory(new PropertyValueFactory<>("team"));
        employeeAnnualWorkingHours.setCellValueFactory(new PropertyValueFactory<>("annualWorkingHours"));
        employeeUtilization.setCellValueFactory(new PropertyValueFactory<>("utilization"));
        employeeType.setCellValueFactory(new PropertyValueFactory<>("employeeType"));

        employees.setAll(model.getProfiles());
        employeesTable.setItems(employees);
    }*/
    @FXML
    private void onCreateEmployee() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        CreateEmployee createEmployee = loader.getController();
        createEmployee.setModel(this.model);

    }
    @FXML
    private void onEditProfile(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(""));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        EditEmployee editEmployee = loader.getController();
        editEmployee.setEmployeeToEdit(EmployeeToEdit);
    }
    @FXML
    private void onDeleteEmployee() {
        Employee selectedEmployee = employeesTable.getSelectionModel().getSelectedItem();
        if (selectedEmployee != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
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
}
