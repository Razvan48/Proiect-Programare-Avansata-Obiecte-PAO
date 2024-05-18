package Rooms;

import Facilities.Facility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import Crud.CRUD;
import People.Person;
import Services.Setup;

public class Room implements CRUD<Room> {
    protected int roomID;
    protected int hotelID;
    protected int roomNumber;
    protected int floor;
    protected double price;

    public Room() {

    }

    public Room(int roomID, int hotelID, int roomNumber, int floor, double price) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.roomNumber = roomNumber;
        this.floor = floor;
        this.price = price;
    }

    public Room(Room room) {
        this.roomID = room.roomID;
        this.hotelID = room.hotelID;
        this.roomNumber = room.roomNumber;
        this.floor = room.floor;
        this.price = room.price;
    }

    public int getRoomID() { return this.roomID; }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Room ( ID=" + this.roomID + " HOTEL_ID=" + this.hotelID +
                " ROOM_NUM=" + this.roomNumber + " FLOOR=" + this.floor + " PRICE=" + this.price + " )\n");

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
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hotelID=");
        this.hotelID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("roomNumber=");
        this.roomNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("floor=");
        this.floor = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("price=");
        this.price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("roomID=");
        this.roomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("roomID=");
        this.roomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("hotelID=");
        this.hotelID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("roomNumber=");
        this.roomNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("floor=");
        this.floor = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("price=");
        this.price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("roomID=");
        this.roomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO room(hotelID, roomNumber, floor, price) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.getHotelID());
        preparedStatement.setInt(2, this.getRoomNumber());
        preparedStatement.setInt(3, this.getFloor());
        preparedStatement.setDouble(4, this.getPrice());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Room read() throws SQLException {
        final String read = "SELECT * FROM room WHERE roomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getRoomID());

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
    public int update() throws SQLException {
        final String update = "UPDATE room SET hotelID = ?, roomNumber = ?, floor = ?, price = ? WHERE roomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, this.getHotelID());
        preparedStatement.setInt(2, this.getRoomNumber());
        preparedStatement.setInt(3, this.getFloor());
        preparedStatement.setDouble(4, this.getPrice());
        preparedStatement.setInt(5, this.getRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM room WHERE roomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getRoomID());

        return preparedStatement.executeUpdate();
    }

    public static List<Room> readAllRooms() throws SQLException {
        final String readAll = "SELECT * FROM room";

        List<Room> roomList = new ArrayList<Room>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            roomList.add(new Room(rs.getInt("roomID"), rs.getInt("hotelID"),
                    rs.getInt("roomNumber"), rs.getInt("floor"), rs.getDouble("price")));
        }

        return roomList;
    }
}
