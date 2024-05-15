//package com.se.ecostruxure_mmirzakhani.gui.dashboard;
//
//import com.se.ecostruxure_mmirzakhani.be.Country;
//import com.se.ecostruxure_mmirzakhani.be.Employee;
//import com.se.ecostruxure_mmirzakhani.be.Team;
//import com.se.ecostruxure_mmirzakhani.bll.EmployeeLogic;
//import com.se.ecostruxure_mmirzakhani.exceptions.AlertHandler;
//import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
//import com.se.ecostruxure_mmirzakhani.gui.IController;
//import com.se.ecostruxure_mmirzakhani.model.Model;
//import com.se.ecostruxure_mmirzakhani.utils.window.Window;
//import com.se.ecostruxure_mmirzakhani.utils.window.WindowType;
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.ImageView;
//import javafx.stage.Modality;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * <a href="https://github.com/NilIQW/">Author: NilIQW</a>
// */
//public class EmployeeDashboardController implements IController {
//
//    @FXML
//    private TableView<Employee> employeeTableView;
//    @FXML
//    private TableColumn<Employee, String> firstNameColumn;
//    @FXML
//    private TableColumn<Employee, String> lastNameColumn;
//    @FXML
//    private TableColumn<Employee, String> employeeCountry;
//    @FXML
//    private TableColumn<Employee, String> employeeTeam, hourlyRateColumn, dailyRateColumn;
//    @FXML
//    private ListView<Label> employeeInfoList;
//    @FXML
//    private ComboBox filterComboBox;
//    @FXML
//    private ComboBox teamComboBox;
//    @FXML
//    private Label teamsLabel;
//    @FXML
//    private Label employeesLabel;
//
//    @FXML
//    private TextField markupTextField;
//    @FXML
//    private TextField gmTextField;
//
//    //get the list of countries from the enum and change it to observable
//    ObservableList<Country> countryList = FXCollections.observableArrayList(Country.values());
//
//    private Model model;
//
//    // ToDo: Implement controller methods such as buttons and other nodes.
//
//    @FXML
//    private void onCreateEmployee() {
//        try {
//            Window.createStage(WindowType.CREATE_EMPLOYEE, model, Modality.APPLICATION_MODAL, false);
//        } catch (ExceptionHandler e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
//
//
//    @Override
//    public void setModel(Object model) {
//        if (model instanceof Model) {
//            this.model = (Model) model;
//        } else {
//            // If the incoming model is not of type Model, create a new one
//            this.model = new Model();
//        }
//
//        try {
//            initEmployeesTable();
//            initEmployeeColumns();
//            populateChoicebox();
//
//            teamsLabel.setOnMouseClicked(event -> {
//                try {
//                    Window.createStage(WindowType.TEAMS, this.model, Modality.WINDOW_MODAL, false);
//                    Window.closeStage(teamsLabel.getScene());
//                } catch (ExceptionHandler e) {
//                    throw new RuntimeException(e);
//                }
//            });
//            employeesLabel.setOnMouseClicked(event -> {
//                AlertHandler.displayAlert("Employee Dashboard is already open!", Alert.AlertType.INFORMATION);
//            });
//
//        } catch (ExceptionHandler exceptionHandler) {
//            AlertHandler.displayAlert(exceptionHandler.getMessage(), Alert.AlertType.ERROR);
//        }
//    }
//
//    /**
//     * Initializing the Employees table
//     */
//    private void initEmployeesTable() throws ExceptionHandler{
//        employeeTableView.setItems(model.getAllEmployees());
//    }
//
//    /**
//     * Initializing columns for the Employees table
//     */
//    private void initEmployeeColumns() throws ExceptionHandler {
//        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
//        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//        employeeCountry.setCellValueFactory(cellData -> {
//            // Retrieve the country enum value from the Employee object
//            Country country = cellData.getValue().getCountry();
//
//            // Return the string representation
//            return new SimpleStringProperty(country.getValue());
//        });
//        employeeTeam.setCellValueFactory(cellData -> {
//            // Retrieve the team object from the employee
//            Team team = cellData.getValue().getTeam();
//
//            // Return the string representation
//            return new SimpleStringProperty(team.getName());
//        });
//
//        dailyRateColumn.setCellValueFactory(cellData -> {
//            // Retrieve the daily rate
//            double dailyRate = cellData.getValue().getContract().getDailyRate();
//
//            // Return the string representation with 2 decimal float number
//            return new SimpleStringProperty(String.format("%.2f", dailyRate));
//        });
//        hourlyRateColumn.setCellValueFactory(cellData -> {
//            // Retrieve the hourly rate
//            double hourlyRate = cellData.getValue().getContract().getHourlyRate();
//
//            // Return the string representation with 2 decimal float number
//            return new SimpleStringProperty(String.format("%.2f", hourlyRate));
//        });
//
//        // sets a listener to update the listview based on the selected Employee
//        employeeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            try {
//                if (newValue != null) {
//                    // Updates the employee object in the model with the selected employee from the table
//                    model.setEmployee(newValue);
//
//                    List<Label> labels = new ArrayList<>();
//
//                    Label employeeExtraInfo = new Label("Contract info of " + model.getEmployee().getFirstName() + " " + model.getEmployee().getLastName() + ":");
//                    employeeExtraInfo.setStyle("-fx-font-weight: bold;");
//                    labels.add(employeeExtraInfo);
//                    Label annualSalaryLabel = new Label("Annual Salary: " + model.getEmployee().getContract().getAnnualSalary());
//                    labels.add(annualSalaryLabel);
//                    Label fixedAnnualAmountLabel = new Label("Annual Amount: " + model.getEmployee().getContract().getFixedAnnualAmount());
//                    labels.add(fixedAnnualAmountLabel);
//                    Label averageDailyWorkHoursLabel = new Label("Average Daily Work Hours: " + model.getEmployee().getContract().getAverageDailyWorkHours());
//                    labels.add(averageDailyWorkHoursLabel);
//                    Label overheadPercentageLabel = new Label("Overhead Multiplier: " + model.getEmployee().getContract().getOverheadPercentage());
//                    labels.add(overheadPercentageLabel);
//                    Label annualWorkHours = new Label("Annual Working Hours: " + model.getEmployee().getContract().getAnnualWorkHours());
//                    labels.add(annualWorkHours);
//                    Label utilizationPercentageLabel = new Label("Utilization Percentage: " + model.getEmployee().getContract().getUtilizationPercentage());
//                    labels.add(utilizationPercentageLabel);
//
//
//                    // Check if the employee is considered overhead or not
//                    if (model.getEmployee().getContract().isOverhead()) {
//                        Label typeLabel = new Label("Employee Type: Overhead Cost");
//                        labels.add(typeLabel);
//                    } else {
//                        Label typeLabel = new Label("Employee Type: Production Resource");
//                        labels.add(typeLabel);
//                    }
//
//                    Label lineSeparator = new Label("-------------------");
//                    labels.add(lineSeparator);
//
//                    Label dailyRate = new Label("Daily Rate: " + String.format("%.2f", model.getDailyRate()));
//                    labels.add(dailyRate);
//
//                    Label hourlyRate = new Label("Hourly Rate: " + String.format("%.2f", model.getHourlyRate()));
//                    labels.add(hourlyRate);
//
//                    employeeInfoList.getItems().setAll(labels);
//                }
//
//            } catch (ExceptionHandler e) {
//                AlertHandler.displayAlert(e.getMessage(), Alert.AlertType.ERROR);
//            }});
//    }
//
//
//    public void populateChoicebox() {
//        filterComboBox.getItems().addAll("Country", "Team");
//
//        // Initially set the value to "All"
//        filterComboBox.setValue("All");
//
//        teamComboBox.setVisible(false);
//
//        filterComboBox.setOnAction(event -> {
//                String selectedOption = filterComboBox.getValue().toString();
//                if (selectedOption.equals("Country")) {
//                    teamComboBox.setVisible(true);
//                    teamComboBox.setValue("Select Country");
//                    teamComboBox.getItems().addAll(countryList);
//
//                } else if (selectedOption.equals("Team")) {
//                    teamComboBox.setVisible(true);
//                    teamComboBox.setValue("Select Team");
//                    teamComboBox.getItems().setAll("Production", "Management");
//                }
//            });
//        // Set cell factory to display each country with its flag
//        teamComboBox.setCellFactory(param -> new ListCell<Country>() {
//            @Override
//            protected void updateItem(Country country, boolean empty) {
//                super.updateItem(country, empty);
//                if (empty || country == null) {
//                    setText(null);
//                    setGraphic(null);
//                } else {
//                    ImageView flagImageView = FlagService.getFlagImageView(country.getCode());
//                    if (flagImageView != null) {
//                        flagImageView.setFitWidth(20);
//                        flagImageView.setFitHeight(20);
//                        setGraphic(flagImageView);
//                        setText(country.getValue());
//                    } else {
//                        setText(country.getValue());
//                    }
//                }
//            }
//        });
//        teamComboBox.setOnKeyPressed(event -> {
//            if (event.getCode().isLetterKey()) {
//                String typedText = event.getText();
//                filterCountriesByFirstLetter(typedText);
//            }
//        });
//        }
//    private void filterCountriesByFirstLetter(String letter) {
//        //filter the list by the first typed letter
//        ObservableList<Country> filteredList = countryList.filtered(country ->
//                country.getValue().toLowerCase().startsWith(letter.toLowerCase()));
//
//        teamComboBox.setItems(filteredList);
//        teamComboBox.getSelectionModel().selectFirst();
//    }
//
//    private double getMultiplier(TextField textField) {
//        try {
//            double value = Double.parseDouble(textField.getText());
//            if (value < 0 || value > 100) {
//                throw new IllegalArgumentException("Multiplier must be between 0 and 100.");
//            }
//            return value;
//        } catch (NumberFormatException e) {
//            throw new IllegalArgumentException("Invalid input. Please enter a number between 0 and 100.");
//        }
//    }
//    @FXML
//    public void onCalculate(ActionEvent event) {
//        try {
//            Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
//            if (selectedEmployee == null) {
//                showError("Please select an employee.");
//                return;
//            }
//
//            double markupPercentage = getMultiplier(markupTextField);
//            double gmPercentage = getMultiplier(gmTextField);
//
//            EmployeeLogic logic = new EmployeeLogic();
//
//            double hourlyRate = selectedEmployee.getContract().getHourlyRate();
//            double dailyRate = selectedEmployee.getContract().getDailyRate();
//
//            double markupHourlyRate = logic.hourlyRateMarkup(hourlyRate, markupPercentage);
//            double markupDailyRate = logic.dailyRateMarkup(dailyRate, markupPercentage);
//
//            double gmHourlyRate = logic.hourlyRateGM(markupHourlyRate, gmPercentage);
//            double gmDailyRate = logic.dailyRateGM(markupDailyRate, gmPercentage);
//
//            List<Label> labels = new ArrayList<>();
//            labels.add(new Label("Markup Hourly Rate: " + markupHourlyRate));
//            labels.add(new Label("GM Hourly Rate: " + gmHourlyRate));
//            labels.add(new Label("Markup Daily Rate: " + markupDailyRate));
//            labels.add(new Label("GM Daily Rate: " + gmDailyRate));
//
//            employeeInfoList.getItems().setAll(labels);
//
//        } catch (IllegalArgumentException e) {
//            showError(e.getMessage());
//        }
//    }
//
//    private void showError(String message) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    public void editEmployee(ActionEvent actionEvent) {
//        Employee selectedEmployee = employeeTableView.getSelectionModel().getSelectedItem();
//        model.setSelectedEmployee(selectedEmployee);
//        try {
//            Window.createStage(WindowType.CREATE_EMPLOYEE, model, Modality.APPLICATION_MODAL, false);
//        } catch (ExceptionHandler e) {
//            throw new RuntimeException(e);
//        }    }
//
//    public void deleteEmployee(ActionEvent actionEvent) {
//    }
//
//
//    public void onSave(ActionEvent actionEvent) {
//
//    }
//}
