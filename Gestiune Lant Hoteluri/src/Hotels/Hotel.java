package Hotels;

import Facilities.Facility;
import Rooms.Room;

import java.util.HashSet;

public class Hotel {

    static private int hotelIDGenerator = 0;

    private int hotelID;

    private String hotelName;
    private int numStars;
    private int constructionYear;
    private HashSet<Room> rooms;

    Hotel(String hotelName, int numStars, int constructionYear) {
        ++hotelIDGenerator;

        this.hotelID = hotelIDGenerator;
        this.hotelName = hotelName;
        this.numStars = numStars;
        this.constructionYear = constructionYear;

        this.rooms = new HashSet<Room>();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Hotel ( ID=" + this.hotelID + " HOTEL_NAME=" + this.hotelName + " NUM_STARS=" + this.numStars + " CONSTR_YEAR=" + this.constructionYear + " )\n");

        result.append("Rooms (\n");
        for (Room room : this.rooms) {
            result.append("\t").append(room.toString()).append("\n");
        }

        result.append(")");

        return result.toString();
    }

    public void addRoom(Room room) {
        this.rooms.add(room.clone());
    }
}
