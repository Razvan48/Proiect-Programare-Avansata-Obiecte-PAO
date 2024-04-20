package Rooms;

import Facilities.Facility;

import java.util.Map;
import java.util.HashMap;

public abstract class Room {
    protected static double pricePerPerson = 500.0;
    private static int roomIDGenerator = 0;

    protected final int roomID;
    protected int roomNumber;
    protected int floor;
    Map<Integer, Facility> facilities;

    Room(int roomNumber, int floor) {
        ++roomIDGenerator;

        this.roomID = roomIDGenerator;
        this.roomNumber = roomNumber;
        this.floor = floor;

        this.facilities = new HashMap<Integer, Facility>();
    }

    public Room(Room room) {
        this.roomID = room.roomID;
        this.roomNumber = room.roomNumber;
        this.floor = room.floor;

        this.facilities = new HashMap<Integer, Facility>(room.facilities);
    }

    public abstract double getPrice();

    public void addFacility(Facility facility) {
        this.facilities.put(facility.getFacilityID(), new Facility(facility));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Room ( ID=" + this.roomID + " ROOM_NUM=" + this.roomNumber + " FLOOR=" + this.floor + " )\n");

        result.append("Facilities (\n");
        for (Facility facility : this.facilities.values()) {
            result.append("\t").append(facility.toString()).append("\n");
        }

        result.append(")");

        return result.toString();
    }

    @Override
    public int hashCode() {
        return this.roomID;
    }

    public abstract Room clone();

    public int getRoomID() { return this.roomID; }
}
