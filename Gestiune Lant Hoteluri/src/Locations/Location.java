package Locations;
public class Location {
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
        return "Location ( ID=" + this.locationID + " ADDR=" + this.address + " )";
    }
}
