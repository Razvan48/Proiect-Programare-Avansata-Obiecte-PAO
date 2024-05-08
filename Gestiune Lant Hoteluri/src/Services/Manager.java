package Services;

import Locations.Location;
import Buildings.Hotel;
import Rooms.Room;

import java.util.Map;
import java.util.TreeMap;

public class Manager {

    private static Manager INSTANCE = null;
    private Manager() {
        this.locations = new TreeMap<Integer, Location>();
    }

    private Map<Integer, Location> locations;

    static public Manager get() {
        if (INSTANCE == null) {
            INSTANCE = new Manager();
        }
        return INSTANCE;
    }

    /*
    public String displayHotels() {
        StringBuilder result = new StringBuilder("");

        for (Hotel hotel : this.hotels.values()) {
            result.append(hotel.toString());
            result.append("\n\n\n");
        }

        return result.toString();
    }
     */

    /*
    public void addHotel(Hotel hotel) {
        this.hotels.put(hotel.getHotelID(), new Hotel(hotel));
    }
     */

    /*
    public void addRoom(int hotelID, Room room) {
        if (this.hotels.containsKey(hotelID)) {
            this.hotels.get(hotelID).addRoom(room);
        }
        else {
            System.out.println("ERROR :: The given hotel ID does not exist!\n");
        }
    }

    public String displayHotel(int hotelID) {
        if (this.hotels.containsKey(hotelID)) {
            return this.hotels.get(hotelID).toString();
        }
        else {
            return "ERROR :: The given hotel ID does not exist!\n";
        }
    }

    public String displayRoom(int hotelID, int roomID) {
        if (this.hotels.containsKey(hotelID)) {
            return this.hotels.get(hotelID).displayRoom(roomID);
        }
        else {
            return "ERROR :: The given hotel ID does not exist!\n";
        }
    }
     */
}
