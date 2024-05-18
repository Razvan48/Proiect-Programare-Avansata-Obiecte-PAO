package People;

import Crud.CRUD;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person implements CRUD<Person> {
    protected final int personID;
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
    public int create(Person person) throws SQLException {
        final String create = "INSERT INTO person(firstName, middleName, lastName, emailAddress) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getMiddleName());
        preparedStatement.setString(3, person.getLastName());
        preparedStatement.setString(4, person.getEmailAddress());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Person read(Person person) throws SQLException {
        final String read = "SELECT * FROM person WHERE personID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, person.getPersonID());

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
    public int update(Person person) throws SQLException {
        final String update = "UPDATE person SET firstName = ?, middleName = ?, lastName = ?, emailAddress = ? WHERE personID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setString(1, person.getFirstName());
        preparedStatement.setString(2, person.getMiddleName());
        preparedStatement.setString(3, person.getLastName());
        preparedStatement.setString(4, person.getEmailAddress());
        preparedStatement.setInt(5, person.getPersonID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Person person) throws SQLException {
        final String delete = "DELETE FROM person WHERE personID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, person.getPersonID());

        return preparedStatement.executeUpdate();
    }
}
