package Services;

import Buildings.Building;
import Buildings.Hotel;
import Buildings.Restaurant;
import Facilities.Facility;
import Locations.Location;
import People.Client;
import People.Employee;
import People.Person;
import Rooms.DoubleRoom;
import Rooms.Room;
import Rooms.SingleRoom;
import Rooms.TripleRoom;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static MainMenu INSTANCE = null;
    private MainMenu() {
        this.isRunning = true;
    }

    private boolean isRunning;

    public static MainMenu get() {
        if (MainMenu.INSTANCE == null) {
            MainMenu.INSTANCE = new MainMenu();
        }
        return MainMenu.INSTANCE;
    }

    public void run() throws SQLException {
        while (this.isRunning) {
            this.oneCommand();
        }
    }

    private void oneCommand() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("0 EXIT");
        System.out.println("1 building CRUD");
        System.out.println("2 hotel CRUD");
        System.out.println("3 restaurant CRUD");
        System.out.println("4 facility CRUD");
        System.out.println("5 location CRUD");
        System.out.println("6 client CRUD");
        System.out.println("7 employee CRUD");
        System.out.println("8 person CRUD");
        System.out.println("9 doubleRoom CRUD");
        System.out.println("10 room CRUD");
        System.out.println("11 singleRoom CRUD");
        System.out.println("12 tripleRoom CRUD");
        System.out.println("13 more complex queries");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int command = scanner.nextInt();
        while (command <= -1 || 14 <= command) {
            System.out.println("\n");
            command = scanner.nextInt();
        }
        if (command == 0)
        {
            this.isRunning = false;
            return;
        }


        System.out.println("\n\n\n\n\n\n\n\n\n");


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        switch (command) {
            case 1:
                System.out.println("buildingCRUD");
                break;
            case 2:
                System.out.println("hotelCRUD");
                break;
            case 3:
                System.out.println("restaurantCRUD");
                break;
            case 4:
                System.out.println("facilityCRUD");
                break;
            case 5:
                System.out.println("locationCRUD");
                break;
            case 6:
                System.out.println("clientCRUD");
                break;
            case 7:
                System.out.println("employeeCRUD");
                break;
            case 8:
                System.out.println("personCRUD");
                break;
            case 9:
                System.out.println("doubleRoomCRUD");
                break;
            case 10:
                System.out.println("roomCRUD");
                break;
            case 11:
                System.out.println("singleRoomCRUD");
                break;
            case 12:
                System.out.println("tripleRoomCRUD");
                break;
            case 13:
                System.out.println("complex queries");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                this.complexQueries();
                return;
            default:
                break;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("0 create");
        System.out.println("1 read");
        System.out.println("2 update");
        System.out.println("3 delete");
        System.out.println("4 readAll");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int specificCRUDcommand = scanner.nextInt();
        while (specificCRUDcommand <= -1 || 5 <= specificCRUDcommand) {
            System.out.println("\n");
            specificCRUDcommand = scanner.nextInt();
        }


        System.out.println("\n\n\n\n\n\n\n\n\n");


        switch (command) {
            case 1:
                this.buildingCRUD(specificCRUDcommand);
                break;
            case 2:
                this.hotelCRUD(specificCRUDcommand);
                break;
            case 3:
                this.restaurantCRUD(specificCRUDcommand);
                break;
            case 4:
                this.facilityCRUD(specificCRUDcommand);
                break;
            case 5:
                this.locationCRUD(specificCRUDcommand);
                break;
            case 6:
                this.clientCRUD(specificCRUDcommand);
                break;
            case 7:
                this.employeeCRUD(specificCRUDcommand);
                break;
            case 8:
                this.personCRUD(specificCRUDcommand);
                break;
            case 9:
                this.doubleRoomCRUD(specificCRUDcommand);
                break;
            case 10:
                this.roomCRUD(specificCRUDcommand);
                break;
            case 11:
                this.singleRoomCRUD(specificCRUDcommand);
                break;
            case 12:
                this.tripleRoomCRUD(specificCRUDcommand);
                break;
            default:
                break;
        }
    }

    private void buildingCRUD(int command) throws SQLException {
        Building building = new Building(0, 0, 0);
        switch(command) {
            case 0:
                building.inputForCreate();
                building.create();
                break;
            case 1:
                building.inputForRead();
                building.read();
                System.out.println(building);
            case 2:
                building.inputForUpdate();
                building.update();
            case 3:
                building.inputForDelete();
                building.delete();
            case 4:
                List<Building> buildingList = Building.readAllBuildings();
                for (Building b : buildingList) {
                    System.out.println(b);
                }
        }
    }
    private void hotelCRUD(int command) throws SQLException {
        Hotel hotel = new Hotel(0, "", 0);
        switch(command) {
            case 0:
                hotel.inputForCreate();
                hotel.create();
                break;
            case 1:
                hotel.inputForRead();
                hotel.read();
                System.out.println(hotel);
            case 2:
                hotel.inputForUpdate();
                hotel.update();
            case 3:
                hotel.inputForDelete();
                hotel.delete();
            case 4:
                List<Hotel> hotelList = Hotel.readAllHotels();
                for (Hotel h : hotelList) {
                    System.out.println(h);
                }
        }
    }
    private void restaurantCRUD(int command) throws SQLException {
        Restaurant restaurant = new Restaurant(0, "", 0);
        switch(command) {
            case 0:
                restaurant.inputForCreate();
                restaurant.create();
                break;
            case 1:
                restaurant.inputForRead();
                restaurant.read();
                System.out.println(restaurant);
            case 2:
                restaurant.inputForUpdate();
                restaurant.update();
            case 3:
                restaurant.inputForDelete();
                restaurant.delete();
            case 4:
                List<Restaurant> restaurantList = Restaurant.readAllRestaurants();
                for (Restaurant r : restaurantList) {
                    System.out.println(r);
                }
        }
    }
    private void facilityCRUD(int command) throws SQLException {
        Facility facility = new Facility(0, "");
        switch(command) {
            case 0:
                facility.inputForCreate();
                facility.create();
                break;
            case 1:
                facility.inputForRead();
                facility.read();
                System.out.println(facility);
            case 2:
                facility.inputForUpdate();
                facility.update();
            case 3:
                facility.inputForDelete();
                facility.delete();
            case 4:
                List<Facility> facilityList = Facility.readAllFacilities();
                for (Facility f : facilityList) {
                    System.out.println(f);
                }
        }
    }
    private void locationCRUD(int command) throws SQLException {
        Location location = new Location(0, "");
        switch(command) {
            case 0:
                location.inputForCreate();
                location.create();
                break;
            case 1:
                location.inputForRead();
                location.read();
                System.out.println(location);
            case 2:
                location.inputForUpdate();
                location.update();
            case 3:
                location.inputForDelete();
                location.delete();
            case 4:
                List<Location> locationList = Location.readAllLocations();
                for (Location l : locationList) {
                    System.out.println(l);
                }
        }
    }
    private void clientCRUD(int command) throws SQLException {
        Client client = new Client(0);
        switch(command) {
            case 0:
                client.inputForCreate();
                client.create();
                break;
            case 1:
                client.inputForRead();
                client.read();
                System.out.println(client);
            case 2:
                client.inputForUpdate();
                client.update();
            case 3:
                client.inputForDelete();
                client.delete();
            case 4:
                List<Client> clientList = Client.readAllClients();
                for (Client c : clientList) {
                    System.out.println(c);
                }
        }
    }
    private void employeeCRUD(int command) throws SQLException {
        Employee employee = new Employee(0, 0.0, 0, "");
        switch(command) {
            case 0:
                employee.inputForCreate();
                employee.create();
                break;
            case 1:
                employee.inputForRead();
                employee.read();
                System.out.println(employee);
            case 2:
                employee.inputForUpdate();
                employee.update();
            case 3:
                employee.inputForDelete();
                employee.delete();
            case 4:
                List<Employee> employeeList = Employee.readAllEmployees();
                for (Employee e : employeeList) {
                    System.out.println(e);
                }
        }
    }
    private void personCRUD(int command) throws SQLException {
        Person person = new Person(0, "", "", "", "");
        switch(command) {
            case 0:
                person.inputForCreate();
                person.create();
                break;
            case 1:
                person.inputForRead();
                person.read();
                System.out.println(person);
            case 2:
                person.inputForUpdate();
                person.update();
            case 3:
                person.inputForDelete();
                person.delete();
            case 4:
                List<Person> personList = Person.readAllPeople();
                for (Person p : personList) {
                    System.out.println(p);
                }
        }
    }
    private void doubleRoomCRUD(int command) throws SQLException {
        DoubleRoom doubleRoom = new DoubleRoom(0);
        switch(command) {
            case 0:
                doubleRoom.inputForCreate();
                doubleRoom.create();
                break;
            case 1:
                doubleRoom.inputForRead();
                doubleRoom.read();
                System.out.println(doubleRoom);
            case 2:
                doubleRoom.inputForUpdate();
                doubleRoom.update();
            case 3:
                doubleRoom.inputForDelete();
                doubleRoom.delete();
            case 4:
                List<DoubleRoom> doubleRoomList = DoubleRoom.readAllDoubleRooms();
                for (DoubleRoom dr : doubleRoomList) {
                    System.out.println(dr);
                }
        }
    }
    private void roomCRUD(int command) throws SQLException {
        Room room = new Room(0, 0, 0, 0, 0.0);
        switch(command) {
            case 0:
                room.inputForCreate();
                room.create();
                break;
            case 1:
                room.inputForRead();
                room.read();
                System.out.println(room);
            case 2:
                room.inputForUpdate();
                room.update();
            case 3:
                room.inputForDelete();
                room.delete();
            case 4:
                List<Room> roomList = Room.readAllRooms();
                for (Room r : roomList) {
                    System.out.println(r);
                }
        }
    }
    private void singleRoomCRUD(int command) throws SQLException {
        SingleRoom singleRoom = new SingleRoom(0);
        switch(command) {
            case 0:
                singleRoom.inputForCreate();
                singleRoom.create();
                break;
            case 1:
                singleRoom.inputForRead();
                singleRoom.read();
                System.out.println(singleRoom);
            case 2:
                singleRoom.inputForUpdate();
                singleRoom.update();
            case 3:
                singleRoom.inputForDelete();
                singleRoom.delete();
            case 4:
                List<SingleRoom> singleRoomList = SingleRoom.readAllSingleRooms();
                for (SingleRoom sr : singleRoomList) {
                    System.out.println(sr);
                }
        }
    }
    private void tripleRoomCRUD(int command) throws SQLException {
        TripleRoom tripleRoom = new TripleRoom(0);
        switch(command) {
            case 0:
                tripleRoom.inputForCreate();
                tripleRoom.create();
                break;
            case 1:
                tripleRoom.inputForRead();
                tripleRoom.read();
                System.out.println(tripleRoom);
            case 2:
                tripleRoom.inputForUpdate();
                tripleRoom.update();
            case 3:
                tripleRoom.inputForDelete();
                tripleRoom.delete();
            case 4:
                List<TripleRoom> tripleRoomList = TripleRoom.readAllTripleRooms();
                for (TripleRoom tr : tripleRoomList) {
                    System.out.println(tr);
                }
        }
    }

    private void complexQueries() {
        // TODO:
    }
}
