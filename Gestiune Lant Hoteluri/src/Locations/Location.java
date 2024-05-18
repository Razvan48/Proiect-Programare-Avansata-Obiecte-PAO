package Locations;

import Facilities.Facility;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Crud.CRUD;

public class Location implements CRUD<Location> {
    private int locationID;
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
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("address=");
        this.address = scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("locationID=");
        this.locationID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("locationID=");
        this.locationID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("address=");
        this.address = scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("locationID=");
        this.locationID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO location(address) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setString(1, this.getAddress());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Location read() throws SQLException {
        final String read = "SELECT * FROM location WHERE locationID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getLocationID());

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
    public int update() throws SQLException {
        final String update = "UPDATE location SET address = ? WHERE locationID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setString(1, this.getAddress());
        preparedStatement.setInt(2, this.getLocationID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM location WHERE locationID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getLocationID());

        return preparedStatement.executeUpdate();
    }

    public static List<Location> readAllLocations() throws SQLException {
        final String readAll = "SELECT * FROM location";

        List<Location> locationList = new ArrayList<Location>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            locationList.add(new Location(rs.getInt("locationID"), rs.getString("address")));
        }

        return locationList;
    }
}
