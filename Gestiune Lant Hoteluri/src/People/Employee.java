package People;

import Services.Database;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Employee extends Person {
    private int employeeID;
    private double monthlySalary;
    private int age;
    private String occupation;

    public Employee(int employeeID, double monthlySalary, int age, String occupation) {
        super(Database.get().getPerson(employeeID));
        this.monthlySalary = monthlySalary;
        this.age = age;
        this.occupation = occupation;
    }

    public Employee(Employee employee) {
        super(employee);
        this.monthlySalary = employee.monthlySalary;
        this.age = employee.age;
        this.occupation = employee.occupation;
    }

    public int getEmployeeID() { return this.employeeID; }
    public double getMonthlySalary() { return this.monthlySalary; }
    public int getAge() { return this.age; }
    public String getOccupation() { return this.occupation; }

    @Override
    public String toString() {
        return "Employee ( ID=" + this.employeeID + " FULL_NAME="
                + super.firstName + " " + super.middleName + " " + super.lastName
                + " EMAIL=" + super.emailAddress + " MON_SALARY=" + this.monthlySalary
                + " AGE=" + this.age + " OCC=" + this.occupation + " )\n";
    }

    @Override
    public Employee clone() { return new Employee(this); }

    @Override
    public int create(Person person) throws SQLException {
        Employee employee = (Employee)person;
        final String create = "INSERT INTO employee(employeeID, monthlySalary, age, occupation) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, employee.getEmployeeID());
        preparedStatement.setDouble(2, employee.getMonthlySalary());
        preparedStatement.setInt(3, employee.getAge());
        preparedStatement.setString(4, employee.getOccupation());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Employee read(Person person) throws SQLException {
        Employee employee = (Employee)person;
        final String read = "SELECT * FROM employee WHERE employeeID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, employee.getEmployeeID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Employee(rs.getInt("employeeID"), rs.getDouble("monthlySalary"),
                    rs.getInt("age"), rs.getString("occupation"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update(Person person) throws SQLException {
        Employee employee = (Employee)person;
        final String update = "UPDATE employee SET employeeID = ?, monthlySalary = ?, age = ?, occupation = ? WHERE employeeID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, employee.getEmployeeID());
        preparedStatement.setDouble(2, employee.getMonthlySalary());
        preparedStatement.setInt(3, employee.getAge());
        preparedStatement.setString(4, employee.getOccupation());
        preparedStatement.setInt(5, employee.getEmployeeID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Person person) throws SQLException {
        Employee employee = (Employee)person;
        final String delete = "DELETE FROM employee WHERE employeeID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, employee.getEmployeeID());

        return preparedStatement.executeUpdate();
    }
}
