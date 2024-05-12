package Buildings;

import Services.Database;
import People.Employee;
import Rooms.Room;

import java.util.Map;
import java.util.TreeMap;

public class Hotel extends Building {

    private int hotelID;
    private String hotelName;
    private int numStars;
    private Map<Integer, Room> rooms;
    private Map<Integer, Employee> employees;

    public Hotel(int hotelID, String hotelName, int numStars) {
        super(Database.get().getBuilding(hotelID));
        this.hotelName = hotelName;
        this.numStars = numStars;

        this.rooms = new TreeMap<Integer, Room>();
        this.employees = new TreeMap<Integer, Employee>();
    }

    public Hotel(Hotel hotel) {
        super(hotel);

        this.hotelName = hotel.hotelName;
        this.numStars = hotel.numStars;

        this.rooms = new TreeMap<Integer, Room>(hotel.rooms);
        this.employees = new TreeMap<Integer, Employee>(hotel.employees);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Hotel ( ID=" + this.hotelID
                + " CONSTR_YEAR=" + super.constructionYear + " LOCATION_ID=" + super.locationID
                + " HOTEL_NAME=" + this.hotelName + " NUM_STARS="+ this.numStars + " )\n");

        result.append("Rooms (\n");
        for (Room room : this.rooms.values()) {
            result.append("\t").append(room.toString());
        }

        result.append(")\n");

        result.append("Employees (\n");
        for (Employee employee : this.employees.values()) {
            result.append("\t").append(employee.toString());
        }

        result.append(")\n");

        return result.toString();
    }

    public void addRoom(Room room) {
        this.rooms.put(room.getRoomID(), room);
    }
    public void addEmployee(Employee employee) { this.employees.put(employee.getEmployeeID(), employee); };

    public String displayRoom(int roomID) {
        if (this.rooms.containsKey(roomID)) {
            return this.rooms.get(roomID).toString();
        }
        else {
            return "ERROR :: Hotel displayRoom :: room does not exist\n";
        }
    }

    public String displayEmployee(int employeeID) {
        if (this.employees.containsKey(employeeID)) {
            return this.employees.get(employeeID).toString();
        }
        else {
            return "ERROR :: Hotel displayEmployee :: employee does not exist\n";
        }
    }

    @Override
    public Hotel clone() { return new Hotel(this); }
}
