package Hotels;

import Facilities.Facility;
import Rooms.Room;

import java.util.HashMap;
import java.util.Map;

public class Hotel {

    private static int hotelIDGenerator = 0;

    private int hotelID;

    private String hotelName;
    private int numStars;
    private int constructionYear;
    private Map<Integer, Room> rooms;

    public Hotel(String hotelName, int numStars, int constructionYear) {
        ++hotelIDGenerator;

        this.hotelID = hotelIDGenerator;
        this.hotelName = hotelName;
        this.numStars = numStars;
        this.constructionYear = constructionYear;

        this.rooms = new HashMap<Integer, Room>();
    }

    public Hotel(Hotel hotel) {
        this.hotelID = hotel.hotelID;
        this.hotelName = hotel.hotelName;
        this.numStars = hotel.numStars;
        this.constructionYear = hotel.constructionYear;

        this.rooms = new HashMap<Integer, Room>(hotel.rooms);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Hotel ( ID=" + this.hotelID + " HOTEL_NAME=" + this.hotelName + " NUM_STARS=" + this.numStars + " CONSTR_YEAR=" + this.constructionYear + " )\n");

        result.append("Rooms (\n");
        for (Room room : this.rooms.values()) {
            result.append("\t").append(room.toString()).append("\n");
        }

        result.append(")");

        return result.toString();
    }

    public void addRoom(Room room) {
        this.rooms.put(room.getRoomID(), room.clone());
    }

    public String displayRoom(int roomID) {
        if (this.rooms.containsKey(roomID)) {
            return this.rooms.get(roomID).toString();
        }
        else {
            return "ERROR :: The given room ID does not exist!\n";
        }
    }

    @Override
    public int hashCode() {
        return this.hotelID;
    }

    public int getHotelID() { return this.hotelID; }
}
