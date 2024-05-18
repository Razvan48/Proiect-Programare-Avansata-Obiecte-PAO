package People;

import Services.DatabaseGetter;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Employee extends Person {
    private int employeeID;
    private double monthlySalary;
    private int age;
    private String occupation;

    public Employee(int employeeID, double monthlySalary, int age, String occupation) throws SQLException {
        this.employeeID = employeeID;
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
        return "Employee ( ID=" + this.employeeID + " MON_SALARY=" + this.monthlySalary
                + " AGE=" + this.age + " OCC=" + this.occupation + " )\n";
    }

    @Override
    public Employee clone() { return new Employee(this); }

    @Override
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("employeeID=");
        this.employeeID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("monthlySalary=");
        this.monthlySalary = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("age=");
        this.age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("occupation=");
        this.occupation = scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("employeeID=");
        this.employeeID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("employeeID=");
        this.employeeID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("monthlySalary=");
        this.monthlySalary = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("age=");
        this.age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
        System.out.println("occupation=");
        this.occupation = scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("employeeID=");
        this.employeeID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO employee(employeeID, monthlySalary, age, occupation) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.getEmployeeID());
        preparedStatement.setDouble(2, this.getMonthlySalary());
        preparedStatement.setInt(3, this.getAge());
        preparedStatement.setString(4, this.getOccupation());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Employee read() throws SQLException {
        final String read = "SELECT * FROM employee WHERE employeeID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getEmployeeID());

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
    public int update() throws SQLException {
        final String update = "UPDATE employee SET employeeID = ?, monthlySalary = ?, age = ?, occupation = ? WHERE employeeID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, this.getEmployeeID());
        preparedStatement.setDouble(2, this.getMonthlySalary());
        preparedStatement.setInt(3, this.getAge());
        preparedStatement.setString(4, this.getOccupation());
        preparedStatement.setInt(5, this.getEmployeeID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM employee WHERE employeeID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getEmployeeID());

        return preparedStatement.executeUpdate();
    }

    public static List<Employee> readAllEmployees() throws SQLException {
        final String readAll = "SELECT * FROM employee";

        List<Employee> employeeList = new ArrayList<Employee>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            employeeList.add(new Employee(rs.getInt("employeeID"), rs.getDouble("monthlySalary"),
                    rs.getInt("age"), rs.getString("occupation")));
        }

        return employeeList;
    }
}
