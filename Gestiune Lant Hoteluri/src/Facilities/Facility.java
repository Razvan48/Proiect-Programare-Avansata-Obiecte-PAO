package Facilities;

import Crud.CRUD;
import Services.Setup;

import java.sql.*;

public class Facility implements CRUD<Facility> {
    private final int facilityID;
    private String description;

    public Facility(int facilityID, String description) {
        this.facilityID = facilityID;
        this.description = description;
    }

    public Facility(Facility facility) {
        this.facilityID = facility.facilityID;
        this.description = facility.description;
    }

    public int getFacilityID() { return this.facilityID; }
    public String getDescription() { return this.description; }

    @Override
    public int hashCode() {
        return this.facilityID;
    }

    @Override
    public String toString() {
        return "Facility ( ID=" + this.facilityID + " DESC=" + this.description + " )\n";
    }

    @Override
    public int create(Facility facility) throws SQLException {
        final String create = "INSERT INTO facility(description) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setString(1, facility.getDescription());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Facility read(Facility facility) throws SQLException {
        final String read = "SELECT * FROM facility WHERE facilityID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, facility.getFacilityID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Facility(rs.getInt("facilityID"), rs.getString("description"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update(Facility facility) throws SQLException {
        final String update = "UPDATE facility SET description = ? WHERE facilityID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setString(1, facility.getDescription());
        preparedStatement.setInt(2, facility.getFacilityID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Facility facility) throws SQLException {
        final String delete = "DELETE FROM facility WHERE facilityID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, facility.getFacilityID());

        return preparedStatement.executeUpdate();
    }
}
