package Facilities;

import Buildings.Building;
import Crud.CRUD;
import Services.Setup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Facility implements CRUD<Facility> {
    private int facilityID;
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
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("description=");
        this.description = scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("facilityID=");
        this.facilityID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("facilityID=");
        this.facilityID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("description=");
        this.description = scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("facilityID=");
        this.facilityID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO facility(description) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setString(1, this.getDescription());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Facility read() throws SQLException {
        final String read = "SELECT * FROM facility WHERE facilityID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getFacilityID());

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
    public int update() throws SQLException {
        final String update = "UPDATE facility SET description = ? WHERE facilityID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setString(1, this.getDescription());
        preparedStatement.setInt(2, this.getFacilityID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM facility WHERE facilityID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getFacilityID());

        return preparedStatement.executeUpdate();
    }

    public static List<Facility> readAllFacilities() throws SQLException {
        final String readAll = "SELECT * FROM facility";

        List<Facility> facilityList = new ArrayList<Facility>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            facilityList.add(new Facility(rs.getInt("facilityID"), rs.getString("description")));
        }

        return facilityList;
    }
}
