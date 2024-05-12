package Services;

import Buildings.Building;
import People.Person;
import Rooms.Room;

import java.util.Map;
import java.util.TreeMap;

public class Database {

    private Map<Integer, Building> buildings;
    private Map<Integer, Person> people;

    private Map<Integer, Room> rooms;

    private static Database INSTANCE = null;
    private Database() {
        this.buildings = new TreeMap<Integer, Building>();
        this.people = new TreeMap<Integer, Person>();
        this.rooms = new TreeMap<Integer, Room>();
    }

    static public Database get() {
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }

    public Building getBuilding(int buildingID) {
        Building result = this.buildings.get(buildingID);
        if (result == null) {
            System.out.println("ERROR :: Database getBuilding :: building does not exist.\n");
        }
        return result;
    }

    public void addBuilding(Building building) {
        this.buildings.put(building.getBuildingID(), building);
    }

    public Person getPerson(int personID) {
        Person result = this.people.get(personID);
        if (result == null) {
            System.out.println("ERROR :: Database getPerson :: person does not exist.\n");
        }
        return result;
    }

    public void addPerson(Person person) {
        this.people.put(person.getPersonID(), person);
    }

    public Room getRoom(int roomID) {
        Room result = this.rooms.get(roomID);
        if (result == null) {
            System.out.println("ERROR :: Database getRoom :: room does not exist.\n");
        }
        return result;
    }

    public void addRoom(Room room) {
        this.rooms.put(room.getRoomID(), room);
    }
}
