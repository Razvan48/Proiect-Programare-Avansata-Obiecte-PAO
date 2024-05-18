package Services;

import java.sql.*;

public class Setup {

    private final String DATABASE_URL = "jdbc:mysql://localhost:3306/GestiuneLantHoteluri";
    private final String DATABASE_USER = "root";
    private final String DATABASE_PASSWORD = "root";
    private Connection connection = null;

    private static Setup INSTANCE = null;
    private Setup() {
        loadDriver();
        createConnection();
    }

    public static Setup get() {
        if (Setup.INSTANCE == null) {
            Setup.INSTANCE = new Setup();
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
            this.connection = DriverManager.getConnection(this.DATABASE_URL, this.DATABASE_USER, this.DATABASE_PASSWORD);
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
