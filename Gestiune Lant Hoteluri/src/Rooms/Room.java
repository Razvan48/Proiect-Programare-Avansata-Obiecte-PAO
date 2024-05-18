package Rooms;

import Facilities.Facility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import Crud.CRUD;
import Services.Setup;

public class Room implements CRUD<Room> {
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

    public int getHotelID() { return this.hotelID; }
    public int getRoomNumber() { return this.roomNumber; }
    public int getFloor() { return this.floor; }
    public double getPrice() { return this.price; }

    @Override
    public int create(Room room) throws SQLException {
        final String create = "INSERT INTO room(hotelID, roomNumber, floor, price) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, room.getHotelID());
        preparedStatement.setInt(2, room.getRoomNumber());
        preparedStatement.setInt(3, room.getFloor());
        preparedStatement.setDouble(4, room.getPrice());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Room read(Room room) throws SQLException {
        final String read = "SELECT * FROM room WHERE roomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, room.getRoomID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Room(rs.getInt("roomID"), rs.getInt("hotelID"),
                    rs.getInt("roomNumber"), rs.getInt("floor"), rs.getDouble("price"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update(Room room) throws SQLException {
        final String update = "UPDATE room SET hotelID = ?, roomNumber = ?, floor = ?, price = ? WHERE roomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, room.getHotelID());
        preparedStatement.setInt(2, room.getRoomNumber());
        preparedStatement.setInt(3, room.getFloor());
        preparedStatement.setDouble(4, room.getPrice());
        preparedStatement.setInt(5, room.getRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Room room) throws SQLException {
        final String delete = "DELETE FROM room WHERE roomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, room.getRoomID());

        return preparedStatement.executeUpdate();
    }
}
