package Buildings;
public abstract class Building {

    private static int buildingIDGenerator = 0;

    protected int buildingID;
    protected int constructionYear;

    public Building(int constructionYear) {
        ++buildingIDGenerator;

        this.buildingID = buildingIDGenerator;
        this.constructionYear = constructionYear;
    }

    public Building(Building building) {
        this.buildingID = building.buildingID;
        this.constructionYear = building.constructionYear;
    }

    @Override
    public String toString() {
        return "Building ( ID=" + this.buildingID + " CONSTR_YEAR=" + this.constructionYear + " )";
    }

    @Override
    public int hashCode() {
        return this.buildingID;
    }

    public int getBuildingID() { return this.buildingID; }

    public abstract Building clone();
}
