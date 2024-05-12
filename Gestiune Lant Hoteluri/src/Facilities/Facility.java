package Facilities;

public class Facility {
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
}
