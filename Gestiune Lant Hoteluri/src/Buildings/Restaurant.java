package Buildings;

import Facilities.Facility;
import Services.DatabaseGetter;
import People.Employee;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Restaurant extends Building {

    private int restaurantID;
    private String restaurantName;
    private int numStars;

    public Restaurant(int restaurantID, String restaurantName, int numStars) throws SQLException {
        super(DatabaseGetter.get().getBuilding(restaurantID));
        this.restaurantID = restaurantID;
        this.restaurantName = restaurantName;
        this.numStars = numStars;
    }

    public Restaurant(Restaurant restaurant) {
        super(restaurant);
        this.restaurantID = restaurant.restaurantID;
        this.restaurantName = restaurant.restaurantName;
        this.numStars = restaurant.numStars;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Restaurant ( ID=" + this.restaurantID
                + " CONSTR_YEAR=" + this.constructionYear + " LOCATION_ID=" + this.locationID
                + " RESTAURANT_NAME=" + this.restaurantName + " NUM_STARS="+ this.numStars + " )\n");

        return result.toString();
    }

    @Override
    public Restaurant clone() { return new Restaurant(this); }

    public int getRestaurantID() { return this.restaurantID; }
    public String getRestaurantName() { return this.restaurantName; }
    public int getNumStars() { return this.numStars; }

    @Override
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("restaurantID=");
        this.restaurantID = scanner.nextInt();
        System.out.println("\n");
        System.out.println("restaurantName=");
        this.restaurantName = scanner.nextLine();
        System.out.println("\n");
        System.out.println("numStars=");
        this.numStars = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("restaurantID=");
        this.restaurantID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("restaurantID=");
        this.restaurantID = scanner.nextInt();
        System.out.println("\n");
        System.out.println("restaurantName=");
        this.restaurantName = scanner.nextLine();
        System.out.println("\n");
        System.out.println("numStars=");
        this.numStars = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("restaurantID=");
        this.restaurantID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO restaurant(restaurantID, restaurantName, numStars) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.getRestaurantID());
        preparedStatement.setString(2, this.getRestaurantName());
        preparedStatement.setInt(3, this.getNumStars());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Restaurant read() throws SQLException {
        final String read = "SELECT * FROM restaurant WHERE restaurantID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getRestaurantID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Restaurant(rs.getInt("restaurantID"), rs.getString("restaurantName"),
                    rs.getInt("numStars"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update() throws SQLException {
        final String update = "UPDATE restaurant SET restaurantID = ?, restaurantName = ?, numStars = ? WHERE restaurantID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, this.getRestaurantID());
        preparedStatement.setString(2, this.getRestaurantName());
        preparedStatement.setInt(3, this.getNumStars());
        preparedStatement.setInt(4, this.getRestaurantID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM restaurant WHERE restaurantID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getRestaurantID());

        return preparedStatement.executeUpdate();
    }

    public static List<Restaurant> readAllRestaurants() throws SQLException {
        final String readAll = "SELECT * FROM restaurant";

        List<Restaurant> restaurantList = new ArrayList<Restaurant>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            restaurantList.add(new Restaurant(rs.getInt("restaurantID"), rs.getString("restaurantName"),
                    rs.getInt("numStars")));
        }

        return restaurantList;
    }
}
