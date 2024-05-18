package Rooms;

import Services.DatabaseGetter;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TripleRoom extends Room
{
    private int tripleRoomID;
    public TripleRoom(int tripleRoomID) throws SQLException {
        this.tripleRoomID = tripleRoomID;
    }

    public TripleRoom(TripleRoom tripleRoom) {
        super(tripleRoom);
        this.tripleRoomID = tripleRoom.tripleRoomID;
    }

    @Override
    public String toString() { return "TripleRoom ( ID =" + this.tripleRoomID + " )\n"; }

    @Override
    public TripleRoom clone() {
        return new TripleRoom(this);
    }

    public int getTripleRoomID() { return this.tripleRoomID; }

    @Override
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("tripleRoomID=");
        this.tripleRoomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("tripleRoomID=");
        this.tripleRoomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("tripleRoomID=");
        this.tripleRoomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("tripleRoomID=");
        this.tripleRoomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO tripleRoom(tripleRoomID) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.getTripleRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public TripleRoom read() throws SQLException {
        final String read = "SELECT * FROM tripleRoom WHERE tripleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getTripleRoomID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new TripleRoom(rs.getInt("tripleRoomID"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update() throws SQLException {
        final String update = "UPDATE tripleRoom SET tripleRoomID = ? WHERE tripleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, this.getTripleRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM tripleRoom WHERE tripleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getTripleRoomID());

        return preparedStatement.executeUpdate();
    }

    public static List<TripleRoom> readAllTripleRooms() throws SQLException {
        final String readAll = "SELECT * FROM tripleRoom";

        List<TripleRoom> tripleRoomList = new ArrayList<TripleRoom>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            tripleRoomList.add(new TripleRoom(rs.getInt("tripleRoomID")));
        }

        return tripleRoomList;
    }
}
