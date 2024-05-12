package People;

import Services.Database;

public class Client extends Person {

    private int clientID;

    public Client(int clientID) {
        super(Database.get().getPerson(clientID));

        this.clientID = clientID;
    }

    public Client(Client client) {
        super(client);

        this.clientID = client.clientID;
    }

    @Override
    public String toString() {
        return "Client ( ID=" + this.clientID + " FULL_NAME="
                + super.firstName + " " + super.middleName + " " + super.lastName
                + " EMAIL=" + super.emailAddress + " )\n";
    }

    @Override
    public Client clone() { return new Client(this); }
}
