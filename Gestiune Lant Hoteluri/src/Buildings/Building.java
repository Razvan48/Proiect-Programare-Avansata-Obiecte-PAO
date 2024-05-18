package Buildings;

import Services.Database;

import Crud.CRUD;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Building implements CRUD<Building> {
    protected int buildingID;
    protected int constructionYear;
    protected int locationID;

    public Building(int buildingID, int constructionYear, int locationID) {
        this.buildingID = buildingID;
        this.constructionYear = constructionYear;
        this.locationID = locationID;

        Database.get().addBuilding(this);
    }

    public Building(Building building) {
        this.buildingID = building.buildingID;
        this.constructionYear = building.constructionYear;
        this.locationID = building.locationID;
    }

    @Override
    public String toString() {
        return "Building ( ID=" + this.buildingID + " CONSTR_YEAR=" + this.constructionYear +
                " locationID=" + this.locationID + " )";
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
    public int create(Building building) throws SQLException {
        final String create = "INSERT INTO building(constructionYear, locationID) VALUES (?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, building.getConstructionYear());
        preparedStatement.setInt(2, building.getLocationID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Building read(Building building) throws SQLException {
        final String read = "SELECT * FROM building WHERE buildingID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, building.getBuildingID());

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
    public int update(Building building) throws SQLException {
        final String update = "UPDATE building SET constructionYear = ?, locationID = ? WHERE buildingID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, building.getConstructionYear());
        preparedStatement.setInt(2, building.getLocationID());
        preparedStatement.setInt(3, building.getBuildingID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Building building) throws SQLException {
        final String delete = "DELETE FROM building WHERE buildingID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, building.getBuildingID());

        return preparedStatement.executeUpdate();
    }
}
