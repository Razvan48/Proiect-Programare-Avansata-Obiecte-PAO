package Services;

import Buildings.Building;
import People.Person;
import Rooms.Room;

import java.sql.SQLException;

public class DatabaseGetter {

    private static DatabaseGetter INSTANCE = null;
    private DatabaseGetter() {}

    public static DatabaseGetter get() {
        if (DatabaseGetter.INSTANCE == null) {
            DatabaseGetter.INSTANCE = new DatabaseGetter();
        }
        return DatabaseGetter.INSTANCE;
    }

    public Building getBuilding(int buildingID) throws SQLException {
        return new Building(buildingID, 0, 0).read();
    }

    public Person getPerson(int personID) throws SQLException {
        return new Person(personID, "", "", "", "").read();
    }

    public Room getRoom(int roomID) throws SQLException {
        return new Room(roomID, 0, 0, 0, 0.0).read();
    }
}
