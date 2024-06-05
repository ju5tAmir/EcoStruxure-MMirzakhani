package com.se.ecostruxure_mmirzakhani.dal.multiplier;

import com.se.ecostruxure_mmirzakhani.be.entities.Assignment;
import com.se.ecostruxure_mmirzakhani.be.entities.Multiplier;
import com.se.ecostruxure_mmirzakhani.be.entities.Project;
import com.se.ecostruxure_mmirzakhani.be.enums.Country;
import com.se.ecostruxure_mmirzakhani.be.enums.MultiplierType;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MultiplierDAO {
    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public MultiplierDAO() {
        dbConnection = new DBConnection();
    }

    /**
     * Checks if the given multiplier exists, it will be useful when user wants to save.
     */
    public boolean doesMultiplierExist(Multiplier multiplier) throws ExceptionHandler {
        String sql = "SELECT 1 FROM Multipliers WHERE ID = ?";

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, multiplier.getId());


            try (ResultSet resultSet = statement.executeQuery()) {

                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(e.getMessage());
        }
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

    public List<Multiplier> getMultipliersByProject(Project project) throws ExceptionHandler {
        String sql = "SELECT Multipliers.id, Multipliers.ProjectID, Multipliers.MultiplierType, Multipliers.Value, p.ProjectName, p.Country FROM Multipliers LEFT JOIN dbo.Projects P on P.ProjectID = Multipliers.ProjectID WHERE p.ProjectID = ?;";
        List<Multiplier> multipliers = new ArrayList<>();

        try (Connection connection = dbConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set the parameter for the prepared statement
            statement.setInt(1, project.getId());

            // Execute the query
            try (ResultSet resultSet = statement.executeQuery()) {
                // Process the result set
                while (resultSet.next()) {
                    Multiplier multiplier = new Multiplier();
                    multiplier.setId(resultSet.getInt("ID"));
                    multiplier.setProject(new Project(resultSet.getInt("ProjectID"), resultSet.getString("ProjectName"), Country.fromString(resultSet.getString("Country"))));
                    multiplier.setMultiplierType(MultiplierType.valueOf(resultSet.getString("MultiplierType")));
                    multiplier.setValue(resultSet.getDouble("Value"));
                    multipliers.add(multiplier);
                }
            }
        } catch (SQLException e) {
            throw new ExceptionHandler(e.getMessage());
        }

        return multipliers;
    }
}
