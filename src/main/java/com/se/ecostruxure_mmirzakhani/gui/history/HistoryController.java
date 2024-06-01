package com.se.ecostruxure_mmirzakhani.gui.history;

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
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class HistoryController implements IController<Model> {
    @FXML
    private DatePicker fromDate, toDate;

    @FXML
    private Pane mainPane;
    private LineChart<Number, Number> lineChart;
    private Model model;

    @Override
    public void setModel(Model model) {
        this.model = model;

        setDatePickerListener();
    }

    private void setDatePickerListener(){
        fromDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Get number of days in the selected month
            YearMonth yearMonthObject = YearMonth.of(newValue.getYear(), newValue.getMonth());
            int daysIntMonth          = yearMonthObject.lengthOfMonth();

            setDailyGraph(daysIntMonth);
        });
    }

    /**
     * Set graph charts, currently only daily chart is available
     */
    private void setDailyGraph(int numberOfDays){
        // Remove the previous chart from the main pane
        mainPane.getChildren().remove(lineChart);


        // Defining the y-axis (Price)
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Price");


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

            series.getData().add(new XYChart.Data<>(i, Math.random() * 500));
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
}
