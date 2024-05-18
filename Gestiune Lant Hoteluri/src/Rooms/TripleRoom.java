package Rooms;

import Services.Database;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TripleRoom extends Room
{
    private int tripleRoomID;
    public TripleRoom(int tripleRoomID) {
        super(Database.get().getRoom(tripleRoomID));

        this.tripleRoomID = tripleRoomID;
    }

    public TripleRoom(TripleRoom tripleRoom) {
        super(tripleRoom);

        this.tripleRoomID = tripleRoom.tripleRoomID;
    }

    @Override
    public String toString() { return "Triple " + super.toString(); }

    @Override
    public TripleRoom clone() {
        return new TripleRoom(this);
    }

    public int getTripleRoomID() { return this.tripleRoomID; }

    @Override
    public int create(Room room) throws SQLException {
        TripleRoom tripleRoom = (TripleRoom) room;
        final String create = "INSERT INTO tripleRoom(tripleRoomID) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, tripleRoom.getTripleRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public TripleRoom read(Room room) throws SQLException {
        TripleRoom tripleRoom = (TripleRoom) room;
        final String read = "SELECT * FROM tripleRoom WHERE tripleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, tripleRoom.getTripleRoomID());

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
    public int update(Room room) throws SQLException {
        TripleRoom tripleRoom = (TripleRoom) room;
        final String update = "UPDATE tripleRoom SET tripleRoomID = ? WHERE tripleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, tripleRoom.getTripleRoomID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Room room) throws SQLException {
        TripleRoom tripleRoom = (TripleRoom) room;
        final String delete = "DELETE FROM tripleRoom WHERE tripleRoomID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, tripleRoom.getTripleRoomID());

        return preparedStatement.executeUpdate();
    }
}
