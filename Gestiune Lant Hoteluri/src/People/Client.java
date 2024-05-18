package People;

import Locations.Location;
import Services.DatabaseGetter;
import Services.Setup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client extends Person {

    private int clientID;

    public Client(int clientID) throws SQLException {
        this.clientID = clientID;
    }

    public Client(Client client) {
        super(client);

        this.clientID = client.clientID;
    }

    @Override
    public String toString() {
        return "Client ( ID=" + this.clientID + " )\n";
    }

    public int getClientID() { return this.clientID; }

    @Override
    public Client clone() { return new Client(this); }

    @Override
    public void inputForCreate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("clientID=");
        this.clientID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForRead() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("clientID=");
        this.clientID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForUpdate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("clientID=");
        this.clientID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public void inputForDelete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("clientID=");
        this.clientID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("\n");
    }

    @Override
    public int create() throws SQLException {
        final String create = "INSERT INTO client(clientID) VALUES (?)";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(create);
        preparedStatement.setInt(1, this.getClientID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public Client read() throws SQLException {
        final String read = "SELECT * FROM client WHERE clientID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(read);
        preparedStatement.setInt(1, this.getClientID());

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
    public int update() throws SQLException {
        final String update = "UPDATE client SET clientID = ? WHERE clientID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(update);
        preparedStatement.setInt(1, this.getClientID());
        preparedStatement.setInt(2, this.getClientID());

        return preparedStatement.executeUpdate();
    }

    @Override
    public int delete() throws SQLException {
        final String delete = "DELETE FROM client WHERE clientID = ?";

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(delete);
        preparedStatement.setInt(1, this.getClientID());

        return preparedStatement.executeUpdate();
    }

    public static List<Client> readAllClients() throws SQLException {
        final String readAll = "SELECT * FROM client";

        List<Client> clientList = new ArrayList<Client>();

        PreparedStatement preparedStatement = Setup.get().getConnection().prepareStatement(readAll);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            clientList.add(new Client(rs.getInt("clientID")));
        }

        return clientList;
    }
}
