package Services;

public final class ConnectionDetails {
    private final String DATABASE_URL;
    private final String DATABASE_USER;
    private final String DATABASE_PASSWORD;

    public ConnectionDetails(String DATABASE_URL, String DATABASE_USER, String DATABASE_PASSWORD) {
        this.DATABASE_URL = DATABASE_URL;
        this.DATABASE_USER = DATABASE_USER;
        this.DATABASE_PASSWORD = DATABASE_PASSWORD;
    }

    String getDATABASE_URL() { return this.DATABASE_URL; }
    String getDATABASE_USER() { return this.DATABASE_USER; }
    String getDATABASE_PASSWORD() { return this.DATABASE_PASSWORD; }
}
