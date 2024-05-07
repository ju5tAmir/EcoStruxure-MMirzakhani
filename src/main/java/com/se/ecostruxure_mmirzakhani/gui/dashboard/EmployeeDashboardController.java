package com.se.ecostruxure_mmirzakhani.gui.dashboard;

import com.se.ecostruxure_mmirzakhani.be.Country;
import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.be.Team;
import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import com.se.ecostruxure_mmirzakhani.utils.window.Window;
import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://github.com/NilIQW/">Author: NilIQW</a>
 */
public class EmployeeDashboardController implements IController {

    @FXML
    private TableView<Employee> employeeTableView;
    @FXML
    private TableColumn<Employee, String> firstNameColumn;
    @FXML
    private TableColumn<Employee, String> lastNameColumn;
    @FXML
    private TableColumn<Employee, String> employeeCountry;
    @FXML
    private TableColumn<Employee, String> employeeTeam;
    @FXML
    private ListView<Label> employeeInfoList;
    @FXML
    private ComboBox filterComboBox;
    @FXML
    private ComboBox teamComboBox;
    @FXML
    private Label teamsLabel;
    @FXML
    private Label employeesLabel;


    //get the list of countries from the enum and change it to observable
    ObservableList<Country> countryList = FXCollections.observableArrayList(Country.values());

    private Model model;

    // ToDo: Implement controller methods such as buttons and other nodes.

    @FXML
    private void onCreateEmployee() {
        try {
            Window.createStage(WindowType.CREATE_EMPLOYEE, model, Modality.APPLICATION_MODAL, false);
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }


    }


    @Override
    public void setModel(Object model) {
        if (model instanceof Model) {
            this.model = (Model) model;
        } else {
            // If the incoming model is not of type Model, create a new one
            this.model = new Model();
        }

        try {
            initEmployeesTable();
            initEmployeeColumns();
            populateChoicebox();

            teamsLabel.setOnMouseClicked(event -> {
                try {
                    Window.createStage(WindowType.TEAMS, this.model, Modality.WINDOW_MODAL, false);
                    Window.closeStage(teamsLabel.getScene());
                } catch (ExceptionHandler e) {
                    throw new RuntimeException(e);
                }
            });
            employeesLabel.setOnMouseClicked(event -> {
                AlertHandler.displayAlert("Employee Dashboard is already open!", Alert.AlertType.INFORMATION);
            });

        } catch (ExceptionHandler exceptionHandler) {
            AlertHandler.displayAlert(exceptionHandler.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Initializing the Employees table
     */
    private void initEmployeesTable() throws ExceptionHandler{
        employeeTableView.setItems(model.getAllEmployees());
    }

    /**
     * Initializing columns for the Employees table
     */
    private void initEmployeeColumns(){
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        employeeCountry.setCellValueFactory(cellData -> {
            // Retrieve the country enum value from the Employee object
            Country country = cellData.getValue().getCountry();

            // Return the string representation
            return new SimpleStringProperty(country.getValue());
        });
        employeeTeam.setCellValueFactory(cellData -> {
            // Retrieve the team object from the employee
            Team team = cellData.getValue().getTeam();

            // Return the string representation
            return new SimpleStringProperty(team.getName());
        });


        // sets a listener to update the listview based on the selected Employee
        employeeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                List<Label> labels = new ArrayList<>();

                Label employeeExtraInfo = new Label("Contract info of " + newValue.getFirstName() + " " + newValue.getLastName() + ":");
                employeeExtraInfo.setStyle("-fx-font-weight: bold;");
                labels.add(employeeExtraInfo);
                Label annualSalaryLabel = new Label("Annual Salary: " + newValue.getContract().getAnnualSalary());
                labels.add(annualSalaryLabel);
                Label fixedAnnualAmountLabel = new Label("Annual Amount: " + newValue.getContract().getFixedAnnualAmount());
                labels.add(fixedAnnualAmountLabel);
                Label averageDailyWorkHoursLabel = new Label("Average Daily Work Hours: " + newValue.getContract().getAverageDailyWorkHours());
                labels.add(averageDailyWorkHoursLabel);
                Label overheadPercentageLabel = new Label("Overhead Multiplier: " + newValue.getContract().getOverheadPercentage());
                labels.add(overheadPercentageLabel);
                Label annualWorkHours = new Label("Annual Working Hours: " + newValue.getContract().getAnnualWorkHours());
                labels.add(annualWorkHours);
                Label utilizationPercentageLabel = new Label("Utilization Percentage: " + newValue.getContract().getUtilizationPercentage());
                labels.add(utilizationPercentageLabel);

                // Check if the employee is considered overhead or not
                if (newValue.getContract().isOverhead()) {
                    Label typeLabel = new Label("Employee Type: Overhead Cost");
                    labels.add(typeLabel);
                } else {
                    Label typeLabel = new Label("Employee Type: Production Resource");
                    labels.add(typeLabel);
                }

                employeeInfoList.getItems().setAll(labels);
            }
        });
    }
    public void populateChoicebox() {
        filterComboBox.getItems().addAll("Country", "Team");

        // Initially set the value to "All"
        filterComboBox.setValue("All");

        teamComboBox.setVisible(false);

        filterComboBox.setOnAction(event -> {
                String selectedOption = filterComboBox.getValue().toString();
                if (selectedOption.equals("Country")) {
                    teamComboBox.setVisible(true);
                    teamComboBox.setValue("Select Country");
                    teamComboBox.getItems().addAll(countryList);

                } else if (selectedOption.equals("Team")) {
                    teamComboBox.setVisible(true);
                    teamComboBox.setValue("Select Team");
                    teamComboBox.getItems().setAll("Production", "Management");
                }
            });
        // Set cell factory to display each country with its flag
        teamComboBox.setCellFactory(param -> new ListCell<Country>() {
            @Override
            protected void updateItem(Country country, boolean empty) {
                super.updateItem(country, empty);
                if (empty || country == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    ImageView flagImageView = FlagService.getFlagImageView(country.getCode());
                    if (flagImageView != null) {
                        flagImageView.setFitWidth(20);
                        flagImageView.setFitHeight(20);
                        setGraphic(flagImageView);
                        setText(country.getValue());
                    } else {
                        setText(country.getValue());
                    }
                }
            }
        });
        teamComboBox.setOnKeyPressed(event -> {
            if (event.getCode().isLetterKey()) {
                String typedText = event.getText();
                filterCountriesByFirstLetter(typedText);
            }
        });
        }
    private void filterCountriesByFirstLetter(String letter) {
        //filter the list by the first typed letter
        ObservableList<Country> filteredList = countryList.filtered(country ->
                country.getValue().toLowerCase().startsWith(letter.toLowerCase()));

        teamComboBox.setItems(filteredList);
        teamComboBox.getSelectionModel().selectFirst();
    }


    public void calculateMultipliers(ActionEvent actionEvent) {
        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        try{
            Window.createStage(WindowType.CALCULATOR, model, Modality.WINDOW_MODAL, false);
        }
        catch (ExceptionHandler e){
            throw new RuntimeException();
        }
    }

    public void editEmployee(ActionEvent actionEvent) {
        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
        model.setSelectedEmployee(selectedEmployee);
        try {
            Window.createStage(WindowType.CREATE_EMPLOYEE, model, Modality.APPLICATION_MODAL, false);
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }    }

    public void deleteEmployee(ActionEvent actionEvent) {
    }


    public void onSave(ActionEvent actionEvent) {

    }
}
