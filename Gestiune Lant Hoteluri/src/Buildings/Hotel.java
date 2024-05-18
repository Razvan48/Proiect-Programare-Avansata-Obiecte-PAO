package Buildings;

import Services.DatabaseGetter;
import People.Employee;
import Rooms.Room;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Hotel extends Building {

    private int hotelID;
    private String hotelName;
    private int numStars;

    public Hotel(int hotelID, String hotelName, int numStars) throws SQLException {
        super(DatabaseGetter.get().getBuilding(hotelID));
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.numStars = numStars;
    }

    public Hotel(Hotel hotel) {
        super(hotel);
        this.hotelID = hotel.hotelID;
        this.hotelName = hotel.hotelName;
        this.numStars = hotel.numStars;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Hotel ( ID=" + this.hotelID
                + " CONSTR_YEAR=" + super.constructionYear + " LOCATION_ID=" + super.locationID
                + " HOTEL_NAME=" + this.hotelName + " NUM_STARS="+ this.numStars + " )\n");

        return result.toString();
    }

    public int getHotelID() { return this.hotelID; }
    public String getHotelName() { return this.hotelName; }
    public int getNumStars() { return this.numStars; }

    @Override
    public Hotel clone() { return new Hotel(this); }

    @Override
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hotelID=");
        this.hotelID = scanner.nextInt();
        System.out.println("\n");
        System.out.println("hotelName=");
        this.hotelName = scanner.nextLine();
        System.out.println("\n");
        System.out.println("numStars=");
        this.numStars = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hotelID=");
        this.hotelID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hotelID=");
        this.hotelID = scanner.nextInt();
        System.out.println("\n");
        System.out.println("hotelName=");
        this.hotelName = scanner.nextLine();
        System.out.println("\n");
        System.out.println("numStars=");
        this.numStars = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("hotelID=");
        this.hotelID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO hotel(hotelID, hotelName, numStars) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.getHotelID());
        preparedStatement.setString(2, this.getHotelName());
        preparedStatement.setInt(3, this.getNumStars());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Hotel read() throws SQLException {
        final String read = "SELECT * FROM hotel WHERE hotelID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getHotelID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Hotel(rs.getInt("hotelID"), rs.getString("hotelName"),
                    rs.getInt("numStars"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update() throws SQLException {
        final String update = "UPDATE hotel SET hotelID = ?, hotelName = ?, numStars = ? WHERE hotelID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, this.getHotelID());
        preparedStatement.setString(2, this.getHotelName());
        preparedStatement.setInt(3, this.getNumStars());
        preparedStatement.setInt(4, this.getHotelID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM hotel WHERE hotelID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getHotelID());

        return preparedStatement.executeUpdate();
    }

    public static List<Hotel> readAllHotels() throws SQLException {
        final String readAll = "SELECT * FROM hotel";

        List<Hotel> hotelList = new ArrayList<Hotel>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            hotelList.add(new Hotel(rs.getInt("hotelID"), rs.getString("hotelName"),
                    rs.getInt("numStars")));
        }

        return hotelList;
    }
}
