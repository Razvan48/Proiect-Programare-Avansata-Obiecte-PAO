package Buildings;

import People.Employee;

import java.util.Map;
import java.util.TreeMap;

public class Restaurant extends Building {
    private String restaurantName;
    private int numStars;
    private Map<Integer, Employee> employees;

    public Restaurant(int constructionYear, String restaurantName, int numStars) {
        super(constructionYear);

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
        StringBuilder result = new StringBuilder("Restaurant ( ID=" + this.buildingID
                + " CONSTR_YEAR=" + this.constructionYear
                + " RESTAURANT_NAME=" + this.restaurantName + " NUM_STARS="+ this.numStars + " )\n");

        result.append("Employees (\n");
        for (Employee employee : this.employees.values()) {
            result.append("\t").append(employee.toString()).append("\n");
        }

        result.append(")");

        return result.toString();
    }

    public void addEmployee(Employee employee) { this.employees.put(employee.getPersonID(), employee.clone()); };

    public String displayEmployee(int employeeID) {
        if (this.employees.containsKey(employeeID)) {
            return this.employees.get(employeeID).toString();
        }
        else {
            return "ERROR :: The given employee ID does not exist!\n";
        }
    }

    @Override
    public Restaurant clone() { return new Restaurant(this); }
}
