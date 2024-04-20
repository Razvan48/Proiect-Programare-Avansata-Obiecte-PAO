package Rooms;

import Facilities.Facility;

import java.util.HashSet;

public abstract class Room {
    static protected double pricePerPerson = 500.0;
    static private int roomIDGenerator = 0;

    protected final int roomID;
    protected int roomNumber;
    protected int floor;
    HashSet<Facility> facilities;

    Room(int roomNumber, int floor) {
        ++roomIDGenerator;

        this.roomID = roomIDGenerator;
        this.roomNumber = roomNumber;
        this.floor = floor;

        this.facilities = new HashSet<Facility>();
    }

    public Room(Room room) {
        this.roomID = room.roomID;
        this.roomNumber = room.roomNumber;
        this.floor = room.floor;

        this.facilities = new HashSet<Facility>(room.facilities);
    }

    public abstract double getPrice();

    public void addFacility(Facility facility) {
        this.facilities.add(new Facility(facility));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Room ( ID=" + this.roomID + " ROOM_NUM=" + this.roomNumber + " FLOOR=" + this.floor + " )\n");

        result.append("Facilities (\n");
        for (Facility facility : this.facilities) {
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
}
