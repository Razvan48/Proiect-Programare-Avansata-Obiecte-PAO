package Buildings;
public abstract class Building {
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
                " locationID=" + this.locationID + " )";
    }

    @Override
    public int hashCode() {
        return this.buildingID;
    }

    public int getBuildingID() { return this.buildingID; }
    public int getConstructionYear() { return this.constructionYear; }
    public int getLocationID() { return this.locationID; }

    public abstract Building clone();
}
