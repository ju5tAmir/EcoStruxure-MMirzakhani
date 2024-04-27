package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.Contract;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContractManager {
    private final DBConnection dbConnection;

    public ContractManager() throws ExceptionHandler {
        dbConnection = new DBConnection();
    }

    public List<Contract> getContractHistory(int employeeId) throws ExceptionHandler {
        List<Contract> contractHistory = new ArrayList<>();

        String getContractHistoryQuery = "SELECT * FROM ContractHistory WHERE EmployeeID = ?";
        try (Connection conn = dbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(getContractHistoryQuery)) {

            statement.setInt(1, employeeId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Contract contract = new Contract();
                contract.setAnnualSalary(rs.getDouble("AnnualSalary"));

                contractHistory.add(contract);
            }
            return contractHistory;

        } catch (SQLException e) {
            throw new ExceptionHandler(e.getMessage());
        }
    }

    public boolean updateContract(Contract contract) throws ExceptionHandler {
        String updateContract = "UPDATE Contract SET AnnualSalary = ?, FixedAnnualAmount = ?, AnnualWorkHours = ?, " +
                "AverageDailyWorkHours = ?, OverheadPercentage = ?, UtilizationPercentage = ?, MarkupPercentage = ?, " +
                "GrossMarginPercentage = ?, IsOverhead = ? WHERE EmployeeID = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(updateContract)) {

            statement.setDouble(1, contract.getAnnualSalary());
            statement.setDouble(2, contract.getFixedAnnualAmount());
            statement.setDouble(3, contract.getAnnualWorkHours());
            statement.setDouble(4, contract.getAverageDailyWorkHours());
            statement.setDouble(5, contract.getOverheadPercentage());
            statement.setDouble(6, contract.getUtilizationPercentage());
            statement.setDouble(7, contract.getMarkupPercentage());
            statement.setDouble(8, contract.getGrossMarginPercentage());
            statement.setBoolean(9, contract.isOverhead());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new ExceptionHandler(e.getMessage());
        }
    }
}

