package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.*;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public EmployeeDAO() throws ExceptionHandler{
        dbConnection = new DBConnection();
    }

    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employees (FirstName, LastName) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public Employee getEmployee(int employeeID) throws SQLException {
        String sql = "SELECT * FROM Employees WHERE EmployeeID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeID);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return new Employee(
                            resultSet.getInt("EmployeeID"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName")
                    );
                }
            }
            return null;
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employees";
        try (Connection connection = dbConnection.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Employee employee = new Employee(
                            resultSet.getInt("EmployeeID"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName")
                    );
                    employees.add(employee);
                }
            }
            return employees;
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE Employees SET FirstName = ?, LastName = ? WHERE EmployeeID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getLastName());
                statement.setInt(3, employee.getId());
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void deleteEmployee(int employeeID) throws SQLException {
        String sql = "DELETE FROM Employees WHERE EmployeeID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, employeeID);
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }
}
