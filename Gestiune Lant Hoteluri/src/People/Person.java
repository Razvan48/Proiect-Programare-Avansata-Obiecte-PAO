package People;

import Crud.CRUD;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Person implements CRUD<Person> {
    protected int personID;
    protected String firstName;
    protected String middleName;
    protected String lastName;
    protected String emailAddress;

    public Person(int personID, String firstName, String middleName, String lastName, String emailAddress) {
        this.personID = personID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public Person(Person person) {
        this.personID = person.personID;
        this.firstName = person.firstName;
        this.middleName = person.middleName;
        this.lastName = person.lastName;
        this.emailAddress = person.emailAddress;
    }

    public int getPersonID() { return this.personID; }
    public String getFirstName() { return this.firstName; }
    public String getMiddleName() { return this.middleName; }
    public String getLastName() { return this.lastName; }
    public String getEmailAddress() { return this.emailAddress; }

    @Override
    public int hashCode() { return this.personID; }

    @Override
    public String toString() {
        return "Person ( ID=" + this.personID + " FULL_NAME="
                + this.firstName + " " + this.middleName + " " + this.lastName
                + " EMAIL=" + this.emailAddress + " )\n";
    }

    public Person clone() { return new Person(this); };

    @Override
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("firstName=");
        this.firstName = scanner.nextLine();
        System.out.println("\n");
        System.out.println("middleName=");
        this.middleName = scanner.nextLine();
        System.out.println("\n");
        System.out.println("lastName=");
        this.lastName = scanner.nextLine();
        System.out.println("\n");
        System.out.println("emailAddress=");
        this.emailAddress = scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("personID=");
        this.personID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("personID=");
        this.personID = scanner.nextInt();
        System.out.println("\n");
        System.out.println("firstName=");
        this.firstName = scanner.nextLine();
        System.out.println("\n");
        System.out.println("middleName=");
        this.middleName = scanner.nextLine();
        System.out.println("\n");
        System.out.println("lastName=");
        this.lastName = scanner.nextLine();
        System.out.println("\n");
        System.out.println("emailAddress=");
        this.emailAddress = scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("personID=");
        this.personID = scanner.nextInt();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO person(firstName, middleName, lastName, emailAddress) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setString(1, this.getFirstName());
        preparedStatement.setString(2, this.getMiddleName());
        preparedStatement.setString(3, this.getLastName());
        preparedStatement.setString(4, this.getEmailAddress());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Person read() throws SQLException {
        final String read = "SELECT * FROM person WHERE personID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getPersonID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Person(rs.getInt("personID"), rs.getString("firstName"),
                    rs.getString("middleName"), rs.getString("lastName"),
                    rs.getString("emailAddress"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update() throws SQLException {
        final String update = "UPDATE person SET firstName = ?, middleName = ?, lastName = ?, emailAddress = ? WHERE personID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setString(1, this.getFirstName());
        preparedStatement.setString(2, this.getMiddleName());
        preparedStatement.setString(3, this.getLastName());
        preparedStatement.setString(4, this.getEmailAddress());
        preparedStatement.setInt(5, this.getPersonID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM person WHERE personID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getPersonID());

        return preparedStatement.executeUpdate();
    }

    public static List<Person> readAllPeople() throws SQLException {
        final String readAll = "SELECT * FROM person";

        List<Person> personList = new ArrayList<Person>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            personList.add(new Person(rs.getInt("personID"), rs.getString("firstName"),
                    rs.getString("middleName"), rs.getString("lastName"),
                    rs.getString("emailAddress")));
        }

        return personList;
    }
}
