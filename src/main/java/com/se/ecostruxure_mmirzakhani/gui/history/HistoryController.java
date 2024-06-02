package com.se.ecostruxure_mmirzakhani.gui.history;

import com.se.ecostruxure_mmirzakhani.be.entities.*;
import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.be.enums.EmployeeType;
import com.se.ecostruxure_mmirzakhani.gui.IController;
import com.se.ecostruxure_mmirzakhani.model.Model;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HistoryController implements IController<Model> {
    @FXML
    private DatePicker fromDate, toDate;

    @FXML
    private Pane mainPane;
    private LineChart<Number, Number> lineChart;
    private Model model;
    private List<Assignment> assignments = new ArrayList<>();

    @Override
    public void setModel(Model model) {
        this.model = model;
        initMockData();
        setDatePickerListener();
    }

    private void setDatePickerListener(){
        fromDate.valueProperty().addListener((observable, oldValue, newValue) -> {

            setDailyGraph(newValue, assignments);
        });
    }

    /**
     * Set graph charts, currently only daily chart is available
     */
    private void setDailyGraph(LocalDate date, List<Assignment> assignments){
        // Remove the previous chart from the main pane (if exists)
        mainPane.getChildren().remove(lineChart);

        // Get number of days in the selected month
        int year = date.getYear();
        int month = date.getMonthValue();
        YearMonth yearMonthObject = YearMonth.of(year, month);
        int numberOfDays          = yearMonthObject.lengthOfMonth();


        // Defining the y-axis (Price)
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Cost");


        // Defining the x-axis (Time)
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Day of the Month");
        xAxis.setAutoRanging(false);
        xAxis.setLowerBound(1);
        xAxis.setUpperBound(numberOfDays);
        xAxis.setTickUnit(1);
        xAxis.setMinorTickCount(0);
        xAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(xAxis) {
            @Override
            public String toString(Number object) {
                return String.format("%d", object.intValue());
            }
        });

        // Creating the line chart
        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setLayoutX(415);
        lineChart.setLayoutY(135);
        lineChart.setPrefWidth(770);
        lineChart.setPrefHeight(525);
        lineChart.setTitle("Price Changes");

        // Defining a series to display data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Price over Time");


        // Populating the series with data with number of days in that month
        for (int i = 1; i <= numberOfDays; i ++){
            // Iterate over days of the selected month
            LocalDate localDate = yearMonthObject.atDay(i);
            for (Assignment assignment: assignments){
                LocalDateTime localDateTime = assignment.getTimeLine().getFrom();


                if (localDateTime.toLocalDate().isEqual(localDate)){
                    series.getData().add(new XYChart.Data<>(i, assignment.getEmployee().getContract().getAnnualSalary()));

                }
            }

        }

        // Adding the series to the line chart
        lineChart.getData().add(series);

        // Add the new chart to the main pane
        mainPane.getChildren().add(lineChart);


    }

    @FXML
    private void onDailyButton(){

    }

    @FXML
    private void onMonthlyButton(){

    }

    private void initMockData(){

        // Create TimeLine objects
        TimeLine timeLine1 = new TimeLine(LocalDateTime.of(2024, 6, 10, 9, 0), LocalDateTime.of(2023, 12, 31, 18, 0));
        TimeLine timeLine2 = new TimeLine(LocalDateTime.of(2024, 6, 10, 9, 0), LocalDateTime.of(2023, 11, 30, 17, 0));
        TimeLine timeLine3 = new TimeLine(LocalDateTime.of(2024, 6, 11, 9, 0), LocalDateTime.of(2023, 10, 31, 18, 0));
        TimeLine timeLine4 = new TimeLine(LocalDateTime.of(2024, 6, 12, 9, 0), LocalDateTime.of(2023, 9, 30, 17, 0));
        TimeLine timeLine5 = new TimeLine(LocalDateTime.of(2024, 6, 15, 9, 0), LocalDateTime.of(2023, 8, 31, 18, 0));

        // Create Contract objects
        Contract contract1 = new Contract();
        contract1.setId(101);
        contract1.setAnnualSalary(75000);
        contract1.setFixedAnnualAmount(5000);
        contract1.setAnnualWorkHours(2000);
        contract1.setAverageDailyWorkHours(8);
        contract1.setOverheadPercentage(15);
        contract1.setCurrency(Currency.USD);
        contract1.setTimeLine(timeLine1);

        Contract contract2 = new Contract();
        contract2.setId(102);
        contract2.setAnnualSalary(80000);
        contract2.setFixedAnnualAmount(6000);
        contract2.setAnnualWorkHours(1950);
        contract2.setAverageDailyWorkHours(7.5);
        contract2.setOverheadPercentage(12);
        contract2.setCurrency(Currency.EUR);
        contract2.setTimeLine(timeLine2);

        Contract contract3 = new Contract();
        contract3.setId(103);
        contract3.setAnnualSalary(90000);
        contract3.setFixedAnnualAmount(7000);
        contract3.setAnnualWorkHours(1900);
        contract3.setAverageDailyWorkHours(7);
        contract3.setOverheadPercentage(18);
        contract3.setCurrency(Currency.GBP);
        contract3.setTimeLine(timeLine3);

        Contract contract4 = new Contract();
        contract4.setId(104);
        contract4.setAnnualSalary(85000);
        contract4.setFixedAnnualAmount(6500);
        contract4.setAnnualWorkHours(1850);
        contract4.setAverageDailyWorkHours(7.5);
        contract4.setOverheadPercentage(20);
        contract4.setCurrency(Currency.JPY);
        contract4.setTimeLine(timeLine4);

        Contract contract5 = new Contract();
        contract5.setId(105);
        contract5.setAnnualSalary(95000);
        contract5.setFixedAnnualAmount(8000);
        contract5.setAnnualWorkHours(1800);
        contract5.setAverageDailyWorkHours(8);
        contract5.setOverheadPercentage(10);
        contract5.setCurrency(Currency.AUD);
        contract5.setTimeLine(timeLine5);

        // Create Employee objects
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setContract(contract1);

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setContract(contract2);

        Employee employee3 = new Employee();
        employee3.setId(3);
        employee3.setFirstName("Alice");
        employee3.setLastName("Johnson");
        employee3.setContract(contract3);

        Employee employee4 = new Employee();
        employee4.setId(4);
        employee4.setFirstName("Bob");
        employee4.setLastName("Williams");
        employee4.setContract(contract4);

        Employee employee5 = new Employee();
        employee5.setId(5);
        employee5.setFirstName("Charlie");
        employee5.setLastName("Brown");
        employee5.setContract(contract5);


        // Create Project objects
        Project project1 = new Project();
        Project project2 = new Project();

        // Create Team objects
        Team team1 = new Team(301, "Team A");
        Team team2 = new Team(302, "Team B");

        // Create Assignment objects
        Assignment assignment1 = new Assignment();
        assignment1.setId(1);
        assignment1.setEmployee(employee1);
        assignment1.setProject(project1);
        assignment1.setTeam(team1);
        assignment1.setUtilizationPercentage(75);
        assignment1.setEmployeeType(EmployeeType.OVERHEAD);
        assignment1.setTimeLine(timeLine1);

        Assignment assignment2 = new Assignment();
        assignment2.setId(2);
        assignment2.setEmployee(employee2);
        assignment2.setProject(project2);
        assignment2.setTeam(team2);
        assignment2.setUtilizationPercentage(80);
        assignment2.setEmployeeType(EmployeeType.OVERHEAD);
        assignment2.setTimeLine(timeLine5);

        Assignment assignment3 = new Assignment();
        assignment3.setId(3);
        assignment3.setEmployee(employee3);
        assignment3.setProject(project1);
        assignment3.setTeam(team1);
        assignment3.setUtilizationPercentage(70);
        assignment3.setEmployeeType(EmployeeType.OVERHEAD);
        assignment3.setTimeLine(timeLine3);


        assignments.addAll(List.of(assignment1, assignment2, assignment3));
    }
}
