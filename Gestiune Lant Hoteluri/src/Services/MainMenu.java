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
import RoomFacility.RoomFacility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class MainMenu {
    private static MainMenu INSTANCE = null;

    private final String logPath;
    private final int employeePadding;
    private final String employeePath;
    private final int logPadding;

    private MainMenu() {
        this.isRunning = true;
        this.logPath = "Gestiune Lant Hoteluri/log/log.csv";
        this.employeePath = "Gestiune Lant Hoteluri/employee/employee.csv";
        this.logPadding = 100;
        this.employeePadding = 50;
    }

    private boolean isRunning;

    public static MainMenu get() {
        if (MainMenu.INSTANCE == null) {
            MainMenu.INSTANCE = new MainMenu();
        }
        return MainMenu.INSTANCE;
    }

    private boolean isLogFileEmpty() {
        File file = new File(this.logPath);
        return file.length() == 0;
    }

    private boolean isEmployeeFileEmpty() {
        File file = new File(this.employeePath);
        return file.length() == 0;
    }

    private void writeLog(String command) {
        try(FileWriter out = new FileWriter(this.logPath, true)) {
            /*
            if (this.isLogFileEmpty()) {
                String commandFormat = String.format("%-" + this.logPadding + "s", "Command");
                String timeWhenFormat = String.format("%-" + this.logPadding + "s", "TimeWhen (hh:mm:ss DD/MM/YYYY)" + "\n");
                out.write(commandFormat + " | " + timeWhenFormat + "\n\n\n");
            }
            String commandFormat = String.format("%-" + this.logPadding + "s", command);
            LocalDateTime now = LocalDateTime.now();
            String timeWhenFormat = String.format("%-" + this.logPadding + "s", now.getHour() + ":" + now.getMinute() + ":" + now.getSecond() + " " + now.getDayOfMonth() + "/" + now.getMonthValue() + "/" + now.getYear());
            out.write(commandFormat + " | " + timeWhenFormat + "\n");
             */
            if (this.isLogFileEmpty()) {
                out.write("Command,TimeWhen(hh:mm:ss DD/MM/YYYY)\n");
            }
            LocalDateTime now = LocalDateTime.now();
            String timeWhen = String.format("%02d:%02d:%02d %02d/%02d/%d", now.getHour(), now.getMinute(), now.getSecond(), now.getDayOfMonth(), now.getMonthValue(), now.getYear());
            out.write(command + "," + timeWhen + "\n");
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
        System.out.println("13 roomFacility CRUD");
        System.out.println("14 more complex queries");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int command = scanner.nextInt();
        while (command <= -1 || 15 <= command) {
            System.out.println("\n");
            command = scanner.nextInt();
        }
        if (command == 0)
        {
            this.isRunning = false;
            this.writeLog("EXIT");
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
                System.out.println("roomFacilityCRUD");
                break;
            case 14:
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
            case 13:
                this.roomFacilityCRUD(specificCRUDcommand);
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

    private void roomFacilityCRUD(int command) {
        try {
            RoomFacility roomFacility = new RoomFacility(0, 0);
            switch (command) {
                case 0:
                    roomFacility.inputForCreate();
                    roomFacility.create();
                    this.writeLog("RoomFacility Create");
                    break;
                case 1:
                    roomFacility.inputForRead();
                    roomFacility = roomFacility.read();
                    System.out.println(roomFacility);
                    this.writeLog("RoomFacility Read");
                    break;
                case 2:
                    roomFacility.inputForUpdate();
                    roomFacility.update();
                    this.writeLog("RoomFacility Update");
                    break;
                case 3:
                    roomFacility.inputForDelete();
                    roomFacility.delete();
                    this.writeLog("RoomFacility Delete");
                    break;
                case 4:
                    List<RoomFacility> roomFacilityList = RoomFacility.readAllRoomFacilities();
                    for (RoomFacility rf : roomFacilityList) {
                        System.out.print(rf);
                    }
                    this.writeLog("RoomFacility ReadAll");
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
            Scanner scanner = new Scanner(System.in);

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("0 show hotels in ascending order of hotelName from a specific location (locationID) with a number of stars greater than (num_stars)");
            System.out.println("1 for a given hotel(hotelID) display the number of single/double/triple rooms of that hotel");
            System.out.println("2 group all employees by their salary and write in employee.csv file how many employees have a specific salary");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            int command = scanner.nextInt();
            while (command <= -1 || 3 <= command) {
                System.out.println("\n");
                command = scanner.nextInt();
            }

            System.out.println("\n\n\n");

            switch(command) {
                case 0:
                    this.complexQuery0();
                    break;
                case 1:
                    this.complexQuery1();
                    break;
                case 2:
                    this.complexQuery2();
                    break;
                default:
                    break;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void complexQuery0() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("locationID=");
        int locationID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("numStars=");
        int numStars = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");

        final String query = "SELECT hotel.hotelID, hotel.hotelName, hotel.numStars FROM hotel " +
                "JOIN building ON hotel.hotelID = building.buildingID JOIN location " +
                "ON building.locationID = location.locationID WHERE building.locationID = ? AND hotel.numStars > ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(query);
        preparedStatement.setInt(1, locationID);
        preparedStatement.setInt(2, numStars);

        ResultSet rs = preparedStatement.executeQuery();

        List<Hotel> hotelList = new ArrayList<Hotel>();

        while (rs.next()) {
            hotelList.add(new Hotel(rs.getInt("hotelID"), rs.getString("hotelName"), rs.getInt("numStars")));
        }

        /*
        Comparator<Hotel> hotelComparator = new Comparator<Hotel>() {
            @Override
            public int compare(Hotel a, Hotel b) {
                return a.getHotelName().compareTo(b.getHotelName());
            }
        };
         */

        hotelList.sort((a, b) -> a.getHotelName().compareTo(b.getHotelName()));

        for (Hotel h : hotelList) {
            System.out.println(h);
        }

        this.writeLog("Complex Query 0 with locationID=" + locationID + " and numStars=" + numStars);
    }

    private void complexQuery1() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hotelID=");
        int hotelID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");

        final String query = "SELECT " +
                "(SELECT COUNT(*) FROM room JOIN singleRoom ON room.roomID = singleRoom.singleRoomID WHERE room.hotelID = ?) AS cnt1, " +
                "(SELECT COUNT(*) FROM room JOIN doubleRoom ON room.roomID = doubleRoom.doubleRoomID WHERE room.hotelID = ?) AS cnt2, " +
                "(SELECT COUNT(*) FROM room JOIN tripleRoom ON room.roomID = tripleRoom.tripleRoomID WHERE room.hotelID = ?) AS cnt3";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(query);
        preparedStatement.setInt(1, hotelID);
        preparedStatement.setInt(2, hotelID);
        preparedStatement.setInt(3, hotelID);

        ResultSet rs = preparedStatement.executeQuery();

        int numSingleRoom = -1;
        int numDoubleRoom = -1;
        int numTripleRoom = -1;

        if (rs.next()) {
            numSingleRoom = rs.getInt("cnt1");
            numDoubleRoom = rs.getInt("cnt2");
            numTripleRoom = rs.getInt("cnt3");
        }

        System.out.println("Single Count=" + numSingleRoom + " Double Count=" + numDoubleRoom + " Triple Count=" + numTripleRoom);

        this.writeLog("Complex Query 1 with hotelID=" + hotelID);
    }

    private void complexQuery2() throws SQLException {
        final String query = "SELECT employee.employeeID AS ID, employee.monthlySalary AS monthlySalary FROM employee JOIN person " +
                "ON employee.employeeID = person.personID";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(query);

        ResultSet rs = preparedStatement.executeQuery();

        Map<Double, Integer> salaryFrequency = new HashMap<Double, Integer>();

        while (rs.next()) {
            int employeeID = rs.getInt("ID");
            double salary = rs.getDouble("monthlySalary");
            salaryFrequency.put(salary, salaryFrequency.getOrDefault(salary, 0) + 1);
        }

        try(FileWriter out = new FileWriter(this.employeePath, false)) {
            /*
            String salaryFormat = String.format("%-" + this.employeePadding + "s", "Salary");
            String numberEmployeesFormat = String.format("%-" + this.employeePadding + "s", "Number of Employees" + "\n");
            out.write(salaryFormat + " | " + numberEmployeesFormat + "\n\n\n");

            for (double salary : salaryFrequency.keySet()) {
                int frequency = salaryFrequency.get(salary);

                salaryFormat = String.format("%-" + this.employeePadding + "s", salary);
                numberEmployeesFormat = String.format("%-" + this.employeePadding + "s", frequency);
                out.write(salaryFormat + " | " + numberEmployeesFormat + "\n");
            }
             */
            out.write("Salary,Number of Employees\n");

            for (double salary : salaryFrequency.keySet()) {
                int frequency = salaryFrequency.get(salary);

                out.write(salary + "," + frequency + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String salaryFormat = String.format("%-" + this.employeePadding + "s", "Salary");
        String numberEmployeesFormat = String.format("%-" + this.employeePadding + "s", "Number of Employees" + "\n");
        System.out.print(salaryFormat + " | " + numberEmployeesFormat + "\n\n\n");

        for (double salary : salaryFrequency.keySet()) {
            int frequency = salaryFrequency.get(salary);

            salaryFormat = String.format("%-" + this.employeePadding + "s", salary);
            numberEmployeesFormat = String.format("%-" + this.employeePadding + "s", frequency);
            System.out.print(salaryFormat + " | " + numberEmployeesFormat + "\n");
        }

        this.writeLog("Complex Query 2");
    }
}




