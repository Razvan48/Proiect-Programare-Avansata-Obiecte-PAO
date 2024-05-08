package People;

public class Client extends Person {

    public Client(String firstName, String middleName, String lastName, String emailAddress) {
        super(firstName, middleName, lastName, emailAddress);
    }

    public Client(Client client) {
        super(client);
    }

    @Override
    public String toString() {
        return "Client ( ID=" + super.personID + " FULL_NAME="
                + super.firstName + " " + super.middleName + " " + super.lastName
                + " EMAIL=" + super.emailAddress + " )";
    }

    @Override
    public Client clone() { return new Client(this); }
}
