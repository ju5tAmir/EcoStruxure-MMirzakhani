package com.se.ecostruxure_mmirzakhani.dal.multiplier;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.entities.Multiplier;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.sql.*;

public class MultiplierDAO {
    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public MultiplierDAO() {
        dbConnection = new DBConnection();
    }

    public boolean createMultiplier(Multiplier multiplier) throws ExceptionHandler {
        String sql = "INSERT INTO Multipliers (ProjectID, MultiplierType, Value) " +
                "VALUES (?, ?, ?)";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // Set parameters for the prepared statement
            statement.setInt(1, multiplier.getProject().getId());
            statement.setString(2, multiplier.getMultiplierType().name());
            statement.setDouble(3, multiplier.getValue());


            // Execute the SQL statement
            int rowsInserted = statement.executeUpdate();

            // Check if the assignment was successfully created
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        // Retrieve and update the ID of newly created assignment
                        int multiplierID = generatedKeys.getInt(1);
                        multiplier.setId(multiplierID);
                        return true;
                    } else {
                        throw new SQLException("Creating multiplier failed, no ID obtained.");
                    }
                }
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(e.getMessage());
        }
    }


    public boolean updateMultiplier(Multiplier multiplier) throws ExceptionHandler {
        String sql = "UPDATE Multipliers SET ProjectID = ?, MultiplierType = ?, Value = ? WHERE ID = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set parameters for the prepared statement
            statement.setInt(1, multiplier.getProject().getId());
            statement.setString(2, multiplier.getMultiplierType().name());
            statement.setDouble(3, multiplier.getValue());
            statement.setInt(4, multiplier.getId());

            // Execute the SQL statement
            int rowsUpdated = statement.executeUpdate();

            // Check if the multiplier was successfully updated
            return rowsUpdated > 0;
        } catch (SQLException e) {
            throw new ExceptionHandler(e.getMessage());
        }
    }
}
