package com.se.ecostruxure_mmirzakhani.bll.rate;

import com.se.ecostruxure_mmirzakhani.be.entities.*;
import com.se.ecostruxure_mmirzakhani.be.enums.Country;
import com.se.ecostruxure_mmirzakhani.be.enums.Currency;
import com.se.ecostruxure_mmirzakhani.be.enums.EmployeeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static javafx.beans.binding.Bindings.when;
import static org.junit.jupiter.api.Assertions.*;

class RateServiceTest {

    private Project project;
    private List<Assignment> assignments;
    private RateService rateService;

    @BeforeEach
    public void setUp() {
        project = new Project(1, "Project1", Country.DENMARK);
        assignments = new ArrayList<>();

        // Assignment 1
        Assignment assignment1 = new Assignment();
        assignment1.setId(1);
        assignment1.setTeam(new Team());

        Employee employee1 = new Employee();
        employee1.setId(1);
        Contract contract1 = new Contract();
        contract1.setId(1);
        contract1.setAnnualSalary(100_000);
        contract1.setFixedAnnualAmount(5_000);
        contract1.setAnnualWorkHours(2000);
        contract1.setAverageDailyWorkHours(8);
        contract1.setOverheadPercentage(0);
        contract1.setCurrency(Currency.EUR);
        employee1.setContract(contract1);

        assignment1.setEmployee(employee1);
        assignment1.setProject(project);
        assignment1.setUtilizationPercentage(80);
        assignment1.setEmployeeType(EmployeeType.OVERHEAD);

        // Assignment 2
        Assignment assignment2 = new Assignment();
        assignment2.setId(2);
        assignment2.setTeam(new Team());

        Employee employee2 = new Employee();
        employee2.setId(2);
        Contract contract2 = new Contract();
        contract2.setId(2);
        contract2.setAnnualSalary(120_000);
        contract2.setFixedAnnualAmount(6_000);
        contract2.setAnnualWorkHours(2100);
        contract2.setAverageDailyWorkHours(8.5);
        contract2.setOverheadPercentage(0);
        contract2.setCurrency(Currency.USD);
        employee2.setContract(contract2);

        assignment2.setEmployee(employee2);
        assignment2.setProject(project);
        assignment2.setUtilizationPercentage(90);
        assignment2.setEmployeeType(EmployeeType.OVERHEAD);

        assignments.add(assignment1);
        assignments.add(assignment2);

        rateService = new RateService(project, assignments);

    }

    @Test
    @DisplayName("Test getHourlyRate with valid assignments")
    public void testGetHourlyRate_withValidAssignments() {


        double expectedHourlyRate = 91.63;
        double actualHourlyRate = rateService.getHourlyRate();

        assertEquals(expectedHourlyRate, actualHourlyRate, 0.0001);
    }

    @Test
    @DisplayName("Test getDailyRate with valid assignments")
    public void testGetDailyRate_withValidAssignments() {
        double expectedDailyRate = 757.89; // Calculated manually
        double actualDailyRate = rateService.getDailyRate();
        assertEquals(expectedDailyRate, actualDailyRate, 0.01);
    }


    @Test
    @DisplayName("Test getDirectCosts Yearly with valid assignments")
    public void testGetDirectCosts_withValidAssignments() {
        double expectedDirectCosts = 0.0; // Expected is 0 because all the employees are overhead
        double actualDirectCosts = rateService.getDirectCosts();
        assertEquals(expectedDirectCosts, actualDirectCosts, 0.01);
    }

    @Test
    @DisplayName("Test getOverheadCosts Yearly with valid assignments")
    public void testGetOverheadCosts_withValidAssignments() {
        double expectedOverheadCosts = 188231.56; // Calculated manually
        double actualOverheadCosts = rateService.getOverheadCosts();
        assertEquals(expectedOverheadCosts, actualOverheadCosts, 0.01);
    }


    @Test
    @DisplayName("Test getTotalCosts Yearly with valid assignments")
    public void testGetTotalCosts_withValidAssignments() {
        double expectedTotalCosts = 188231.56; // Calculated manually
        double actualTotalCosts = rateService.getTotalCosts();
        assertEquals(expectedTotalCosts, actualTotalCosts, 0.01);
    }
}