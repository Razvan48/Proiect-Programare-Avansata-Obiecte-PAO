package Locations;
public class Location {
    private static int locationIDGenerator = 0;
    private final int locationID;
    private String address;

    Location(String address) {
        ++locationIDGenerator;

        this.locationID = locationIDGenerator;
        this.address = address;
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
