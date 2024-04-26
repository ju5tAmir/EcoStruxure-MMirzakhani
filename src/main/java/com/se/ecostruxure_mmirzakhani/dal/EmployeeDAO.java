package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.Employee;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private DBConnection dbConnection;

    public EmployeeDAO(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void createEmployee(Employee employee) {
        String sql = "INSERT INTO employees (annualSalary, overheadMultiplier, fixedAnnualAmount, geography, team, annualEffectiveHours, utilizationPercentage, isOverhead) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, employee.getAnnualSalary());
            preparedStatement.setDouble(2, employee.getOverheadMultiplier());
            preparedStatement.setDouble(3, employee.getFixedAnnualAmount());
            preparedStatement.setString(4, employee.getGeography());
            preparedStatement.setString(5, employee.getTeam());
            preparedStatement.setDouble(6, employee.getAnnualEffectiveHours());
            preparedStatement.setDouble(7, employee.getUtilizationPercentage());
            preparedStatement.setBoolean(8, employee.isOverhead());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Employee readEmployee(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new Employee(
                            resultSet.getInt("id"),
                            resultSet.getDouble("annualSalary"),
                            resultSet.getDouble("overheadMultiplier"),
                            resultSet.getDouble("fixedAnnualAmount"),
                            resultSet.getString("geography"),
                            resultSet.getString("team"),
                            resultSet.getInt("annualEffectiveHours"),
                            resultSet.getDouble("utilizationPercentage"),
                            resultSet.getBoolean("isOverhead")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<Employee> readAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                employees.add(new Employee(
                        rs.getInt("id"),
                        rs.getDouble("annualSalary"),
                        rs.getDouble("overheadMultiplier"),
                        rs.getDouble("fixedAnnualAmount"),
                        rs.getString("geography"),
                        rs.getString("team"),
                        rs.getInt("annualEffectiveHours"),
                        rs.getDouble("utilizationPercentage"),
                        rs.getBoolean("isOverhead")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }

    public void updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET annualSalary = ?, overheadMultiplier = ?, fixedAnnualAmount = ?, geography = ?, team = ?, annualEffectiveHours = ?, utilizationPercentage = ?, isOverhead = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, employee.getAnnualSalary());
            preparedStatement.setDouble(2, employee.getOverheadMultiplier());
            preparedStatement.setDouble(3, employee.getFixedAnnualAmount());
            preparedStatement.setString(4, employee.getGeography());
            preparedStatement.setString(5, employee.getTeam());
            preparedStatement.setDouble(6, employee.getAnnualEffectiveHours());
            preparedStatement.setDouble(7, employee.getUtilizationPercentage());
            preparedStatement.setBoolean(8, employee.isOverhead());
            preparedStatement.setInt(9, employee.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
