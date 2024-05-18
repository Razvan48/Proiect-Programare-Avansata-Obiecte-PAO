package People;

import Services.Database;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public int getClientID() { return this.clientID; }

    @Override
    public Client clone() { return new Client(this); }

    @Override
    public int create(Person person) throws SQLException {
        Client client = (Client) person;
        final String create = "INSERT INTO client(clientID) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, client.getClientID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Client read(Person person) throws SQLException {
        Client client = (Client)person;
        final String read = "SELECT * FROM client WHERE clientID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, client.getClientID());

        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            return new Client(rs.getInt("clientID"));
        }
        else
        {
            return null;
        }
    }

    @Override
    public int update(Person person) throws SQLException {
        Client client = (Client) person;
        final String update = "UPDATE client SET clientID = ? WHERE clientID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, client.getClientID());
        preparedStatement.setInt(2, client.getClientID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete(Person person) throws SQLException {
        Client client = (Client) person;
        final String delete = "DELETE FROM client WHERE clientID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, client.getClientID());

        return preparedStatement.executeUpdate();
    }
}
