package Services;

import java.sql.*;

public class Setup {
    private final ConnectionDetails connectionDetails;
    private Connection connection = null;

    private static Setup INSTANCE = null;
    private Setup(String DATABASE_URL, String DATABASE_USER, String DATABASE_PASSWORD) {
        this.connectionDetails = new ConnectionDetails(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        loadDriver();
        createConnection();
    }

    public static Setup get() {
        if (Setup.INSTANCE == null) {
            Setup.INSTANCE = new Setup("jdbc:mysql://localhost:3306/GestiuneLantHoteluri",
                    "root", "root");
        }
        return Setup.INSTANCE;
    }

    private void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception e) {
            System.out.println("ERROR :: Setup loadDriver :: could not load driver\n");
            e.printStackTrace();
        }
    }

    private void createConnection() {
        try {
            this.connection = DriverManager.getConnection(this.connectionDetails.getDATABASE_URL(),
                    this.connectionDetails.getDATABASE_USER(),
                    this.connectionDetails.getDATABASE_PASSWORD());
        }
        catch (Exception e) {
            System.out.println("ERROR :: Setup createConnection :: could not create connection\n");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}
