package com.se.ecostruxure_mmirzakhani.dal;

import com.se.ecostruxure_mmirzakhani.be.Geography;
import com.se.ecostruxure_mmirzakhani.dal.db.DBConnection;
import com.se.ecostruxure_mmirzakhani.exceptions.ExceptionHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeographiesDAO {

    private final DBConnection dbConnection;

    /**
     * Constructor
     */
    public GeographiesDAO() throws ExceptionHandler {
        dbConnection = new DBConnection();
    }

    public void addGeography(Geography geography) throws SQLException {
        String sql = "INSERT INTO Geographies (CountryName, Region) VALUES (?, ?)";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, geography.getCountryName());
                statement.setString(2, geography.getRegion());
                statement.executeUpdate();
            }
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public Geography getGeography(int geographyId) throws SQLException {
        String sql = "SELECT * FROM Geographies WHERE GeographyID = ?";
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, geographyId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return new Geography(
                            resultSet.getInt("GeographyID"),
                            resultSet.getString("CountryName"),
                            resultSet.getString("Region")
                    );
                }
            }
            return null;
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }

    public List<Geography> getAllGeographies() throws SQLException {
        List<Geography> geographies = new ArrayList<>();
        String sql = "SELECT * FROM Geographies";
        try (Connection connection =dbConnection.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Geography geography = new Geography(
                            resultSet.getInt("GeographyID"),
                            resultSet.getString("CountryName"),
                            resultSet.getString("Region")
                    );
                    geographies.add(geography);
                }
            }
            return geographies;
        } catch (ExceptionHandler e) {
            throw new RuntimeException(e);
        }
    }
}
