package Rooms;

import Services.DatabaseGetter;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoubleRoom extends Room
{
    private int doubleRoomID;
    public DoubleRoom(int doubleRoomID) throws SQLException {
        super(DatabaseGetter.get().getRoom(doubleRoomID));
        this.doubleRoomID = doubleRoomID;
    }

    public DoubleRoom(DoubleRoom doubleRoom) {
        super(doubleRoom);

        this.doubleRoomID = doubleRoom.doubleRoomID;
    }

    @Override
    public String toString() {
        return "Double " + super.toString();
    }

    @Override
    public DoubleRoom clone() {
        return new DoubleRoom(this);
    }

    public int getDoubleRoomID() { return this.doubleRoomID; }

    @Override
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("doubleRoomID=");
        this.doubleRoomID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("doubleRoomID=");
        this.doubleRoomID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("doubleRoomID=");
        this.doubleRoomID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("doubleRoomID=");
        this.doubleRoomID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO doubleRoom(doubleRoomID) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.getDoubleRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public DoubleRoom read() throws SQLException {
        final String read = "SELECT * FROM doubleRoom WHERE doubleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getDoubleRoomID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new DoubleRoom(rs.getInt("doubleRoomID"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update() throws SQLException {
        final String update = "UPDATE doubleRoom SET doubleRoomID = ? WHERE doubleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, this.getDoubleRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM doubleRoom WHERE doubleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getDoubleRoomID());

        return preparedStatement.executeUpdate();
    }

    public static List<DoubleRoom> readAllDoubleRooms() throws SQLException {
        final String readAll = "SELECT * FROM doubleRoom";

        List<DoubleRoom> doubleRoomList = new ArrayList<DoubleRoom>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            doubleRoomList.add(new DoubleRoom(rs.getInt("doubleRoomID")));
        }

        return doubleRoomList;
    }
}
