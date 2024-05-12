package Rooms;

import Facilities.Facility;

import java.util.Map;
import java.util.TreeMap;

public class Room {
    protected final int roomID;
    protected int hotelID;
    protected int roomNumber;
    protected int floor;
    protected double price;
    Map<Integer, Facility> facilities;

    Room(int roomID, int hotelID, int roomNumber, int floor, double price) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.price = price;

        this.facilities = new TreeMap<Integer, Facility>();
    }

    public Room(Room room) {
        this.roomID = room.roomID;
        this.hotelID = room.hotelID;
        this.roomNumber = room.roomNumber;
        this.floor = room.floor;
        this.price = room.price;

        this.facilities = new TreeMap<Integer, Facility>(room.facilities);
    }

    public void addFacility(Facility facility) {
        this.facilities.put(facility.getFacilityID(), facility);
    }

    public int getRoomID() { return this.roomID; }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Room ( ID=" + this.roomID + " HOTEL_ID=" + this.hotelID +
                " ROOM_NUM=" + this.roomNumber + " FLOOR=" + this.floor + " PRICE=" + this.price + " )\n");

        result.append("Facilities (\n");
        for (Facility facility : this.facilities.values()) {
            result.append("\t").append(facility.toString());
        }

        result.append(")\n");

        return result.toString();
    }

    @Override
    public int hashCode() {
        return this.roomID;
    }

    public Room clone() { return new Room(this); };
}
