package People;

public class Person {
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
}
