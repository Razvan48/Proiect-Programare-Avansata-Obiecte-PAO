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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private static MainMenu INSTANCE = null;

    private final String logPath;
    private int logPadding;

    private MainMenu() {
        this.isRunning = true;
        this.logPath = "Gestiune Lant Hoteluri/log/log.txt";
        this.logPadding = 100;
    }

    private boolean isRunning;

    public static MainMenu get() {
        if (MainMenu.INSTANCE == null) {
            MainMenu.INSTANCE = new MainMenu();
        }
        return MainMenu.INSTANCE;
    }

    private boolean isFileEmpty() {
        File file = new File(this.logPath);
        return file.length() == 0;
    }

    private void writeLog(String command) {
        try {
            FileWriter out = new FileWriter(this.logPath, true);
            if (this.isFileEmpty()) {
                String commandFormat = String.format("%-" + this.logPadding + "s", "Command");
                String timeWhenFormat = String.format("%-" + this.logPadding + "s", "TimeWhen (ss:mm:hh DD:MM:YYYY)" + "\n");
                out.write(commandFormat + " | " + timeWhenFormat + "\n\n\n");
            }
            String commandFormat = String.format("%-" + this.logPadding + "s", command);
            LocalDateTime now = LocalDateTime.now();
            String timeWhenFormat = String.format("%-" + this.logPadding + "s", now.getHour() + ":" + now.getMinute() + ":" + now.getSecond() + " " + now.getDayOfMonth() + "/" + now.getMonthValue() + "/" + now.getYear());
            out.write(commandFormat + " | " + timeWhenFormat + "\n");
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (this.isRunning) {
            this.oneCommand();
        }
    }

    private void oneCommand() {
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


        System.out.println("\n\n\n");


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


        System.out.println("\n\n\n");


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

    private void buildingCRUD(int command) {
        try {
            Building building = new Building(0, 0, 0);
            switch (command) {
                case 0:
                    building.inputForCreate();
                    building.create();
                    this.writeLog("Building Create");
                    break;
                case 1:
                    building.inputForRead();
                    building = building.read();
                    System.out.println(building);
                    this.writeLog("Building Read");
                    break;
                case 2:
                    building.inputForUpdate();
                    building.update();
                    this.writeLog("Building Update");
                    break;
                case 3:
                    building.inputForDelete();
                    building.delete();
                    this.writeLog("Building Delete");
                    break;
                case 4:
                    List<Building> buildingList = Building.readAllBuildings();
                    for (Building b : buildingList) {
                        System.out.print(b);
                    }
                    this.writeLog("Building ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void hotelCRUD(int command) {
        try {
            Hotel hotel = new Hotel(0, "", 0);
            switch (command) {
                case 0:
                    hotel.inputForCreate();
                    hotel.create();
                    this.writeLog("Hotel Create");
                    break;
                case 1:
                    hotel.inputForRead();
                    hotel = hotel.read();
                    System.out.println(hotel);
                    this.writeLog("Hotel Read");
                    break;
                case 2:
                    hotel.inputForUpdate();
                    hotel.update();
                    this.writeLog("Hotel Update");
                    break;
                case 3:
                    hotel.inputForDelete();
                    hotel.delete();
                    this.writeLog("Hotel Delete");
                    break;
                case 4:
                    List<Hotel> hotelList = Hotel.readAllHotels();
                    for (Hotel h : hotelList) {
                        System.out.print(h);
                    }
                    this.writeLog("Hotel ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void restaurantCRUD(int command) {
        try {
            Restaurant restaurant = new Restaurant(0, "", 0);
            switch (command) {
                case 0:
                    restaurant.inputForCreate();
                    restaurant.create();
                    this.writeLog("Restaurant Create");
                    break;
                case 1:
                    restaurant.inputForRead();
                    restaurant = restaurant.read();
                    System.out.println(restaurant);
                    this.writeLog("Restaurant Read");
                    break;
                case 2:
                    restaurant.inputForUpdate();
                    restaurant.update();
                    this.writeLog("Restaurant Update");
                    break;
                case 3:
                    restaurant.inputForDelete();
                    restaurant.delete();
                    this.writeLog("Restaurant Delete");
                    break;
                case 4:
                    List<Restaurant> restaurantList = Restaurant.readAllRestaurants();
                    for (Restaurant r : restaurantList) {
                        System.out.print(r);
                    }
                    this.writeLog("Restaurant ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void facilityCRUD(int command) {
        try {
            Facility facility = new Facility(0, "");
            switch (command) {
                case 0:
                    facility.inputForCreate();
                    facility.create();
                    this.writeLog("Facility Create");
                    break;
                case 1:
                    facility.inputForRead();
                    facility = facility.read();
                    System.out.println(facility);
                    this.writeLog("Facility Read");
                    break;
                case 2:
                    facility.inputForUpdate();
                    facility.update();
                    this.writeLog("Facility Update");
                    break;
                case 3:
                    facility.inputForDelete();
                    facility.delete();
                    this.writeLog("Facility Delete");
                    break;
                case 4:
                    List<Facility> facilityList = Facility.readAllFacilities();
                    for (Facility f : facilityList) {
                        System.out.print(f);
                    }
                    this.writeLog("Facility ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void locationCRUD(int command) {
        try {
            Location location = new Location(0, "");
            switch (command) {
                case 0:
                    location.inputForCreate();
                    location.create();
                    this.writeLog("Location Create");
                    break;
                case 1:
                    location.inputForRead();
                    location = location.read();
                    System.out.println(location);
                    this.writeLog("Location Read");
                    break;
                case 2:
                    location.inputForUpdate();
                    location.update();
                    this.writeLog("Location Update");
                    break;
                case 3:
                    location.inputForDelete();
                    location.delete();
                    this.writeLog("Location Delete");
                    break;
                case 4:
                    List<Location> locationList = Location.readAllLocations();
                    for (Location l : locationList) {
                        System.out.print(l);
                    }
                    this.writeLog("Location ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void clientCRUD(int command) {
        try {
            Client client = new Client(0);
            switch (command) {
                case 0:
                    client.inputForCreate();
                    client.create();
                    this.writeLog("Client Create");
                    break;
                case 1:
                    client.inputForRead();
                    client = client.read();
                    System.out.println(client);
                    this.writeLog("Client Read");
                    break;
                case 2:
                    client.inputForUpdate();
                    client.update();
                    this.writeLog("Client Update");
                    break;
                case 3:
                    client.inputForDelete();
                    client.delete();
                    this.writeLog("Client Delete");
                    break;
                case 4:
                    List<Client> clientList = Client.readAllClients();
                    for (Client c : clientList) {
                        System.out.print(c);
                    }
                    this.writeLog("Client ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void employeeCRUD(int command) {
        try {
            Employee employee = new Employee(0, 0.0, 0, "");
            switch (command) {
                case 0:
                    employee.inputForCreate();
                    employee.create();
                    this.writeLog("Employee Create");
                    break;
                case 1:
                    employee.inputForRead();
                    employee = employee.read();
                    System.out.println(employee);
                    this.writeLog("Employee Read");
                    break;
                case 2:
                    employee.inputForUpdate();
                    employee.update();
                    this.writeLog("Employee Update");
                    break;
                case 3:
                    employee.inputForDelete();
                    employee.delete();
                    this.writeLog("Employee Delete");
                    break;
                case 4:
                    List<Employee> employeeList = Employee.readAllEmployees();
                    for (Employee e : employeeList) {
                        System.out.print(e);
                    }
                    this.writeLog("Employee ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void personCRUD(int command) {
        try {
            Person person = new Person(0, "", "", "", "");
            switch (command) {
                case 0:
                    person.inputForCreate();
                    person.create();
                    this.writeLog("Person Create");
                    break;
                case 1:
                    person.inputForRead();
                    person = person.read();
                    System.out.println(person);
                    this.writeLog("Person Read");
                    break;
                case 2:
                    person.inputForUpdate();
                    person.update();
                    this.writeLog("Person Update");
                    break;
                case 3:
                    person.inputForDelete();
                    person.delete();
                    this.writeLog("Person Delete");
                    break;
                case 4:
                    List<Person> personList = Person.readAllPeople();
                    for (Person p : personList) {
                        System.out.print(p);
                    }
                    this.writeLog("Person ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void doubleRoomCRUD(int command) {
        try {
            DoubleRoom doubleRoom = new DoubleRoom(0);
            switch (command) {
                case 0:
                    doubleRoom.inputForCreate();
                    doubleRoom.create();
                    this.writeLog("DoubleRoom Create");
                    break;
                case 1:
                    doubleRoom.inputForRead();
                    doubleRoom = doubleRoom.read();
                    System.out.println(doubleRoom);
                    this.writeLog("DoubleRoom Read");
                    break;
                case 2:
                    doubleRoom.inputForUpdate();
                    doubleRoom.update();
                    this.writeLog("DoubleRoom Update");
                    break;
                case 3:
                    doubleRoom.inputForDelete();
                    doubleRoom.delete();
                    this.writeLog("DoubleRoom Delete");
                    break;
                case 4:
                    List<DoubleRoom> doubleRoomList = DoubleRoom.readAllDoubleRooms();
                    for (DoubleRoom dr : doubleRoomList) {
                        System.out.print(dr);
                    }
                    this.writeLog("DoubleRoom ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void roomCRUD(int command) {
        try {
            Room room = new Room(0, 0, 0, 0, 0.0);
            switch (command) {
                case 0:
                    room.inputForCreate();
                    room.create();
                    this.writeLog("Room Create");
                    break;
                case 1:
                    room.inputForRead();
                    room = room.read();
                    System.out.println(room);
                    this.writeLog("Room Read");
                    break;
                case 2:
                    room.inputForUpdate();
                    room.update();
                    this.writeLog("Room Update");
                    break;
                case 3:
                    room.inputForDelete();
                    room.delete();
                    this.writeLog("Room Delete");
                    break;
                case 4:
                    List<Room> roomList = Room.readAllRooms();
                    for (Room r : roomList) {
                        System.out.print(r);
                    }
                    this.writeLog("Room ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void singleRoomCRUD(int command) {
        try {
            SingleRoom singleRoom = new SingleRoom(0);
            switch (command) {
                case 0:
                    singleRoom.inputForCreate();
                    singleRoom.create();
                    this.writeLog("SingleRoom Create");
                    break;
                case 1:
                    singleRoom.inputForRead();
                    singleRoom = singleRoom.read();
                    System.out.println(singleRoom);
                    this.writeLog("SingleRoom Read");
                    break;
                case 2:
                    singleRoom.inputForUpdate();
                    singleRoom.update();
                    this.writeLog("SingleRoom Update");
                    break;
                case 3:
                    singleRoom.inputForDelete();
                    singleRoom.delete();
                    this.writeLog("SingleRoom Delete");
                    break;
                case 4:
                    List<SingleRoom> singleRoomList = SingleRoom.readAllSingleRooms();
                    for (SingleRoom sr : singleRoomList) {
                        System.out.print(sr);
                    }
                    this.writeLog("SingleRoom ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }
    private void tripleRoomCRUD(int command) {
        try {
            TripleRoom tripleRoom = new TripleRoom(0);
            switch (command) {
                case 0:
                    tripleRoom.inputForCreate();
                    tripleRoom.create();
                    this.writeLog("TripleRoom Create");
                    break;
                case 1:
                    tripleRoom.inputForRead();
                    tripleRoom = tripleRoom.read();
                    System.out.println(tripleRoom);
                    this.writeLog("TripleRoom Read");
                    break;
                case 2:
                    tripleRoom.inputForUpdate();
                    tripleRoom.update();
                    this.writeLog("TripleRoom Update");
                    break;
                case 3:
                    tripleRoom.inputForDelete();
                    tripleRoom.delete();
                    this.writeLog("TripleRoom Delete");
                    break;
                case 4:
                    List<TripleRoom> tripleRoomList = TripleRoom.readAllTripleRooms();
                    for (TripleRoom tr : tripleRoomList) {
                        System.out.print(tr);
                    }
                    this.writeLog("TripleRoom ReadAll");
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void complexQueries() {
        try
        {
            // TODO:
            throw new SQLException();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        /*
        TODO: posibile query-uri complexe
        1. sa se afiseze toate hotelurile dintr-o locatie data cu mai mult de nr stele date
        2. sa se mareasca salariul tuturor angajatilor dintr-un hotel dat cu un anumit procent
        3. sa se concedieze toti angajati dintr-un anumit hotel care au salariul peste o valoare data
        4. sa se afiseze toti angajatii din toate hotelurile in ordine descrescatoare dupa salariu (union, ca un angajat poate lucra in mai multe locuri)
        5. sa se adauge un nou angajat la o locatie si un hotel specificat
        6. sa se adauge un nou hotel la o locatie specificata
        7. sa se adauge o locatie specificata
        8. sa se afiseze toti angajatii cu peste x salariu sortati crescator dupa primul prenume, descrescator dupa al doilea, crescator dupa ultimul
        9. sa se stearga un angajat
        10. sa se stearga un hotel (stergere in cascada)
        11. sa se afiseze pt un hotel dintr-o locatie data numarul de camere single/double/triple pe care le are
        12. sa se adauge la un hotel o camera singla
        13. sa se adauge la un hotel o camera dubla
        14. sa se adauge la un hotel o camera tripla
        15. sa se stearga o camera dintr-un hotel
         */
    }
}
