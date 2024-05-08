package People;

enum Occupation {
    ADMINISTRATOR,
    RECEPTIONIST;

    @Override
    public String toString() {
        return switch (this) {
            case ADMINISTRATOR -> "administrator";
            case RECEPTIONIST -> "receptionist";
            default -> "other";
        };
    }
}

public class Employee extends Person {
    private int monthlySalary;
    private Occupation occupation;
    private int age;

    public Employee(String firstName, String middleName, String lastName, String emailAddress, int monthlySalary, Occupation occupation, int age) {
        super(firstName, middleName, lastName, emailAddress);
        this.monthlySalary = monthlySalary;
        this.occupation = occupation;
        this.age = age;
    }

    public Employee(Employee employee) {
        super(employee);
        this.monthlySalary = employee.monthlySalary;
        this.occupation = employee.occupation;
        this.age = employee.age;
    }

    public int getMonthlySalary() { return this.monthlySalary; }
    public Occupation getOccupation() { return this.occupation; }

    @Override
    public String toString() {
        return "Employee ( ID=" + super.personID + " FULL_NAME="
                + super.firstName + " " + super.middleName + " " + super.lastName
                + " EMAIL=" + super.emailAddress + " MON_SALARY=" + this.monthlySalary
                + " OCC=" + this.occupation.toString() + " AGE=" + this.age + " )";
    }

    @Override
    public Employee clone() { return new Employee(this); }
}
