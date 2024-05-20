package RoomFacility;

import Crud.CRUD;
import Rooms.Room;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomFacility implements CRUD<RoomFacility> {

    private int roomID;
    private int facilityID;

    public RoomFacility(int roomID, int facilityID) {
        this.roomID = roomID;
        this.facilityID = facilityID;
    }

    public RoomFacility(RoomFacility roomFacility) {
        this.roomID = roomFacility.roomID;
        this.facilityID = roomFacility.facilityID;
    }

    public int getRoomID() { return this.roomID; }
    public int getFacilityID() { return this.facilityID; }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("RoomFacility ( ID=" + this.roomID + ", " + this.facilityID + " )\n");

        return result.toString();
    }

    public RoomFacility clone() { return new RoomFacility(this); };

    @Override
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("roomID=");
        this.roomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("facilityID=");
        this.facilityID = scanner.nextInt();
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
        System.out.println("facilityID=");
        this.facilityID = scanner.nextInt();
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
        System.out.println("facilityID=");
        this.facilityID = scanner.nextInt();
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
        System.out.println("facilityID=");
        this.facilityID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO roomFacility(roomID, facilityID) VALUES (?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.getRoomID());
        preparedStatement.setInt(2, this.getFacilityID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public RoomFacility read() throws SQLException {
        final String read = "SELECT * FROM roomFacility WHERE roomID = ? AND facilityID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getRoomID());
        preparedStatement.setInt(2, this.getFacilityID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new RoomFacility(rs.getInt("roomID"), rs.getInt("facilityID"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update() throws SQLException {
        final String update = "UPDATE roomFacility SET roomID = ?, facilityID = ? WHERE roomID = ? AND facilityID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, this.getRoomID());
        preparedStatement.setInt(2, this.getFacilityID());
        preparedStatement.setInt(3, this.getRoomID());
        preparedStatement.setInt(4, this.getFacilityID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM roomFacility WHERE roomID = ? AND facilityID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getRoomID());
        preparedStatement.setInt(2, this.getFacilityID());

        return preparedStatement.executeUpdate();
    }

    public static List<RoomFacility> readAllRoomFacilities() throws SQLException {
        final String readAll = "SELECT * FROM roomFacility";

        List<RoomFacility> roomFacilityList = new ArrayList<RoomFacility>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            roomFacilityList.add(new RoomFacility(rs.getInt("roomID"), rs.getInt("facilityID")));
        }

        return roomFacilityList;
    }
}
