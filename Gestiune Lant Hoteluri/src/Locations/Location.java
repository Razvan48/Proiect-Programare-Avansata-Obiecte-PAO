package Locations;

import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Crud.CRUD;

public class Location implements CRUD<Location> {
    private final int locationID;
    private String address;

    public Location(int locationID, String address) {
        this.locationID = locationID;
        this.address = address;
    }

    public Location(Location location) {
        this.locationID = location.locationID;
        this.address = location.address;
    }

    public int getLocationID() { return this.locationID; }
    public String getAddress() { return this.address; }

    @Override
    public int hashCode() { return this.locationID; }

    @Override
    public String toString() {
        return "Location ( ID=" + this.locationID + " ADDR=" + this.address + " )\n";
    }

    @Override
    public int create(Location location) throws SQLException {
        final String create = "INSERT INTO location(address) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setString(1, location.getAddress());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Location read(Location location) throws SQLException {
        final String read = "SELECT * FROM location WHERE locationID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, location.getLocationID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Location(rs.getInt("locationID"), rs.getString("address"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update(Location location) throws SQLException {
        final String update = "UPDATE location SET address = ? WHERE locationID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setString(1, location.getAddress());
        preparedStatement.setInt(2, location.getLocationID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Location location) throws SQLException {
        final String delete = "DELETE FROM location WHERE locationID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, location.getLocationID());

        return preparedStatement.executeUpdate();
    }
}
