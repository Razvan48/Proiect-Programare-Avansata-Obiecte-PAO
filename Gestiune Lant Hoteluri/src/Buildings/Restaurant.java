package Buildings;

import Facilities.Facility;
import Services.Database;
import People.Employee;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

public class Restaurant extends Building {

    private int restaurantID;
    private String restaurantName;
    private int numStars;
    private Map<Integer, Employee> employees;

    public Restaurant(int restaurantID, String restaurantName, int numStars) {
        super(Database.get().getBuilding(restaurantID));
        this.restaurantName = restaurantName;
        this.numStars = numStars;

        this.employees = new TreeMap<Integer, Employee>();
    }

    public Restaurant(Restaurant restaurant) {
        super(restaurant);

        this.restaurantName = restaurant.restaurantName;
        this.numStars = restaurant.numStars;

        this.employees = new TreeMap<Integer, Employee>(restaurant.employees);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Restaurant ( ID=" + this.restaurantID
                + " CONSTR_YEAR=" + this.constructionYear + " LOCATION_ID=" + this.locationID
                + " RESTAURANT_NAME=" + this.restaurantName + " NUM_STARS="+ this.numStars + " )\n");

        result.append("Employees (\n");
        for (Employee employee : this.employees.values()) {
            result.append("\t").append(employee.toString());
        }

        result.append(")\n");

        return result.toString();
    }

    public void addEmployee(Employee employee) { this.employees.put(employee.getEmployeeID(), employee); };

    public String displayEmployee(int employeeID) {
        if (this.employees.containsKey(employeeID)) {
            return this.employees.get(employeeID).toString();
        }
        else {
            return "ERROR :: Restaurant displayEmployee :: employee does not exist\n";
        }
    }

    @Override
    public Restaurant clone() { return new Restaurant(this); }

    public int getRestaurantID() { return this.restaurantID; }
    public String getRestaurantName() { return this.restaurantName; }
    public int getNumStars() { return this.numStars; }

    @Override
    public int create(Building building) throws SQLException {
        Restaurant restaurant = (Restaurant) building;
        final String create = "INSERT INTO restaurant(restaurantID, restaurantName, numStars) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, restaurant.getRestaurantID());
        preparedStatement.setString(2, restaurant.getRestaurantName());
        preparedStatement.setInt(3, restaurant.getNumStars());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Restaurant read(Building building) throws SQLException {
        Restaurant restaurant = (Restaurant) building;
        final String read = "SELECT * FROM restaurant WHERE restaurantID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, restaurant.getRestaurantID());

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
    public int update(Building building) throws SQLException {
        Restaurant restaurant = (Restaurant) building;
        final String update = "UPDATE restaurant SET restaurantID = ?, restaurantName = ?, numStars = ? WHERE restaurantID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, restaurant.getRestaurantID());
        preparedStatement.setString(2, restaurant.getRestaurantName());
        preparedStatement.setInt(3, restaurant.getNumStars());
        preparedStatement.setInt(4, restaurant.getRestaurantID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Building building) throws SQLException {
        Restaurant restaurant = (Restaurant) building;
        final String delete = "DELETE FROM restaurant WHERE restaurantID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, restaurant.getRestaurantID());

        return preparedStatement.executeUpdate();
    }
}
