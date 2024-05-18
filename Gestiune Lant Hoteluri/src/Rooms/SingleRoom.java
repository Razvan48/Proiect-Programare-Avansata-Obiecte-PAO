package Rooms;

import People.Client;
import People.Person;
import Services.Database;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingleRoom extends Room
{
    private int singleRoomID;

    public SingleRoom(int singleRoomID) {
        super(Database.get().getRoom(singleRoomID));

        this.singleRoomID = singleRoomID;
    }

    public SingleRoom(SingleRoom singleRoom) {
        super(singleRoom);

        this.singleRoomID = singleRoom.singleRoomID;
    }

    @Override
    public String toString() {
        return "Single " + super.toString();
    }

    @Override
    public SingleRoom clone() {
        return new SingleRoom(this);
    }

    public int getSingleRoomID() { return this.singleRoomID; }

    @Override
    public int create(Room room) throws SQLException {
        SingleRoom singleRoom = (SingleRoom) room;
        final String create = "INSERT INTO singleRoom(singleRoomID) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, singleRoom.getSingleRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public SingleRoom read(Room room) throws SQLException {
        SingleRoom singleRoom = (SingleRoom) room;
        final String read = "SELECT * FROM singleRoom WHERE singleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, singleRoom.getSingleRoomID());

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
    public int update(Room room) throws SQLException {
        SingleRoom singleRoom = (SingleRoom) room;
        final String update = "UPDATE singleRoom SET singleRoomID = ? WHERE singleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, singleRoom.getSingleRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Room room) throws SQLException {
        SingleRoom singleRoom = (SingleRoom) room;
        final String delete = "DELETE FROM singleRoom WHERE singleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, singleRoom.getSingleRoomID());

        return preparedStatement.executeUpdate();
    }
}
