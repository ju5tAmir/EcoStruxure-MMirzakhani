package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionMessage;

import java.sql.*;

public class EmployeeDAO {
    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public EmployeeDAO() throws ExceptionHandler{
        dbConnection = new DBConnection();
    }

    public boolean createEmployee(Employee employee) throws ExceptionHandler {
        // Insert query to Employee table
        String insertEmployee = "INSERT INTO Employees(FirstName, LastName, Region, Country) VALUES (?,?,?,?)";
        // Insert query for connection an employee to a team (if possible)
        String insertTeam = "INSERT INTO Team(TeamName, EmployeeID) VALUES (?,?)";
        // Insert contract information
        String insertContract = "INSERT INTO Contract(EmployeeID, AnnualSalary, FixedAnnualAmount, AnnualWorkHours, AverageDailyWorkHours, OverheadPercentage, UtilizationPercentage, MarkupPercentage, GrossMarginPercentage, IsOverhead) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = dbConnection.getConnection()) {
            // Starting a transaction
            conn.setAutoCommit(false);
            // Protect from update/delete for this row
            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            // Statements
            try (PreparedStatement employeeStatement = conn.prepareStatement(insertEmployee, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement teamStatement = conn.prepareStatement(insertTeam);
                 PreparedStatement contractStatement = conn.prepareStatement(insertContract)) {

                // Insert employee information
                employeeStatement.setString(1, employee.getFirstName());
                employeeStatement.setString(2, employee.getLastName());
                employeeStatement.setString(3, employee.getRegion().getName());
                employeeStatement.setString(4, employee.getCountry().getValue());

                employeeStatement.executeUpdate();

                // Retrieve the generated id for the employee
                try (ResultSet rs = employeeStatement.getGeneratedKeys()) {
                    if (rs.next()) {
                        employee.setId(rs.getInt(1));
                    } else {
                        throw new ExceptionHandler(ExceptionMessage.KEY_GENERATION_FAILURE.getValue());
                    }
                }

                // Insert team details if employee is part of a team
                if (employee.getTeam() != null) {
                    teamStatement.setString(1, employee.getTeam().getName());
                    teamStatement.setInt(2, employee.getId());
                    teamStatement.addBatch();
                }

                // Insert contract information
                contractStatement.setInt(1, employee.getId());
                contractStatement.setDouble(2, employee.getAnnualSalary());
                contractStatement.setDouble(3, employee.getFixedAnnualAmount());
                contractStatement.setDouble(4, employee.getAnnualWorkHours());
                contractStatement.setDouble(5, employee.getAverageDailyWorkHours());
                contractStatement.setDouble(6, employee.getOverheadPercentage());
                contractStatement.setDouble(7, employee.getUtilizationPercentage());
                contractStatement.setDouble(8, employee.getMarkupPercentage());
                contractStatement.setDouble(9, employee.getGrossMarginPercentage());
                contractStatement.setBoolean(10, employee.isOverhead());
                contractStatement.addBatch();

                // Execute batches
                employeeStatement.executeBatch();
                teamStatement.executeBatch();
                contractStatement.executeBatch();

                // Commit transaction
                conn.commit();
            } catch (SQLException e) {
                // Rollback if something went wrong
                conn.rollback();
                throw new ExceptionHandler(ExceptionMessage.EMPLOYEE_INSERT_FAILED.getValue(), e.getMessage());
            }
            return true;
        } catch (SQLException ex) {

            throw new ExceptionHandler(ExceptionMessage.DB_CONNECTION_FAILURE.getValue(), ex.getMessage());
        }
    }

}
