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
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.lang.Double;
import java.io.IOException;

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
    public void calculateHourlyRate(ActionEvent actionEvent) throws IOException{

    }

    @FXML
    public void calculateDailyRate(ActionEvent actionEvent) throws IOException{

    }
}
