package People;

import Services.Database;

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
}
