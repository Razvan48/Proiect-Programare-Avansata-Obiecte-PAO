package Buildings;

import Crud.CRUD;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Building implements CRUD<Building> {
    protected int buildingID;
    protected int constructionYear;
    protected int locationID;

    public Building(int buildingID, int constructionYear, int locationID) {
        this.buildingID = buildingID;
        this.constructionYear = constructionYear;
        this.locationID = locationID;
    }

    public Building(Building building) {
        this.buildingID = building.buildingID;
        this.constructionYear = building.constructionYear;
        this.locationID = building.locationID;
    }

    @Override
    public String toString() {
        return "Building ( ID=" + this.buildingID + " CONSTR_YEAR=" + this.constructionYear +
                " locationID=" + this.locationID + " )\n";
    }

    @Override
    public int hashCode() {
        return this.buildingID;
    }

    public int getBuildingID() { return this.buildingID; }
    public int getConstructionYear() { return this.constructionYear; }
    public int getLocationID() { return this.locationID; }

    public Building clone() { return new Building(this); }

    @Override
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("constructionYear=");
        this.constructionYear = scanner.nextInt();
        System.out.println("\n");
        System.out.println("locationID=");
        this.locationID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("buildingID=");
        this.buildingID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("buildingID=");
        this.buildingID = scanner.nextInt();
        System.out.println("\n");
        System.out.println("constructionYear=");
        this.constructionYear = scanner.nextInt();
        System.out.println("\n");
        System.out.println("locationID=");
        this.locationID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("buildingID=");
        this.buildingID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO building(constructionYear, locationID) VALUES (?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.getConstructionYear());
        preparedStatement.setInt(2, this.getLocationID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Building read() throws SQLException {
        final String read = "SELECT * FROM building WHERE buildingID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getBuildingID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Building(rs.getInt("buildingID"), rs.getInt("constructionYear"),
                    rs.getInt("locationID"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update() throws SQLException {
        final String update = "UPDATE building SET constructionYear = ?, locationID = ? WHERE buildingID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, this.getConstructionYear());
        preparedStatement.setInt(2, this.getLocationID());
        preparedStatement.setInt(3, this.getBuildingID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM building WHERE buildingID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getBuildingID());

        return preparedStatement.executeUpdate();
    }

    public static List<Building> readAllBuildings() throws SQLException {
        final String readAll = "SELECT * FROM building";

        List<Building> buildingList = new ArrayList<Building>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            buildingList.add(new Building(rs.getInt("buildingID"), rs.getInt("constructionYear"),
                    rs.getInt("locationID")));
        }

        return buildingList;
    }
}
