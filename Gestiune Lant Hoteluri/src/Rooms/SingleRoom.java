package Rooms;

import People.Client;
import People.Person;
import Services.DatabaseGetter;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SingleRoom extends Room
{
    private int singleRoomID;

    public SingleRoom(int singleRoomID) throws SQLException {
        this.singleRoomID = singleRoomID;
    }

    public SingleRoom(SingleRoom singleRoom) {
        super(singleRoom);

        this.singleRoomID = singleRoom.singleRoomID;
    }

    @Override
    public String toString() {
        return "SingleRoom ( ID =" + this.singleRoomID + " )\n";
    }

    @Override
    public SingleRoom clone() {
        return new SingleRoom(this);
    }

    public int getSingleRoomID() { return this.singleRoomID; }

    @Override
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("singleRoomID=");
        this.singleRoomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("singleRoomID=");
        this.singleRoomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("singleRoomID=");
        this.singleRoomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("singleRoomID=");
        this.singleRoomID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO singleRoom(singleRoomID) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.getSingleRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public SingleRoom read() throws SQLException {
        final String read = "SELECT * FROM singleRoom WHERE singleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getSingleRoomID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new SingleRoom(rs.getInt("singleRoomID"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update() throws SQLException {
        final String update = "UPDATE singleRoom SET singleRoomID = ? WHERE singleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, this.getSingleRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM singleRoom WHERE singleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getSingleRoomID());

        return preparedStatement.executeUpdate();
    }

    public static List<SingleRoom> readAllSingleRooms() throws SQLException {
        final String readAll = "SELECT * FROM singleRoom";

        List<SingleRoom> singleRoomList = new ArrayList<SingleRoom>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            singleRoomList.add(new SingleRoom(rs.getInt("singleRoomID")));
        }

        return singleRoomList;
    }
}
